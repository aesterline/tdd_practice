package com.tdd;

import org.testng.annotations.Test;

import static com.tdd.StringUtils.endOfLineTrim;
import static org.testng.Assert.assertEquals;

@Test
public class EndOfLineTrimTest {
    public void spacesAtEndShouldBeRemoved() {
        String result = endOfLineTrim("abc  ");

        assertEquals(result, "abc");
    }

    public void tabsAtEndShouldBeRemoved() {
        String result = endOfLineTrim("abc\t\t");

        assertEquals(result, "abc");
    }

    public void spacesAtBeginningShouldNotBeRemoved() {
        String result = endOfLineTrim("  abc");

        assertEquals(result, "  abc");
    }

    public void tabsAtBeginningShouldNotBeRemoved() {
        String result = endOfLineTrim("\t\tabc");

        assertEquals(result, "\t\tabc");
    }

    public void newlineCharacterShouldNotBeRemoved() {
        String result = endOfLineTrim("abc\n");

        assertEquals(result, "abc\n");
    }

    public void windowsNewlineCharactersShouldNotBeRemoved() {
        String result = endOfLineTrim("abc\r\n");

        assertEquals(result, "abc\r\n");
    }

    public void spacesBeforeNewlineShouldBeRemoved() {
        String result = endOfLineTrim("abc  \n");

        assertEquals(result, "abc\n");
    }

    public void tabsBeforeNewLineShouldBeRemoved() {
        String result = endOfLineTrim("abc\t\t\r\n");

        assertEquals(result, "abc\r\n");
    }

    public void spacesOnMultipleLinesShouldBeRemoved() {
        String result = endOfLineTrim("abc  \ndef  \r\n");

        assertEquals(result, "abc\ndef\r\n");
    }

    public void tabsOnMultipleLinesShouldBeRemoved() {
        String result = endOfLineTrim("abc\t\t\r\ndef\t\n");

        assertEquals(result, "abc\r\ndef\n");
    }

    public void mixOfTabsAndSpacesOnMultipleLinesShouldBeRemoved() {
        String result = endOfLineTrim("abc\t \ndef \r\n");

        assertEquals(result, "abc\ndef\r\n");
    }

    public void spacesAtBeginningOfMultipleLinesShouldNotBeRemoved() {
        String result = endOfLineTrim("abc\t \n   def \r\n");

        assertEquals(result, "abc\n   def\r\n");
    }
}
