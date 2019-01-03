package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.config.BankConfig;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(name = "accountController")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BankConfig bankConfig;

    @RequestMapping(path = "/account", name = "account", method = RequestMethod.GET)
    public ModelAndView showAccountPage()
    {
        ModelAndView model = new ModelAndView("account");
        model.addObject("account",accountService.getAccount(userService.getUser()));
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

}
