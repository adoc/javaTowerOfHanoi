package io.nicklong.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
    @Test
    public void testRepeatChar() {
        assertEquals(StringUtils.repeatChar('0', 0), "");
        assertEquals(StringUtils.repeatChar('1', 1), "1");
        assertEquals(StringUtils.repeatChar('5', 5), "55555");
    }
}
