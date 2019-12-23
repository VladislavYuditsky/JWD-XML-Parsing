package com.yuditsky.xmlparsing.builder;

public enum FlowerParam {
    FLOWERS("flowers"),
    FLOWER("flower"),
    ID("id"),
    SPECIES("species"),
    CLASS("class"),
    FAMILY("family"),
    GROWING_TIPC("growing_tipc"),
    VISUAL_PARAM("visual_param"),
    SOIL("soil"),
    ORIGIN("origin"),
    TEMPERATURE("temperature"),
    PHOTOPHILOUS("photophilous"),
    WATERING("watering"),
    STEM_COLOR("stem_color"),
    LEAF_COLOR("leaf_color"),
    AVERAGE_SIZE("average_size"),
    MULTIPLYING("multiplying");

    private String value;

    FlowerParam(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
