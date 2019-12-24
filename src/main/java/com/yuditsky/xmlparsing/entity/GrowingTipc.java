package com.yuditsky.xmlparsing.entity;

import java.util.Objects;

public class GrowingTipc {
    private final int temperature;
    private final boolean photophilous;
    private final long watering;

    public static class Builder {
        private int temperature;
        private boolean photophilous;
        private long watering;

        public Builder() {
        }

        public Builder(int temperature, boolean photophilous, long watering) {
            this.temperature = temperature;
            this.photophilous = photophilous;
            this.watering = watering;
        }

        public void withTemperature(int temperature) {
            this.temperature = temperature;
        }

        public void withPhotophilous(boolean photophilous) {
            this.photophilous = photophilous;
        }

        public void withWatering(long watering) {
            this.watering = watering;
        }

        public GrowingTipc build() {
            return new GrowingTipc(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowingTipc that = (GrowingTipc) o;
        return temperature == that.temperature &&
                photophilous == that.photophilous &&
                watering == that.watering;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, photophilous, watering);
    }

    private GrowingTipc(Builder builder) {
        temperature = builder.temperature;
        photophilous = builder.photophilous;
        watering = builder.watering;
    }
}
