package com.example.rodrigoalmeida.appnovo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class TelaDerrota extends AppCompatActivity {

    private Button retornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_derrota);

        //ativa tela cheia no app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        retornar = (Button) findViewById(R.id.retornar);

        retornar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v){
                retornaMenuInicial();
            }
        });
    }

    private void retornaMenuInicial(){
        finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }
}
