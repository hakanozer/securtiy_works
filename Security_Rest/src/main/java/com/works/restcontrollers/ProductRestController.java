package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    final ProductRepository repo;
    public ProductRestController(ProductRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product obj) {
        repo.save(obj);
        return new ResponseEntity( obj, HttpStatus.OK );
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return new ResponseEntity( repo.findAll(), HttpStatus.OK );
    }


}
