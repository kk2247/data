package com.kyka.data.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraphServiceImplTest {

    @Autowired
    private GraphServiceImpl graphService;

    @Test
    public void createGraph() {
    }

    @Test
    public void searchForName() {
    }

    @Test
    public void outPutGraph() {
        graphService.createGraph();
        graphService.outPutGraph();
    }

    @Test
    public void showShortestWay() {
        graphService.createGraph();
//        graphService.outPutGraph();
//        graphService.showShortestWay("北门","一线天");
        graphService.getShortestWayFromGate();
    }

    @Test
    public void getScenicSpotByPopulation() {
    }

    @Test
    public void getScenicSpotByDescription() {
    }
}