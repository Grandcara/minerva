package br.edu.ufsj.minerva.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

import br.edu.ufsj.minerva.R;
import br.edu.ufsj.minerva.control.XML;
import br.edu.ufsj.minerva.criptografia.Ed25519;


public class LiberacaoUrna extends Activity {
    boolean voting = true;
  //  static {
    //    System.loadLibrary("Ed25519-lib");
   // }
    static ArrayList<String> cadidatos = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_votacao);
        //Assinatura com ed25519 (curva eliptica)
        //chave publica
        String pks = "";
        String publickey = "D75A980182B10AB7D54BFED3C964073A0EE172F3DAA62325AF021A68F707511A";

        final byte[] sk = {(byte)0xd7, (byte)0x5a, (byte)0x98, (byte)0x01, (byte)0x82, (byte)0xb1, (byte)0x0a, (byte)0xb7,
                (byte)0xd5, (byte)0x4b, (byte)0xfe, (byte)0xd3, (byte)0xc9, (byte)0x64, (byte)0x07, (byte)0x3a,
                (byte)0x0e, (byte)0xe1, (byte)0x72, (byte)0xf3, (byte)0xda, (byte)0xa6, (byte)0x23, (byte)0x25,
                (byte)0xaf, (byte)0x02, (byte)0x1a, (byte)0x68, (byte)0xf7, (byte)0x07, (byte)0x51, (byte)0x1a};

        final byte[] pk = Ed25519.publickey(sk);

        //fechar tela de senha ao voltar
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        final EditText etPassword = findViewById(R.id.ed_pwchefe);

        Button bt_liberarurna = findViewById(R.id.bt_liberarurna);
        //assinatura
        final byte[] signature = {(byte)0x7b,(byte)0xfe,(byte)0x47,(byte)0x07,(byte)0x1C,(byte)0x3A,(byte)0x9F,(byte)0x4D,(byte)0x7D,(byte)0x55,(byte)0x7B,(byte)0xE9,(byte)0x0A,(byte)0xE3,(byte)0x36,(byte)0x82,
                (byte)0xC7,(byte)0x5C,(byte)0x98,(byte)0xED,(byte)0xA3,(byte)0xCE,(byte)0xC6,(byte)0x2A,(byte)0xC7,(byte)0xCB,(byte)0xA7,0x63,(byte)0xCA,(byte)0x43,(byte)0x57,(byte)0x35,
                (byte)0x08,(byte)0xCF,(byte)0x2C,(byte)0x23,(byte)0x85,(byte)0x13,(byte)0x07,(byte)0x85,(byte)0x35,(byte)0x04,(byte)0x09,(byte)0xAE,(byte)0xB1,(byte)0xD3,(byte)0x82,(byte)0xB7,(byte)0xE5,(byte)0xCD,(byte)0x62,(byte)0x2A,0x7A
                ,(byte)0xED,(byte)0xBD,(byte)0x59,(byte)0xD9,(byte)0xB3,(byte)0xBD,(byte)0x88,(byte)0xB0,(byte)0xC8,(byte)0xD7,(byte)0x02};
        final byte[] msg = {0x31,0x32,0x31,0x33,0x30,0x35,0x30,0x32,0x36,0x30,0x31};
        bt_liberarurna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etPassword.getText().toString().length() == 9 || etPassword.getText().toString().length() == 12){
                    boolean acesso = false;

                    Byte [] assinatura;

                    try {

                        acesso = cadidatos.contains(etPassword.getText().toString());

                        Log.i("Tentativa de aceeso: ", ""+acesso + " = "+ Arrays.toString(etPassword.getText().toString().getBytes()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(!acesso && !etPassword.getText().toString().equals("123456789012")) {
                        cadidatos.add(etPassword.getText().toString());

                        voting = false;
                        etPassword.setText("");
                        Intent intent = new Intent(getApplication(), CandidatoListagem.class);
                        startActivity(intent);

                    }else if(etPassword.getText().toString().equals("123456789012")){
                        etPassword.setText("");
                        voting = false;
                        finish();

                    }else
                        etPassword.setText("");

                }else
                    etPassword.setText("");
            }

        });

    }

    protected void onPause() {
        super.onPause();
        if(voting) {
            Intent intent = new Intent(getApplication(), LiberacaoUrna.class);
            startActivity(intent);
        }
        voting = true;
    }

    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
