package com.kyka.data.web;

import com.kyka.data.entity.Car;
import com.kyka.data.entity.Manager;
import com.kyka.data.entity.ScenicSpot;
import com.kyka.data.service.GarageService;
import com.kyka.data.service.GraphService;
import com.kyka.data.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private GraphService graphService;

    @Autowired
    private GarageService garageService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "getin" ,method = RequestMethod.POST)
    public String getIn(@RequestParam("licenseNum") String licenseNum) {
        Car car=new Car();
        car.setLicenseNum(licenseNum);
        ModelAndView modelAndView=new ModelAndView();
        if(garageService.getIn(car)){
            return "index";
        }
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

    @RequestMapping(value = "/customer",method = RequestMethod.GET)
    public String customer(){
        return "customerIndex";
    }


    @RequestMapping(value = "/garage" ,method = RequestMethod.GET)
    public String garage(Model model){
        int size=garageService.getInformation();
        model.addAttribute("number",size);
        return "garage";
    }

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
        return "managerIndex";
    }

    @RequestMapping(value = "/managerIndex" ,method = RequestMethod.GET)
    public String managerIndex(Model model){
        List<ScenicSpot> scenicSpot=managerService.getScenicSpots();
        model.addAttribute("scenic",scenicSpot);
        return "managerIndex";
    }

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
            model.addAttribute("result",true);
        }
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
}
