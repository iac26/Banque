package Banque;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Versement implements ActionListener {
	public Versement(Compte n) {
		cpt = n;
		frame = new JFrame(cpt.getName()+" - Versement");
		frame.setVisible(true);
		frame.setSize(385, 220);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Container c = frame.getContentPane();
		c.setLayout(null);
		verser = new JButton("Verser");
		montant = new JTextField();
		labelDest = new JLabel("Destinataire");
		labelMontant = new JLabel("Montant");
		dest = new JComboBox(Banque.cl.keySet().toArray());
		verser.setBounds(240, 140, 100, 30);
		montant.setBounds(140, 40, 200, 25);
		labelMontant.setBounds(40, 40, 200, 25);
		dest.setBounds(240, 100, 100, 20);
		labelDest.setBounds(40, 100, 100, 20);
		verser.addActionListener(this);
		c.add(verser);
		c.add(labelDest);
		c.add(labelMontant);
		c.add(montant);
		c.add(dest);
		frame.getRootPane().setDefaultButton(verser);
	}
	private Compte cpt;
	private JFrame frame;
	private JButton verser;
	private JTextField montant;
	private JComboBox dest; 
	private JLabel labelDest, labelMontant;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean t = true;
			int n = 0;
			try {
				n = Integer.parseInt(montant.getText());
			}
			catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(frame, "Erreur!! Nombre entier requis!");
				montant.setText("");
				t = false;
			}
			
			if (t){
				cpt.retrait(n);
				Compte cptd = Banque.cl.get(dest.getSelectedItem());
				String des = String.valueOf(dest.getSelectedItem());
				cptd.depot(n);
				String str = "Versement vers " + des;
				cpt.addHItem(Banque.TextAlign(str, n, "-"));
				str = "Versement depuis " + cpt.getName();
				cptd.addHItem(Banque.TextAlign(str, n, "+"));
				frame.setVisible(false);
				cpt.save();
			}
	}
}
