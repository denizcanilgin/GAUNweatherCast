package com.example.gaunhavajson.Model;

public class Hava {
    private String created_At;
    private int entry_Id;
    private int Field3;
    private int Field4;


    public Hava(String created_At, int entry_Id, int field3, int field4) {
        this.created_At = created_At;
        this.entry_Id = entry_Id;
        Field3 = field3;
        Field4 = field4;


    }

    public String getCreated_At() {
        return created_At;
    }

    public void setCreated_At(String created_At) {
        this.created_At = created_At;
    }

    public int getEntry_Id() {
        return entry_Id;
    }

    public void setEntry_Id(int entry_Id) {
        this.entry_Id = entry_Id;
    }

    public int getField3() {
        return Field3;
    }

    public void setField3(int field3) {
        Field3 = field3;
    }

    public int getField4() {
        return Field4;
    }

    public void setField4(int field4) {
        Field4 = field4;
    }
}
