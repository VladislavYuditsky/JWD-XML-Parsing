package com.yuditsky.xmlparsing.entity;

import java.util.Objects;

public class VisualParam {
    private final String stemColor;
    private final String leafColor;
    private final long averageSize;

    public static class Builder {
        private String stemColor;
        private String leafColor;
        private long averageSize;

        public Builder() {
        }

        public Builder(String stemColor, String leafColor, long averageSize) {
            this.stemColor = stemColor;
            this.leafColor = leafColor;
            this.averageSize = averageSize;
        }

        public void withStemColor(String stemColor) {
            this.stemColor = stemColor;
        }

        public void withLeafColor(String leafColor) {
            this.leafColor = leafColor;
        }

        public void withAverageSize(long averageSize) {
            this.averageSize = averageSize;
        }

        public VisualParam build() {
            return new VisualParam(this);
        }
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

    private VisualParam(Builder builder) {
        stemColor = builder.stemColor;
        leafColor = builder.leafColor;
        averageSize = builder.averageSize;
    }
}
