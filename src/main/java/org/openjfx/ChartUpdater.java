package org.openjfx;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChartUpdater extends Thread {
    private MyChart myChart;

    public ChartUpdater(MyChart myChart) {
        this.myChart = myChart;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(7000);
            Socket s = ss.accept();  //Establish connection
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = (String) dis.readUTF();
            Thread updater = new Thread(new Runnable() {
                @Override
                public void run() {
                    myChart.add(Double.valueOf(str));
                }
            });
            updater.setDaemon(true);
            updater.start();
            Platform.runLater(updater);
            System.out.println("message=" + str);
            ss.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}


