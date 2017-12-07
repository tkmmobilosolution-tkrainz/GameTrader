package gametrade.ld.tkm.at.gametrade

import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Handles user authentication.
 *
 * Created 28.11.17
 *
 * @author Thomas Krainz-Mischitz
 * @version %I%, %G%
 */

class AuthenticationActivity : BaseActivity() {

    companion object {
        private val KEY = AuthenticationActivity::class.java.canonicalName
        private val FRAGMENT_LOGIN_TAG = KEY + ".login"
        private val FRAGMENT_REGISTER_TAG = KEY + ".register"
        private val FRAGMENT_REGISTER_USER_DETAIL_KEY = KEY + ".detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        showLoginFragment()
    }

    /**
     * Shows the LoginFragment and handles subjects of LoginFragment.
     */
    private fun showLoginFragment() {
        val loginFragment = LoginFragment()
        loginFragment.registrationClicked.firstElement().subscribeOn(AndroidSchedulers.mainThread()).subscribe {
            showRegistrationFragment()
        }

        loginFragment.loginUser.firstElement().subscribeOn(AndroidSchedulers.mainThread()).subscribe {
            Toast.makeText(this, "User login success", Toast.LENGTH_SHORT).show()
        }

        pushFragment(R.id.containerView, loginFragment, FRAGMENT_LOGIN_TAG)
    }

    /**
     * Shows the RegisterFragment and handles subjects of RegisterFragment.
     */
    private fun showRegistrationFragment() {
        val registerFragment = RegisterFragment()
        registerFragment.registerUser.firstElement().subscribeOn(AndroidSchedulers.mainThread()).subscribe {
            Toast.makeText(this, "User register success", Toast.LENGTH_SHORT).show()
        }

        pushFragment(R.id.containerView, registerFragment, FRAGMENT_REGISTER_TAG)
    }
}
