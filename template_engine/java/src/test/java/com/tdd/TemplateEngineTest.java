package com.tdd;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@Test
public class TemplateEngineTest {
    public void shouldNotReplaceIfNoVariablePresent() {
        String template = "No variables";
        String results = evaluateTemplate(template, Collections.<String, Object>emptyMap());

        assertEquals(results, template);
    }

    public void shouldReplaceOneVariable() {
        Map<String, Object> variables = Collections.<String, Object>singletonMap("name", "Adam");

        String results = evaluateTemplate("Hello {$name}", variables);

        assertEquals(results, "Hello Adam");
    }

    public void shouldReplaceMultipleVariables() {
        Map<String,Object> variables = new HashMap<String, Object>();
        variables.put("firstName", "Adam");
        variables.put("lastName", "Esterline");

        String results = evaluateTemplate("Hello {$firstName} {$lastName}", variables);

        assertEquals(results, "Hello Adam Esterline");
    }

    public void shouldReplaceWhenTemplateIsMadeUpOnlyOfVariables() {
        Map<String,Object> variables = new HashMap<String, Object>();
        variables.put("firstName", "Adam");
        variables.put("lastName", "Esterline");

        String results = evaluateTemplate("{$firstName}{$lastName}", variables);

        assertEquals(results, "AdamEsterline");        
    }

    public void shouldReplaceNestedVariables() {
        Map<String, Object> variables = Collections.<String, Object>singletonMap("name", "Adam");

        String results = evaluateTemplate("Hello ${{$name}}", variables);

        assertEquals(results, "Hello ${Adam}");
    }

    public void shouldThrowExceptionWhenVariableDoesNotExist() {
        Map<String, Object> variables = Collections.emptyMap();

        try {
            evaluateTemplate("Hello {$name}", variables);
            fail("Should throw Exception");
        }
        catch(MissingValueException e) {
            // expected
        }
    }

    private String evaluateTemplate(String template, Map<String, Object> variables) {
        TemplateEngine templateEngine = new TemplateEngine(template);
        return templateEngine.evaluate(variables);
    }
}
