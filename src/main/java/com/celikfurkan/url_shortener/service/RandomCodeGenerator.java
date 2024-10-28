package com.celikfurkan.url_shortener.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class RandomCodeGenerator {

    private RandomCodeGenerator() {}

    private static int codeLength;

    @Value("${code.length}")
    public void setCodeLength(int codeLength) {
        RandomCodeGenerator.codeLength = codeLength;
    }
    private final static String LETTERS = "abcdefghijklmnprstuvyzqw123456789";

    public static String generate() {
        SecureRandom random = new SecureRandom();

        StringBuilder generated = new StringBuilder();

        List<Character> letterList = LETTERS
                .toUpperCase(Locale.ROOT)
                .chars()
                .mapToObj(value -> (char) value)
                .collect(Collectors.toList());
        Collections.shuffle(letterList);

        for (int i = 0; i < codeLength; i++) {
            generated.append(letterList.get(random.nextInt(letterList.size())));
        }

        return generated.toString();
    }
}
