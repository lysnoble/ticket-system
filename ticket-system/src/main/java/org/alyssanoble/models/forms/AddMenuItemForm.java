package org.alyssanoble.models.forms;

import org.alyssanoble.models.Ticket;
import org.hibernate.annotations.Entity;
import org.alyssanoble.models.Ticket;
import org.alyssanoble.models.Menu;


import javax.validation.constraints.NotNull;

@Entity
public class AddMenuItemForm {
    private Menu menu;

    private Iterable<Ticket> tickets;

    @NotNull
    private int menuId;

    @NotNull
    private int ticketId;

    public int getMenuId() {
        return this.menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Iterable<Ticket> tickets) {
        this.tickets = tickets;
    }

    public AddMenuItemForm(Menu menu, Iterable<Ticket> tickets) {
        this.menu = menu;
        this.tickets = tickets;
    }
}