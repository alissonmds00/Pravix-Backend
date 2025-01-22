package com.alissonmds.pravix.CRUD.Clientes.domain.cliente;

import com.alissonmds.pravix.CRUD.Clientes.domain.endereco.Endereco;

public record DadosDetalhamentoCliente(Long id, String nome, String telefone, Endereco endereco) {
    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEndereco());
    }
}
