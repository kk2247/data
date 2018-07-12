package com.kyka.data.dao;

import com.kyka.data.entity.Line;

import java.util.List;


public interface LineDao {
    List<Line> getAllLine();
    Line getLineById(int id);
    int insertLine(Line line);
    int deleteLine(int id);
    int deleteLineByName(String name);
    int modifyLine(Line line);
}
