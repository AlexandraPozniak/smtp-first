package com.model;

import com.view.Window;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Model {
    private static DefaultListModel<String> _model = new DefaultListModel<>();
    private BufferedReader _in;
    private ObjectOutputStream _out;

    private void send(String msg) throws IOException {

        if (msg != null) {
            _model.addElement("C: " + msg);
            _out.writeObject(msg);
            _out.flush();
        }

        String line = _in.readLine();
        if (line != null) {
            _model.addElement("S: " + line);
        }
    }


    public void Send_actionPerformed() {
        try {
            Socket s = new Socket(Window.getSMTP(), 25);
            _out = new ObjectOutputStream(s.getOutputStream());
            _in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            send(null);
            send("HELO" + InetAddress.getLocalHost().getHostAddress());
            send("MAIL FROM: " + Window.getFrom());
            send("RCPT TO: " + Window.getTo());
            send("DATA");
            _out.writeObject("Subject: " + Window.getSubject());
            _out.writeObject(Window.getBody());
            send(".");
            _out.close();
            _in.close();
            s.close();

        } catch (Exception e) {
            _model.addElement("Error: " + e);
        }
    }

    public static DefaultListModel<String> getList() {
        return _model;
    }
}
