package Banque;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Depot implements ActionListener {
	public Depot(Compte n) {
		cpt = n;
		frame = new JFrame(cpt.getName()+" - Depot");
		frame.setVisible(true);
		frame.setSize(385, 180);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Container c = frame.getContentPane();
		c.setLayout(null);
		depot = new JButton("Deposer");
		montant = new JTextField();
		labelMontant = new JLabel("Montant");
		depot.setBounds(240, 100, 100, 20);
		labelMontant.setBounds(40, 40, 200, 25);
		montant.setBounds(140, 40, 200, 25);
		depot.addActionListener(this);
		c.add(depot);
		c.add(labelMontant);
		c.add(montant);
		frame.getRootPane().setDefaultButton(depot);
	}
	private Compte cpt;
	private JFrame frame;
	private JButton depot;
	private JTextField montant;
	private JLabel labelMontant;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int n = 0;
		boolean t = true;
			try {
				n = Integer.parseInt(montant.getText());
			}
			catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(frame, "Erreur!! Nombre entier requis!");
				montant.setText("");
				t = false;
			}		
			if (t){
				cpt.depot(n);
				cpt.addHItem(Banque.TextAlign("Depot", n, "+"));
				frame.setVisible(false);
				cpt.save();
				}
		}
	

}
