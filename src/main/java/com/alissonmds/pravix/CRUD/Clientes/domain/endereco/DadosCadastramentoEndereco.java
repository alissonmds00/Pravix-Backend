package com.alissonmds.pravix.CRUD.Clientes.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastramentoEndereco(
        @NotBlank @Pattern(regexp = "\\d{8}") String cep,
        @NotBlank String estado,
        @NotBlank String cidade,
        @NotBlank String bairro,
        @NotBlank String rua,
        String complemento
) {
}
