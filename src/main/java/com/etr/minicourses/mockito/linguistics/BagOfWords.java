package com.etr.minicourses.mockito.linguistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BagOfWords {

    public Map<String, Long> analyze(String title, String content) {
        List<String> words = getWords(title, content);
        return words.stream()
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

    static List<String> getWords(String title, String content) {
        List<String> titleWords = List.of(title.replace("\n", " ")
            .split(" "));
        List<String> contentWords = List.of(content.replace("\n", " ")
            .split(" "));

        List<String> allWords = new ArrayList<>();
        allWords.addAll(titleWords);
        allWords.addAll(contentWords);
        return allWords;
    }

}
