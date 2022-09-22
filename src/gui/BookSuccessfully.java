/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import behaviours.RequestPerformer;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 *
 */
public class BookSuccessfully extends JFrame{
    
    public BookSuccessfully(String bookTitle, String agent, int price){
        JPanel p = new JPanel();
        JButton b = new JButton("Ok");
        p.setLayout(new GridLayout(5, 1));
        p.add(new JLabel("CONFIRMACIÓN DE COMPRA"));
	p.add(new JLabel("Libro: " + bookTitle));
        p.add(new JLabel("Vendido por: " + agent));
        p.add(new JLabel("Al precio de $" + price));
        p.add(b);
        getContentPane().add(p, BorderLayout.CENTER);
        
	b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
            
        }); 
	
        
    }
    
    public void showGui() {
	  pack();
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  int centerX = (int)screenSize.getWidth() / 2;
	  int centerY = (int)screenSize.getHeight() / 2;
	  
	  setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	  super.setVisible(true);
    }
    
}
