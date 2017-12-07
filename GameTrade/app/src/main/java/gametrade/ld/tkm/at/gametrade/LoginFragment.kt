package gametrade.ld.tkm.at.gametrade

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.SingleSubject
import kotlinx.android.synthetic.main.fragment_login.email
import kotlinx.android.synthetic.main.fragment_login.loginButton
import kotlinx.android.synthetic.main.fragment_login.password
import kotlinx.android.synthetic.main.fragment_login.registerButton

/**
 * Handles user login.
 *
 * Created 29.11.17
 *
 * @author Thomas Krainz-Mischitz
 * @version %I%, %G%
 */

class LoginFragment : Fragment() {

    /**
     * The FirebaseAuth instance.
     */
    val authentication = FirebaseAuth.getInstance()

    /**
     * The subject for notifying the caller about registrationButton click.
     */
    val registrationClicked = PublishSubject.create<Any>()

    /**
     * The subject for notifying the caller about successful sign in.
     */
    val loginUser = PublishSubject.create<Any>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RxView.clicks(loginButton).subscribe {
            loginUser()
        }

        RxView.clicks(registerButton).subscribe {
            registrationClicked.onNext(it)
        }
    }

    /**
     * Logging in user with given email and password.
     *
     * @param email: The given email for login.
     * @param password: The given email to login.
     */
    private fun loginFirebaseUser(email: String, password: String) {
        authentication.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, { task ->
            if (!task.isSuccessful) {
                signInFailed(task)
            } else {
                loginUser.onNext("")
            }
        })
    }

    /**
     * Checks email validation an checks field for character length.
     */
    private fun loginUser() {
        val emailAddress = email.text.toString()
        val password = password.text.toString()

        if (emailAddress.isEmpty() || password.isEmpty()) {
            Toast.makeText(activity, "Insert email and pasword", Toast.LENGTH_SHORT).show()
            return
        }

        if (!emailAddress.isValidEmail()) {
            Toast.makeText(activity, "Invalid email", Toast.LENGTH_SHORT).show()
            return
        }

        loginFirebaseUser(emailAddress, password)
    }

    /**
     * Handling sign in failure with given task.
     *
     * @param task: The given task for getting error code.
     */
    private fun signInFailed(task: Task<AuthResult>) {
        val exception = task.exception as FirebaseAuthException?

        if (exception!!.errorCode == "ERROR_USER_NOT_FOUND") {
            Toast.makeText(activity, "No user found", Toast.LENGTH_SHORT).show()

        } else if (exception.errorCode == "ERROR_WRONG_PASSWORD") {
            Toast.makeText(activity, "Wrong pasword", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Default error", Toast.LENGTH_SHORT).show()
        }
    }
}
