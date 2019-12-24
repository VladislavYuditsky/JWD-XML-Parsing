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
    public void buildFlowers(String fileName) {
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
        Flower.Builder builder = new Flower.Builder();
        String param;

        param = reader.getAttributeValue(null, FlowerParam.ID.getValue());
        builder.withId(param);

        param = reader.getAttributeValue(null, FlowerParam.SPECIES.getValue());
        builder.withSpecies(param);

        param = reader.getAttributeValue(null, FlowerParam.FAMILY.getValue());
        if (param != null) {
            builder.withFamily(param);
        }

        param = reader.getAttributeValue(null, FlowerParam.CLASS.getValue());
        if (param != null) {
            builder.withClazz(param);
        }

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    param = reader.getLocalName();
                    switch (FlowerParam.valueOf(param.toUpperCase())) {
                        case SOIL:
                            builder.withSoil(getXMLText(reader));
                            break;
                        case ORIGIN:
                            builder.withOrigin(getXMLText(reader));
                            break;
                        case GROWING_TIPC:
                            builder.withGrowingTipc(getXMLGrowingTipc(reader));
                            break;
                        case VISUAL_PARAM:
                            builder.withVisualParam(getXMLVisualParam(reader));
                            break;
                        case MULTIPLYING:
                            builder.withMultiplying(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    param = reader.getLocalName();
                    if (FlowerParam.valueOf(param.toUpperCase()) == FlowerParam.FLOWER) {
                        return builder.build();
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Flower");
    }

    private GrowingTipc getXMLGrowingTipc(XMLStreamReader reader) throws XMLStreamException {
        GrowingTipc.Builder builder = new GrowingTipc.Builder();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerParam.valueOf(name.toUpperCase())) {
                        case TEMPERATURE:
                            builder.withTemperature(Integer.parseInt(getXMLText(reader)));
                            break;
                        case PHOTOPHILOUS:
                            builder.withPhotophilous(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case WATERING:
                            builder.withWatering(Long.parseLong(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerParam.valueOf(name.toUpperCase()) == FlowerParam.GROWING_TIPC) {
                        return builder.build();
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag GrowingTipc");
    }

    private VisualParam getXMLVisualParam(XMLStreamReader reader) throws XMLStreamException {
        VisualParam.Builder builder = new VisualParam.Builder();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerParam.valueOf(name.toUpperCase())) {
                        case STEM_COLOR:
                            builder.withStemColor(getXMLText(reader));
                            break;
                        case LEAF_COLOR:
                            builder.withLeafColor(getXMLText(reader));
                            break;
                        case AVERAGE_SIZE:
                            builder.withAverageSize(Long.parseLong(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerParam.valueOf(name.toUpperCase()) == FlowerParam.VISUAL_PARAM) {
                        return builder.build();
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
