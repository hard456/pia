package cz.filek.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/", name = "HomeCtl")
public class HomeController
{
    @RequestMapping(path = "/", name = "homePage")
    public ModelAndView showHomepage()
    {
        return new ModelAndView("home");
    }
}