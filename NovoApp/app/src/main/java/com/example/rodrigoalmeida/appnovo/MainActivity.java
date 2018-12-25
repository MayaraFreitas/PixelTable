package com.example.rodrigoalmeida.appnovo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button connectBtn;
    private ImageView startBtn;
    private Button info;


    //Declaração para o bluetooth
    BluetoothAdapter myBluetooth = null;
    BluetoothDevice myDevice = null;
    BluetoothSocket mySocket = null;

    UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private static final int ENABLED_BLUETOOTH = 1;
    private static final int CONECTION_BLUETOOTH = 2;
    boolean connection = false;

    private static String MAC;

    //Menu Principal
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //ativa tela cheia no app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        connectBtn = (Button) findViewById(R.id.button);
        startBtn = (ImageView) findViewById(R.id.start);
        info = (Button) findViewById(R.id.informacao);

        //area do codigo bluetooth
        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        turnOnBluetooth();

        connectBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v){
                System.out.print("\n\nCONECTADO!!!!!!!!!");
                Toast.makeText(getApplicationContext(), "O dispositivo está conectado!", Toast.LENGTH_SHORT).show();
                //bluetooth sendo checado
                enabledConection();
            }
        });

        startBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v){

                    if(myBluetooth.isEnabled()){
                        //Metodo que confirma o status ddo app para verificar se o botao conectar ja foi clicado
                        if(connection == true){
                            abreJogo();
                        }else{
                            Toast.makeText(getApplicationContext(), "O dispositivo não está conectado!", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(getApplicationContext(), "Ligue seu bluetooth para começar! ", Toast.LENGTH_SHORT).show();
                    }
                    //chama a tela do jogo
                }
        });

        info.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v){
                abreInfo();
            }
        });
    }


    //Acessar página do jogo
    public void abreJogo() {
        //muda para pagina do jogo
        Intent intent = new Intent(this, TelaJogo.class);
        startActivity(intent);

    }

    public void abreInfo() {
        //muda para pagina do jogo
        Intent intentInfo = new Intent(this, Info.class);
        startActivity(intentInfo);

    }





    //Mostrar dispositivos disponiveis para conexão
    public void enabledConection(){
        if(connection){

            try {

                mySocket.close();
                connection = false;

            } catch (IOException erro) {

            }

        }else {
            Intent openListaDipositivos = new Intent(MainActivity.this, ListaDispositivos.class);
            startActivityForResult(openListaDipositivos, CONECTION_BLUETOOTH);
        }
    }

    //Ligando Bluetooth
    public void turnOnBluetooth(){
        if(myBluetooth == null){
            Toast.makeText(getApplicationContext(), "O dispositivo não possuí bluetooth!", Toast.LENGTH_SHORT).show();
        } else if(myBluetooth.isEnabled()){
        }
        if(!myBluetooth.isEnabled()){
            //metodo que confirma o status ddo app para verificar se o botao conectar ja foi clicado
            Intent enabledBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enabledBtIntent, ENABLED_BLUETOOTH);
            //abre a janela de ativação do bluetooth do aparelho
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case CONECTION_BLUETOOTH:
                if (resultCode == Activity.RESULT_OK) {
                    MAC = data.getExtras().getString(ListaDispositivos.ENDERECO_MAC);
                    Toast.makeText(getApplicationContext(), String.valueOf(MAC), Toast.LENGTH_SHORT).show();
                    myDevice = myBluetooth.getRemoteDevice(MAC);

                    try {
                        //faz a conexão com o dispositivo bluetooth
                        mySocket = myDevice.createRfcommSocketToServiceRecord(MY_UUID);

                        mySocket.connect();

                        connection = true;

                        ConnectedThreadSingleton.getInstance().setBluetoothSocket(mySocket);
                        ConnectedThreadSingleton.getInstance().start();

                        //connectBtn.setText("Disconnect");

                        Toast.makeText(getApplicationContext(), "Você está conectado com " + MAC, Toast.LENGTH_SHORT).show();

                    } catch (IOException erro) {
                        //caso nao consiga conectar
                        Toast.makeText(getApplicationContext(), "Erro ao conectar", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Falha ao obter o MAC!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //Gerenciamento de conexão do bluetooth (Aula-7)

}
