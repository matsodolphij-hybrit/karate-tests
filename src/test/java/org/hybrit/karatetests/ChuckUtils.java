package org.hybrit.karatetests;

import org.apache.commons.lang.StringUtils;

public class ChuckUtils {

    /**
     * Validate that the fact contains chuck.
     *
     * @param value the fact.
     * @return true when it contains 'chuck' the roundhouse kick Norris.
     */
    public static boolean isValidChuck(final String value) {
       return StringUtils.containsIgnoreCase(value, "chuck");
    }

}
