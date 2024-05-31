package com.example.produto.produto_app.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientRecordDto( @NotBlank String name) {
    

}
