package br.edu.ufsj.minerva.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ufsj.minerva.R;

public class LiberacaoUrna extends Activity {
    boolean voting = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_votacao);

        //fechar tela de senha ao voltar
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        final EditText etPassword = findViewById(R.id.ed_pwchefe);

        Button bt_liberarurna = findViewById(R.id.bt_liberarurna);


        bt_liberarurna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etPassword.getText().toString().length() == 6 || etPassword.getText().toString().length() == 12){
                    if(etPassword.getText().toString().equals("123456")) {
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
