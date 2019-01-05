package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.config.BankConfig;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Template;
import cz.jpalcut.pia.model.Transaction;
import cz.jpalcut.pia.service.*;
import cz.jpalcut.pia.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Controller pro správu transakcí
 */
@Controller
@RequestMapping(name = "transactionController")
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

    @Autowired
    CaptchaService captchaService;

    /**
     * Zobrazí stránku s formulářem pro vytvoření nové transakce
     *
     * @return ModelAndView
     */
    @RequestMapping(path = "/transaction/new", name = "new", method = RequestMethod.GET)
    public ModelAndView showNewTransactionPage() {
        ModelAndView model = new ModelAndView("transaction/new");
        Account account = accountService.getAccount(userService.getUser());
        model.addObject("transaction", new Transaction());
        model.addObject("templates", templateService.getTemplatesByAccount(account));
        return model;
    }

    /**
     * Přidá novou transakci
     *
     * @param transaction   data z formuláře pro vytvoření nové transakce
     * @param bindingResult vyhodnicení údajů z formuláře validací
     * @param request       požadavek na server
     * @return ModelAndView
     */
    @RequestMapping(path = "/transaction/new/add", name = "new-add", method = RequestMethod.POST)
    public ModelAndView addNewTransaction(@Valid @ModelAttribute("transaction") Transaction transaction,
                                          BindingResult bindingResult, HttpServletRequest request) {

        ModelAndView model = new ModelAndView("transaction/new");
        Account account = accountService.getAccount(userService.getUser());
        model.addObject("templates", templateService.getTemplatesByAccount(account));

        if (bindingResult.hasErrors()) {
            //flash message danger
            model.addObject("flashMessageSuccess", false);
            model.addObject("flashMessageText", "Nastala chyba při vyplnění formuláře.");

            return model;
        }

        String captchaResponse = request.getParameter("g-recaptcha-response");

        //ověření google captcha
        if (!captchaService.processResponse(captchaResponse, request.getRemoteAddr())) {
            model.addObject("flashMessageSuccess", false);
            model.addObject("flashMessageText", "Nastala chyba při ověření formuláře - Google reCAPTCHA ");
            return model;
        }

        //validace data splatnosti
        if (!Utils.isValidTransactionDate(transaction.getDueDate())) {
            //flash message danger
            model.addObject("flashMessageSuccess", false);
            model.addObject("flashMessageText", "Datum splatnosti nesmí být v minulosti");

            return model;
        }

        //zakazání poslání peněz na vlastní účet
        if (account.getNumber().equals(transaction.getNumber()) && transaction.getCode().equals(bankConfig.getBankCode())) {
            //flash message danger
            model.addObject("flashMessageSuccess", false);
            model.addObject("flashMessageText", "Nemůžete poslat peníze na vlastní účet.");

            return model;
        }

        //kontrola zadané částky transakce na větší než 0.00
        if (transaction.getValue() <= 0.00) {
            //flash message danger
            model.addObject("flashMessageSuccess", false);
            model.addObject("flashMessageText", "Lze poslat jen částku větší než nula.");

            return model;
        }

        //kontrola stavu peněž na účtu po odečtení částky transakce
        if ((account.getBalance() + account.getLimitBelow() - account.getBlockedBalance() - transaction.getValue()) < 0.0) {

            //flash message danger
            model.addObject("flashMessageSuccess", false);
            model.addObject("flashMessageText", "Nemáte dostatek peněz na účtu.");

            return model;

        }

        //kontrola existence účtu v bance
        if (transaction.getCode().equals(bankConfig.getBankCode())) {
            if (accountService.getAccountByNumber(transaction.getNumber()) == null) {
                //flash message danger
                model.addObject("flashMessageSuccess", false);
                model.addObject("flashMessageText", "Zvolený účet v naší bance neexistuje.");

                return model;
            } else {
                transactionService.addInterBankTransaction(transaction);

                //flash message success
                model.addObject("flashMessageSuccess", true);
                model.addObject("flashMessageText", "Byla přijata transakce ke zpracování.");

                model.addObject("transaction", new Transaction());
                return model;
            }
        }

        transactionService.addTransaction(transaction);

        //flash message success
        model.addObject("flashMessageSuccess", true);
        model.addObject("flashMessageText", "Byla přijata transakce ke zpracování.");

        model.addObject("transaction", new Transaction());
        return model;
    }

    /**
     * Zobrazí stránku s seznam transakcí
     *
     * @param pageable omezuje zobrazení na počet elementů a číslo stránky
     * @return ModelAndView
     */
    @RequestMapping(path = "/transaction/list", name = "list", method = RequestMethod.GET)
    public ModelAndView showTransactionListPage(Pageable pageable) {
        ModelAndView model = new ModelAndView("transaction/list");
        Account account = accountService.getAccount(userService.getUser());
        Page<Transaction> pages = transactionService.getTransactionsByAccountPageable(account, pageable);
        model.addObject("pagination", pages);
        model.addObject("transactions", pages.getContent());
        return model;
    }

    /**
     * Zobrazí stránku s detailem transakce
     *
     * @param transactionId      id transakce
     * @param redirectAttributes pro přenos objektů na stránku přesměrování
     * @return ModelAndView
     */
    @RequestMapping(path = "/transaction/detail/{id}", name = "id-detail", method = RequestMethod.GET)
    public ModelAndView showTransactionDetailPage(@PathVariable("id") Integer transactionId, RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView("transaction/detail");
        Transaction transaction = transactionService.getTransactionById(transactionId);

        //kontrola existence šablony
        if (transaction == null) {
            model.setViewName("redirect:/transaction/list");
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Transakce neexistuje.");
            return model;
        }

        //ověření uživatele pro zobrazení
        if (!userService.getUser().getId().equals(transaction.getAccount().getUser().getId())) {
            model.setViewName("redirect:/transaction/list");
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Nepovolený požadavek.");
            return model;
        }

        model.addObject("transaction", transaction);
        return model;
    }

    /**
     * Zobrazí stránku pro vytvoření transakce se předvyplněnými údaji z šablony
     *
     * @param templateId         id šablony
     * @param redirectAttributes pro přenos objektů na stránku přesměrování
     * @return ModelAndView
     */
    @RequestMapping(path = "/transaction/new/{id}", name = "new-id", method = RequestMethod.GET)
    public ModelAndView showNewTemplateTransactionPage(@PathVariable("id") Integer templateId, RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView("transaction/new");
        Account account = accountService.getAccount(userService.getUser());
        Template template = templateService.getTemplateById(templateId);

        //kontrola existence šablony
        if (template == null) {
            model.setViewName("redirect:/transaction/new");
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Šablona neexistuje.");
            return model;
        }

        //ověření uživatele pro použití šablony
        if (!userService.getUser().getId().equals(template.getAccount().getUser().getId())) {
            model.setViewName("redirect:/transaction/new");
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Nepovolený požadavek.");
            return model;
        }

        //předáního hodnot šablony do transakce
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
