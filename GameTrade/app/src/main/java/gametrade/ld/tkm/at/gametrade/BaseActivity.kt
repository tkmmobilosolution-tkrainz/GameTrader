package gametrade.ld.tkm.at.gametrade

import android.support.v7.app.AppCompatActivity

/**
 * The BaseActivity.
 *
 * Created 29.11.17
 *
 * @author Thomas Krainz-Mischitz
 * @version %I%, %G%
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}