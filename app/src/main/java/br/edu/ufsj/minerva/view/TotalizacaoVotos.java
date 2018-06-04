package br.edu.ufsj.minerva.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.ufsj.minerva.R;
import br.edu.ufsj.minerva.control.XML;
import br.edu.ufsj.minerva.model.Candidado;


public class TotalizacaoVotos extends Activity {

    private TextView totalDeVotosResultado;
    private TextView totalDeVotosValidosResultado;
    private TextView totalDeVotosBrancosResultado;
    private TextView totalDeVotosAnuladosResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totaldevotos);


        totalDeVotosResultado = findViewById(R.id.tv_totalVotosResultado);

        totalDeVotosValidosResultado = findViewById(R.id.tv_votosValidosResultado);


        totalDeVotosBrancosResultado = findViewById(R.id.tv_votosBrancosResultado);

        totalDeVotosAnuladosResultado = findViewById(R.id.tv_votosAnuladosResultado);



        Button btVoltar = findViewById(R.id.bt_Voltar);

        //fechar tela de senha ao voltar
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if(new Candidado().getCandidatos().size() == 0) {
            //recupera os candidatos do arquivo
            new XML().readCandidatos(getApplicationContext());
            ArrayList<Candidado> votos = new XML().readVotos(getApplicationContext());
            int votosValidos = votos.size();
            int votosBrancos = 0;
            int votosNulos = 0;
            totalDeVotosResultado.setText(String.valueOf(votos.size()));
            for (Candidado canditado : votos) {
                if (canditado.getId() == 0) {
                    votosValidos--;
                    votosBrancos++;
                } else if (canditado.getId() == -1) {
                    votosValidos--;
                    votosNulos++;
                }
            }
            //apaga os candidatos da memoria
            new Candidado().getCandidatos().clear();
            totalDeVotosValidosResultado.setText(String.valueOf(votosValidos));
            totalDeVotosBrancosResultado.setText(String.valueOf(votosBrancos));
            totalDeVotosAnuladosResultado.setText(String.valueOf(votosNulos));
        }else{
            totalDeVotosResultado.setText(String.valueOf("Votação em andamento"));
            totalDeVotosValidosResultado.setText(String.valueOf("Votação em andamento"));
            totalDeVotosBrancosResultado.setText(String.valueOf("Votação em andamento"));
            totalDeVotosAnuladosResultado.setText(String.valueOf("Votação em andamento"));
        }
    }
}
