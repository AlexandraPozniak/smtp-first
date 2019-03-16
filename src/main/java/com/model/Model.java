package com.model;

import com.view.Window;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Base64;

public class Model {
    private static DefaultListModel<String> _model = new DefaultListModel<>();
    private BufferedReader _in;
    private PrintWriter _out;




    private void send(String msg) throws IOException {

        if (msg != null) {
            _model.addElement("C: " + msg);
            _out.println(msg);
            _out.flush();
        }

        String line = _in.readLine();
        if (line != null) {
            _model.addElement("S: " + line);
        }
    }


    public void Send_actionPerformed() {
        try{
            InetAddress mailhost = InetAddress.getByName(Window.getSMTP());
            InetAddress localhost = InetAddress.getLocalHost();
            SSLSocket socket = (SSLSocket)((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(mailhost, 465);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            _in = new BufferedReader(new InputStreamReader(in));
            _out = new PrintWriter(out, true);
            String login = Base64.getEncoder().encodeToString(Window.getFrom().getBytes());
            String password = Base64.getEncoder().encodeToString(Window.getPassword().getBytes());
            send("HELO " + localhost.getHostName());
            send("AUTH LOGIN " + login);
            send(password);
            send("MAIL FROM:<" + Window.getFrom() + ">");
            send("RCPT TO:<" + Window.getTo() + ">");
            send("DATA ");
            _out.println("Subject: " + Window.getSubject());
            _out.println(Window.getBody());
            send(".");
            send("QUIT");
            socket.close();


        } catch (Exception e){
            _model.addElement("Error: " + e);
        }

    }
    public static DefaultListModel<String> getList() {
        return _model;
    }
}
