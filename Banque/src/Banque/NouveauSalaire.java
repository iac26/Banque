package Banque;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NouveauSalaire implements ActionListener {
	public NouveauSalaire(){
		String choix[] = {"salaire", "impot"};
		String choixVal[] = {"abs","rel"};
		frame = new JFrame("Nouveau Salaire");
		frame.setVisible(true);
		frame.setSize(385, 240);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Container c = frame.getContentPane();
		c.setLayout(null);
		ajouter = new JButton("Creer");
		montant = new JTextField();
		frequence = new JTextField();
		name = new JTextField();
		val = new JComboBox(choixVal);
		labelName = new JLabel("Nom");
		labelMontant = new JLabel("Montant");
		labelFrequence = new JLabel("Frequence");
		labelDest = new JLabel("Destinataire");
		dest = new JComboBox(Banque.cl.keySet().toArray());
		type = new JComboBox(choix);
		val.setBounds(270, 62, 70, 20);
		name.setBounds(140, 30, 200, 25);
		type.setBounds(140, 130, 80, 20);
		ajouter.setBounds(240, 170, 100, 20);
		frequence.setBounds(140, 90, 100, 25);
		montant.setBounds(140, 60, 100, 25);
		dest.setBounds(240, 130, 100, 20);
		labelName.setBounds(40, 30, 200, 25);
		labelFrequence.setBounds(40, 90, 200, 25);
		labelMontant.setBounds(40, 60, 200, 25);
		labelDest.setBounds(40, 130, 100, 20);
		ajouter.addActionListener(this);
		c.add(labelFrequence);
		c.add(labelDest);
		c.add(labelMontant);
		c.add(ajouter);
		c.add(montant);
		c.add(dest);
		c.add(frequence);
		c.add(type);
		c.add(name);
		c.add(labelName);
		c.add(val);
		frame.getRootPane().setDefaultButton(ajouter);
		
		
	}
	
	private JButton ajouter;
	private JTextField montant, frequence, name;
	private JLabel labelMontant, labelFrequence, labelDest, labelName;
	private JComboBox dest, type, val;
	private JFrame frame;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ajouter) {
			String dst = null;
			Compte cpt = null;
			int frq = 0;
			int mont = 0;
			String tpe = null;
			String nme = null;
			String n = null;
			String v = null;
			boolean t = true;
		
			try {
				n = name.getText();
				dst = String.valueOf(dest.getSelectedItem());
				cpt = Banque.cl.get(dst);
				frq = Integer.parseInt(frequence.getText());
				mont = Integer.parseInt(montant.getText());
				tpe = String.valueOf(type.getSelectedItem());
				nme = tpe + " - " + n + ": " + dst;
				v = String.valueOf(val.getSelectedItem());
			}
			catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(frame, "Erreur!!");
				montant.setText("");
				t = false;
			}		
			if (t) {
				Salaire salaire = new Salaire(cpt, mont, frq, tpe, n, dst, v);
				salaire.start();
				Banque.sls.put(nme, salaire);
				Banque.sals.addElement(nme);
				frame.setVisible(false);
			}
			Salaires.save();
			
		}
		
		}
	}

