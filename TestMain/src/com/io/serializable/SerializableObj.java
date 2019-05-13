package com.io.serializable;

import java.io.Serializable;

/**
 * Create with IntelliJ IDEA
 * Create By zhangwenbao
 * Date:{$Date}
 * Time:{TIME}
 */
public class SerializableObj implements Serializable {

    private String name;                                 //名字
    private int year;                                    //年龄
    private String city;                                 //城市
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.year=year;
        this.city=city;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString(){
        return this.name+" "+this.year+" "+this.city+" ".toString();
    }
}
