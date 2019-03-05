package com.model;

import com.view.Window;
import javax.swing.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;

public class Model {
    private static DefaultListModel<String> _model = new DefaultListModel<>();
//    private BufferedReader _in;
//    private ObjectOutputStream _out;
    private Properties props = new Properties();



//    private void send(String msg) throws IOException {
//
//        if (msg != null) {
//            _model.addElement("C: " + msg);
//            _out.writeObject(msg);
//            _out.flush();
//        }
//
//        String line = _in.readLine();
//        if (line != null) {
//            _model.addElement("S: " + line);
//        }
//    }


    public void Send_actionPerformed() {
        props.put("mail.smtp.host", Window.getSMTP());
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Window.getFrom(), Window.getPassword());
            }
        });
        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(Window.getFrom()));
            InternetAddress[] adres = {new InternetAddress(Window.getTo())};
            msg.setRecipients(Message.RecipientType.TO, adres);
            msg.setSubject(Window.getSubject());
            msg.setSentDate(new Date());
            msg.setText(Window.getBody());
            Transport.send(msg);
//            Socket s = new Socket(Window.getSMTP(), 25);
//            _out = new ObjectOutputStream(.getOutputStream());
//            _in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//            send(null);
//            send("HELO" + InetAddress.getLocalHost().getHostAddress());
//            send("MAIL FROM: " + Window.getFrom());
//            send("RCPT TO: " + Window.getTo());
//            send("DATA");
//            _out.writeObject("Subject: " + Window.getSubject());
//            _out.writeObject(Window.getBody());
//            send(".");
//            _out.close();
//            _in.close();
//            s.close();

        } catch (MessagingException e) {
            _model.addElement("Error: " + e);
            e.printStackTrace();
        }
    }

    public static DefaultListModel<String> getList() {
        return _model;
    }
}
