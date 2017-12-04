package gametrade.ld.tkm.at.gametrade

/**
 * [Add class description here]
 *
 * Created 04.12.17
 *
 * @author Thomas Krainz-Mischitz (Level1 GmbH)
 * @version %I%, %G%
 */

fun String.isValidEmail() : Boolean {
    val emailPattern = "[A-Z0-9a-z._%+-]+@([A-Za-z0-]+\\.)+[A-Za-z]{2,}"
    return this.matches(emailPattern.toRegex())
}