package com.yuditsky.xmlparsing.entity;

import java.util.Objects;

public class Flower {
    private final String id;
    private final String species;
    private final String clazz;
    private final String family;
    private final String soil;
    private final String origin;
    private final GrowingTipc growingTipc;
    private final VisualParam visualParam;
    private final String multiplying;

    public static class Builder {
        private String id;
        private String species;
        private String soil;
        private String origin;
        private GrowingTipc growingTipc;
        private VisualParam visualParam;
        private String multiplying;

        private String clazz = "";
        private String family = "";

        public Builder() {
        }

        public Builder(String id, String species, String clazz, String family, String soil, String origin,
                       GrowingTipc growingTipc, VisualParam visualParam, String multiplying) {
            this.id = id;
            this.species = species;
            this.soil = soil;
            this.origin = origin;
            this.growingTipc = growingTipc;
            this.visualParam = visualParam;
            this.multiplying = multiplying;
            this.clazz = clazz;
            this.family = family;
        }

        public Builder(String id, String species, String soil, String origin, GrowingTipc growingTipc,
                       VisualParam visualParam, String multiplying) {
            this.id = id;
            this.species = species;
            this.soil = soil;
            this.origin = origin;
            this.growingTipc = growingTipc;
            this.visualParam = visualParam;
            this.multiplying = multiplying;
        }

        public void withId(String id) {
            this.id = id;
        }

        public void withSpecies(String species) {
            this.species = species;
        }

        public void withSoil(String soil) {
            this.soil = soil;
        }

        public void withOrigin(String origin) {
            this.origin = origin;
        }

        public void withGrowingTipc(GrowingTipc growingTipc) {
            this.growingTipc = growingTipc;
        }

        public void withVisualParam(VisualParam visualParam) {
            this.visualParam = visualParam;
        }

        public void withMultiplying(String multiplying) {
            this.multiplying = multiplying;
        }

        public Builder withClazz(String clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder withFamily(String family) {
            this.family = family;
            return this;
        }

        public Flower build() {
            return new Flower(this);
        }
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

    private Flower(Builder builder) {
        id = builder.id;
        species = builder.species;
        clazz = builder.clazz;
        family = builder.family;
        soil = builder.soil;
        origin = builder.origin;
        growingTipc = builder.growingTipc;
        visualParam = builder.visualParam;
        multiplying = builder.multiplying;
    }
}
