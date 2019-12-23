package com.yuditsky.xmlparsing.builder;

import com.yuditsky.xmlparsing.entity.Flower;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class FlowersBuilder {
    protected List<Flower> flowers;

    public FlowersBuilder() {
        flowers = new ArrayList<Flower>();
    }

    public FlowersBuilder(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    abstract public void buildSetFlowers(String fileName);
}
