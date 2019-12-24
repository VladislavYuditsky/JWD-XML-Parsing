package com.yuditsky.xmlparsing.builder;

import com.yuditsky.xmlparsing.entity.Flower;

import java.util.ArrayList;
import java.util.List;

public abstract class FlowersBuilder {
    protected List<Flower> flowers;

    public FlowersBuilder() {
        flowers = new ArrayList<Flower>();
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    abstract public void buildFlowers(String fileName);
}
