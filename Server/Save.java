package com.company.Commands;

import com.company.Collection.City;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class Save {
    public static void save(List<City> list, String nameOfFile) throws ParserConfigurationException, TransformerException {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element listCity = document.createElement("ListCity");
            document.appendChild(listCity);


            for (City city : list) {

                Element City = document.createElement("City");
                Element name = document.createElement("name");
                Element coordinates = document.createElement("coordinates");
                Element area = document.createElement("area");
                Element population = document.createElement("population");
                Element metersAboveSeaLevel = document.createElement("metersAboveSeaLevel");
                Element capital = document.createElement("capital");
                Element agglomeration = document.createElement("agglomeration");
                Element climate = document.createElement("climate");
                Element governor = document.createElement("governor");
                Element x = document.createElement("x");
                Element y = document.createElement("y");

                Text name1 = document.createTextNode(city.getName());
                Text x1 = document.createTextNode(Float.toString(city.getCoordinates().getX()));
                Text y1 = document.createTextNode(Integer.toString(city.getCoordinates().getY()));
                Text area1 = document.createTextNode(Long.toString(city.getArea()));
                Text population1 = document.createTextNode(Long.toString(city.getPopulation()));
                Text metersAboveSeaLevel1 = document.createTextNode(Double.toString(city.getMetersAboveSeaLevel()));
                Text capital1 = document.createTextNode(Boolean.toString(city.getCapital()));
                Text agglomeration1 = document.createTextNode(Long.toString(city.getAgglomeration()));
                Text climate1 = document.createTextNode(city.getClimate().toString());
                Text governor1 = document.createTextNode(city.getGovernor().getName());


                listCity.appendChild(City);
                City.appendChild(name);
                City.appendChild(coordinates);
                City.appendChild(area);
                City.appendChild(population);
                City.appendChild(metersAboveSeaLevel);
                City.appendChild(capital);
                City.appendChild(agglomeration);
                City.appendChild(climate);
                City.appendChild(governor);
                coordinates.appendChild(x);
                coordinates.appendChild(y);

                name.appendChild(name1);
                x.appendChild(x1);
                y.appendChild(y1);
                area.appendChild(area1);
                population.appendChild(population1);
                metersAboveSeaLevel.appendChild(metersAboveSeaLevel1);
                capital.appendChild(capital1);
                agglomeration.appendChild(agglomeration1);
                climate.appendChild(climate1);
                governor.appendChild(governor1);

            }

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            FileOutputStream outputStream = new FileOutputStream(nameOfFile);
            t.transform(new DOMSource(document), new StreamResult(outputStream));
            System.out.println("exit");
        }  catch (FileNotFoundException e) {
            System.out.println("Нет доступа к файлу");
        }
    }
}