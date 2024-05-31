package com.example.produto.produto_app.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.produto.produto_app.dtos.ClientProductRecordDto;
import com.example.produto.produto_app.models.ClientProductModel;
import com.example.produto.produto_app.repositories.ClientProductRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ClientProductController {

    @Autowired
    ClientProductRepository clientProductRepositoy;

    @GetMapping("/clientproducts")
    public ResponseEntity<List<ClientProductModel>> index() {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientProductRepositoy.findAll());
    }

    @GetMapping("/clientproducts/{id}")
    public ResponseEntity<Object> show(@PathVariable(value="id") UUID id) {
        Optional<ClientProductModel> clientProduct0 = clientProductRepositoy.findById(id);
        if(clientProduct0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientProduct0.get());
    }

    @PostMapping("/clientproducts")
    public ResponseEntity<ClientProductModel> saveProduct(@RequestBody @Valid ClientProductRecordDto clientProductDto) {
        //var clientProductModel = new ClientProductModel();
        ClientProductModel clientProductModel = new ClientProductModel();
        BeanUtils.copyProperties(clientProductDto, clientProductModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientProductRepositoy.save(clientProductModel));
    }

    @PutMapping("/clientproducts/{id}")
    public ResponseEntity<Object> update(@PathVariable(value="id") UUID id, @RequestBody @Valid ClientProductRecordDto clientProductDto) {
        Optional<ClientProductModel> clientProduct0 = clientProductRepositoy.findById(id);
        if(clientProduct0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var clientProductModel = clientProduct0.get();
        BeanUtils.copyProperties(clientProductDto, clientProductModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientProductRepositoy.save(clientProductModel));
    }

    @DeleteMapping("/clientproducts/{id}")
    public ResponseEntity<Object> update(@PathVariable(value="id") UUID id) {
        Optional<ClientProductModel> clientProduct0 = clientProductRepositoy.findById(id);
        if(clientProduct0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        clientProductRepositoy.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");
    }
    
}
