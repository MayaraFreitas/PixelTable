package com.example.rodrigoalmeida.appnovo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class TelaJogo extends AppCompatActivity {

    Random random = new Random();

    //propriedades do jogo
    private static int clique;
    private String stringCores = "";

    //declaração de botões
    private Button redBtn;
    private Button blueBtn;
    private Button greenBtn;
    private Button yellowBtn;


    public TelaJogo(){

        geraString();

        //ConnectedThreadSingleton.getInstance().enviar("BAYG");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo);

        redBtn = (Button) findViewById(R.id.red);
        blueBtn = (Button) findViewById(R.id.blue);
        greenBtn = (Button) findViewById(R.id.green);
        yellowBtn = (Button) findViewById(R.id.yellow);

        redBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v){
                confereSequencia("R");
            }
        });

        blueBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v){
                confereSequencia("B");
            }
        });

        greenBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v){
                confereSequencia("G");
            }
        });

        yellowBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v){
                confereSequencia("Y");
            }
        });
    }

    //acrescenta nova letra à string
    public void geraString(){

        int numeroRandom = random.nextInt(4);

        switch (numeroRandom){
            case 0:
                stringCores += "R";
                break;
            case 1:
                stringCores += "B";
                break;
            case 2:
                stringCores += "G";
                break;
            case 3:
                stringCores += "Y";
                break;
        }

        System.out.print("\n\n\n -----------------------------String: " + stringCores + "\n\n\n\n");

        ConnectedThreadSingleton.getInstance().enviar(stringCores);
    }

    //confere o botao clicado
    public void confereSequencia(String botaoClicado){
        System.out.print("\n\n\n||||||||||||||||||||||||||||||||||||||||||>" + stringCores);
        System.out.print("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>" + String.valueOf(stringCores.charAt(clique)) + " | " + botaoClicado);

        if(String.valueOf(stringCores.charAt(clique)).equals(botaoClicado)){
            System.out.print("\n\n\n---------------Certo\n");
            clique++;

            if(clique == stringCores.length()){
                System.out.print("\n\n\n\n--------------Jogo Finalizado");
                clique = 0;

                geraString();
            }
        }else{
            System.out.print("\n\n\n---------------ERROUUUUUUUU\n");

            stringCores = "";
            geraString();
        }
    }



}
