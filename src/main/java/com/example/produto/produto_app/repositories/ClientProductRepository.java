package com.example.produto.produto_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.produto.produto_app.models.ClientProductModel;

import java.util.UUID;

@Repository
public interface ClientProductRepository extends JpaRepository<ClientProductModel,UUID> {

}
