package com.alissonmds.pravix.CRUD.Clientes.domain.cliente;

import com.alissonmds.pravix.CRUD.Clientes.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

        private final ClienteRepository clienteRepository;

        @Autowired
        public ClienteService(ClienteRepository clienteRepository) {
            this.clienteRepository = clienteRepository;
        }

        public List<Cliente> buscarClientePorCriterio(DadosBuscaCliente dados) {
            if (dados.nome() != null) {
                return clienteRepository.buscarPeloNome(dados.nome());
            }
            if (dados.cpf() != null) {
                return clienteRepository.buscarPeloCpf(dados.cpf());
            }
            if (dados.telefone() != null) {
                return clienteRepository.buscarPeloTelefone(dados.telefone());
            }
            else {
                throw new ValidacaoException("Busca inválida");
            }
        }
}
