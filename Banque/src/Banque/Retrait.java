package Banque;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Retrait implements ActionListener {
	public Retrait(Compte n) {
		cpt = n;
		frame = new JFrame(cpt.getName()+" - Retrait");
		frame.setVisible(true);
		frame.setSize(385, 180);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Container c = frame.getContentPane();
		c.setLayout(null);
		retrait = new JButton("Retirer");
		montant = new JTextField();
		labelMontant = new JLabel("Montant");
		retrait.setBounds(240, 100, 100, 20);
		montant.setBounds(140, 40, 200, 25);
		labelMontant.setBounds(40, 40, 200, 25);
		retrait.addActionListener(this);
		c.add(retrait);
		c.add(montant);
		c.add(labelMontant);
		frame.getRootPane().setDefaultButton(retrait);
	}
	private Compte cpt;
	private JFrame frame;
	private JButton retrait;
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
			
			if(t) {
				cpt.retrait(n);
				cpt.addHItem(Banque.TextAlign("Retrait", n, "-"));
				frame.setVisible(false);
				cpt.save();
		}
	}

}
