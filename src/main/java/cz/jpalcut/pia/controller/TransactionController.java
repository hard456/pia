package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.config.BankConfig;
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
import java.sql.Date;
import java.util.Calendar;

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

    @Autowired
    BankConfig bankConfig;

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

        //Porovnání data splatnosti s aktuálním datem
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(transaction.getMaturity());
        cal.setTime(new Date(System.currentTimeMillis()));
        if(cal.compareTo(cal2) < 0){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Datum splatnosti nesmí být v minulosti");

            return model;
        }

        //Zakazání poslání peněz na vlastní účet
        if(account.getNumber().equals(transaction.getNumber()) && transaction.getCode().equals(bankConfig.getBankCode())){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nemůžete poslat peníze na vlastní účet.");

            return model;
        }

        //Kontrola zadané částky transakce na větší než 0.00
        if(transaction.getValue() <= 0.00){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Lze poslat jen částku větší než nula.");

            return model;
        }

        //Kontrola stavu peněž na účtu po odečtení částky transakce
        if((account.getBalance()-account.getBlockedBalance()-transaction.getValue()) < 0.0){

            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nemáte dostatek peněz na účtu.");

            return model;

        }

        //Kontrola existence účtu v bance
        if(transaction.getCode().equals(bankConfig.getBankCode())){
            if(accountService.getAccountByNumber(transaction.getNumber()) == null){
                //flash message danger
                model.addObject("flashMessageSuccess",false);
                model.addObject("flashMessageText","Zvolený účet v naší bance neexistuje.");

                return model;
            }
            else{
                transactionService.addInterBankTransaction(transaction);

                //flash message success
                model.addObject("flashMessageSuccess",true);
                model.addObject("flashMessageText","Byla přijata transakce ke zpracování.");

                model.addObject("transaction", new Transaction());

                return model;
            }
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
