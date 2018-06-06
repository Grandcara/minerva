package br.edu.ufsj.minerva.model;

import br.edu.ufsj.minerva.criptografia.RSA;

/**
 * Created by Iago Alvarenga on 05/06/2018.
 */

public class RSAModel {

    public static RSA arquivoCriptografico;

    public void inicializar() {
        arquivoCriptografico = new RSA();
    }



}
