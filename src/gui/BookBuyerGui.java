package gui;

import agents.BookBuyerAgent;
import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import agents.BookSellerAgent;

public class BookBuyerGui extends JFrame {
	//private BookSellerAgent myAgent;
	private BookBuyerAgent myAgent;
        
	//private JTextField titleField, priceField;
	private JTextField titleField;
        
        public BookBuyerGui(BookBuyerAgent a){
	//public BookBuyerGui(BookSellerAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("Book title:"));
		titleField = new JTextField(15);
		p.add(titleField);
		//p.add(new JLabel("Price:"));
		//priceField = new JTextField(15);
		//p.add(priceField);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String title = titleField.getText().trim();
					//String price = priceField.getText().trim();
					
					myAgent.setBookTitle(title);
                                        System.out.println("Libro que desea comprar: " + title);
					//titleField.setText("");
					//priceField.setText("");
				}catch(Exception e) {
					JOptionPane.showMessageDialog(BookBuyerGui.this, "Invalid values","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
		    //myAgent.doDelete();
                    myAgent.doSuspend();
                    myAgent.doActivate();
		  }
		});
		
		setResizable(false);
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
