package com.yuditsky.xmlparsing.builder.stax;

import com.yuditsky.xmlparsing.builder.FlowerParam;
import com.yuditsky.xmlparsing.builder.FlowersBuilder;
import com.yuditsky.xmlparsing.entity.Flower;
import com.yuditsky.xmlparsing.entity.GrowingTipc;
import com.yuditsky.xmlparsing.entity.VisualParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FlowersStAXBuilder extends FlowersBuilder {
    private final static Logger logger = LogManager.getLogger(FlowersStAXBuilder.class);
    private XMLInputFactory inputFactory;

    public FlowersStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetFlowers(String fileName) {
        XMLStreamReader reader;
        String name;

        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (FlowerParam.valueOf(name.toUpperCase()) == FlowerParam.FLOWER) {
                        Flower flower = buildFlower(reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("StAX parsing error! " + e.getMessage(), e);
        } catch (FileNotFoundException e) {
            logger.error("File " + fileName + " not found! " + e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private Flower buildFlower(XMLStreamReader reader) throws XMLStreamException {
        Flower flower = new Flower();
        String param;

        param = reader.getAttributeValue(null, FlowerParam.ID.getValue());
        flower.setId(param);

        param = reader.getAttributeValue(null, FlowerParam.SPECIES.getValue());
        flower.setSpecies(param);

        param = reader.getAttributeValue(null, FlowerParam.FAMILY.getValue());
        if (param != null) {
            flower.setFamily(param);
        } else {
            flower.setFamily("");
        }

        param = reader.getAttributeValue(null, FlowerParam.CLASS.getValue());
        if (param != null) {
            flower.setClazz(param);
        } else {
            flower.setClazz("");
        }

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    param = reader.getLocalName();
                    switch (FlowerParam.valueOf(param.toUpperCase())) {
                        case SOIL:
                            flower.setSoil(getXMLText(reader));
                            break;
                        case ORIGIN:
                            flower.setOrigin(getXMLText(reader));
                            break;
                        case GROWING_TIPC:
                            flower.setGrowingTipc(getXMLGrowingTipc(reader));
                            break;
                        case VISUAL_PARAM:
                            flower.setVisualParam(getXMLVisualParam(reader));
                            break;
                        case MULTIPLYING:
                            flower.setMultiplying(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    param = reader.getLocalName();
                    if (FlowerParam.valueOf(param.toUpperCase()) == FlowerParam.FLOWER) {
                        return flower;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Flower");
    }

    private GrowingTipc getXMLGrowingTipc(XMLStreamReader reader) throws XMLStreamException {
        GrowingTipc growingTipc = new GrowingTipc();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerParam.valueOf(name.toUpperCase())) {
                        case TEMPERATURE:
                            growingTipc.setTemperature(Integer.parseInt(getXMLText(reader)));
                            break;
                        case PHOTOPHILOUS:
                            growingTipc.setPhotophilous(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case WATERING:
                            growingTipc.setWatering(Long.parseLong(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerParam.valueOf(name.toUpperCase()) == FlowerParam.GROWING_TIPC) {
                        return growingTipc;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag GrowingTipc");
    }

    private VisualParam getXMLVisualParam(XMLStreamReader reader) throws XMLStreamException {
        VisualParam visualParam = new VisualParam();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerParam.valueOf(name.toUpperCase())) {
                        case STEM_COLOR:
                            visualParam.setStemColor(getXMLText(reader));
                            break;
                        case LEAF_COLOR:
                            visualParam.setLeafColor(getXMLText(reader));
                            break;
                        case AVERAGE_SIZE:
                            visualParam.setAverageSize(Long.parseLong(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerParam.valueOf(name.toUpperCase()) == FlowerParam.VISUAL_PARAM) {
                        return visualParam;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag VisualParam");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
