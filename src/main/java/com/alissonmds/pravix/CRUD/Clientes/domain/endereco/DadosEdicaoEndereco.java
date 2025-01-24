package com.alissonmds.pravix.CRUD.Clientes.domain.endereco;

import jakarta.validation.constraints.Pattern;

public record DadosEdicaoEndereco(
        @Pattern(regexp = "\\d{8}") String cep,
        String estado,
        String cidade,
        String bairro,
        String rua,
        String complemento
) {
}

