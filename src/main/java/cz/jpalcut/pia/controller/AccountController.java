package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.config.BankConfig;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.UserRequest;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.UserRequestService;
import cz.jpalcut.pia.service.UserService;
import cz.jpalcut.pia.service.interfaces.IAccountService;
import cz.jpalcut.pia.service.interfaces.IUserRequestService;
import cz.jpalcut.pia.service.interfaces.IUserService;
import cz.jpalcut.pia.utils.Enum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller pro správu bankovních účtů
 */
@Controller
@RequestMapping(name = "accountController")
public class AccountController {

    private IUserService userService;

    private IAccountService accountService;

    private BankConfig bankConfig;

    private IUserRequestService userRequestService;

    /**
     * Konstruktor třídy
     * @param userService UserService
     * @param accountService AccountService
     * @param userRequestService UserRequestService
     * @param bankConfig BankConfig
     */
    @Autowired
    public AccountController(UserService userService, AccountService accountService,
                             UserRequestService userRequestService, BankConfig bankConfig) {
        this.userService = userService;
        this.accountService = accountService;
        this.userRequestService = userRequestService;
        this.bankConfig = bankConfig;
    }

    /**
     * Zobrazí informace o bankovním účtu uživatele
     *
     * @return ModelAndView
     */
    @RequestMapping(path = "/account", name = "account", method = RequestMethod.GET)
    public ModelAndView showAccountPage() {
        ModelAndView model = new ModelAndView("user/account");
        Account account = accountService.getAccount(userService.getUser());
        model.addObject("account", account);
        model.addObject("requests", userRequestService.getUserRequestsByAcount(account));
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

    /**
     * Změní hodnotu mezinárodní platby kartou
     *
     * @param accountId          id bankovního účtu
     * @param redirectAttributes pro přenos objektů na stránku přesměrování
     * @return ModelAndView
     */
    @RequestMapping(path = "/account/changeInternationalPayment/{id}", name = "id-change-payment", method = RequestMethod.GET)
    public ModelAndView confirmRequest(@PathVariable("id") Integer accountId, RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView("redirect:/account");
        Account account = accountService.getAccountById(accountId);

        //kontrola existence účtu
        if (account == null) {
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Nepovolený požadavek.");

            return model;
        }

        //ověření uživatele pro změnu
        if (!userService.getUser().getId().equals(account.getUser().getId())) {
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Nepovolený požadavek.");

            return model;
        } else {
            if (userRequestService.getUserRequestByTypeAndAccount(Enum.UserRequestType.valueOf("INTERNATIONAL_PAYMENT").toString(), account) != null) {
                //flash message danger
                redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
                redirectAttributes.addFlashAttribute("flashMessageText", "Požadavek tohoto typu už existuje.");

                return model;
            }
            UserRequest request = new UserRequest();
            request.setAccount(account);
            request.setValue(null);
            request.setType(Enum.UserRequestType.valueOf("INTERNATIONAL_PAYMENT").toString());
            userRequestService.saveUserRequest(request);
        }

        //flash message success
        redirectAttributes.addFlashAttribute("flashMessageSuccess", true);
        redirectAttributes.addFlashAttribute("flashMessageText", "Požadavek na změnu mezinárdní platba kartou byl poslán.");

        return model;
    }

    /**
     * Změní limit možnosti provedení transakce pod nulovou hodnotu účtu
     *
     * @param value              hodnota pro změnu
     * @param accountId          id bankovního účtu
     * @param redirectAttributes pro přenos objektů na stránku přesměrování
     * @return ModelAndView
     */
    @RequestMapping(path = "/account/changeValueLimitBelow/{id}", name = "id-change-limit", method = RequestMethod.POST)
    public ModelAndView confirmRequest(@RequestParam("value") Double value, @PathVariable("id") Integer accountId, RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView("redirect:/account");
        Account account = accountService.getAccountById(accountId);

        //kontrola existence účtu
        if (account == null) {
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Nepovolený požadavek.");

            return model;
        }

        //ověření uživatele pro změnu
        if (!userService.getUser().getId().equals(account.getUser().getId())) {
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Nepovolený požadavek.");

            return model;
        } else {
            if (userRequestService.getUserRequestByTypeAndAccount(Enum.UserRequestType.valueOf("LIMIT_BELOW").toString(), account) != null) {
                //flash message danger
                redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
                redirectAttributes.addFlashAttribute("flashMessageText", "Požadavek tohoto typu už existuje.");

                return model;
            }
            if (account.getLimitBelow().equals(value)) {
                //flash message danger
                redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
                redirectAttributes.addFlashAttribute("flashMessageText", "Nelze poslat požadavek na stejnou částku jako je nastavena.");

                return model;
            }
            UserRequest request = new UserRequest();
            request.setAccount(account);
            request.setValue(value);
            request.setType(Enum.UserRequestType.valueOf("LIMIT_BELOW").toString());
            userRequestService.saveUserRequest(request);
        }

        //flash message success
        redirectAttributes.addFlashAttribute("flashMessageSuccess", true);
        redirectAttributes.addFlashAttribute("flashMessageText", "Požadavek na změnu částky do mínusu byl odeslán.");

        return model;
    }

}
