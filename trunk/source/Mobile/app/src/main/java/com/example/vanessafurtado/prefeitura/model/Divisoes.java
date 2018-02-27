package com.example.vanessafurtado.prefeitura.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Divisoes {

    private ArrayList<Edital> todos;
    private ArrayList<SubDivosoes> orgao;
    private ArrayList<SubDivosoes> categoria;
    private ArrayList<SubDivosoes> modalidade;
    private HashMap<String, Edital> hashEdital;

    private HashMap<String, Integer> hashOrgao;
    private HashMap<String, Integer> hashCategoria;
    private HashMap<String, Integer> hashModalidade;




    public Divisoes(){

        todos = new ArrayList<>();
        orgao = new ArrayList<>();
        categoria = new ArrayList<>();
        modalidade = new ArrayList<>();
        hashEdital = new HashMap<>();
        hashCategoria = new HashMap<>();
        hashModalidade = new HashMap<>();

    }

    public void adicionarEdital(Edital e) {

        todos.add(e);

        Integer categoria = hashCategoria.get(e.getCategoria());
        Integer orgao = hashOrgao.get(e.getOrgao());
        Integer modalidade = hashModalidade.get(e.getModalidade());

        todos.add(e);
        adicionar(categoria, this.categoria, e.getCategoria(), e, hashCategoria);
        adicionar(orgao, this.orgao, e.getOrgao(), e, hashOrgao);
        adicionar(modalidade, this.modalidade, e.getModalidade(), e, hashModalidade);


    }

    private void adicionar(Integer integer, ArrayList<SubDivosoes> lista, String key, Edital e, HashMap<String,Integer> hash){

        if(integer==null){
            SubDivosoes nova = new SubDivosoes(key);
            nova.adicionarEdital(e);
            lista.add(nova);
            hash.put(key, lista.size()-1);


        }else{
            lista.get(integer).adicionarEdital(e);
        }

    }


    public ArrayList<String> modalidades(){
        ArrayList<String> todasModalidades = new ArrayList<>();

        for(SubDivosoes s : modalidade){
            todasModalidades.add(s.getSubDivisao());

        }
    return todasModalidades;

    }

    public ArrayList<String> orgaos(){
        ArrayList<String> todasOrgaos = new ArrayList<>();

        for(SubDivosoes s : orgao){
            todasOrgaos.add(s.getSubDivisao());

        }
        return todasOrgaos;

    }

    public ArrayList<String> categorias(){
        ArrayList<String> todasCategorias = new ArrayList<>();

        for(SubDivosoes s : categoria){
            todasCategorias.add(s.getSubDivisao());

        }
        return todasCategorias;

    }


}
