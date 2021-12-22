package com.example.kms;

public class Data {

    private static Data data = new Data(); //static self referential instance


    private EmployeeKMS currentEmployeeKms;
    private Message message;
    private Employee employee;
    private String word;

    public EmployeeKMS getCurrentEmployeeKms() {
        return currentEmployeeKms;
    }

    public void setCurrentEmployeeKms(EmployeeKMS currentEmployeeKms) {
        this.currentEmployeeKms = currentEmployeeKms;
    }


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

    public void setMessage(Message message)
    {
        this.message = message;
    }

    public Message getMessage()
    {
        return message;
    }

    public String getWord()
    {
        return this.word;
    }
}
