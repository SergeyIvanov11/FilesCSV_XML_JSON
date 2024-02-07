package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //Задача 1: CSV - JSON парсер
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        writeString(json, "data.json");

        //Задача 2: XML - JSON парсер
        List<Employee> list2 = parseXML("data.xml");
        writeString(listToJson(list2), "data2.json");

        //Задача 3: JSON парсер (со звездочкой *)
        String json3 = readString("data.json");
        List<Employee> list3 = jsonToList(json3);
        System.out.println(list3);
    }

    public static List parseCSV(String[] columnMapping, String fileName) {
        List<Employee> staff = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();
            staff = csv.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return staff;
    }

    public static String listToJson(List list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    public static void writeString(String str, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(str);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List parseXML(String fileName) {
        List<Employee> staff = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));
            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    NamedNodeMap map = element.getAttributes();
                    Employee newEmp = new Employee(
                            Integer.parseInt(map.item(0).getNodeValue()),
                            map.item(1).getNodeValue(),
                            map.item(2).getNodeValue(),
                            map.item(3).getNodeValue(),
                            Integer.parseInt(map.item(4).getNodeValue()));
                    staff.add(newEmp);
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return staff;
    }

    public static String readString(String fileName) throws IOException {
        String str;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            str = bufferedReader.readLine();
        }
        return str;
    }

    public static List jsonToList(String str) {
        List<Employee> staff = new ArrayList<>();
        JSONParser parser = new JSONParser();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            JSONArray array = (JSONArray) parser.parse(str);
            for (int i = 0; i < array.size(); i++) {
                Employee newEmp = gson.fromJson(array.get(i).toString(), Employee.class);
                staff.add(newEmp);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return staff;
    }
}