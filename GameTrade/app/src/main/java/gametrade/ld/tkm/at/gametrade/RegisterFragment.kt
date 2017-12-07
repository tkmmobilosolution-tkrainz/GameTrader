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
import kotlinx.android.synthetic.main.fragment_register.registerEmail
import kotlinx.android.synthetic.main.fragment_register.registerPassword
import kotlinx.android.synthetic.main.fragment_register.registerPasswordCompare
import kotlinx.android.synthetic.main.fragment_register.registrationButton

/**
 * Handles user registration.
 *
 * Created 07.12.17
 *
 * @author Thomas Krainz-Mischitz
 * @version %I%, %G%
 */
class RegisterFragment : Fragment() {

    /**
     * The FirebaseAuth instance.
     */
    val authentication = FirebaseAuth.getInstance()

    /**
     * The subject for notifying the caller about successful registration.
     */
    val registerUser = PublishSubject.create<Any>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RxView.clicks(registrationButton).subscribe {
            registerUser()
        }
    }

    /**
     * Checks email and password validation.
     */
    private fun registerUser() {
        val emailAddress = registerEmail.text.toString()
        val password = registerPassword.text.toString()
        val comparePassword = registerPasswordCompare.text.toString()

        if (emailAddress.isEmpty() || password.isEmpty() || comparePassword.isEmpty()) {
            Toast.makeText(activity, "Insert email and pasword", Toast.LENGTH_SHORT).show()
            return
        }

        if (!emailAddress.isValidEmail()) {
            Toast.makeText(activity, "Invalid email", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 8) {
            Toast.makeText(activity, "Password must have min 8 chars", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != comparePassword) {
            Toast.makeText(activity, "Passwords must be equal", Toast.LENGTH_SHORT).show()
            return
        }

        registerFirebaseUser(emailAddress, password)
    }

    /**
     * Registers new user with given email and password.
     *
     * @param email: The given email for registration.
     * @param password: The given email for registration.
     */
    private fun registerFirebaseUser(email: String, password: String) {
        authentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, { task ->
            if (!task.isSuccessful) {
                registrationInFailed(task)
            } else {
                registerUser.onNext("")
            }
        })
    }

    /**
     * Handling registration failure with given task.
     *
     * @param task: The given task for getting error code.
     */
    private fun registrationInFailed(task: Task<AuthResult>) {
        val exception = task.exception as FirebaseAuthException?

        if (exception!!.errorCode == "ERROR_EMAIL_ALREADY_IN_USE") {
            Toast.makeText(activity, "This email already exsists", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Default error", Toast.LENGTH_SHORT).show()
        }
    }
}