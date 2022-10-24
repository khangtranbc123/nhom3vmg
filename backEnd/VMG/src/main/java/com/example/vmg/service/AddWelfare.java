package com.example.vmg.service;

import java.util.ArrayList;

public class AddWelfare {
    private int id;
    private ArrayList<Integer> list;

    public AddWelfare() {
    }
    public AddWelfare(int id, ArrayList<Integer> list) {
        this.id = id;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }
}
