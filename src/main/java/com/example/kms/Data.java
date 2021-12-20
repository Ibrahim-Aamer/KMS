package com.example.kms;

public class Data {

    private static Data data = new Data(); //static self referential instance

    private Employee employee;
    private String word;


    public Data() {
        this.word = null;
    }

    static public Data getDataInstance()
    {
        return data;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public String getWord()
    {
        return this.word;
    }
}
