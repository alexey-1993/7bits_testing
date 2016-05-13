package com.roon;

import com.roon.processor.StringProcessor;
import com.roon.processor.StringProcessorImpl;

import java.io.*;
import java.util.function.Consumer;

public class MainClass {
    StringProcessor processor = null;

    public static void main(String[] args) {
        MainClass main = new MainClass();
        main.process("C:\\Users\\Алексей\\Downloads\\lefan_formatting\\lefan_formatting\\7bits_testing\\test.txt");
    }

    private void process(String filepath) {
        processor = new StringProcessorImpl();
        File f = new File(filepath);
        StringBuilder text = new StringBuilder();
        try (BufferedReader fr = new BufferedReader(new FileReader(f))) {
            fr.lines().forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    text.append(processor.processString(s));
                }
            });

            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
