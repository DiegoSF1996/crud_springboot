package com.example.produto.produto_app.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record ClientProductRecordDto( @NotNull UUID idClient, @NotNull UUID idProduct) {
    
}
