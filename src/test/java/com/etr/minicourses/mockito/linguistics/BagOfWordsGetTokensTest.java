package com.etr.minicourses.mockito.linguistics;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class BagOfWordsGetTokensTest {

    @Test
    void test() {
        List<String> tokens = BagOfWords.getWords("Learn Mockito", "Mockito is a\ntesting framework");

        assertThat(tokens)
            .containsExactlyInAnyOrder("Learn", "Mockito", "Mockito", "is", "a", "testing", "framework");
    }

}