package cz.jpalcut.pia.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("error");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addObject("errorCode", "404 Not Found");
                model.addObject("errorMessage", "Požadovaný dokument nebyl nalezen, ale v budoucnosti může ale i nemusí být dostupný.");
                return model;
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addObject("errorCode", "500 Internal Server Error");
                model.addObject("errorMessage", "Obecná chybová zpráva. Při zpracovávání požadavku došlo ke blíže nespecifikované chybě.");
                return model;
            }
        }
        model.addObject("errorCode", "ERROR chyba");
        model.addObject("errorMessage", "Nastala blíže nespecifikovaná chyba.");
        return model;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
