package com.codekul.jsonparsing;

import java.util.ArrayList;

/**
 * Created by aniruddha on 18/10/16.
 */

public class MyObj {

    private String name; // var name should be same as json key
    private ArrayList<String> cities = new ArrayList<>();
    private Integer age;
    private Boolean isIndian;
    private InnerObj obj;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIndian() {
        return isIndian;
    }

    public void setIndian(Boolean indian) {
        isIndian = indian;
    }

    public InnerObj getObj() {
        return obj;
    }

    public void setObj(InnerObj obj) {
        this.obj = obj;
    }
}
