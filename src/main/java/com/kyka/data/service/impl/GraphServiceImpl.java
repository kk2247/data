package com.kyka.data.service.impl;

import com.kyka.data.dao.LineDao;
import com.kyka.data.dao.ScenicSpotDao;
import com.kyka.data.entity.Line;
import com.kyka.data.entity.ScenicSpot;
import com.kyka.data.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphServiceImpl implements GraphService {

    private LineDao lineDao;

    private ScenicSpotDao scenicSpotDao;

    private ArrayList<ArrayList<Object>> graph;

    @Autowired
    public GraphServiceImpl(LineDao lineDao,ScenicSpotDao scenicSpotDao){
        this.lineDao=lineDao;
        this.scenicSpotDao=scenicSpotDao;
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

    @Override
    public void createGraph() {
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
    public int searchForName(String name) {
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

    /**
     * 将链表中的内容输出
     */
    @Override
    public ArrayList<ArrayList<Object>> outPutGraph(){
        return graph;
    }

    /**
     * 迪杰斯特拉算法
     * @param start
     * @param end
     * @return
     */
    @Override
    public ArrayList<Line> showShortestWay(String start, String end) {
        createGraph();
        ArrayList<ArrayList<Object>>map= (ArrayList<ArrayList<Object>>) graph.clone();
        ArrayList<Line> lines=new ArrayList<>();
        int startIndex=searchForName(start);
        while(true){
            int endIndex=searchForShortest(map.get(startIndex));
            lines.add((Line)map.get(startIndex).get(endIndex));
            int length=((Line)map.get(startIndex).get(endIndex)).getLength();
            System.out.println(((ScenicSpot)map.get(0).get(endIndex)).getName());
            if(((ScenicSpot)map.get(0).get(endIndex)).getName().equals(end)){
                break;
            }else {
                for(int i=1;i<map.get(0).size();i++){
                    if(((Line)map.get(startIndex).get(i)).getLength()!=0&&((Line)map.get(startIndex).get(i)).isShortest()==false){
                        if(((Line)map.get(startIndex).get(i)).getLength()>(((Line)map.get(endIndex).get(i)).getLength()+length)){
                            Line line=new Line(((Line)map.get(endIndex).get(i)).getLength()+length);
                            line.setSideName1(((ScenicSpot)map.get(0).get(startIndex)).getName());
                            line.setSideName2(((ScenicSpot)map.get(0).get(i)).getName());
                            map.get(startIndex).set(i,line);
                        }
                    }
                }
            }
        }
        return lines;
    }

    /**
     * 寻找一行数据中最小的
     * @param list
     * @return
     */
    private int searchForShortest(ArrayList<Object> list){
        int index=1;
        if(list.get(index) instanceof Line) {
            while(((Line) list.get(index)).getLength()==0||((Line) list.get(index)).isShortest()){
                index++;
            }
        }
        for(int i=1;i<list.size();i++){
            if(list.get(i) instanceof Line&&list.get(index) instanceof Line) {
                if(((Line) list.get(i)).isShortest()==false&&((Line) list.get(i)).getLength()!=0){
                    if(((Line) list.get(i)).getLength()<((Line)list.get(index)).getLength()){
                        index=i;
                    }
                }
            }
        }
        ((Line)list.get(index)).setShortest(true);
        return index;
    }

    @Override
    public List<ScenicSpot> getScenicSpotByPopulation() {
        return scenicSpotDao.getAllScenicSpot();
    }

    @Override
    public List<ScenicSpot> getScenicSpotByDescription(String description) {
        return scenicSpotDao.getScenicSpotByDescription(description);
    }

    /**
     * 找到导游最短路径的点
     * @return
     */
    @Override
    public ArrayList<ScenicSpot> getShortestWayFromGate(){
        createGraph();
        if(graph.size()==0){
            return null;
        }
        ArrayList<ArrayList<Object>>map= (ArrayList<ArrayList<Object>>) graph.clone();
        ArrayList<ScenicSpot> lines=new ArrayList<>();
        BinaryTree binaryTree=new BinaryTree();
        binaryTree.setRoot(((ScenicSpot) map.get(0).get(1)).getName());
        ArrayList<ScenicSpot>scenicSpots=new ArrayList<>();
        int startIndex=searchForName("北门");
        scenicSpots.add((ScenicSpot) map.get(0).get(startIndex));
        while(true){
            if(scenicSpots.size()==(map.get(0).size()-1)){
                break;
            }
            int endIndex=searchForShortest(map.get(startIndex));
            scenicSpots.add((ScenicSpot) map.get(0).get(endIndex));
            //将线中的端点放入二叉树中
//            System.out.println(((Line)map.get(startIndex).get(endIndex)).getSideName1()+"+"+((Line)map.get(startIndex).get(endIndex)).getSideName2());
            BinaryTree name1=searchNode(binaryTree,((Line)map.get(startIndex).get(endIndex)).getSideName1());
            BinaryTree name2=searchNode(binaryTree,((Line)map.get(startIndex).get(endIndex)).getSideName2());
            if(name1!=null){
                BinaryTree binaryTree2=new BinaryTree();
                binaryTree2.setRoot(((Line)map.get(startIndex).get(endIndex)).getSideName2());
                if(name1.getLeft()==null){
                    name1.setLeft(binaryTree2);
                }else{
                    name1.setRight(binaryTree2);
                }
            }else if(name2!=null){
                BinaryTree binaryTree2=new BinaryTree();
                binaryTree2.setRoot(((Line)map.get(startIndex).get(endIndex)).getSideName1());
                if(name2.getLeft()==null){
                    name2.setLeft(binaryTree2);
                }else{
                    name2.setRight(binaryTree2);
                }
            }
            int length=((Line)map.get(startIndex).get(endIndex)).getLength();
//            System.out.println(((ScenicSpot)map.get(0).get(endIndex)).getName());
            for(int i=1;i<map.get(0).size();i++){
                if(map.get(startIndex).get(i) instanceof Line && map.get(endIndex).get(i) instanceof Line){
                    if(((Line)map.get(startIndex).get(i)).getLength()!=0&&((Line)map.get(startIndex).get(i)).isShortest()==false){
                        if(((Line)map.get(startIndex).get(i)).getLength()>(((Line)map.get(endIndex).get(i)).getLength()+length)){
                            Line line=new Line(((Line)map.get(endIndex).get(i)).getLength());
                            line.setSideName1(((ScenicSpot)map.get(0).get(endIndex)).getName());
                            line.setSideName2(((ScenicSpot)map.get(0).get(i)).getName());
                            map.get(startIndex).set(i,line);
                        }
                    }
                }
            }
        }
        lines=createShortestLine(binaryTree,lines);
        lines.add((ScenicSpot) graph.get(0).get(1));
        for(int i=0;i<lines.size();i++){
            System.out.println(lines.get(i).getName());
        }
        return lines;
    }

    private ArrayList<ScenicSpot> createShortestLine(BinaryTree binaryTree,ArrayList<ScenicSpot> scenicSpots){
        if(binaryTree!=null){
            scenicSpots.add((ScenicSpot) graph.get(0).get(searchForName(binaryTree.getRoot())));
        }
        if(binaryTree.getLeft()!=null){
            createShortestLine(binaryTree.getLeft(),scenicSpots);
        }
        if(binaryTree.getRight()!=null){
            createShortestLine(binaryTree.getRight(),scenicSpots);
        }
        return scenicSpots;
    }


    public BinaryTree searchNode(BinaryTree root,String string){
        if(root!=null){
            if(root.getRoot().equals(string)){
                return root;
            }
            if(root.getLeft()!=null){
                BinaryTree binaryTree=searchNode(root.getLeft(),string);
                if(binaryTree!=null){
                    return binaryTree;
                }
            }
            if(root.getRight()!=null){
                BinaryTree binaryTree=searchNode(root.getRight(),string);
                if(binaryTree!=null){
                    return binaryTree;
                }
            }
        }
        return null;
    }

    class BinaryTree{
        private String root;
        private BinaryTree left;
        private BinaryTree right;

        public BinaryTree() {
        }


        public String getRoot() {
            return root;
        }

        public void setRoot(String root) {
            this.root = root;
        }

        public BinaryTree getLeft() {
            return left;
        }

        public void setLeft(BinaryTree left) {
            this.left = left;
        }

        public BinaryTree getRight() {
            return right;
        }

        public void setRight(BinaryTree right) {
            this.right = right;
        }
    }

}
