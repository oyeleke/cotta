package com.example.oyeleke.myapplication.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by oyeleke on 3/10/18.
 */

public class InputValidator {
    public static String EMAIL_REGEX_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static String NUMBER_REGEX_PATTER = "\\d{11}";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isValidNumber(String number) {
        Pattern pattern = Pattern.compile(NUMBER_REGEX_PATTER);
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }

    public static boolean validatePhone(String phoneNumber) {
        if (phoneNumber == null) return false;

        if (isValidNumber(phoneNumber)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validatePasswordLength(String password) {
        if (password == null) return false;

        if (password.length() < 6) {
            return false;
        }
        return true;
    }

    public static boolean validateIsInputEmpty(String input) {
        if (input == null) return false;
        if (TextUtils.isEmpty(input)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validateEmail(String email) {
        if (email == null) return false;

        if (isValidEmail(email)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateInputsMatch(String firstInput, String secondInput) {
        if (firstInput == null || firstInput == null) return false;

        if (firstInput.equals(secondInput)) {
            return true;
        } else {
            return false;
        }
    }
}
