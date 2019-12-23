package com.yuditsky.xmlparsing.builder.sax;

import com.yuditsky.xmlparsing.builder.FlowerParam;
import com.yuditsky.xmlparsing.entity.Flower;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class FlowerHandler extends DefaultHandler {
    private List<Flower> flowers;
    private Flower flower;
    private FlowerParam flowerParam;
    private EnumSet<FlowerParam> paramRange;

    public FlowerHandler() {
        flowers = new ArrayList<Flower>();
        paramRange = EnumSet.range(FlowerParam.SOIL, FlowerParam.MULTIPLYING);
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("flower".equals(localName)) {
            flower = new Flower();
            flower.setId(attrs.getValue(0));
            flower.setSpecies(attrs.getValue(1));

            String param;
            param = attrs.getValue("family");
            if(param!=null){
                flower.setFamily(param);
            } else {
                flower.setFamily("");
            }

            param = attrs.getValue("class");
            if(param!=null){
                flower.setClazz(param);
            } else {
                flower.setClazz("");
            }
        } else {
            FlowerParam temp = FlowerParam.valueOf(localName.toUpperCase());
            if(paramRange.contains(temp)) {
                flowerParam = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("flower".equals(localName)) {
            flowers.add(flower);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (flowerParam != null) {
            switch (flowerParam) {
                case SOIL:
                    flower.setSoil(s);
                    break;
                case ORIGIN:
                    flower.setOrigin(s);
                    break;
                case TEMPERATURE:
                    flower.getGrowingTipc().setTemperature(Integer.parseInt(s));
                    break;
                case PHOTOPHILOUS:
                    flower.getGrowingTipc().setPhotophilous(Boolean.parseBoolean(s));
                    break;
                case WATERING:
                    flower.getGrowingTipc().setWatering(Long.parseLong(s));
                    break;
                case STEM_COLOR:
                    flower.getVisualParam().setStemColor(s);
                    break;
                case LEAF_COLOR:
                    flower.getVisualParam().setLeafColor(s);
                    break;
                case AVERAGE_SIZE:
                    flower.getVisualParam().setAverageSize(Long.parseLong(s));
                    break;
                case MULTIPLYING:
                    flower.setMultiplying(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            flowerParam.getDeclaringClass(), flowerParam.name());
            }
        }
        flowerParam = null;
    }
}
