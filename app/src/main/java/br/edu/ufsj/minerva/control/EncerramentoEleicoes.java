package br.edu.ufsj.minerva.control;

import android.content.Context;

/**
 * Created by Iago Alvarenga on 25/05/2018.
 */

public class EncerramentoEleicoes {
    public EncerramentoEleicoes(){
        new XML().finalizeWriteVoto();
    }
}
