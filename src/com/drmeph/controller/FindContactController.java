package com.drmeph.controller;

import com.drmeph.entity.Contact;
import com.drmeph.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@Controller
public class FindContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/findcontact", method = RequestMethod.POST)
    public String findContact(HttpServletRequest request,
                             HttpServletResponse response,
                             ModelMap modelMap) {

        String name = request.getParameter("name");
        List<Contact> contactList = null;
        try {
            contactList = contactService.findContactByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "Error while searching "+name);
        }

        modelMap.addAttribute("listcontact", contactList);
        return "searchContact";
    }
}
