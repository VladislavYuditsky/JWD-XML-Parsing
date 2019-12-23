package com.yuditsky.xmlparsing.builder.sax;

import com.yuditsky.xmlparsing.builder.FlowersBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class FlowersSAXBuilder extends FlowersBuilder {
    private FlowerHandler flowerHandler;
    private XMLReader reader;

    public FlowersSAXBuilder() {
        flowerHandler = new FlowerHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(flowerHandler);
        } catch (SAXException e) {
            //System.err.print("ошибка SAX парсера: " + e);
        }
    }

    @Override
    public void buildSetFlowers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            //System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            //System.err.print("ошибка I/О потока: " + e);
        }

        flowers = flowerHandler.getFlowers();
    }
}
