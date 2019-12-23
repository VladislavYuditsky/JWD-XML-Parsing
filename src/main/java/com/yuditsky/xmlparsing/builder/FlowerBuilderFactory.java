package com.yuditsky.xmlparsing.builder;

import com.yuditsky.xmlparsing.builder.dom.FlowersDOMBuilder;
import com.yuditsky.xmlparsing.builder.sax.FlowersSAXBuilder;
import com.yuditsky.xmlparsing.builder.stax.FlowersStAXBuilder;

public class FlowerBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public FlowersBuilder createFlowersBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new FlowersDOMBuilder();
            case STAX:
                return new FlowersStAXBuilder();
            case SAX:
                return new FlowersSAXBuilder();
            default:
                throw new EnumConstantNotPresentException (type.getDeclaringClass(), type.name());
        }
    }
}