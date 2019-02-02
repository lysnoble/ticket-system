package org.alyssanoble.controllers;

import org.alyssanoble.models.Category;
import org.alyssanoble.models.Ticket;
import org.alyssanoble.models.data.CategoryDao;
import org.alyssanoble.models.data.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: /ticket
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("tickets", ticketDao.findAll());
        model.addAttribute("title", "My Tickets");
        return "ticket/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddTicketForm(Model model) {
        model.addAttribute("title", "Add Ticket");
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute(new Ticket());
        return "ticket/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTicketForm(@ModelAttribute @Valid Ticket newTicket,
                                       Errors errors, @RequestParam int categoryId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ticket");
            model.addAttribute("categories", categoryDao.findAll());
            return "ticket/add";
        }
        Category cat = categoryDao.findOne(categoryId);
        newTicket.setCategory(cat);
        ticketDao.save(newTicket);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveTicketForm(Model model) {
        model.addAttribute("tickets", ticketDao.findAll());
        model.addAttribute("title", "Close Ticket");
        return "ticket/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveTicketForm(@RequestParam int[] ticketIds) {

        for (int ticketId : ticketIds) {
            ticketDao.delete(ticketId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "category/{id}", method = RequestMethod.GET)
    public String displayAllTicketForCat(Model model, @PathVariable int id) {

        Category cat = categoryDao.findOne(id);
        if (cat != null) {
            List<Ticket> tickets = cat.getTickets();
            model.addAttribute("tickets", tickets);
            model.addAttribute("title", "Category : " + cat.getName());
            return "ticket/index";
        }
        return "redirect:/category";

    }

    @RequestMapping(value = "edit/{ticketId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int ticketId) {
        Ticket ticket = ticketDao.findOne(ticketId);
        if (ticket != null) {
            model.addAttribute("title", "Edit - " + ticket.getName());
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("ticket", ticket);
            return "ticket/edit";
        }
        return "redirect:/ticket";
    }

    @RequestMapping(value = "edit/{ticketId}", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Ticket ticket, @RequestParam int categoryId, @RequestParam int id, Errors errors) {
        if (ticket == null && errors.hasErrors()) {
            return "ticket/edit";
        }
        Ticket updateTicket = ticketDao.findOne(id);
        Category cat = categoryDao.findOne(categoryId);
        updateTicket.setCategory(cat);
        updateTicket.setName(ticket.getName());
        updateTicket.setDescription(ticket.getDescription());
        ticketDao.save(updateTicket);
        return "redirect:/ticket";
    }
}