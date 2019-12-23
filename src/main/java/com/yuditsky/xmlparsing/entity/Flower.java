package com.yuditsky.xmlparsing.entity;

import java.util.Objects;

public class Flower {
    private String id;
    private String species;
    private String clazz;
    private String family;
    private String soil;
    private String origin;
    private GrowingTipc growingTipc;
    private VisualParam visualParam;
    private String multiplying;

    public Flower() {
        growingTipc = new GrowingTipc();
        visualParam = new VisualParam();
    }

    public Flower(String id, String species, String clazz, String family, String soil,
                  String origin, GrowingTipc growingTipc, VisualParam visualParam, String multiplying) {
        this.id = id;
        this.species = species;
        this.clazz = clazz;
        this.family = family;
        this.soil = soil;
        this.origin = origin;
        this.growingTipc = growingTipc;
        this.visualParam = visualParam;
        this.multiplying = multiplying;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public GrowingTipc getGrowingTipc() {
        return growingTipc;
    }

    public void setGrowingTipc(GrowingTipc growingTipc) {
        this.growingTipc = growingTipc;
    }

    public VisualParam getVisualParam() {
        return visualParam;
    }

    public void setVisualParam(VisualParam visualParam) {
        this.visualParam = visualParam;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return id.equals(flower.id) &&
                species.equals(flower.species) &&
                clazz.equals(flower.clazz) &&
                family.equals(flower.family) &&
                soil.equals(flower.soil) &&
                origin.equals(flower.origin) &&
                growingTipc.equals(flower.growingTipc) &&
                visualParam.equals(flower.visualParam) &&
                multiplying.equals(flower.multiplying);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, species, clazz, family, soil, origin, growingTipc, visualParam, multiplying);
    }
}
