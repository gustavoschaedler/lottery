package com.gustavoschaedler.api.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gustavoschaedler.api.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
	List<Ticket> findByNumber(int number);
	//Ticket findById(long id);
}
