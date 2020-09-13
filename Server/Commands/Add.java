package com.company.Commands;

import com.company.Collection.City;
import com.company.Collection.Climate;
import com.company.Collection.Coordinates;
import com.company.Collection.Human;

import java.io.IOException;
import java.util.List;

public class Add {
    public static void add(List<City> list, String arg) throws IOException {
        City city;
        while (true) {
            city = new City();
            String[] args = arg.split(" ");

            String name = args[0];
            if (Constants.nullChecker(name)) {
                city.setName(name);
            } else {
                System.out.print("Имя введено некоректно, попробуйте еще раз: ");
                break;
            }

            Coordinates coordinates = new Coordinates();
            String x = args[1];
            try {
                if (Float.parseFloat(x) > -554) {
                    coordinates.setX(Float.parseFloat(x));
                } else System.out.print("Координата X <= -554...");
            } catch (NumberFormatException e) {
                System.out.print("Вы ввели число неверно, попробуйте еще раз: ");
                break;
            }

            String y = args[2];
            try {
                coordinates.setY(Integer.valueOf(y));
            } catch (NumberFormatException e) {
                System.out.print("Вы ввели число неверно, попробуйте еще раз: ");
                break;
            }
            city.setCoordinates(coordinates);

            String area = args[3];
            try {
                city.setArea(Long.parseLong(area));
            } catch (NumberFormatException e) {
                System.out.print("Вы ввели число неверно, попробуйте еще раз: ");
                break;
            }

            String population = args[4];
            try {
                city.setPopulation(Long.parseLong(population));
            } catch (NumberFormatException e) {
                System.out.print("Вы ввели число неверно, попробуйте еще раз: ");
                break;
            }

            String metersAboveSeaLevel = args[5];
            try {
                city.setMetersAboveSeaLevel(Double.parseDouble(metersAboveSeaLevel));
            } catch (NumberFormatException e) {
                System.out.print("Вы ввели число неверно, попробуйте еще раз: ");
                break;
            }

            String capital = args[6];
            if (capital.equals("true") || capital.equals("false")) {
                city.setCapital(Boolean.parseBoolean(capital));
            } else {
                System.out.print("Вы ввели значение неверно, попробуйте еще раз: ");
                break;
            }

            String agglomeration = args[7];
            try {
                city.setAgglomeration(Long.parseLong(agglomeration));
            } catch (NumberFormatException e) {
                System.out.print("Вы ввели число неверно, попробуйте еще раз: ");
                break;
            }

            String climate = args[8];
            if (climate.equals("HUMIDSUBTROPICAL") || climate.equals("STEPPE") || climate.equals("TUNDRA")) {
                city.setClimate(Climate.valueOf(climate));
            } else {
                System.out.print("Вы ввели значение неверно, попробуйте еще раз: ");
                break;
            }

            Human human = new Human();
            String nameGovernor = args[9];
            if (Constants.nullChecker(name)) {
                human.setName(nameGovernor);
            } else {
                System.out.print("Имя введено некоректно, попробуйте еще раз: ");
                break;
            }
            city.setGovernor(human);
            break;
        }
        list.add(city);
        list.sort(City::compareTo);
        System.out.println("Объект был добавлен");
    }
}