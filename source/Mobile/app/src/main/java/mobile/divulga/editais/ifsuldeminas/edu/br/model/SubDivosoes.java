package mobile.divulga.editais.ifsuldeminas.edu.br.model;

import java.util.ArrayList;

public class SubDivosoes {

    private ArrayList<Edital> editais;
    private String subDivisao;

    public SubDivosoes(String divisao) {
        this.subDivisao = divisao;
    }

    public ArrayList<Edital> getEditais() {
        return editais;
    }

    public void setEditais(ArrayList<Edital> editais) {
        this.editais = editais;
    }

    public String getSubDivisao() {
        return subDivisao;
    }

    public void setSubDivisao(String subDivisao) {
        this.subDivisao = subDivisao;
    }

    public void adicionarEdital(Edital e)
    {
        editais.add(e);
    }

}
