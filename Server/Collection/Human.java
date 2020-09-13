package com.company.Collection;

import com.company.Commands.Constants;

import java.io.Serializable;

public class Human implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Constants.nullChecker(name)) {
            this.name = name;
        } else Constants.breakComment("Пустая строка в поле governer");
    }
}