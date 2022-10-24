package org.openjfx;
import java.io.*;
import java.net.*;

public class MyServer {
    public static void main (String[] args){
        try {
            ServerSocket ss = new ServerSocket(7000);
            Socket s = ss.accept();  //Establish connection
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = (String) dis.readUTF();
            System.out.println("message="+str);
            ss.close();
        }catch(Exception e){
            System.err.println(e);}
        }
        }


