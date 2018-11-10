package com.example.rodrigoalmeida.appnovo;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

public class ListaDispositivos extends ListActivity {

    private BluetoothAdapter myBluetooth2 = null;

    static String ENDERECO_MAC = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        myBluetooth2 = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = myBluetooth2.getBondedDevices();

        if (dispositivosPareados.size() > 0){
            for (BluetoothDevice dispositivo : dispositivosPareados) {
                String nomeBt = dispositivo.getName();
                String macBt = dispositivo.getAddress();
                ArrayBluetooth.add(nomeBt + "\n" + macBt);
            }
        }
        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String informacaoGeral = ((TextView) v).getText().toString();

        // Toast.makeText(getApplicationContext(), "info: " + informacaoGeral, Toast.LENGTH_LONG).show();

        String enderecoMac = informacaoGeral.substring(informacaoGeral.length() - 17);

        //Toast.makeText(getApplicationContext(), "info: " + enderecoMac, Toast.LENGTH_LONG).show();

        Intent retornaMac = new Intent();
        retornaMac.putExtra(ENDERECO_MAC, enderecoMac);
        setResult(RESULT_OK, retornaMac);
        finish();
    }
}

