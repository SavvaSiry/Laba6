package com.company.Collection;

import com.company.Commands.CollectionWorker;
import com.company.Server.Server;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) throws SAXException, IOException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            LinkedList<City> cityList = ParserXML.parseXML(args[0]);
            CollectionWorker collectionWorker = new CollectionWorker(cityList, args[0]);
            Server server = new Server(collectionWorker);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Забыли передать аргументы");
        } catch (SAXException e) {
            System.out.println("Файл XML поврежден");
        }
    }
}