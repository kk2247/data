package com.kyka.data.service;

import com.kyka.data.entity.Line;
import com.kyka.data.entity.ScenicSpot;

import java.util.ArrayList;
import java.util.List;

public interface GraphService {
    void createGraph();
    int searchForName(String name);
    ArrayList<ArrayList<Object>> outPutGraph();
    ArrayList<Line> showShortestWay(String start, String end);
    List<ScenicSpot> getScenicSpotByPopulation();
    List<ScenicSpot> getScenicSpotByDescription(String description);
    ArrayList<ScenicSpot> getShortestWayFromGate();
}
