package com.gustavoschaedler.api.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavoschaedler.api.models.Ticket;
import com.gustavoschaedler.api.repositories.TicketRepository;

@Service("TicketService")
public class TicketServiceImpl implements TicketService {

	private int idx;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Override
	public List<Ticket> checkStatus(List<Ticket> tickets) {
		
		idx = 0;
		/* Execute if exist tickets */
		if(!tickets.isEmpty()) {
			/* Sum N1+N2+N3 in Result */
			tickets.forEach(ticket -> tickets.get(idx++).setResult((short) (ticket.getN1() + ticket.getN2() + ticket.getN3())));
			
			/* Order by Result DESC */
			Comparator<Ticket> ticketResultComparatorLambda =
			    (ticket1, ticket2) -> ticket2.getResult() - ticket1.getResult();
			
			Collections.sort(tickets, ticketResultComparatorLambda);
		}

		return ticketRepository.saveAll(tickets);
	}

	@Override
	public List<Ticket> saveNewLines(int number, List<Ticket> tickets) {
		
		List<Ticket> newLines = new ArrayList<>(); /* New array empty list */
		List<Ticket> findTicketNumber = ticketRepository.findByNumber(number);
		
		/* Add lines only if status has never been queried */
		if(!findTicketNumber.isEmpty()) /* Exists one or more tickets with number param */
			if(findTicketNumber.get(0).getResult() == -1) /* Status has never been queried */
				newLines = new ArrayList<>(tickets);
		
		return ticketRepository.saveAll(newLines);
		//return ticketRepository.saveAll(tickets);
	}
}
