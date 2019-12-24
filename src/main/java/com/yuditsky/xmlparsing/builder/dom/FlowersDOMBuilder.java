package com.yuditsky.xmlparsing.builder.dom;

import com.yuditsky.xmlparsing.builder.FlowerParam;
import com.yuditsky.xmlparsing.builder.FlowersBuilder;
import com.yuditsky.xmlparsing.entity.Flower;
import com.yuditsky.xmlparsing.entity.GrowingTipc;
import com.yuditsky.xmlparsing.entity.VisualParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class FlowersDOMBuilder extends FlowersBuilder {
    private final static Logger logger = LogManager.getLogger(FlowersDOMBuilder.class);
    private DocumentBuilder documentBuilder;

    public FlowersDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void buildFlowers(String fileName) {
        Document document;
        try {
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList flowersList = root.getElementsByTagName(FlowerParam.FLOWER.getValue());
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                Flower flower = buildFlower(flowerElement);
                flowers.add(flower);
            }
        } catch (IOException | SAXException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private Flower buildFlower(Element flowerElement) {
        String id = flowerElement.getAttribute(FlowerParam.ID.getValue());
        String species = flowerElement.getAttribute(FlowerParam.SPECIES.getValue());
        String clazz = flowerElement.getAttribute(FlowerParam.CLASS.getValue());
        String family = flowerElement.getAttribute(FlowerParam.FAMILY.getValue());
        String soil = getElementTextContent(flowerElement, FlowerParam.SOIL.getValue());
        String origin = getElementTextContent(flowerElement, FlowerParam.ORIGIN.getValue());

        Element growingTipcElement = (Element) flowerElement.getElementsByTagName(
                "growing_tipc").item(0);
        int temperature = Integer.parseInt(getElementTextContent(growingTipcElement, FlowerParam.TEMPERATURE.getValue()));
        boolean photophilous = Boolean.parseBoolean(
                getElementTextContent(growingTipcElement, FlowerParam.PHOTOPHILOUS.getValue()));
        long watering = Long.parseLong(getElementTextContent(growingTipcElement, FlowerParam.WATERING.getValue()));

        Element visualParamElement = (Element) flowerElement.getElementsByTagName(
                "visual_param").item(0);
        String stemColor = getElementTextContent(visualParamElement, FlowerParam.STEM_COLOR.getValue());
        String leafColor = getElementTextContent(visualParamElement, FlowerParam.LEAF_COLOR.getValue());
        long averageSize = Long.parseLong(getElementTextContent(visualParamElement, FlowerParam.AVERAGE_SIZE.getValue()));

        String multiplying = getElementTextContent(flowerElement, FlowerParam.MULTIPLYING.getValue());

        GrowingTipc growingTipc = new GrowingTipc.Builder(temperature, photophilous, watering).build();
        VisualParam visualParam = new VisualParam.Builder(stemColor, leafColor, averageSize).build();
        return new Flower.Builder(id, species, soil, origin, growingTipc, visualParam, multiplying)
                .withClazz(clazz)
                .withFamily(family)
                .build();

    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}