package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.TransactionService;
import cz.jpalcut.pia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionListController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @RequestMapping(path = "/transaction/list")
    public ModelAndView showNewTransactionPage()
    {
        ModelAndView model = new ModelAndView("transaction_list");
        Account account = accountService.getAccount(userService.getUser());
        model.addObject("transactions", transactionService.getTransactionsByAccount(account));
        return model;
    }

    @RequestMapping(path = "/transaction/{id}/detail")
    public ModelAndView showTransactionDetailPage(@PathVariable("id") Integer transactionId)
    {
        ModelAndView model = new ModelAndView("transaction_detail");
        model.addObject("transaction", transactionService.getTransactionById(transactionId));
        return model;
    }

}
