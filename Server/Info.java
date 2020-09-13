package com.company.Commands;

import com.company.Collection.City;

import java.time.LocalDateTime;
import java.util.List;

public class Info {

    private static LocalDateTime creationDate;

    public static void info(List<City> list){
        System.out.println("Тип колекции " + list.getClass());
        System.out.println("Количество объектов " + list.size());
        System.out.println("Время инициализации " + creationDate.toString());
    }

    public static void setCreationDate(LocalDateTime date) {
        creationDate = date;
    }

    public static void head(List<City> list){
        System.out.println(list.get(0).toString());
    }

    public static void help(){
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add : добавить новый элемент в коллекцию\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "exit : завершить программу и сохранить \n" +
                "head : вывести первый элемент коллекции\n" +
                "remove_greater : удалить из коллекции все элементы, превышающие заданный\n" +
                "remove_lower : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "count_greater_than_meters_above_sea_level metersAboveSeaLevel : вывести количество элементов, значение поля metersAboveSeaLevel которых больше заданного\n" +
                "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки\n");
    }

    public static void count_greater_than_meters_above_sea_level(List<City> list, Double x){
        long value = list.stream().filter(p -> p.getMetersAboveSeaLevel() > x).count();
        System.out.println("Количество городов, высота над уровнем моря превышающие " + x + " м равна: " + value);
    }

    public static void filter_starts_with_name(List<City> list, String name){
        boolean check = list.stream().anyMatch(x -> x.getName().equals(name));
        if (check)
            list.stream().filter(x -> x.getName().equals(name)).forEach(x -> System.out.println(x.toString()));
        else System.out.println("Совпадений с именем " + name + " не найдено");
    }

    static public void show(List<City> list){
        list.forEach(System.out::println);
    }
}