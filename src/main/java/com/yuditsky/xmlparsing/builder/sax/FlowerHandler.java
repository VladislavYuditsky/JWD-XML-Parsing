package com.yuditsky.xmlparsing.builder.sax;

import com.yuditsky.xmlparsing.builder.FlowerParam;
import com.yuditsky.xmlparsing.entity.Flower;
import com.yuditsky.xmlparsing.entity.GrowingTipc;
import com.yuditsky.xmlparsing.entity.VisualParam;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class FlowerHandler extends DefaultHandler {
    private List<Flower> flowers;
    private FlowerParam flowerParam;
    private EnumSet<FlowerParam> paramRange;
    private Flower.Builder builder;
    private GrowingTipc.Builder growingTipcBuilder;
    private VisualParam.Builder visualParamBuilder;

    public FlowerHandler() {
        flowers = new ArrayList<Flower>();
        paramRange = EnumSet.range(FlowerParam.SOIL, FlowerParam.MULTIPLYING);
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (FlowerParam.FLOWER.getValue().equals(localName)) {
            builder = new Flower.Builder();
            growingTipcBuilder = new GrowingTipc.Builder();
            visualParamBuilder = new VisualParam.Builder();

            builder.withId(attrs.getValue(0));
            builder.withSpecies(attrs.getValue(1));

            String param;
            param = attrs.getValue(FlowerParam.FAMILY.getValue());
            if (param != null) {
                builder.withFamily(param);
            }

            param = attrs.getValue(FlowerParam.CLASS.getValue());
            if (param != null) {
                builder.withClazz(param);
            }
        } else {
            FlowerParam temp = FlowerParam.valueOf(localName.toUpperCase());
            if (paramRange.contains(temp)) {
                flowerParam = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (FlowerParam.FLOWER.getValue().equals(localName)) {
            GrowingTipc growingTipc = growingTipcBuilder.build();
            VisualParam visualParam = visualParamBuilder.build();
            builder.withVisualParam(visualParam);
            builder.withGrowingTipc(growingTipc);
            Flower flower = builder.build();

            flowers.add(flower);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (flowerParam != null) {
            switch (flowerParam) {
                case SOIL:
                    builder.withSoil(s);
                    break;
                case ORIGIN:
                    builder.withOrigin(s);
                    break;
                case TEMPERATURE:
                    growingTipcBuilder.withTemperature(Integer.parseInt(s));
                    break;
                case PHOTOPHILOUS:
                    growingTipcBuilder.withPhotophilous(Boolean.parseBoolean(s));
                    break;
                case WATERING:
                    growingTipcBuilder.withWatering(Long.parseLong(s));
                    break;
                case STEM_COLOR:
                    visualParamBuilder.withStemColor(s);
                    break;
                case LEAF_COLOR:
                    visualParamBuilder.withLeafColor(s);
                    break;
                case AVERAGE_SIZE:
                    visualParamBuilder.withAverageSize(Long.parseLong(s));
                    break;
                case MULTIPLYING:
                    builder.withMultiplying(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            flowerParam.getDeclaringClass(), flowerParam.name());
            }
        }
        flowerParam = null;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }
}
