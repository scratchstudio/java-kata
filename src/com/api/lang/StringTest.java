package com.api.lang;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * java.lang.String 클래스 테스트
 */
public class StringTest {

    @Before
    public void setUp() {

    }

    @Test
    public void lengthAndEmptyTest() {
        String str = "";
        assertEquals(0, str.length());
        assertEquals(true, str.isEmpty());

        str = " ";
        assertEquals(1, str.length());
        assertEquals(false, str.isEmpty());

        str = "a";
        assertEquals(1, str.length());
        assertEquals(false, str.isEmpty());
    }

    @Test
    public void equalsTests() {
        String str1 = "I am a boy";
        String str2 = "I am a boy";
        String str3 = new String("I am a boy");

        String str4 = "I am a girl";
        String str5 = "i AM A BOY";


        assertTrue(str1 == str2);
        assertTrue(str1.equals(str2));

        assertFalse(str1 == str3);
        assertTrue(str1.equals(str3));

        assertFalse(str1.equals(str5));
        assertTrue(str1.equalsIgnoreCase(str5));

        assertEquals(1,  "b".compareTo("a"));
        assertEquals(-1, "b".compareTo("c"));
        assertEquals(-2, "a".compareTo("c"));
        assertEquals(32, "b".compareTo("B"));
        assertEquals(0, "b".compareToIgnoreCase("B"));

        String address = "서울시 강남구 대치동";
        assertTrue(address.startsWith("서울시"));
        assertTrue(address.contains("강남구"));
        assertTrue(address.matches(".*대치동.*"));
        assertTrue(address.endsWith("대치동"));
    }

    @Test
    public void indexOfTest() {
        String str = "Java technology is both a programming language and a platform.";

        assertEquals(1, str.indexOf('a'));
        assertEquals(3, str.indexOf("a "));
        assertEquals(24, str.indexOf('a', 20));
        assertEquals(24, str.indexOf("a ", 20));
        assertEquals(-1, str.indexOf('z'));

        assertEquals(55, str.lastIndexOf('a'));
        assertEquals(51, str.lastIndexOf("a "));
        assertEquals(3,  str.lastIndexOf('a', 20));
        assertEquals(3,  str.lastIndexOf("a ", 20));
        assertEquals(-1, str.lastIndexOf('z'));
    }

    @Test(expected = NullPointerException.class)
    public void nullObjReferenceTest() {
        nullCheck(null);
    }

    public boolean nullCheck(String str) {
        int textLength = str.length();

        if (str == null)
            return  true;
        else
            return false;
    }

    @Test
    public void editStringTest() {
        assertEquals("Technology", "Java Technology".substring(5));
        assertEquals("Tech", "Java Technology".substring(5, 9));

        String str = "Hello,String,Class";
        String[] splitArray = str.split(",");
        assertEquals("Hello", splitArray[0]);
        assertEquals("String", splitArray[1]);
        assertEquals("Class", splitArray[2]);

        assertFalse("trim".equals(" trim "));
        assertTrue("trim".equals(" trim ".trim()));

        str = "abcdef";
        assertEquals("abadef", str.replace('c', 'a'));
        assertEquals("abcdef", str);
        str = str.replace('c', 'a');
        assertEquals("abadef", str);
        assertEquals("zbadef", str.replaceFirst("a", "z"));
    }

    @Test
    public void stringCastTest() {
        byte b = 1;

        String castToString1 = String.valueOf(b);
        String castToString2 = b+"";

        assertTrue(castToString1 instanceof String);
        assertTrue(castToString2 instanceof String);

        String nullStr = null;
        assertEquals("null", String.valueOf(nullStr));


    }

}