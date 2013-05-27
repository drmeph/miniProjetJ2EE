package com.drmeph.controller;

import com.drmeph.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
public class DeleteContactController extends MultiActionController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/initListDel", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        try {
            modelMap.addAttribute("listcontact", contactService.getListContact());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "removeContact";
    }

    @RequestMapping(value = "/deletecontact", method = RequestMethod.POST)
    public String deletecontact(HttpServletRequest request,
                                HttpServletResponse response,
                                ModelMap modelMap) {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            contactService.deleteContact(id);
        } catch (SQLException e) {
            e.printStackTrace();
            modelMap.addAttribute("message","Error while removing contact!");
            return "accueil";
        }
        modelMap.addAttribute("message", "Contact removed!");
        return "accueil";
    }
}
