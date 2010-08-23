package com.tdd;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public abstract class StringUtils {
    static List<String> wrap(String input, int lineLength) {
        if(input.length() <= lineLength) return asList(input);

        String currentInput = input;
        List<String> lines = new ArrayList<String>();

        while(lineLength < currentInput.length()) {
            String line = currentInput.substring(0, lineLength);
            lines.add(line.trim());

            currentInput = currentInput.substring(lineLength).trim();
        }

        lines.add(currentInput);

        return lines;
    }
}
