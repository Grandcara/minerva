package br.edu.ufsj.minerva.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.edu.ufsj.minerva.R;


public class TotalizacaoVotos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totaldevotos);


        TextView totalDeVotosResultado = findViewById(R.id.tv_totalVotosResultado);

        TextView totalDeVotosValidosResultado = findViewById(R.id.tv_votosValidosResultado);


        TextView totalDeVotosBrancosResultado = findViewById(R.id.tv_votosBrancosResultado);

        TextView totalDeVotosAnuladosResultado = findViewById(R.id.tv_votosAnuladosResultado);


        totalDeVotosResultado.setText("9");
        totalDeVotosValidosResultado.setText("22");
        totalDeVotosBrancosResultado .setText("354");
        totalDeVotosAnuladosResultado.setText("8476");

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


}
