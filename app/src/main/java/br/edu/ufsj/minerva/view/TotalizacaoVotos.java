package br.edu.ufsj.minerva.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.edu.ufsj.minerva.R;
import br.edu.ufsj.minerva.control.XML;
import br.edu.ufsj.minerva.model.Candidado;


public class TotalizacaoVotos extends Activity {

    private TextView totalDeVotosResultado;
    private TextView totalDeVotosValidosResultado;
    private TextView totalDeVotosBrancosResultado;
    private TextView totalDeVotosAnuladosResultado;

    private TextView totalDeVotoscand1;
    private TextView totalDeVotoscand2;
    private TextView totalDeVotoscand3;
    private TextView totalDeVotoscand4;

    private TextView candidato1;
    private TextView candidato2;
    private TextView candidato3;
    private TextView candidato4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totaldevotos);


        totalDeVotosResultado = findViewById(R.id.tv_totalVotosResultado);
        totalDeVotosValidosResultado = findViewById(R.id.tv_votosValidosResultado);
        totalDeVotosBrancosResultado = findViewById(R.id.tv_votosBrancosResultado);
        totalDeVotosAnuladosResultado = findViewById(R.id.tv_votosAnuladosResultado);

        totalDeVotoscand1 = findViewById(R.id.tv_votos_cand1);
        totalDeVotoscand2 = findViewById(R.id.tv_votos_cand2);
        totalDeVotoscand3 = findViewById(R.id.tv_votos_cand3);
        totalDeVotoscand4 = findViewById(R.id.tv_votos_cand4);

        candidato1 = findViewById(R.id.tv_cand1);
        candidato2 = findViewById(R.id.tv_cand2);
        candidato3 = findViewById(R.id.tv_cand3);
        candidato4 = findViewById(R.id.tv_cand4);

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
        Map<String, Integer> hash = new HashMap<String, Integer>();

        if(new Candidado().getCandidatos().size() == 0) {
            //recupera os candidatos do arquivo
            new XML().readCandidatos(getApplicationContext());
            for (Candidado canditado : new Candidado().getCandidatos()) {
                hash.put(canditado.getNome(), 0);
                if(canditado.getId() == 1) {
                    candidato1.setText(canditado.getNome());
                }else if(canditado.getId() == 2) {
                    candidato2.setText(canditado.getNome());
                }else if(canditado.getId() == 3) {
                    candidato3.setText(canditado.getNome());
                }else if(canditado.getId() == 4) {
                    candidato4.setText(canditado.getNome());
                }
            }
            totalDeVotoscand1.setText(String.valueOf(0));
            totalDeVotoscand2.setText(String.valueOf(0));
            totalDeVotoscand3.setText(String.valueOf(0));
            totalDeVotoscand4.setText(String.valueOf(0));

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
                }else {
                    if(hash.containsKey(canditado.getNome())){
                        int value = hash.get(canditado.getNome()) + 1;
                        hash.remove(canditado.getNome());
                        hash.put(canditado.getNome(), value);
                        if(canditado.getId() == 1) {
                            totalDeVotoscand1.setText(String.valueOf(value));
                        }else if(canditado.getId() == 2) {
                            totalDeVotoscand2.setText(String.valueOf(value));
                        }else if(canditado.getId() == 3) {
                            totalDeVotoscand3.setText(String.valueOf(value));
                        }else if(canditado.getId() == 4) {
                            totalDeVotoscand4.setText(String.valueOf(value));
                        }
                    }
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
