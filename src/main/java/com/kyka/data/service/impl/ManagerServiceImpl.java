package com.kyka.data.service.impl;


import com.kyka.data.dao.LineDao;
import com.kyka.data.dao.ScenicSpotDao;
import com.kyka.data.entity.Line;
import com.kyka.data.entity.ScenicSpot;
import com.kyka.data.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ScenicSpotDao scenicSpotDao;

    @Autowired
    private LineDao lineDao;

    public ScenicSpotDao getScenicSpotDao() {
        return scenicSpotDao;
    }

    public void setScenicSpotDao(ScenicSpotDao scenicSpotDao) {
        this.scenicSpotDao = scenicSpotDao;
    }

    public LineDao getLineDao() {
        return lineDao;
    }

    public void setLineDao(LineDao lineDao) {
        this.lineDao = lineDao;
    }

    /**
     * 路的插入
     * @param line
     */
    @Override
    public boolean insertLine(Line line) {
        int eff=lineDao.insertLine(line);
        if(eff==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteScenicSpot(int id) {
        int eff=scenicSpotDao.deleteScenicSpot(id);
        if(eff==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteLine(int id) {
        int eff=lineDao.deleteLine(id);
        if(eff==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean insertScenicSpot(ScenicSpot scenicSpot) {
        int eff=scenicSpotDao.insertScenicSpot(scenicSpot);
        if(eff==1){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public boolean modifyScenicSpot(ScenicSpot scenicSpot) {
        int eff=scenicSpotDao.modifyScenicSpot(scenicSpot);
        if(eff==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean modifyLine(Line line) {
        int eff=lineDao.modifyLine(line);
        if(eff==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ScenicSpot getScenicSpotById(int id) {
        return scenicSpotDao.getScenicSpotById(id);
    }

    @Override
    public Line getLineById(int id) {
        return lineDao.getLineById(id);
    }

    @Override
    public List<ScenicSpot> getScenicSpots() {
        return scenicSpotDao.getAllScenicSpot();
    }

    @Override
    public List<Line> getLines() {
        return lineDao.getAllLine();
    }

    @Override
    public void publish(){

    }

}
