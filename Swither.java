package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Swither {

    public static String start(String name, BufferedReader reader) throws IOException {

        switch (name) { //передаем оператору swith нашу собранную строку на сравение с допустимыми командами
            case ("add"):
                return add(reader);
            case ("remove_greater"):
            case ("remove_lower"):
                return remove(reader);
            case ("update"):

        }
        return null;
    }

    public static String add(BufferedReader reader) throws IOException {
        String bigString = "";
        String lilString = "";
        System.out.print("Введите название города: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        System.out.print("Введите значение координаты X: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        System.out.print("Введите значение координаты Y: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        System.out.print("Введите значегие поля area: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        System.out.print("Введите значегие поля population: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        System.out.print("Введите значегие поля metersAboveSeaLevel: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        System.out.print("Введите значегие (true or false) поля capital: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        System.out.print("Введите значегие поля agglomeration: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        System.out.print("Введите значегие (HUMIDSUBTROPICAL, STEPPE, TUNDRA) поля climate: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        System.out.print("Введите имя Губернатора: ");
        lilString = reader.readLine();
        bigString += lilString + " ";
        return bigString;
    }

    static public String remove(BufferedReader reader) throws IOException {
        System.out.print("Поля по кторомы можно проводить сравнения:\n\"area\", \"population\", \"metersAboveSeaLevel\", \"agglomeration\" \n"
                + "Введите название поля по которому вы хотите исключить объекты: ");
        String key = reader.readLine();
        System.out.print("Введите число по кторому будет проводиться сравнение: ");
        String valueString = reader.readLine();
        return key + " " + valueString;
    }
}
