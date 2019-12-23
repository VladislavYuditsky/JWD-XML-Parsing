package com.yuditsky.xmlparsing.builder.sax;

import com.yuditsky.xmlparsing.builder.FlowersBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class FlowersSAXBuilder extends FlowersBuilder {
    private final static Logger logger = LogManager.getLogger(FlowersSAXBuilder.class);
    private FlowerHandler flowerHandler;
    private XMLReader reader;

    public FlowersSAXBuilder() {
        flowerHandler = new FlowerHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(flowerHandler);
        } catch (SAXException e) {
            logger.error("SAX parser error " + e.getMessage(), e);
        }
    }

    @Override
    public void buildSetFlowers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.error("SAX parser error " + e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        flowers = flowerHandler.getFlowers();
    }
}
