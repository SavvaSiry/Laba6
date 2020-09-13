package com.company.Collection;

import com.company.Commands.Constants;
import com.company.Server.Server;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;

public class ParserXML {


    private static final Logger logger = Logger.getLogger(Server.class.getName());
    static LinkedList<City> parseXML(String nameOfFile) throws SAXException {

        LinkedList<City> cityList = new LinkedList<>();

        try {
            File file = new File(nameOfFile);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList cityNodeList = document.getElementsByTagName("City");



            for (int i = 0; i < cityNodeList.getLength(); i++) {
                if (cityNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element cityElement = (Element) cityNodeList.item(i);

                    City city = new City();


                    NodeList childNodeList = cityElement.getChildNodes();
                    for (int j = 0; j < childNodeList.getLength(); j++) {
                        if (childNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) childNodeList.item(j);
                            switch (childElement.getNodeName()) {
                                case "name": {
                                    city.setName(childElement.getTextContent());
                                }
                                break;
                                case "coordinates": {
                                    Coordinates coordinates = new Coordinates();
                                    NodeList childNodeList_2 = childElement.getChildNodes();
                                    for (int k = 0; k < childNodeList_2.getLength(); k++) {
                                        if (childNodeList_2.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                            Element coordinatesElement = (Element) childNodeList_2.item(k);
                                            if (coordinatesElement.getNodeName().equals("x"))
                                                try {
                                                    coordinates.setX(Float.parseFloat(coordinatesElement.getTextContent()));
                                                } catch (NumberFormatException e) {
                                                    Constants.breakComment("В поле координаты X встртилась строка");
                                                }
                                            if (coordinatesElement.getNodeName().equals("y"))
                                                try {
                                                    coordinates.setY(Integer.valueOf(coordinatesElement.getTextContent()));
                                                } catch (NumberFormatException e) {
                                                    Constants.breakComment("В поле Y встретилась строка, или формат числа не поддерживается");
                                                }
                                        }
                                        city.setCoordinates(coordinates);
                                    }
                                }
                                break;
                                case "area": {
                                    try {
                                        city.setArea(Long.parseLong(childElement.getTextContent()));
                                    } catch (NumberFormatException e) {
                                        Constants.breakComment("Поле Area не поддерживает переданный формат");
                                    }
                                }
                                break;
                                case "population": {
                                    try {
                                        city.setPopulation(Long.parseLong(childElement.getTextContent()));
                                    } catch (NumberFormatException e) {
                                        Constants.breakComment("Поле population не пооддерживает переданный формат");
                                    }
                                }
                                break;
                                case "metersAboveSeaLevel": {
                                    try {
                                        city.setMetersAboveSeaLevel(Double.parseDouble(childElement.getTextContent()));
                                    } catch (NumberFormatException e) {
                                        Constants.breakComment("Поле Метры над уровнем моря не поодерживает переданный формат");
                                    }
                                }
                                break;
                                case "capital": {
                                    if (childElement.getTextContent().equals("false") || childElement.getTextContent().equals("true")) {
                                        city.setCapital(Boolean.parseBoolean(childElement.getTextContent()));
                                    } else Constants.breakComment("Поле capital не поддерживает переданный формат данных ");
                                }
                                break;
                                case "agglomeration": {
                                    try {
                                        city.setAgglomeration(Long.parseLong(childElement.getTextContent()));
                                    } catch (NumberFormatException e) {
                                        Constants.breakComment("Поле aglomiration не поддерживает переданный формат данных");
                                    }
                                }
                                break;
                                case "climate": {
                                    if (childElement.getTextContent().equals("HUMIDSUBTROPICAL") || childElement.getTextContent().equals("STEPPE") || childElement.getTextContent().equals("TUNDRA")) {
                                        city.setClimate(Climate.valueOf(childElement.getTextContent()));
                                    } else Constants.breakComment("Ошибка контекста в поле climate");
                                }
                                break;
                                case "governor": {
                                    Human human = new Human();
                                    human.setName((childElement.getTextContent()));
                                    city.setGovernor(human);
                                }
                            }
                        }
                    }
                    cityList.add(city);
                }
            }

            logger.info("File was read correctly");

        } catch (ParserConfigurationException | SAXParseException  | DOMException e) {
            Constants.breakComment("проверьте содержание и корректность указанного файла");
        } catch (IllegalArgumentException | IOException ex){
            System.out.println("Нет доступа к файлу, или нет указанного файла");
        }
        Collections.sort(cityList);
        return cityList;
    }
}