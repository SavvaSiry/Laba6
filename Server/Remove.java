package com.company.Commands;

import com.company.Collection.City;
import com.company.Server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Remove {

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void remove_by_id(List<City> list, int id) {
        list.removeIf(x -> x.getId().equals(id));
        list.sort(City::compareTo);
    }

    static public void clear(List<City> list) {
        list.clear();
    }

    static public void remove_greater(List<City> list, String string) throws IOException {
        String[] strings = string.split(" ");
        String key = strings[0];
        String valueString = strings[1];
        try {
            switch (key) {
                case "area": {
                    Long value = Long.parseLong(valueString);
                    list.removeIf(x -> x.getArea() > value);
                }
                break;
                case "population": {
                    Long value = Long.parseLong(valueString);
                    list.removeIf(x -> x.getPopulation() > value);
                }
                break;
                case "metersAboveSeaLevel": {
                    Double value = Double.parseDouble(valueString);
                    list.removeIf(x -> x.getMetersAboveSeaLevel() > value);
                }
                break;
                case "agglomeration": {
                    Long value = Long.parseLong(valueString);
                    list.removeIf(x -> x.getAgglomeration() > value);
                }
                break;
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
            System.out.println("Значение введено неправильно");
        }
        System.out.println("Объекты были удалены");
        list.sort(City::compareTo);
    }

    static public void remove_lower(List<City> list, String string) throws IOException {
        String[] strings = string.split(" ");
        String key = strings[0];
        String valueString = strings[1];
        try {
            switch (key) {
                case "area": {
                    Long value = Long.parseLong(valueString);
                    list.removeIf(x -> x.getArea() < value);
                }
                break;
                case "population": {
                    Long value = Long.parseLong(valueString);
                    list.removeIf(x -> x.getPopulation() < value);
                }
                break;
                case "metersAboveSeaLevel": {
                    Double value = Double.parseDouble(valueString);
                    list.removeIf(x -> x.getMetersAboveSeaLevel() < value);
                }
                break;
                case "agglomeration": {
                    Long value = Long.parseLong(valueString);
                    list.removeIf(x -> x.getAgglomeration() < value);
                }
                break;
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
            System.out.println("Значение введено неправильно");
        }
        System.out.println("Объекты были удалены");
        list.sort(City::compareTo);
    }

    public static void update(List<City> list) throws IOException {
        System.out.print("Введите значение id обекта, который желаете исправить: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int value = Integer.parseInt(reader.readLine());
            Remove.remove_by_id(list, value);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
            System.out.println("Значение не корректно");
        }
//        Add.add(list);
        reader.close();
    }
}