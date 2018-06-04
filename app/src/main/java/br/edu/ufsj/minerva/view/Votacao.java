package br.edu.ufsj.minerva.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ufsj.minerva.MainActivity;
import br.edu.ufsj.minerva.R;
import br.edu.ufsj.minerva.control.XML;
import br.edu.ufsj.minerva.model.Candidado;


public class Votacao extends Activity {
    ImageView ivFotoCandidato;
    TextView tvNomeCandidatoNOME;
    TextView tvNomeCandidatoPARTIDO;
    TextView tvNomeCandidatoNUMERO;
    Button confirmarVoto;
    Button cancelarVoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.votacao_candidato);



        //recupera as informaçoes do candidato

        //campo candidatos FOTO
        ivFotoCandidato = findViewById(R.id.iv_candidatovotar);


        //campo candidatos NOME
        tvNomeCandidatoNOME = findViewById(R.id.tv_nomecandidatovotar);


        //campo candidatos PARTIDO
        tvNomeCandidatoPARTIDO = findViewById(R.id.tv_partidovotar);


        //campo cadidatos NUMERO
        tvNomeCandidatoNUMERO = findViewById(R.id.tv_numerocadidatovotar);

        //botoes
        confirmarVoto = findViewById(R.id.bt_confirmar);

        cancelarVoto = findViewById(R.id.bt_voltarvotar);


    }
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        cadidato();
    }

    private void cadidato(){
        Candidado candidatoFinal = null;
        //Recupera as informações da listagem dos candidatos
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int idCandidato = bundle.getInt("candidatoNum");
        for (Candidado canditado :  new Candidado().getCandidatos()) {
            if(canditado.getIdRandom() == idCandidato ){
                candidatoFinal = canditado;
            }
        }
        ivFotoCandidato.setImageResource(R.drawable.avatar);
        tvNomeCandidatoNOME.setText(candidatoFinal.getNome());
        tvNomeCandidatoPARTIDO.setText(candidatoFinal.getPartido());
        tvNomeCandidatoNUMERO.setText(String.valueOf(candidatoFinal.getNumero()));

        confirmarVoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                Log.i("Voto candidato confirmado:"," OK");
                new XML().saveWriteVoto(idCandidato);
                MediaPlayer mp = MediaPlayer.create(Votacao.this, R.raw.encerramentovoto);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }

                });
                mp.start();
                //tela de finalização e musiquinha
                new AlertDialog.Builder(Votacao.this)
                        .setTitle("Sucesso!")
                        .setMessage("Voto salvo com sucesso.")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();

            }
        });

        cancelarVoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Voto candidato Cancelado:"," FAIL");
                finish();
            }
        });

    }
}
