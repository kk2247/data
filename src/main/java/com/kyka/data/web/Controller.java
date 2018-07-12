package com.kyka.data.web;

import com.kyka.data.entity.*;
import com.kyka.data.service.GarageService;
import com.kyka.data.service.GraphService;
import com.kyka.data.service.ManagerService;
import com.kyka.data.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private GraphService graphService;

    @Autowired
    private GarageService garageService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private NoticeService noticeService;


    //停车场模块
    @RequestMapping(value = "getin" ,method = RequestMethod.POST)
    public String getIn(@RequestParam("licenseNum") String licenseNum,Model model) {
        Car car=new Car();
        car.setLicenseNum(licenseNum);
        ModelAndView modelAndView=new ModelAndView();
        if(garageService.getIn(car)){
            int size=garageService.getInformation();
            model.addAttribute("number",size);
            model.addAttribute("cars",garageService.getCars());
            model.addAttribute("result","true");
            return "garage";
        }
        int size=garageService.getInformation();
        model.addAttribute("number",size);
        model.addAttribute("cars",garageService.getCars());
        model.addAttribute("result","false");
        return "garage";
    }

    @RequestMapping(value = "getout" ,method = RequestMethod.POST)
    public String getOut(@RequestParam("licenseNum") String licenseNum,Model model) {
        if(licenseNum==null){
            int size=garageService.getInformation();
            model.addAttribute("number",size);
            model.addAttribute("cars",garageService.getCars());
            return "garage";
        }
        if(garageService.getOut(licenseNum)){
            int size=garageService.getInformation();
            model.addAttribute("number",size);
            model.addAttribute("cars",garageService.getCars());
            return "garage";
        }
        int size=garageService.getInformation();
        model.addAttribute("number",size);
        model.addAttribute("cars",garageService.getCars());
        return "garage";
    }

    @RequestMapping(value = "/garage" ,method = RequestMethod.GET)
    public String garage(Model model){
        int size=garageService.getInformation();
        model.addAttribute("number",size);
        model.addAttribute("cars",garageService.getCars());
        return "garage";
    }

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login2";
    }


    //登录模块
    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(Model model, @RequestParam("userName")String userName, @RequestParam("password") String password,
                        HttpServletResponse response){
        Manager manager1=managerService.login(new Manager(userName, password));
        if(userName==null||password==null){
            return"index";
        }
        if(manager1==null){
            return"index";
        }
        response.addCookie(new Cookie("account",userName));
        response.addCookie(new Cookie("password",password));
        model.addAttribute("scenic",managerService.getScenicSpots());
        model.addAttribute("way",managerService.getGraph());
        return "managerIndex";
    }

    @RequestMapping(value = "/managerIndex" ,method = RequestMethod.GET)
    public String managerIndex(Model model){
        model.addAttribute("graph",managerService.getGraph());
        model.addAttribute("scenic",managerService.getScenicSpots());
        model.addAttribute("way",managerService.getGraph());
        return "managerIndex";
    }



    //点操作

    @RequestMapping(value = "/change/{id}" ,method = RequestMethod.GET)
    public String changeScenicSpot(@PathVariable("id") int id, Model model){
        ScenicSpot scenicSpot=managerService.getScenicSpotById(id);
        model.addAttribute("scenic",scenicSpot);
        return "scenicController";
    }

    @RequestMapping(value = "/delete/{id}" ,method = RequestMethod.GET)
    public String deleteScenicSpot(@PathVariable("id") int id, Model model){
        managerService.deleteScenicSpot(id);
        model.addAttribute("result",true);
        model.addAttribute("scenic",managerService.getScenicSpots());
        model.addAttribute("way",managerService.getGraph());
        return "managerIndex";
    }

    @RequestMapping(value = "/addScenic", method = RequestMethod.POST)
    public String addScenicSpot(@RequestParam("name")String name, @RequestParam("introduce")String introduce,
                                @RequestParam("welcome")int welcome, @RequestParam("relax")boolean relax,
                                @RequestParam("toilet")boolean toilet, Model model){
        ScenicSpot scenicSpot=new ScenicSpot();
        scenicSpot.setIntroduce(introduce);
        scenicSpot.setName(name);
        scenicSpot.setRelax(relax);
        scenicSpot.setToilet(toilet);
        scenicSpot.setWelcome(welcome);
        if(managerService.insertScenicSpot(scenicSpot)){
            model.addAttribute("result",true);

        }else{
            model.addAttribute("result",false);
        }
        model.addAttribute("scenic",managerService.getScenicSpots());
        model.addAttribute("way",managerService.getGraph());
        return "managerIndex";
    }

    @RequestMapping(value = "/modifyScenic", method = RequestMethod.POST)
    public String modifyScenicSpot(@RequestParam("name")String name, @RequestParam("introduce")String introduce,
                                @RequestParam("welcome")int welcome, @RequestParam("relax")boolean relax,
                                @RequestParam("toilet")boolean toilet,@RequestParam("id")int id, Model model){
        ScenicSpot scenicSpot=new ScenicSpot();
        scenicSpot.setIntroduce(introduce);
        scenicSpot.setName(name);
        scenicSpot.setRelax(relax);
        scenicSpot.setToilet(toilet);
        scenicSpot.setWelcome(welcome);
        scenicSpot.setId(id);
        if(managerService.modifyScenicSpot(scenicSpot)){
            model.addAttribute("result",true);
        }else{
            model.addAttribute("result",false);
        }
        model.addAttribute("scenic",managerService.getScenicSpots());
        model.addAttribute("way",managerService.getGraph());
        return "managerIndex";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addScene( ){
        return "addScenicController";
    }

    @RequestMapping(value = "/scenicController" ,method = RequestMethod.GET)
    public String scenicController(){
        return "scenicController";
    }


    //线操作

    @RequestMapping(value = "/lineController" ,method = RequestMethod.GET)
    public String lineController(Model model){
        List<Line>lines=managerService.getLines();
        model.addAttribute("lines",lines);
        return "lineController";
    }

    @RequestMapping(value = "/modifyline" ,method = RequestMethod.GET)
    public String modifyLine(){
        return "modifyLine";
    }

    @RequestMapping(value = "/changeline/{id}" ,method = RequestMethod.GET)
    public String changeLine(@PathVariable("id") int id, Model model){
        Line line=managerService.getLineById(id);
        model.addAttribute("line",line);
        return "modifyLine";
    }

    @RequestMapping(value = "/modifyl", method = RequestMethod.POST)
    public String modifyScenicSpot(@RequestParam("length")int length, @RequestParam("time")int time,
                                   @RequestParam("sideName1")String sideName1, @RequestParam("sideName2")String sideName2,
                                   @RequestParam("id")int id, Model model){
        Line line=new Line();
        line.setSideName2(sideName2);
        line.setSideName1(sideName1);
        line.setId(id);
        line.setLength(length);
        line.setTime(time);
        if(managerService.modifyLine(line)){
            model.addAttribute("result",true);
        }else{
            model.addAttribute("result",false);
        }
        model.addAttribute("lines",managerService.getLines());
        return "lineController";
    }

    @RequestMapping(value = "/deleteline/{id}" ,method = RequestMethod.GET)
    public String deleteLine(@PathVariable("id") int id, Model model){
        managerService.deleteLine(id);
        model.addAttribute("result",true);
        model.addAttribute("lines",managerService.getLines());
        return "lineController";
    }

    @RequestMapping(value = "/addline", method = RequestMethod.POST)
    public String addLine(@RequestParam("length")int length, @RequestParam("time")int time,
                                   @RequestParam("sideName1")String sideName1, @RequestParam("sideName2")String sideName2,
                                   Model model){
        Line line=new Line();
        line.setSideName2(sideName2);
        line.setSideName1(sideName1);
        line.setLength(length);
        line.setTime(time);
        if(sideName1.equals(sideName2)==false){
            if(managerService.insertLine(line)){
                model.addAttribute("result",true);
                model.addAttribute("lines",managerService.getLines());
                return "lineController";
            }else{
                model.addAttribute("lines",managerService.getLines());
                model.addAttribute("result",false);
                return "lineController";
            }
        }else{
            model.addAttribute("lines",managerService.getLines());
            model.addAttribute("result",false);
            return "lineController";
        }
    }


    @RequestMapping(value = "/addl", method = RequestMethod.GET)
    public String addL(Model model){
        model.addAttribute("scenicSpots",managerService.getScenicSpots());
        return "addLine";
    }


    //发布公告

    @RequestMapping(value = "/publish",method = RequestMethod.GET)
    public String publish(){
        return "publish";
    }

    @RequestMapping(value = "/content",method = RequestMethod.POST)
    public String content(@RequestParam("content")String content,Model model){
        Date date=new Date();
        Notice notice=new Notice();
        notice.setContent(content);
        notice.setDate(date);
        noticeService.insertNotice(notice);
        model.addAttribute("scenic",managerService.getScenicSpots());
        model.addAttribute("way",managerService.getGraph());
        return "managerIndex";
    }

    //客户界面
    @RequestMapping(value = "/customer",method = RequestMethod.GET)
    public String customer(Model model){
        model.addAttribute("notice",noticeService.getNotice());
        model.addAttribute("way",graphService.outPutGraph());
        return "customerIndex";
    }

    @RequestMapping(value = "/shortestway",method = RequestMethod.GET)
    public String shortestWay(Model model){
        model.addAttribute("notice",noticeService.getNotice());
        int length=0;
        model.addAttribute("shortResult","false");
        model.addAttribute("length",length);
        model.addAttribute("scenicSpots",managerService.getScenicSpots());
        return "shortestWay";
    }

    @RequestMapping(value = "/short" ,method = RequestMethod.POST)
    public String shortg(@RequestParam("start")String start,@RequestParam("end")String end, Model model){
        if(start.equals(end)){
            int length=0;
            model.addAttribute("notice",noticeService.getNotice());
            model.addAttribute("scenicSpots",managerService.getScenicSpots());
            model.addAttribute("shortResult","false");
            model.addAttribute("length",length);
            return "shortestWay";
        }
        ArrayList<Line>lines=graphService.showShortestWay(start,end);
        ArrayList<String>scenicSpots=new ArrayList<>();
        int length=0;
        for(int i=0;i<lines.size();i++){
            if(i==0){
                scenicSpots.add(lines.get(i).getSideName1());
            }
            length+=lines.get(i).getLength();
            scenicSpots.add(lines.get(i).getSideName2());
        }
        model.addAttribute("notice",noticeService.getNotice());
        model.addAttribute("shortestWay",scenicSpots);
        model.addAttribute("length",length);
        model.addAttribute("scenicSpots",managerService.getScenicSpots());
        model.addAttribute("shortResult","true");
        return "shortestWay";
    }

    @RequestMapping(value = "/guild" ,method = RequestMethod.GET)
    public String guild(Model model){
        if(graphService.getShortestWayFromGate()!=null){
            model.addAttribute("notice",noticeService.getNotice());
            model.addAttribute("guild",graphService.getShortestWayFromGate());
            model.addAttribute("guildResult","true");
            return "guild";
        }
        model.addAttribute("notice",noticeService.getNotice());
        model.addAttribute("guildResult","false");
        return "guild";
    }


}
