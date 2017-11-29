package gametrade.ld.tkm.at.gametrade

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class StartupActivity : BaseActivity() {

    private val isUserLoggedIn: Boolean
        get() = FirebaseAuth.getInstance().currentUser != null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        val mHandler = Handler()
        mHandler.postDelayed({
            var intent: Intent? = null

            if (isUserLoggedIn) {
                // show overview / main view
            } else {
                // show login flow
                intent = Intent(this@StartupActivity, AuthenticationActivity::class.java)
            }

            startActivityWithIntent(intent)
        }, 2000L)

    }

    /**
     * Starts new Activity with given intent. Also calling finish().
     * @param intent The given intent to start.
     */
    private fun startActivityWithIntent(intent: Intent?) {
        startActivity(intent)
        this.finish()
    }
}
