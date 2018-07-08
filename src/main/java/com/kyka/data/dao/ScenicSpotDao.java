package com.kyka.data.dao;

import com.kyka.data.entity.ScenicSpot;

import java.util.List;


public interface ScenicSpotDao {
    List<ScenicSpot> getAllScenicSpot();
    List<ScenicSpot> getScenicSpotByDescription(String description);
    ScenicSpot getScenicSpotByName(String name);
    ScenicSpot getScenicSpotById(int id);
    int insertScenicSpot(ScenicSpot scenicSpot);
    int deleteScenicSpot(int id);
    int modifyScenicSpot(ScenicSpot scenicSpot);
}
