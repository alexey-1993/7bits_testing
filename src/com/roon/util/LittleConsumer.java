package com.roon.util;

import com.roon.processor.StringProcessor;

import java.util.function.Consumer;

/**
 * Created by Алексей on 15.05.2016.
 */
public class LittleConsumer implements Consumer<String> {
    StringBuilder text = null;
    StringProcessor processor = null;

    public LittleConsumer(StringBuilder text, StringProcessor processor) {
        this.text = text;
        this.processor = processor;

    }
    @Override
    public void accept(String s) {
        text.append(getProcessor().processString(s));
    }


    public StringProcessor getProcessor() {
        return processor;
    }
}
