package com.example.rodrigoalmeida.appnovo;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectedThreadSingleton extends Thread{

    private static ConnectedThreadSingleton mInstance;

    public static ConnectedThreadSingleton getInstance(){
        if (mInstance == null)
            mInstance = new ConnectedThreadSingleton();

        return mInstance;
    }

    private InputStream mmInStream;
    private OutputStream mmOutStream;

    private ConnectedThreadSingleton(){}

    public void setBluetoothSocket(BluetoothSocket socket) {
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);
                // Send the obtained bytes to the UI activity
                // mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                //       .sendToTarget();
            } catch (IOException e) {
                break;
            }
        }
    }

    /* Call this from the main activity to send data to the remote device */
    public void enviar(String dadosEnviar) {
        byte[] msgBuffer = dadosEnviar.getBytes();
        try {
            mmOutStream.write(msgBuffer);
        } catch (IOException e) { }
    }
}