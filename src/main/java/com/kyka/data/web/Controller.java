package com.kyka.data.web;

import com.kyka.data.entity.Car;
import com.kyka.data.entity.Manager;
import com.kyka.data.service.GarageService;
import com.kyka.data.service.GraphService;
import com.kyka.data.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private GraphService graphService;

    @Autowired
    private GarageService garageService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "getin" ,method = RequestMethod.POST)
    public String getIn(@RequestParam("licenseNum") String licenseNum){
        Car car=new Car();
        car.setLicenseNum(licenseNum);
        if(garageService.getIn(car)){
            return "garage";
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


    @RequestMapping(value = "/fgarage" ,method = RequestMethod.GET)
    public String fgarage(Model model){
        garageService.createGarage();
        int size=garageService.getInformation();
        model.addAttribute("number",size);
        return "garage";
    }

    @RequestMapping(value = "/garage" ,method = RequestMethod.GET)
    public String garage(Model model){
        int size=garageService.getInformation();
        model.addAttribute("number",size);
        return "garage";
    }

    @RequestMapping(value = "/managerIndex",method = RequestMethod.POST)
//    HttpServletRequest request, HttpServletResponse response
    public String login(Model model, @RequestParam("userName")String userName, @RequestParam("password") String password){
//        String userName=request.getParameter("userName");
//        String password=request.getParameter("password");
        Manager manager1=managerService.login(new Manager(userName, password));
        if(userName==null||password==null){
            return"redirect:/index";
        }
        if(manager1==null){
            return"redirect:/index";
        }
        model.addAttribute("user",manager1);
        return "model";
    }
}
