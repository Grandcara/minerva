package br.edu.ufsj.minerva.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ufsj.minerva.R;
import br.edu.ufsj.minerva.control.EncerramentoEleicoes;
import br.edu.ufsj.minerva.control.InicializarMidia;
import br.edu.ufsj.minerva.model.Candidado;


public class Validacao extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.teladevalidacao);

        //Recupera as informações da main
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int pressButton = bundle.getInt("pressButton");

        final EditText etPassword = findViewById(R.id.ed_pwchefe);

        Button bt_liberarurna = findViewById(R.id.bt_liberarurna);

        bt_liberarurna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPassword.getText().toString().length() == 12){
                    if(etPassword.getText().toString().equals("123456789012")){
                        if(pressButton == 55){
                            etPassword.setText("");
                            Intent intent = new Intent(getApplication(), TotalizacaoVotos.class);
                            startActivityForResult(intent,55);
                        }
                        if(pressButton == 235){
                            etPassword.setText("");
                            //Intent intent = new Intent(getApplication(), InicializarMidia.class);
                            //startActivityForResult(intent,55);
                            if(new Candidado().getCandidatos().size() > 0) {

                                new AlertDialog.Builder(Validacao.this)
                                        .setTitle("Falha!")
                                        .setMessage("Votação ja foi Inicializada.")
                                        .setCancelable(false)
                                        .setPositiveButton("Encerrar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        }).show();
                            }else{
                                new InicializarMidia(getApplicationContext());
                                new AlertDialog.Builder(Validacao.this)
                                        .setTitle("Sucesso!")
                                        .setMessage("Votação Inicializada com sucesso.")
                                        .setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        }).show();
                            }
                        }
                        if(pressButton == 4564){
                            etPassword.setText("");
                            //Intent intent = new Intent(getApplication(), Validacao.class);
                            //startActivityForResult(intent,55);
                            if(new Candidado().getCandidatos().size() > 0) {
                                new EncerramentoEleicoes();
                                new AlertDialog.Builder(Validacao.this)
                                        .setTitle("Sucesso!")
                                        .setMessage("Votação encerradas com sucesso.")
                                        .setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        }).show();
                            }else{
                                new AlertDialog.Builder(Validacao.this)
                                        .setTitle("Falha!")
                                        .setMessage("Votação não foi inicializada.")
                                        .setCancelable(false)
                                        .setPositiveButton("Encerrar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        }).show();
                            }
                        }
                        if(pressButton == 5123432){
                            etPassword.setText("");
                            if(new Candidado().getCandidatos().size() > 0) {
                                Intent intent = new Intent(getApplication(), LiberacaoUrna.class);
                                startActivityForResult(intent, 55);
                                Log.i("Iniciar Eleição","Sucesso");
                            }else {
                                new AlertDialog.Builder(Validacao.this)
                                        .setTitle("Falha!")
                                        .setMessage("Votação não iniciada.")
                                        .setCancelable(false)
                                        .setPositiveButton("Encerrar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        }).show();
                                Log.i("Iniciar Eleição", "Falhou, Midias não inicializadas!");
                            }
                        }
                    }else{
                        new AlertDialog.Builder(Validacao.this)
                                .setTitle("Falha!")
                                .setMessage("Para acesso, contate um adiministrador.")
                                .setCancelable(false)
                                .setPositiveButton("Encerrar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                }).show();

                    }
                }else{
                    new AlertDialog.Builder(Validacao.this)
                            .setTitle("Falha!")
                            .setMessage("Para acesso, contate um adiministrador.")
                            .setCancelable(false)
                            .setPositiveButton("Encerrar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).show();
                }

            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 55) {
            if(resultCode == RESULT_OK) {
                finish();
            }
        }
    }

    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
