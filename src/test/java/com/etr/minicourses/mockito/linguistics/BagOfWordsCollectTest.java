package com.etr.minicourses.mockito.linguistics;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BagOfWordsCollectTest {

    @Test
    void test() {
        BagOfWords bagOfWords = new BagOfWords();

        Map<String, Long> words = bagOfWords.analyze("aaa bbb", "aaa ccc");

        Assertions.assertThat(words)
            .hasSize(3)
            .containsEntry("aaa", 2L)
            .containsEntry("bbb", 1L)
            .containsEntry("ccc", 1L);
    }

}