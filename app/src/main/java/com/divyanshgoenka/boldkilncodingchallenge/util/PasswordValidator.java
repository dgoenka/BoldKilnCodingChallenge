package com.divyanshgoenka.boldkilncodingchallenge.util;

/**
 * Created by divyanshgoenka on 27/09/17.
 */

public class PasswordValidator {
    /**
     * Validate hex with regular expression
     *
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */

    public static boolean validate(final CharSequence hex) {
        return hex.length()>=Constants.PASSWORD_MIN_LENGTH;
    }
}
