package com.infoshare;

public class StringUtils {


    public String[] getLines(String veryLongText) {
        String[] lines = veryLongText.split("\n");
        return lines;
    }

    public String[] getWords(String oneLineText) {
        String[] words = oneLineText.split(" ");
        return words;
    }
    public String[] replaceSign (String[] wordsWithSign){
        String [] wordsWithout = wordsWithSign;
        for (int i = 0; i < wordsWithSign.length; i++) {
            if (wordsWithSign[i].contains(".")){
                wordsWithout[i] = wordsWithSign[i].substring(0,wordsWithSign[i].length()-1);
            }else if (wordsWithSign[i].contains(";")){
                wordsWithout[i] = wordsWithSign[i].substring(0,wordsWithSign[i].length()-1);
            }else {
                wordsWithout[i] = wordsWithSign[i];
            }
        }
        return wordsWithout;
    }

}
