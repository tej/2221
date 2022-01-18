import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void testCombination() {
        String str1test = "combinethis";
        String str2test = "thisstring";
        int overlap = 4;
        String res = StringReassembly.combination(str1test, str2test, overlap);
        assertEquals("combinethisstring", res);
    }

    @Test
    public void testCombinationWithSpaces() {
        String str1test = "combine this";
        String str2test = "this string";
        int overlap = 4;
        String res = StringReassembly.combination(str1test, str2test, overlap);
        assertEquals("combine this string", res);
    }

    @Test
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> strSetTest = new Set1L<String>();
        strSetTest.add("This is");
        strSetTest.add("a sentence");
        strSetTest.add("it should add together");
        Set<String> strSet2Test = new Set1L<String>();
        strSet2Test.add("This is a sentence it should add together");

        String str = "This is a sentence it should add together";
        StringReassembly.addToSetAvoidingSubstrings(strSetTest, str);
        assertEquals(strSetTest, strSet2Test);
    }

    @Test
    public void testAddToSetAvoidingSubstrings3() {
        Set<String> strSetTest = new Set1L<String>();
        strSetTest.add("" + "" + "");
        strSetTest.add("because the added string");
        Set<String> strSet2Test = new Set1L<String>();
        strSet2Test.add("Both sets should match");
        strSet2Test.add("because the added string");
        strSet2Test.add("is not a substring of this set");
        String str = "is not a substring of this set";
        StringReassembly.addToSetAvoidingSubstrings(strSetTest, str);
        assertEquals(strSetTest, strSet2Test);
    }

    @Test
    public void testAddToSetAvoidingSubstrings4() {
        Set<String> strSetTest = new Set1L<String>();
        strSetTest.add("Both sets should match because the added string");
        strSetTest.add("is a substring of this set");
        Set<String> strSet2Test = new Set1L<String>();
        strSet2Test.add("Both sets should match because the added string");
        strSet2Test.add("is a substring of this set");
        String str = "because the added string";
        StringReassembly.addToSetAvoidingSubstrings(strSetTest, str);
        assertEquals(strSetTest, strSet2Test);
    }

    @Test
    public void testLinesFromInput() {
        Set<String> strSetTest = new Set1L<>();
        strSetTest.add("Bucks -- Beat");
        strSetTest.add("Go Bucks");
        strSetTest.add("o Bucks -- B");
        strSetTest.add("Beat Mich");
        strSetTest.add("Michigan~");
        Set<String> strSet2Test = StringReassembly
                .linesFromInput(new SimpleReader1L("cheer-8-2.txt"));
        assertEquals(strSetTest, strSet2Test);
    }

    @Test
    public void testPrintWithLineSeparators() {
        SimpleWriter expected = new SimpleWriter1L();
        SimpleWriter testCase = new SimpleWriter1L();
        expected.println("Test");
        expected.println("Test 2");
        String testString = "Test~Test 2~";
        StringReassembly.printWithLineSeparators(testString, testCase);
        expected.close();
        testCase.close();
    }

}
