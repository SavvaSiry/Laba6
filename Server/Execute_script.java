package com.company.Commands;

import com.company.Collection.City;
import org.w3c.dom.DOMException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Execute_script {

    public static void run(List<City> cityList, String nameOfFile) throws  TransformerException, ParserConfigurationException {

        try {

            File file = new File(nameOfFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);


            String stringInitial;
            String stringBuffer;

            while (reader.ready()){

            int k = 1;
            String argument = "";
            String stringForComparison = "";

            stringInitial = reader.readLine();
            if (stringInitial.equals("exit")) break;

            //отсечение у введеных строк всех символов до первого знака пробел
            stringBuffer = stringInitial;

            char[] stringbufferToArray = stringBuffer.toCharArray(); //разделение посимвольно строки на массив из 'элементов чар
            for (int i = 0; i < stringbufferToArray.length; i++){
                String valueOfchar = String.valueOf(stringbufferToArray[i]); //преобразование символа чар в строку
                if (valueOfchar.equals(" ")){ //сравнение на пробел, для отсечения ненужной части строки
                    break;
                }
                stringForComparison += valueOfchar; //посимвольно собираем строку пока не встретиться первый пробел
                k+=1;
            }
            for (int i = k; i < stringbufferToArray.length; i++){
                argument += String.valueOf(stringbufferToArray[i]);
            }

            switch (stringForComparison){ //передаем оператору swith нашу собранную строку на сравение с допустимыми командами
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
//                    Add.add(cityList);
                    break;
                case ("update_id"):
                    Remove.update(cityList);
                    break;
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
                case ("save"):
                    Save.save(cityList, nameOfFile);
                    break;
                case ("execute_script"):
                    if (Constants.checkScripts(argument)) {
                        Execute_script.run(cityList, argument);
                    } else System.out.println("Скрипт образовал бесконечный цикл, проверьте не ссылаются ли файлы сами на себя");
                    Constants.checkScriptNull();
                    break;
                case ("head"):
                    Info.head(cityList);
                    break;
                case ("remove_greater"):
//                    Remove.remove_greater(cityList);
                    break;
                case ("remove_lower"):
//                    Remove.remove_lower(cityList);
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
        }
        }  catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Забыли передать файл");
        } catch (IOException | DOMException | IllegalArgumentException e) {
            System.out.println("проверьте содержание и корректность указанного пути");
        }
    }
}