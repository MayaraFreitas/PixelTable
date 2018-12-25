package com.example.rodrigoalmeida.appnovo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Info extends AppCompatActivity {

    private Button info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //ativa tela cheia no app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        info = (Button) findViewById(R.id.information);

        info.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v){
                retornaMenuInicial();
            }
        });
    }

    private void retornaMenuInicial(){
        finish();
    }


}
