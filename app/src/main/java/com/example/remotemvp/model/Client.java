package com.example.remotemvp.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, Void, String> {

    String dstAddress;
    int dstPort;
    String commandClient;
    String nameButton;

    String response = "";
    int TCP_BUFFER_SIZE= 1024*1024;
    Socket socket;
    IClient iClient;

    public Client(String addr, int port, String command, String WHichButton,IClient iClient) {
        dstAddress = addr;
        dstPort = port;
        commandClient=command;
        nameButton = WHichButton;
        this.iClient=iClient;
    }


    @Override
    protected String doInBackground(Void... params) {
        socket = null;
        DataOutputStream out = null;

        try {
//            Log.d("aaa","--------   BEFORE SOCKET   -------");
            Log.d("aaa","connecting...");
            socket = new Socket(dstAddress, dstPort);
            Log.d("aaa","connected");
            socket.setSendBufferSize(TCP_BUFFER_SIZE);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();

            /////  for sending
            out = new DataOutputStream(socket.getOutputStream());
            Log.d("aaa","!!!!!! command in Client !!!!!!    "+commandClient);
            if(nameButton=="STRING"  || nameButton=="STRINgetDATETIME")     { out.writeUTF(commandClient); }
            else                                                            { out.writeUTF(commandClient); }

            /*
             * notice: inputStream.read() will block if no data return
             */
            // for recieving and must be for sending
            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                Log.d("aaa","bytesread...");
                byteArrayOutputStream.write(buffer, 0, bytesRead);
//                Log.d("aaa","byteswrite");
                response += byteArrayOutputStream.toString("UTF-8");
            }


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    protected void onCancelled()
    {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPostExecute(String result)
    {
        iClient.applyResultOfClient(result);
        super.onPostExecute(result);
    }



}

