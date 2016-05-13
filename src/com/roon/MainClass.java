package com.roon;
import java.io.*;
import java.util.function.Consumer;

public class MainClass {
    
    public static void main(String[] args) {
        MainClass main = new MainClass();
        main.process("C:\\Users\\Алексей\\Downloads\\lefan_formatting\\lefan_formatting\\test.txt");
    }

    private void process(String filepath) {
        File f = new File(filepath);
        StringBuilder text = new StringBuilder();
        try (BufferedReader fr = new BufferedReader(new FileReader(f))) {
            ;
            fr.lines().forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    text.append(s);
                }
            });
            String result = processString(text);
            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String processString(StringBuilder text) {
        int startindex = 0;
        while (text.indexOf("}", startindex) != -1) {
            startindex = text.indexOf("}", startindex) + 1;
            if (startindex < text.length()) {
                if (text.charAt(startindex) != ';') {
                    text.insert(startindex, "\r\n");
                } else {
                    text.insert(startindex + 1, "\r\n");
                }
            }
        }

        startindex = 0;
        while (text.indexOf("{", startindex) != -1) {
            startindex = text.indexOf("{", startindex) + 1;
            text.insert(startindex, "\r\n");
        }

        startindex = 0;
        while (text.indexOf("=", startindex) != -1) {
            startindex = text.indexOf("=", startindex) + 2;
            if (text.charAt(startindex - 1) != ' ') {
                text.insert(startindex - 1, " ");
            }
            if (text.charAt(startindex - 2) != ' ') {
                text.insert(startindex - 2, " ");
            }
        }
        startindex = 0;
        while (text.indexOf(";", startindex) != -1) {
            startindex = text.indexOf(";", startindex) + 1;
            text.insert(startindex, "\r\n");
        }

        // processing tabs
        startindex = 0;
        while (text.indexOf("\r\n", startindex) != -1) {
            startindex = text.indexOf("\r\n", startindex) + 2;
            int currentlevel = currentLevel(text, startindex);

            int spacesHave = 0;
            for (int i = startindex; i < text.length(); i++) {
                if (Character.isSpaceChar(text.charAt(i))) {
                    spacesHave++;
                } else {
                    break;
                }

            }
            for (int j = 0; j < currentlevel * 4 - spacesHave; j++) {
                text.insert(startindex, ' ');
                startindex++;
            }
        }
        return text.toString();
    }

    private int currentLevel(StringBuilder text, int startindex) {
        int currentlevel = 0;
        for (int i = 0; i < startindex; i++) {
            if (text.charAt(i) == '}') {
                currentlevel--;
            }
            if (text.charAt(i) == '{') {
                currentlevel++;
            }
        }
        return currentlevel;
    }

}
