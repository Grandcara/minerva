package br.edu.ufsj.minerva;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import br.edu.ufsj.minerva.control.XML;
import br.edu.ufsj.minerva.criptografia.Ed25519;
import br.edu.ufsj.minerva.criptografia.RSA;
import br.edu.ufsj.minerva.model.RSAModel;
import br.edu.ufsj.minerva.view.Validacao;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicialização do RSA
        RSAModel rsa = new RSAModel();
        rsa.inicializar();
        try {
            RSAModel.arquivoCriptografico.GenerateKeys();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Button btLiberacaoVt = findViewById(R.id.bt_liberacao);
        Button btTotalizarVt = findViewById(R.id.bt_totalvt);
        Button btIniciarVt = findViewById(R.id.bt_iniciovt);
        Button btEncerramentoVt = findViewById(R.id.bt_encerravt);

        btLiberacaoVt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Validacao.class);
                Bundle bundle = new Bundle();
                int btInfo = 235;
                bundle.putInt("pressButton", btInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btTotalizarVt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Validacao.class);
                //Cria a class responsavel por armazenar as informações que vão passar a tela A para B
                Bundle bundle = new Bundle();
                int btInfo = 55;
                bundle.putInt("pressButton", btInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        btIniciarVt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cria a intent de transição da tela A para B
                Intent intent = new Intent(getApplication(), Validacao.class);
                Bundle bundle = new Bundle();
                int btInfo = 5123432;
                bundle.putInt("pressButton", btInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        btEncerramentoVt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Validacao.class);
                Bundle bundle = new Bundle();
                int btInfo = 4564;
                bundle.putInt("pressButton", btInfo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
