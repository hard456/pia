package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutUsController {

    @RequestMapping(path = "/about-us")
    public ModelAndView showAboutUsPage()
    {
        return new ModelAndView("about_us");
    }

}
