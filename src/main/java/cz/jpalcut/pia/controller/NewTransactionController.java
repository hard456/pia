package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.model.Transaction;
import cz.jpalcut.pia.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class NewTransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(path = "/new-transaction")
    public ModelAndView showNewTransactionPage()
    {
        ModelAndView model = new ModelAndView("new_transaction");
        model.addObject("transaction", new Transaction());
        return model;
    }

    @RequestMapping(path = "/new-transaction/add")
    public ModelAndView addNewTransactionPage(@Valid @ModelAttribute("transaction")Transaction transaction,
                                              BindingResult bindingResult){

        ModelAndView model = new ModelAndView("new_transaction");

        if(bindingResult.hasErrors()){
            return model;
        }

        transactionService.addTransaction(transaction);
        model.addObject("transaction", new Transaction());

        return model;
    }

}
