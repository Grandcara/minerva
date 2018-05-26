package br.edu.ufsj.minerva.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Iago Alvarenga on 25/05/2018.
 */

public class Candidado {
     int idRandom;
     int id;
     String nome;
     String partido;
    int numero;
    String foto;
    private final static ArrayList<Candidado> candidatos = new ArrayList<>();
    public Candidado(){}
    public Candidado(int idRandom, int id, String nome, String partido, int numero, String foto){
         this.idRandom = idRandom;
         this.id = id;
         this.nome = nome;
         this.partido = partido;
         this.numero = numero;
         this.foto = foto;
     }


    public int getIdRandom() {
        return idRandom;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public int getNumero() {
        return numero;
    }


    public String getFoto() {
        return foto;
    }

    public ArrayList<Candidado> getCandidatos() {
        //embaralha conteudo
        Collections.shuffle(candidatos);
        return candidatos;
    }

    @Override
    public String toString() {
        return "Canditado{" +
                "idRandom=" + idRandom +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", partido='" + partido + '\'' +
                ", numero=" + numero +
                ", foto='" + foto + '\'' +
                '}';
    }
}
