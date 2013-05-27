package com.drmeph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddressBookController {
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/indexagain", method = RequestMethod.GET)
    public String indexWithErrLog(ModelMap model) {
        model.addAttribute("message", "Login needs to be the same as password");
        return "index";
    }

    @RequestMapping(value = "/accueil", method = RequestMethod.POST)
    public String accueil() {
        return "accueil";
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.GET)
    public String addContact() {
        return "addContact";
    }

    @RequestMapping(value = "/removeContact", method = RequestMethod.GET)
    public String removeContact() {
        return "removeContact";
    }

    @RequestMapping(value = "/searchContact", method = RequestMethod.GET)
    public String searchContact() {
        return "searchContact";
    }

    @RequestMapping(value = "/updateContact", method = RequestMethod.GET)
    public String updateContact() {
        return "updateContact";
    }

    @RequestMapping(value = "/checklogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        if (request.getParameter("username").equals(request.getParameter("password"))) {
            return "accueil";
        } else {
            return "redirect:indexagain";
        }
    }
}
