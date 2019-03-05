package com.view;

import javax.swing.*;
import com.controller.SendMail;
import com.model.Model;

public class Window extends JFrame {
    private JLabel JLabel1 = new JLabel();
    private JLabel JLabel2 = new JLabel();
    private JLabel JLabel3 = new JLabel();
    private JLabel JLabel4 = new JLabel();
    private JLabel JLabel5 = new JLabel();
    public static JTextField _from = new JTextField();
    public static JTextField _to = new JTextField();
    public static JTextField _subject = new JTextField();
    public static JTextArea _body = new JTextArea();
    public static JTextField _smtp = new JTextField();
//    public static JTextField _login = new JTextField();
    public static JTextField _password = new JTextField();
    public JList<String> _output = new JList<>();
    JScrollPane _scrollPane = new JScrollPane();
    JScrollPane _scrollPane2 = new JScrollPane();
    public JButton Send = new JButton();
    public JButton Cancel = new JButton();
//    Model model;

    public Window() {
        setTitle("Send Mail");
        getContentPane().setLayout(null);
        setSize(750, 350);
        JLabel1.setText("From: ");
        getContentPane().add(JLabel1);
        JLabel1.setBounds(12, 12, 36, 12);
        JLabel2.setText("To: ");
        getContentPane().add(JLabel2);
        JLabel2.setBounds(12, 48, 36, 12);
        JLabel3.setText("Subject: ");
        getContentPane().add(JLabel3);
        JLabel3.setBounds(12, 84, 48, 12);
        JLabel4.setText("SMTP Server: ");
        getContentPane().add(JLabel4);
        JLabel4.setBounds(12, 120, 84, 12);
        JLabel5.setText("Password: ");
        getContentPane().add(JLabel5);
        JLabel5.setBounds(12, 156, 36, 12);
        getContentPane().add(_from);
        _from.setBounds(96, 12, 300, 24);
        getContentPane().add(_to);
        _to.setBounds(96, 48, 300, 24);
        getContentPane().add(_subject);
        _subject.setBounds(96, 84, 300, 24);
        getContentPane().add(_smtp);
        _smtp.setBounds(96, 120, 300, 24);
        getContentPane().add(_password);
        _password.setBounds(96, 156, 300, 24);
        getContentPane().add(_scrollPane2);
        _scrollPane2.setBounds(12, 156, 384, 108);
        _body.setText("Enter your message here.");
        _scrollPane2.getViewport().add(_body);
        _body.setBounds(0, 0, 381, 105);
        Send.setText("Send");
        Send.setActionCommand("Send");
        getContentPane().add(Send);
        Send.setBounds(60, 276, 132, 24);
        Cancel.setText("Cancel");
        Cancel.setActionCommand("Cancel");
        getContentPane().add(Cancel);
        Cancel.setBounds(216, 276, 120, 24);
        getContentPane().add(_scrollPane);
        _scrollPane.setBounds(408, 12, 312, 288);
        getContentPane().add(_output);
        _output.setBounds(408, 12, 309, 285);
        _output.setModel(Model.getList());
        Model.getList().addElement("Server output displayed here:");
        _scrollPane.getViewport().setView(_output);
        _scrollPane2.getViewport().setView(_body);
        setVisible(true);

        SendMail sendMail = new SendMail();
        Send.addActionListener(sendMail);


    }

    public static String getSMTP(){
        return _smtp.getText();
    }

    public static String getBody(){
        return _body.getText();
    }

    public static String getSubject(){
        return _subject.getText();
    }

    public static String getFrom(){
        return _from.getText();
    }

    public static String getTo(){
        return _to.getText();
    }

    public static String getPassword(){ return _password.getText(); }


}
