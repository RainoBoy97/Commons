package me.raino.commons;

import org.apache.commons.lang.StringUtils;

public class Txt {

    public static String parse(String text, Object... arguments) {
        for (int i = 0; i < arguments.length; i++)
            text = StringUtils.replace(text, "{" + i + "}", arguments[i].toString());
        return text;
    }

    private Txt() {}

}
