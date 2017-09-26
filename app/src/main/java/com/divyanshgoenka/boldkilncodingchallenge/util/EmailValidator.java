package com.divyanshgoenka.boldkilncodingchallenge.util;

import java.util.regex.Pattern;

/**
 * Created by divyanshgoenka on 26/09/17.
 */

public class EmailValidator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * Validate hex with regular expression
     *
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */

    public static boolean validate(final CharSequence hex) {
        return pattern.matcher(hex).matches();
    }
}
