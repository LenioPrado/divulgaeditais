package com.example.vanessafurtado.prefeitura.model;

/**
 * Created by Vanessa Furtado on 05/02/2018.
 */

public class Detalhes {

    private String numero;
    private String modalidade;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getArquivos() {
        return arquivos;
    }

    public void setArquivos(String arquivos) {
        this.arquivos = arquivos;
    }

    private String data;
    private String arquivos;


}
