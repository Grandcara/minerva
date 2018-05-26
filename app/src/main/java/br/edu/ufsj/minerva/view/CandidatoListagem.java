package br.edu.ufsj.minerva.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.ufsj.minerva.R;

import br.edu.ufsj.minerva.model.Candidado;


public class CandidatoListagem extends Activity{

    boolean voting = true;
    View includedLayoutCandidato1;
    View includedLayoutCandidato2;
    View includedLayoutCandidato3;
    View includedLayoutCandidato4;

    ImageView ivFotoCandidato1;
    ImageView ivFotoCandidato2;
    ImageView ivFotoCandidato3;
    ImageView ivFotoCandidato4;

    TextView tvNomeCandidatoNOME1;
    TextView tvNomeCandidatoNOME2;
    TextView tvNomeCandidatoNOME3;
    TextView tvNomeCandidatoNOME4;

    TextView tvNomeCandidatoPARTIDO1;
    TextView tvNomeCandidatoPARTIDO2;
    TextView tvNomeCandidatoPARTIDO3;
    TextView tvNomeCandidatoPARTIDO4;

    TextView tvNomeCandidatoNUMERO1;
    TextView tvNomeCandidatoNUMERO2;
    TextView tvNomeCandidatoNUMERO3;
    TextView tvNomeCandidatoNUMERO4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_candidatos);
        //layouts candidatos
        includedLayoutCandidato1 = findViewById(R.id.cadidato1);
        includedLayoutCandidato2 = findViewById(R.id.cadidato2);
        includedLayoutCandidato3 = findViewById(R.id.cadidato3);
        includedLayoutCandidato4 = findViewById(R.id.cadidato4);

        //campo candidatos FOTO
        ivFotoCandidato1 = includedLayoutCandidato1.findViewById(R.id.iv_candidato);
        ivFotoCandidato2 = includedLayoutCandidato2.findViewById(R.id.iv_candidato);
        ivFotoCandidato3 = includedLayoutCandidato3.findViewById(R.id.iv_candidato);
        ivFotoCandidato4 = includedLayoutCandidato4.findViewById(R.id.iv_candidato);

        //campo candidatos NOME
        tvNomeCandidatoNOME1 = includedLayoutCandidato1.findViewById(R.id.tv_nomecandidato);
        tvNomeCandidatoNOME2 = includedLayoutCandidato2.findViewById(R.id.tv_nomecandidato);
        tvNomeCandidatoNOME3 = includedLayoutCandidato3.findViewById(R.id.tv_nomecandidato);
        tvNomeCandidatoNOME4 = includedLayoutCandidato4.findViewById(R.id.tv_nomecandidato);

        //campo candidatos PARTIDO
        tvNomeCandidatoPARTIDO1 = includedLayoutCandidato1.findViewById(R.id.tv_partido);
        tvNomeCandidatoPARTIDO2 = includedLayoutCandidato2.findViewById(R.id.tv_partido);
        tvNomeCandidatoPARTIDO3 = includedLayoutCandidato3.findViewById(R.id.tv_partido);
        tvNomeCandidatoPARTIDO4 = includedLayoutCandidato4.findViewById(R.id.tv_partido);

        //campo cadidatos NUMERO
        tvNomeCandidatoNUMERO1 = includedLayoutCandidato1.findViewById(R.id.tv_numerocadidato);
        tvNomeCandidatoNUMERO2 = includedLayoutCandidato2.findViewById(R.id.tv_numerocadidato);
        tvNomeCandidatoNUMERO3 = includedLayoutCandidato3.findViewById(R.id.tv_numerocadidato);
        tvNomeCandidatoNUMERO4 = includedLayoutCandidato4.findViewById(R.id.tv_numerocadidato);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                finish();
            }else {
                preencherDados();
            }
        }
    }
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        preencherDados();
    }
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

    }

    protected void onPause() {
        super.onPause();
        if(voting) {
            Intent intent = new Intent(getApplication(), CandidatoListagem.class);
            startActivity(intent);
        }
        voting = true;
    }
    private void preencherDados(){
        ArrayList<Candidado> candidatos = new Candidado().getCandidatos();
        final int idRandom1 = candidatos.get(0).getIdRandom();
        final int idRandom2 = candidatos.get(1).getIdRandom();
        final int idRandom3 = candidatos.get(2).getIdRandom();
        final int idRandom4 = candidatos.get(3).getIdRandom();

        ivFotoCandidato1.setImageResource(R.drawable.avatar);
        ivFotoCandidato2.setImageResource(R.drawable.avatar);
        ivFotoCandidato3.setImageResource(R.drawable.avatar);
        ivFotoCandidato4.setImageResource(R.drawable.avatar);


        tvNomeCandidatoNOME1.setText(candidatos.get(0).getNome());
        tvNomeCandidatoNOME2.setText(candidatos.get(1).getNome());
        tvNomeCandidatoNOME3.setText(candidatos.get(2).getNome());
        tvNomeCandidatoNOME4.setText(candidatos.get(3).getNome());

        tvNomeCandidatoPARTIDO1.setText(candidatos.get(0).getPartido());
        tvNomeCandidatoPARTIDO2.setText(candidatos.get(1).getPartido());
        tvNomeCandidatoPARTIDO3.setText(candidatos.get(2).getPartido());
        tvNomeCandidatoPARTIDO4.setText(candidatos.get(3).getPartido());

        tvNomeCandidatoNUMERO1.setText(String.valueOf(candidatos.get(0).getNumero()));
        tvNomeCandidatoNUMERO2.setText(String.valueOf(candidatos.get(1).getNumero()));
        tvNomeCandidatoNUMERO3.setText(String.valueOf(candidatos.get(2).getNumero()));
        tvNomeCandidatoNUMERO4.setText(String.valueOf(candidatos.get(3).getNumero()));

        includedLayoutCandidato1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voting = false;
                Log.i("listagem candidato:","click 1, random number="+new Candidado().getCandidatos().get(0).getIdRandom());
                Intent intent = new Intent(getApplication(), Votacao.class);
                Bundle bundle = new Bundle();
                int btInfo = 1;
                bundle.putInt("candidatoNum", idRandom1);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });

        includedLayoutCandidato2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voting = false;
                Log.i("listagem candidato:"," 2");
                Intent intent = new Intent(getApplication(), Votacao.class);
                Bundle bundle = new Bundle();
                int btInfo = 2;
                bundle.putInt("candidatoNum", idRandom2);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });

        includedLayoutCandidato3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voting = false;
                Log.i("listagem candidato:"," 3");
                Intent intent = new Intent(getApplication(), Votacao.class);
                Bundle bundle = new Bundle();
                int btInfo = 3;
                bundle.putInt("candidatoNum", idRandom3);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });

        includedLayoutCandidato4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voting = false;
                Log.i("listagem candidato:"," 4");
                Intent intent = new Intent(getApplication(), Votacao.class);
                Bundle bundle = new Bundle();
                int btInfo = 4;
                bundle.putInt("candidatoNum", idRandom4);
                intent.putExtras(bundle);

                startActivityForResult(intent,1);
            }
        });
    }
}
