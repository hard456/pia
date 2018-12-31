package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.config.BankConfig;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BankConfig bankConfig;

    @RequestMapping(path = "/account")
    public ModelAndView showAccountPage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("account");
        model.addObject("account",accountService.getAccount(userService.getUser()));
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

}
