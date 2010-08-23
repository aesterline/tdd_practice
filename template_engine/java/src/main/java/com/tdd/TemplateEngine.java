package com.tdd;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {
    private String template;

    public TemplateEngine(String template) {
        this.template = template;
    }

    public String evaluate(Map<String, Object> variables) {
        Pattern pattern = Pattern.compile("\\{\\$(.*?)\\}");
        Matcher matcher = pattern.matcher(template);

        String result = template;

        while(matcher.find()) {
            String variableName = matcher.group(1);
            int start = matcher.start();
            int end = matcher.end();

            if(variables.containsKey(variableName)) {
                result = result.substring(0, start) + variables.get(variableName) + result.substring(end);
            }
            else {
                throw new MissingValueException();
            }

            matcher = matcher.reset(result);
        }

        return result;
    }
}
