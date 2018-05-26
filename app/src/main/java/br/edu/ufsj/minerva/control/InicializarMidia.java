package br.edu.ufsj.minerva.control;

import android.content.Context;

import br.edu.ufsj.minerva.control.XML;

/**
 * Created by Iago Alvarenga on 25/05/2018.
 */

public class InicializarMidia {
    public InicializarMidia(Context context){
        //preenche os candidatos na memoria
        new XML().readCandidatos(context);
        //carrega as chaves criptograficas

        //carrega o arquivo de salvamento dos candidatos
        new XML().inicializeWriteVoto(context);

    }
}
