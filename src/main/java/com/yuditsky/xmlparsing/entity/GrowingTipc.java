package com.yuditsky.xmlparsing.entity;

import java.util.Objects;

public class GrowingTipc {
    private int temperature;
    private boolean photophilous;
    private long watering;

    public GrowingTipc() {
    }

    public GrowingTipc(int temperature, boolean photophilous, long watering) {
        this.temperature = temperature;
        this.photophilous = photophilous;
        this.watering = watering;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isPhotophilous() {
        return photophilous;
    }

    public void setPhotophilous(boolean photophilous) {
        this.photophilous = photophilous;
    }

    public long getWatering() {
        return watering;
    }

    public void setWatering(long watering) {
        this.watering = watering;
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
}
