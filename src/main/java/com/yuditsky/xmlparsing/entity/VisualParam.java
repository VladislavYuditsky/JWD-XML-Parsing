package com.yuditsky.xmlparsing.entity;

import java.util.Objects;

public class VisualParam {
    private String stemColor;
    private String leafColor;
    private long averageSize;

    public VisualParam() {
    }

    public VisualParam(String stemColor, String leafColor, long averageSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.averageSize = averageSize;
    }

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public long getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(long averageSize) {
        this.averageSize = averageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParam that = (VisualParam) o;
        return averageSize == that.averageSize &&
                stemColor.equals(that.stemColor) &&
                leafColor.equals(that.leafColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stemColor, leafColor, averageSize);
    }
}
