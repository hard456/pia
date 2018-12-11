package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewTransactionController {

    @RequestMapping(path = "/new-transaction")
    public ModelAndView showNewTransactionPage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("new_transaction");
        return model;
    }

}
