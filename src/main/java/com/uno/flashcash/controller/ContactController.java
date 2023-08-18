package com.uno.flashcash.controller;

import com.uno.flashcash.service.LinkService;
import com.uno.flashcash.service.form.AddContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("add-contact")
public class ContactController {
    private final LinkService linkService;
    public ContactController(LinkService linkService){this.linkService = linkService;}

    @GetMapping()
    public ModelAndView getAddConnectionForm(Model model){
        return new ModelAndView("addContact", "addContactForm", new AddContactForm("email"));
    }
    @PostMapping()
    public ModelAndView addConnection(@ModelAttribute("addContactForm") AddContactForm form){
        linkService.addLink(form);
        return new ModelAndView("transfer");
    }




}
