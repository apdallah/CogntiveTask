package com.apdallahy3.basearch.utils

import android.widget.EditText
import java.util.*

class ValidationException(message: String) : RuntimeException(message)
object ValidationUtils {
    fun isValidEmail(email: String): String {
        val regex =
            "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])".toRegex()
        if (!regex.matches(email))
            throw ValidationException("Accept mail format only");
        return email.trim()
    }

    fun isValidEmail(email: EditText): String {
        val regex =
            "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])".toRegex()
        if (!regex.matches(email.text.toString())) {
            email.requestFocus()
            throw ValidationException("Accept mail format only")
        }
        return email.text.toString().trim()
    }

    fun isValidPhone(phone: String, mesg: String): String {
        val regex = "^[+]?[0-9]{1,13}\$".toRegex()
        if (!regex.matches(phone))
            throw ValidationException(mesg);
        return phone.trim()
    }

    fun isValidPhone(phone: EditText, mesg: String): String {
        val regex = "^[+]?[0-9]{1,13}\$".toRegex()
        if (!regex.matches(phone.text.toString())) {
            phone.requestFocus()
            throw ValidationException(mesg)
        }
        return phone.text.toString().trim()
    }

    fun isValidPassword(password: String, confirmPassword: String): String {
        if (password.length < 4) {
            throw ValidationException("please enter password more than 4 chars");
        } else if (!password.equals(confirmPassword)) {
            throw ValidationException("password is not matched");
        }
        return password.trim()
    }

    fun isValidText(text: String, failedMessage: String): String {
        if (text.trim().length < 1) {
            throw ValidationException(failedMessage)
        }
        return text.trim()
    }

    fun isValidText(textField: EditText, failedMessage: String): String {
        if (textField.text.toString().trim().isEmpty()) {
            textField.requestFocus()
            throw ValidationException(failedMessage)
        }
        return textField.text.toString().trim()
    }

    fun isValidText(text: String, failedMessage: String, showView: Boolean): String {
        if (text.trim().length < 1 && showView) {
            throw ValidationException(failedMessage)
        }
        return text.trim()
    }

    fun isValidText(text: String): String {
        return isValidText(text, "please enter valid inputs")
    }


    fun isValidChoice(value: Int): Int {
        return isValidChoice(value, "Please choose value")
    }

    fun isValidChoice(value: Int, failedMessage: String): Int {
        if (value == -1) {
            throw ValidationException(failedMessage)
        }
        return value
    }

    fun isValidChecked(value: Boolean, failedMessage: String): Boolean {
        if (!value) {
            throw ValidationException(failedMessage)
        }

        return true
    }

    fun isValidDates(fromDate: Date, toDate: Date, failedMessage: String): Boolean {
        if (toDate<fromDate) {
            throw ValidationException(failedMessage)
        }

        return true
    }


}