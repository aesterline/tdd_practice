package com.tdd;

public abstract class StringUtils {
    static String endOfLineTrim(String input) {
        input = input.replaceAll("[ \t]*$", "");
        return input.replaceAll("[ \t]*(\r?\n)", "$1");
    }
}
