package com.gustavoschaedler.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavoschaedler.api.models.Ticket;
import com.gustavoschaedler.api.repositories.TicketRepository;
import com.gustavoschaedler.api.services.TicketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Lottery")
public class TicketController {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketService ticketService;
	
	@ApiOperation(value="List all Tickets")
	@GetMapping("/ticket")
	public List<Ticket> listTickets(){
		return ticketRepository.findAll();
	}
	
	@ApiOperation(value="Get Ticket by Number")
	@GetMapping("/ticket/{number}")
	public List<Ticket> getTicketByNumber(@PathVariable(value="number") int number){
		return ticketRepository.findByNumber(number);
	}
	
	@ApiOperation(value="Create a new Ticket (one or more)")
	@PostMapping("/ticket")
	public List<Ticket> saveNewTicket(@RequestBody @Valid List<Ticket> ticket) {
		return ticketRepository.saveAll(ticket);
	}
	
	@ApiOperation(value="Create a new Line (one or more)")
	@PostMapping("/line/{number}")
	public List<Ticket> saveNewLines(@PathVariable(value="number") int number, @RequestBody @Valid List<Ticket> lines) {
		return ticketService.saveNewLines(number, lines);
	}
	
	@ApiOperation(value="=> Delete Ticket by Id (just for Swagger test) <=")
	@DeleteMapping("/ticket/{id}")
	public void deleteTicketById(@PathVariable(value="id") long id) {
		ticketRepository.deleteById(id);
	}
	
	@ApiOperation(value="Update Ticket")
	@PutMapping("/ticket")
	public Ticket updateTicket(@RequestBody @Valid Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
	@ApiOperation(value="=> Clear table (just for Swagger test) <=")
	@DeleteMapping("/clear")
	public void addClearAll() {
		ticketRepository.deleteAll();
	}
	
	@ApiOperation(value="Status of Ticket")
	@PutMapping("/status/{number}")
	public List<Ticket> checkStatus(@PathVariable(value="number") int number) {
		return ticketService.checkStatus(ticketRepository.findByNumber(number));
	}
}

