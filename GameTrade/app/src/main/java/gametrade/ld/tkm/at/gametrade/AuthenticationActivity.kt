package gametrade.ld.tkm.at.gametrade

import android.os.Bundle

/**
 * [Add class description here]
 *
 * Created 28.11.17
 *
 * @author Thomas Krainz-Mischitz (Level1 GmbH)
 * @version %I%, %G%
 */

class AuthenticationActivity : BaseActivity() {

    companion object {
        private val KEY = AuthenticationActivity::class.java.canonicalName
        private val FRAGMENT_LOGIN_TAG = KEY + ".login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        pushFragment(R.id.containerView, LoginFragment(), FRAGMENT_LOGIN_TAG)
    }
}
