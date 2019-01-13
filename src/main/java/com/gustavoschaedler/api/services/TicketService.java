package com.gustavoschaedler.api.services;

import java.util.List;

import com.gustavoschaedler.api.models.Ticket;

public interface TicketService {
	List<Ticket> checkStatus(List<Ticket> tickets);

	List<Ticket> saveNewLines(int number, List<Ticket> tickets);
}
