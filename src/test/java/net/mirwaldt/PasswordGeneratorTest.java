package net.mirwaldt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {
    private static Stream<Arguments> passwordGenerator() {
        return Stream.of(Arguments.of(new DefaultPasswordGenerator()));
    }

    @ParameterizedTest
    @MethodSource("passwordGenerator")
    void test_nextPassword(PasswordGenerator passwordGenerator) {
        assertEquals("abcdffaa", passwordGenerator.generateNextPassword("abcdefgh"));
        assertEquals("ghjaabcc", passwordGenerator.generateNextPassword("ghijklmn"));
    }

    @Test
    void test_isStraight() {
        assertTrue(DefaultPasswordGenerator.isStraight("abc"));
        assertTrue(DefaultPasswordGenerator.isStraight("cde"));
        assertFalse(DefaultPasswordGenerator.isStraight("abd"));
        assertFalse(DefaultPasswordGenerator.isStraight("cef"));
    }

    @Test
    void test_hasStraight() {
        assertTrue(DefaultPasswordGenerator.hasStraight("acdeffaa"));
        assertTrue(DefaultPasswordGenerator.hasStraight("abcdefgh"));
        assertFalse(DefaultPasswordGenerator.hasStraight("abdeghjk"));
        assertFalse(DefaultPasswordGenerator.hasStraight("mnpqstvw"));
    }

    @Test
    void test_hasForbiddenLetters() {
        assertTrue(DefaultPasswordGenerator.hasForbiddenLetters("mnp" + "l" + "stvw"));
        assertTrue(DefaultPasswordGenerator.hasForbiddenLetters("acdeffa" + "i"));
        assertTrue(DefaultPasswordGenerator.hasForbiddenLetters("o" + "bcdefgh"));
        assertFalse(DefaultPasswordGenerator.hasForbiddenLetters("acdeffaa"));
        assertFalse(DefaultPasswordGenerator.hasForbiddenLetters("mnpqstvw"));
    }

    @Test
    void test_hasTwoDifferentNonOverlappingPairs() {
        assertTrue(DefaultPasswordGenerator.hasTwoDifferentNonOverlappingPairs("acdeffaa"));
        assertTrue(DefaultPasswordGenerator.hasTwoDifferentNonOverlappingPairs("acddefaa"));
        assertTrue(DefaultPasswordGenerator.hasTwoDifferentNonOverlappingPairs("accddeaa"));
        assertFalse(DefaultPasswordGenerator.hasTwoDifferentNonOverlappingPairs("acfdeaaa"));
        assertFalse(DefaultPasswordGenerator.hasTwoDifferentNonOverlappingPairs("aacdefaa"));
        assertFalse(DefaultPasswordGenerator.hasTwoDifferentNonOverlappingPairs("acdefaaa"));
    }
}
