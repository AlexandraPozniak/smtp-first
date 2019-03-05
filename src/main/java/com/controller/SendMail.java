package com.controller;

import java.awt.event.*;
import com.model.Model;

public class SendMail implements ActionListener {
   private Model model = new Model();
    public void actionPerformed(ActionEvent e) {
        model.Send_actionPerformed();
    }
}
