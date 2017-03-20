package Banque;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NouveauCompte implements ActionListener{
	public NouveauCompte() {
		frame = new JFrame("Nouveau Compte");
		frame.setVisible(true);
		frame.setSize(385, 180);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Container c = frame.getContentPane();
		c.setLayout(null);
		ajouter = new JButton("Creer");
		nameField = new JTextField();
		labelNameField = new JLabel("Nom");
		ajouter.setBounds(240, 100, 100, 20);
		nameField.setBounds(140, 40, 200, 25);
		labelNameField.setBounds(40, 40, 200, 25);
		ajouter.addActionListener(this);
		c.add(ajouter);
		c.add(labelNameField);
		c.add(nameField);
		frame.getRootPane().setDefaultButton(ajouter);
		
		
	}
	
	private JButton ajouter;
	private JTextField nameField;
	private String name;
	private JFrame frame;
	private JLabel labelNameField;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		name = nameField.getText();
		Compte compte = new Compte(name);
		Banque.cl.put(name, compte);
		Index.lst.addElement(name);
		Compte.saveAll();
		System.out.println("done");
		frame.setVisible(false);
		compte.save();
		
		
	} 

}
