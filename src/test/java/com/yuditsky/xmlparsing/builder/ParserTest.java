package com.yuditsky.xmlparsing.builder;

import com.yuditsky.xmlparsing.entity.Flower;
import com.yuditsky.xmlparsing.entity.GrowingTipc;
import com.yuditsky.xmlparsing.entity.VisualParam;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    private static final String TEST_FILE_PATH = "data/flowers.xml";
    private FlowerBuilderFactory factory = new FlowerBuilderFactory();

    @Test
    public void DOMParserTest() {
        GrowingTipc growingTipc = new GrowingTipc(20, true, 500);
        VisualParam visualParam = new VisualParam("green", "green", 150);
        Flower expected = new Flower("ID-1", "Currant", "", "", "podzolic",
                "Eurasia", growingTipc, visualParam, "cutting");

        FlowersBuilder builder = factory.createFlowersBuilder("dom");
        builder.buildSetFlowers(TEST_FILE_PATH);
        List<Flower> flowers = builder.getFlowers();
        Flower actual = flowers.get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void SAXParserTest() {
        GrowingTipc growingTipc = new GrowingTipc(20, true, 500);
        VisualParam visualParam = new VisualParam("green", "green", 150);
        Flower expected = new Flower("ID-1", "Currant", "", "", "podzolic",
                "Eurasia", growingTipc, visualParam, "cutting");

        FlowersBuilder builder = factory.createFlowersBuilder("sax");
        builder.buildSetFlowers(TEST_FILE_PATH);
        List<Flower> flowers = builder.getFlowers();
        Flower actual = flowers.get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void StAXParserTest() {
        GrowingTipc growingTipc = new GrowingTipc(20, true, 500);
        VisualParam visualParam = new VisualParam("green", "green", 150);
        Flower expected = new Flower("ID-1", "Currant", "", "", "podzolic",
                "Eurasia", growingTipc, visualParam, "cutting");

        FlowersBuilder builder = factory.createFlowersBuilder("StAX");
        builder.buildSetFlowers(TEST_FILE_PATH);
        List<Flower> flowers = builder.getFlowers();
        Flower actual = flowers.get(0);
        assertEquals(expected, actual);
    }
}
