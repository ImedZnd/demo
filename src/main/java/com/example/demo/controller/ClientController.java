package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Client;
import com.example.demo.model.Offre;
import com.example.demo.model.Rapport;
import com.example.demo.service.ClientService;
import com.example.demo.service.RapportService;

@RestController
@RequestMapping("/cli")
@EnableAutoConfiguration
public class ClientController {
	
	@Autowired
	ClientService clientService;
	@Autowired
	RapportService rapportService;

	   @GetMapping("/")
	    public ResponseEntity<List<Client>> getAllClient( ) {
	    return new ResponseEntity<>(clientService.getAll(),HttpStatus.OK);  		
	    }
	
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Client>> getClientbyId( @PathVariable(value = "id") Long id) {
    return new ResponseEntity<>(clientService.getById(id),HttpStatus.OK);  		
    }

    @GetMapping("/mail/{mail}")
    public ResponseEntity<Optional<Client>> getClientbyMail( @PathVariable(value = "mail") String mail) {
    return new ResponseEntity<>(clientService.getByMail(mail),HttpStatus.OK);  		
    }
    
    @GetMapping("/srt/{sortby}")
    public ResponseEntity<List> getAllClientsSorted(@PathVariable String sortby){
        System.out.println("get all clients \n");

        switch (sortby.toLowerCase()) {
        case "name":
            sortby = "name";
            break;    
        case "secteur":
                sortby = "secteur";
                break;
            case "is_engage":
                sortby = "is_engage";
                break;
            case "statu":
                sortby = "status";
                break;
            default:
                sortby = "inscriptionDate";
                break;
        }
        return new ResponseEntity<>(clientService.getAll(sortby), HttpStatus.OK);
    }
    @GetMapping(value = {"/name/{name}"})
    public ResponseEntity<Optional<Client>> getClientsByName( @PathVariable(value = "name") String name) {

        System.out.println("get clients with name\n");
            return new ResponseEntity<>(
            		clientService.getByName(name),
                    HttpStatus.OK
            );
    }
    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {

            System.out.println("add a client \n");
            return new ResponseEntity<>(clientService.addOne(client),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable ("id") Long id) {

            System.out.println("delete a client \n");
            clientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Client> updateCandidat( @RequestBody Client newClient)  {
            System.out.println("update client \n");
            Optional<Client> oldClient = getClientbyId(newClient.getId()).getBody();
            if (oldClient.isPresent()) {
                return new ResponseEntity<Client>(addClient(newClient).getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
}
