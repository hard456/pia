package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionListController {

    @RequestMapping(path = "/transaction-list")
    public ModelAndView showNewTransactionPage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("transaction_list");
        return model;
    }

}
