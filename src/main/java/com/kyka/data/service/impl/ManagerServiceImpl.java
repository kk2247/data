package com.kyka.data.service.impl;


import com.kyka.data.dao.LineDao;
import com.kyka.data.dao.ManagerDao;
import com.kyka.data.dao.ScenicSpotDao;
import com.kyka.data.entity.Line;
import com.kyka.data.entity.Manager;
import com.kyka.data.entity.ScenicSpot;
import com.kyka.data.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao;

    private ScenicSpotDao scenicSpotDao;

    private LineDao lineDao;

    private ArrayList<ArrayList<Object>> graph;

    @Autowired
    public ManagerServiceImpl(LineDao lineDao,ScenicSpotDao scenicSpotDao,ManagerDao managerDao) {
        this.lineDao=lineDao;
        this.scenicSpotDao=scenicSpotDao;
        this.managerDao=managerDao;
        graph=new ArrayList<ArrayList<Object>>();
        List<ScenicSpot> scenicSpots=scenicSpotDao.getAllScenicSpot();
        List<Line> lines=lineDao.getAllLine();
        ArrayList<Object> list=new ArrayList<>();
        list.add(new ScenicSpot(" "));
        graph.add(list);
        for(int i=1;i<=scenicSpots.size();i++){
            graph.get(0).add(scenicSpots.get(i-1));
            ArrayList<Object> list1=new ArrayList<>();
            list1.add(scenicSpots.get(i-1));
            graph.add(list1);
        }
        for(int i=1;i<graph.get(0).size();i++){
            for(int j=1;j<graph.get(0).size();j++){
                if(i==j){
                    graph.get(j).add(new Line(0));
                }else{
                    graph.get(j).add(new Line(32767));
                }
            }
        }
        for(int i=0;i<lines.size();i++) {
            int place1 = searchForName(lines.get(i).getSideName1());
            int place2 = searchForName(lines.get(i).getSideName2());
            int distance=lines.get(i).getLength();
            graph.get(place1).set(place2, lines.get(i));
            graph.get(place2).set(place1, lines.get(i));
        }
    }

    private int searchForName(String name) {
        ArrayList<Object> list=graph.get(0);
        for(int i=0;i<list.size();i++){
            if(list.get(i) instanceof ScenicSpot){
                if(((ScenicSpot) list.get(i)).getName().equals(name)){
                    return i;
                }
            }
        }
        return -1;
    }

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
        int index1=searchForName(line.getSideName1());
        int index2=searchForName(line.getSideName2());
        graph.get(index1).set(index2,line);
        graph.get(index2).set(index1,line);
        if(eff==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteScenicSpot(int id) {
        String name=scenicSpotDao.getScenicSpotById(id).getName();
        int index=searchForName(name);
        lineDao.deleteLineByName(name);
        int eff=scenicSpotDao.deleteScenicSpot(id);
        if(eff==1){
            graph.remove(index);
            for(int i=0;i<graph.size();i++){
                graph.get(i).remove(index);
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteLine(int id) {
        Line line=lineDao.getLineById(id);
        int eff=lineDao.deleteLine(id);
        int name1=searchForName(line.getSideName1());
        int name2=searchForName(line.getSideName2());
        Line newLine=new Line(32767);
        if(eff==1){
            graph.get(name1).set(name2,newLine);
            graph.get(name2).set(name1,newLine);
            return true;
        }else{
            return false;
        }
    }

    private void createGraph() {
        List<ScenicSpot> scenicSpots=scenicSpotDao.getAllScenicSpot();
        List<Line> lines=lineDao.getAllLine();
        ArrayList<Object> list=new ArrayList<>();
        graph=new ArrayList<ArrayList<Object>>();
        list.add(new ScenicSpot(" "));
        graph.add(list);
        for(int i=1;i<=scenicSpots.size();i++){
            graph.get(0).add(scenicSpots.get(i-1));
            ArrayList<Object> list1=new ArrayList<>();
            list1.add(scenicSpots.get(i-1));
            graph.add(list1);
        }
        for(int i=1;i<graph.get(0).size();i++){
            for(int j=1;j<graph.get(0).size();j++){
                if(i==j){
                    graph.get(j).add(new Line(0));
                }else{
                    graph.get(j).add(new Line(32767));
                }
            }
        }
        for(int i=0;i<lines.size();i++) {
            int place1 = searchForName(lines.get(i).getSideName1());
            int place2 = searchForName(lines.get(i).getSideName2());
            int distance=lines.get(i).getLength();
            graph.get(place1).set(place2, lines.get(i));
            graph.get(place2).set(place1, lines.get(i));
        }
    }

    @Override
    public boolean insertScenicSpot(ScenicSpot scenicSpot) {
        int eff=scenicSpotDao.insertScenicSpot(scenicSpot);
        if(eff==1){
            graph.get(0).add(scenicSpot);
            for(int i=1;i<graph.size();i++){
                graph.get(i).add(new Line(32767));
            }
            ArrayList<Object> add=new ArrayList<>();
            add.add(scenicSpot);
            for(int i=1;i<graph.get(0).size();i++){
                add.add(new Line(32767));
            }
            graph.add(add);
            createGraph();
            return true;
        }else{
            return false;
        }
    }


    @Override
    public boolean modifyScenicSpot(ScenicSpot scenicSpot) {
        int eff=scenicSpotDao.modifyScenicSpot(scenicSpot);
        int index=searchForName(scenicSpot.getName());
        if(eff==1){
            graph.get(0).set(index,scenicSpot);
            graph.get(index).set(0,scenicSpot);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean modifyLine(Line line) {
        int eff=lineDao.modifyLine(line);
        int index1=searchForName(line.getSideName1());
        int index2=searchForName(line.getSideName2());
        if(eff==1){
            graph.get(index1).set(index2,line);
            graph.get(index2).set(index1,line);
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

    @Override
    public Manager login(Manager manager) {
        Manager manager1=managerDao.login(manager);
        return manager1;
    }

    @Override
    public ArrayList<ArrayList<Object>> getGraph() {
        return graph;
    }


}
