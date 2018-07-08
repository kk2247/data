package com.kyka.data.service;

import com.kyka.data.entity.Line;
import com.kyka.data.entity.ScenicSpot;

import java.util.List;

public interface ManagerService {
    boolean deleteScenicSpot(int id);
    boolean deleteLine(int id);
    boolean insertScenicSpot(ScenicSpot scenicSpot);
    boolean insertLine(Line line);
    boolean modifyScenicSpot(ScenicSpot scenicSpot);
    boolean modifyLine(Line line);
    ScenicSpot getScenicSpotById(int id);
    Line getLineById(int id);
    List<ScenicSpot> getScenicSpots();
    List<Line> getLines();
    void publish();
}
