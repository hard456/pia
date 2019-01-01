package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Template;
import cz.jpalcut.pia.model.Transaction;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.TemplateService;
import cz.jpalcut.pia.service.TransactionService;
import cz.jpalcut.pia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    TemplateService templateService;

    @RequestMapping(path = "/transaction/new", method = RequestMethod.GET)
    public ModelAndView showNewTransactionPage()
    {
        ModelAndView model = new ModelAndView("new_transaction");
        model.addObject("activeLink","transaction/new");
        Account account = accountService.getAccount(userService.getUser());
        model.addObject("transaction", new Transaction());
        model.addObject("templates", templateService.getTemplatesByAccount(account));
        return model;
    }

    @RequestMapping(path = "/transaction/new/add", method = RequestMethod.POST)
    public ModelAndView addNewTransaction(@Valid @ModelAttribute("transaction")Transaction transaction,
                                              BindingResult bindingResult){

        ModelAndView model = new ModelAndView("new_transaction");
        Account account = accountService.getAccount(userService.getUser());
        model.addObject("templates", templateService.getTemplatesByAccount(account));

        if(bindingResult.hasErrors()){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nastala chyba při vyplnění formuláře.");

            return model;
        }

        transactionService.addTransaction(transaction);

        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Byla přijata transakce ke zpracování.");

        model.addObject("transaction", new Transaction());

        return model;
    }

    @RequestMapping(path = "/transaction/list", method = RequestMethod.GET)
    public ModelAndView showTransactionListPage()
    {
        ModelAndView model = new ModelAndView("transaction_list");
        model.addObject("activeLink","transaction/list");
        Account account = accountService.getAccount(userService.getUser());
        model.addObject("transactions", transactionService.getTransactionsByAccount(account));
        return model;
    }

    @RequestMapping(path = "/transaction/{id}/detail", method = RequestMethod.GET)
    public ModelAndView showTransactionDetailPage(@PathVariable("id") Integer transactionId)
    {
        ModelAndView model = new ModelAndView("transaction_detail");
        model.addObject("activeLink","transaction/list");
        model.addObject("transaction", transactionService.getTransactionById(transactionId));
        return model;
    }

    @RequestMapping(path = "/transaction/new/{id}", method = RequestMethod.GET)
    public ModelAndView showNewTemplateTransactionPage(@PathVariable("id") Integer templateId){
        ModelAndView model = new ModelAndView("new_transaction");
        model.addObject("activeLink","transaction/new");
        Account account = accountService.getAccount(userService.getUser());
        Template template = templateService.getTemplateById(templateId);
        Transaction transaction = new Transaction();
        transaction.setNumber(template.getNumber());
        transaction.setCode(template.getCode());
        transaction.setMessage(template.getMessage());
        transaction.setConstantSymbol(template.getConstantSymbol());
        transaction.setSpecificSymbol(template.getSpecificSymbol());
        transaction.setVariableSymbol(template.getVariableSymbol());
        transaction.setValue(template.getValue());
        model.addObject("transaction", transaction);
        model.addObject("templates", templateService.getTemplatesByAccount(account));
        model.addObject("template", template);
        return model;
    }

}
