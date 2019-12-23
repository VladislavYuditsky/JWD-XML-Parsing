package com.yuditsky.xmlparsing.builder.dom;

import com.yuditsky.xmlparsing.builder.FlowersBuilder;
import com.yuditsky.xmlparsing.entity.Flower;
import com.yuditsky.xmlparsing.entity.GrowingTipc;
import com.yuditsky.xmlparsing.entity.VisualParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

public class FlowersDOMBuilder extends FlowersBuilder {
    private DocumentBuilder documentBuilder;

    public FlowersDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            //System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    @Override
    public void buildSetFlowers(String fileName) {
        Document document;
        try {
            // parsing XML-документа и создание древовидной структуры
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            // получение списка дочерних элементов <student>
            NodeList flowersList = root.getElementsByTagName("flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                Flower flower = buildFlower(flowerElement);
                flowers.add(flower);
            }
        } catch (IOException e) {
            //System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            //System.err.println("Parsing failure: " + e);
        }
    }

    private Flower buildFlower(Element flowerElement) {
        Flower flower = new Flower();
        String param;

        param = flowerElement.getAttribute("id");
        flower.setId(param);

        param = flowerElement.getAttribute("species");
        flower.setSpecies(param);

        param = flowerElement.getAttribute("class");
        flower.setClazz(param);

        param = flowerElement.getAttribute("family");
        flower.setFamily(param);

        param = getElementTextContent(flowerElement, "soil");
        flower.setSoil(param);

        param = getElementTextContent(flowerElement, "origin");
        flower.setOrigin(param);

        GrowingTipc growingTipc = new GrowingTipc();
        Element growingTipcElement = (Element) flowerElement.getElementsByTagName(
                "growing_tipc").item(0);

        param = getElementTextContent(growingTipcElement, "temperature");
        growingTipc.setTemperature(Integer.parseInt(param));

        param = getElementTextContent(growingTipcElement, "photophilous");
        growingTipc.setPhotophilous(Boolean.parseBoolean(param));

        param = getElementTextContent(growingTipcElement, "watering");
        growingTipc.setWatering(Long.parseLong(param));

        VisualParam visualParam = new VisualParam();
        Element visualParamElement = (Element) flowerElement.getElementsByTagName(
                "visual_param").item(0);

        param = getElementTextContent(visualParamElement, "stem_color");
        visualParam.setStemColor(param);

        param = getElementTextContent(visualParamElement, "leaf_color");
        visualParam.setLeafColor(param);

        param = getElementTextContent(visualParamElement, "average_size");
        visualParam.setAverageSize(Long.parseLong(param));

        param = getElementTextContent(flowerElement, "multiplying");
        flower.setMultiplying(param);

        flower.setGrowingTipc(growingTipc);
        flower.setVisualParam(visualParam);

        return flower;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}