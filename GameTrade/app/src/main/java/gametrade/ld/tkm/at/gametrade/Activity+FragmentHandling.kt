package gametrade.ld.tkm.at.gametrade

import android.app.Activity
import android.app.Fragment

/**
 * [Add class description here]
 *
 * Created 29.11.17
 *
 * @author Thomas Krainz-Mischitz (Level1 GmbH)
 * @version %I%, %G%
 */

fun Activity.showFragment(id: Int, fragment: Fragment, tag: String? = null) {
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(id, fragment, tag)
    transaction.commit()
}

fun Activity.pushFragment(containerId: Int, fragment: Fragment, tag: String? = null) {
    val fragmentTransaction = fragmentManager.beginTransaction()

    fragmentTransaction.replace(containerId, fragment, tag)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()

    fragmentManager.executePendingTransactions()
}

fun Activity.popFragment() {
    fragmentManager.popBackStack()
}