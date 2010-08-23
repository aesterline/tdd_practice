package com.tdd;

import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

@Test
public class WordWrappingTest {
    public void lineLengthEqualToInputStringInputShouldBeReturned() {
        List<String> lines = StringUtils.wrap("abc", 3);

        assertEquals(lines, asList("abc"));
    }

    public void inputStringLessThanRowLengthShouldReturnInputString() {
        List<String> lines = StringUtils.wrap("abc", 6);

        assertEquals(lines, asList("abc"));
    }

    public void inputStringLongerThanRowLengthShouldBeBrokenOnWordBoundry() {
        List<String> lines = StringUtils.wrap("abc def", 3);

        assertEquals(lines, asList("abc", "def"));
    }

    public void inputStringShouldSplitWordIfWordCrossesLineBoundry() {
        List<String> lines = StringUtils.wrap("abcdef", 3);

        assertEquals(lines, asList("abc", "def"));
    }

    public void inputStringShouldSplitIntoManyLinesIfTheInputStringIsLargeEnough() {
        List<String> lines = StringUtils.wrap("abcdef abc", 3);

        assertEquals(lines, asList("abc", "def", "abc"));
    }
}
