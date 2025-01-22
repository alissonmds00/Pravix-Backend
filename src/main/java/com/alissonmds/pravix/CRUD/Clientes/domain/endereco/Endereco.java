package com.alissonmds.pravix.CRUD.Clientes.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String complemento;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Endereco(DadosCadastramentoEndereco dados) {
        this.estado = dados.estado();
        this.cidade = dados.cidade();
        this.bairro = dados.bairro();
        this.rua = dados.rua();
        this.complemento = dados.complemento();
    }

    public Endereco alterarEndereco(DadosEdicaoEndereco dados) {
        if (dados.estado() != null) {
            this.estado = dados.estado();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.rua() != null) {
            this.rua = dados.rua();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
        return this;
    }
}
