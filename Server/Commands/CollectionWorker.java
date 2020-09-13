package com.company.Commands;

import com.company.Collection.City;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;

public class CollectionWorker {

    private LinkedList<City> cityList;
    private String fileName;
    ByteArrayOutputStream outputStream;

    public CollectionWorker(LinkedList<City> cityList, String fileName) {
        this.cityList = cityList;
        this.fileName = fileName;
    }

    public String start(String name, String argument) {

        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        try { switch (name) {
            case ("exit"):
                Save.save(cityList, fileName);
                    break;
                case ("help"):
                    Info.help();
                    break;
                case ("info"):
                    Info.info(cityList);
                    break;
                case ("show"):
                    Info.show(cityList);
                    break;
                case ("add"):
                    Add.add(cityList, argument);
                    break;
//                case ("update_id"):
//                    Remove.update(cityList);
//                    break;
                case ("remove_by_id"):
                    try {
                        Remove.remove_by_id(cityList, Integer.parseInt(argument));
                    } catch (NumberFormatException e) {
                        System.out.println("Данные введены неправильно");
                    }
                    break;
                case ("clear"):
                    Remove.clear(cityList);
                    break;
//                case ("execute_script"):
//                    if (Constants.checkScripts(argument)) {
//                        Execute_script.run(cityList, argument);
//                    } else
//                        System.out.println("Скрипт образовал бесконечный цикл, проверьте не ссылаются ли файлы сами на себя");
//                    Constants.checkScriptNull();
//                    break;
                case ("head"):
                    Info.head(cityList);
                    break;
                case ("remove_greater"):
                    Remove.remove_greater(cityList, argument);
                    break;
                case ("remove_lower"):
                    Remove.remove_lower(cityList, argument);
                    break;
                case ("count_greater_than_meters_above_sea_level"):
                    try {
                        Info.count_greater_than_meters_above_sea_level(cityList, Double.parseDouble(argument));
                    } catch (NumberFormatException e) {
                        System.out.println("Данные введены неправильно");
                    }
                    break;
                case ("filter_starts_with_name"):
                    if (Constants.nullChecker(argument)) {
                        Info.filter_starts_with_name(cityList, argument);
                    }
                    break;
                default:
                    System.out.println("Такой команды нет, для просмотра доступных команд введите \"help\"");
            }
        } catch (NullPointerException e) {
            System.out.println("Работа программы завершилась...");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Забыли передать файл");
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (TransformerException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.setOut(consoleStream);
        return outputStream.toString();
    }
}