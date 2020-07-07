/*
 * Rest Conroller for end points
 * @Auther : Prabhu Sahu
 * */
package com.nordea.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nordea.assessment.entity.EndToEnd;
import com.nordea.assessment.service.DocumentService;

import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiResponse;

import io.swagger.annotations.ApiOperation;

@Slf4j
@RestController
@RequestMapping("/document")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
public class DocumentController {

	@Autowired
	DocumentService service;
	
	/*
	 * Returns EndToEndId based on proviced id
	 * @param id The id of the EndToEnd 
	 * 
	 * */	
	@ApiOperation(value = "View a EndToEnd Details", response = EndToEnd.class)
	@GetMapping(path = "/endtoend/{id}", produces = { "application/json" })
	public EndToEnd findEndToEndIdById(@PathVariable("id") int id) {
		log.info("Invoked /endtoend/{id} with id: "+id);
		return service.findEndToEndById(id).orElse(new EndToEnd());
	}
	/*
	 * Returns all EndToEndIds 
	 * Returns Json
	 * 
	 * */	

	@ApiOperation(value = "View all EndToEnd Details", response = List.class)
	@GetMapping(path = "/endtoend", produces = { "application/json" })
	public List<EndToEnd> findEndToEnd() {
		log.info("Invoked /endtoend");
		return service.findAllEndToEnd();
	}

}
