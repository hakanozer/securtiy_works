package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.entities.Product;
import com.works.repositories.CustomerRepository;
import com.works.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    final CustomerRepository repo;
    public CustomerRestController(CustomerRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Customer obj) {
        repo.save(obj);
        return new ResponseEntity( obj, HttpStatus.OK );
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return new ResponseEntity( repo.findAll(), HttpStatus.OK );
    }


}
