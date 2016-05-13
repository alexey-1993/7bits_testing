package com.roon.processor;

/**
 * Created by Алексей on 13.05.2016.
 */
public class StringProcessorImpl implements StringProcessor {
    private int level = 0;

    @Override
    public String processString(String str) {
        StringBuilder text = new StringBuilder(str);
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '{') {
                text.insert(i + 1, "\r\n");
                this.level++;
            }
            if (text.charAt(i) == '}') {
                this.level--;
                if (i + 1 < text.length()) {
                    if (text.charAt(i + 1) != ';') {
                        text.insert(i + 1, "\r\n");
                    } else {
                        text.insert(i + 2, "\r\n");
                    }
                }
            }
            if (text.charAt(i) == ';' && (i + 1 == text.length() || text.charAt(i + 1) != '\r')) {
                text.insert(i + 1, "\r\n");
            }
            if (text.charAt(i) == '\r') {
                int spacesHave = 0;
                for (int j = i + 2; j < text.length(); j++) {
                    if (Character.isSpaceChar(text.charAt(j))) {
                        spacesHave++;
                    } else {
                        break;
                    }

                }
                for (int j = 0; j < this.level * 4 - spacesHave; j++) {
                    text.insert(i + 2, ' ');
                }
            }
        }
        return text.toString();
    }
}