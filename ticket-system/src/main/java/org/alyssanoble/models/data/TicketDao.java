package org.alyssanoble.models.data;

import org.alyssanoble.models.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TicketDao extends CrudRepository<Ticket, Integer> {
}
