package com.drmeph.controller;

import com.drmeph.entity.*;
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
import java.util.HashSet;
import java.util.Set;

@Controller
public class NewContactController extends MultiActionController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/newcontact", method = RequestMethod.POST)
    public String newcontact(HttpServletRequest request,
                             HttpServletResponse response,
                             ModelMap modelMap) {

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String zip = request.getParameter("zip");
        String cc = request.getParameter("cc");
        String homePhone = request.getParameter("homephone");
        String workPhone = request.getParameter("workphone");
        String cellphone = request.getParameter("cellphone");
        String[] groups = request.getParameterValues("groups");
        Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
        Set<ContactGroup> books = new HashSet<ContactGroup>();

        Contact contact = new Contact();
        contact.setFirstName(firstname);
        contact.setLastName(lastname);
        contact.setEmail(email);

        if (!street.equals("") || !zip.equals("") || !cc.equals("")) {
            Address address = new Address();
            address.setStreet(street);
            address.setZip(zip);
            address.setCountry(cc);

            contact.setAdd(address);
        }

        if (!homePhone.equals("")) {
            PhoneNumber hp = new PhoneNumber();
            hp.setContact(contact);
            hp.setType(PhoneType.HOME);
            hp.setPhoneNumber(homePhone);
            phoneNumbers.add(hp);
        }

        if (!workPhone.equals("")) {
            PhoneNumber hp = new PhoneNumber();
            hp.setContact(contact);
            hp.setType(PhoneType.WORK);
            hp.setPhoneNumber(workPhone);
            phoneNumbers.add(hp);
        }

        if (!cellphone.equals("")) {
            PhoneNumber hp = new PhoneNumber();
            hp.setContact(contact);
            hp.setType(PhoneType.CELL);
            hp.setPhoneNumber(cellphone);
            phoneNumbers.add(hp);
        }

        if (phoneNumbers.size() > 0) {
            contact.setPhones(phoneNumbers);
        }

        if (groups != null) {
            for (String group : groups){
                ContactGroup contactGroup = new ContactGroup();
                contactGroup.setGroupName(group);
                books.add(contactGroup);
            }
            contact.setBooks(books);
        }

        try {
            contactService.addContact(contact);
        } catch (SQLException e) {
            e.printStackTrace();
            modelMap.addAttribute("message", "Error while adding "+
                    contact.getFirstName()+" "+contact.getLastName());
            return "accueil";
        }
        modelMap.addAttribute("message", "Successfully added ");
        return "accueil";
    }
}
