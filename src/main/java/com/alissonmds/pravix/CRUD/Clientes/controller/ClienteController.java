package com.alissonmds.pravix.CRUD.Clientes.controller;



import com.alissonmds.pravix.CRUD.Clientes.domain.cliente.*;
import com.alissonmds.pravix.CRUD.Clientes.infra.exceptions.ValidacaoException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository, ClienteService clienteService) {
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> cadastrarCliente(@RequestBody @Valid DadosCadastramentoCliente dados, UriComponentsBuilder uriBuilder) {
        try {
            var cliente = new Cliente(dados);
            clienteRepository.save(cliente);
            var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
        } catch (Exception e) {
            throw new ValidacaoException("Erro ao cadastrar cliente. Já existe um usuário cadastrado com esse número de telefone.");
        }
    }

    @GetMapping("/contagem")
    public ResponseEntity<Long> obterContagemDeClientesCadastrados() {
        return ResponseEntity.ok(clienteRepository.contarClientesCadastrados());
    }

    @GetMapping()
    public ResponseEntity<Page<DadosDetalhamentoCliente>> listarClientes(@PageableDefault(size = 20, sort = {"nome"}) Pageable pagina) {
        var clientes = clienteRepository.findAll(pagina).map(DadosDetalhamentoCliente::new);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> buscarCliente(@PathVariable Long id) {
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new ValidacaoException("Cliente não encontrado na nossa base de dados."));
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Null> deletarCliente(@PathVariable Long id) {
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new ValidacaoException("Cliente não encontrado na nossa base de dados."));
        clienteRepository.delete(cliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> alterarDadosCliente(@PathVariable Long id, @RequestBody @Valid DadosEdicaoDadosCliente dados) {
        System.out.println(dados.telefone());
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new ValidacaoException("Cliente não encontrado na nossa base de dados."));
        cliente.alterarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<DadosDetalhamentoCliente>> buscarClientePorCriterio(@RequestBody @Valid DadosBuscaCliente dados) {
        var clientes = clienteService.buscarClientePorCriterio(dados).stream()
                .map(DadosDetalhamentoCliente::new).collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }
}

