package com.roon;

import com.roon.util.LittleConsumer;
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

    public MainClass() {
        setProcessor(new StringProcessorImpl());
    }

    private void process(String filepath) {
        File f = new File(filepath);
        StringBuilder text = new StringBuilder();
        Consumer<String> consumer = new LittleConsumer(text, processor);
        try (BufferedReader fr = new BufferedReader(new FileReader(f))) {
            fr.lines().forEach(consumer);
            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(StringProcessor processor) {
        this.processor = processor;
    }
}
