package com.example.produto.produto_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.produto.produto_app.models.ClientModel;
import java.util.UUID;

@Repository
public interface ClientRepositoy extends JpaRepository<ClientModel,UUID> {

    @Query(value = "SELECT * FROM tb_clients WHERE unaccent(name) ILIKE unaccent(:name)", nativeQuery = true)
    List<ClientModel> findByNameUnaccent(@Param("name") String nome);

}
