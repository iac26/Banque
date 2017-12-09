package Banque;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ModifierSalaire implements ActionListener{
	public ModifierSalaire(Salaire s){
		salaire = s;
		String choix[] = {salaire.getType()};
		String cdest[] = {salaire.getCName()};
		String choixVal[] = {"abs","rel"};
		frame = new JFrame("Modifier Salaire");
		frame.setVisible(true);
		frame.setSize(385, 240);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Container c = frame.getContentPane();
		c.setLayout(null);
		ajouter = new JButton("Modifier");
		montant = new JTextField(String.valueOf(salaire.getMontant()));
		frequence = new JTextField(String.valueOf(salaire.getFrq()));
		name = new JTextField(String.valueOf(salaire.getName()));
		name.setEditable(false);
		labelName = new JLabel("Nom");
		labelMontant = new JLabel("Montant");
		labelFrequence = new JLabel("Frequence");
		labelDest = new JLabel("Destinataire");
		dest = new JComboBox(cdest);
		type = new JComboBox(choix);
		val = new JComboBox(choixVal);
		val.setSelectedItem(salaire.getVal());
		frequence.setEnabled(false);
		name.setBounds(140, 30, 200, 25);
		type.setBounds(140, 130, 80, 20);
		ajouter.setBounds(240, 170, 100, 20);
		frequence.setBounds(140, 90, 100, 25);
		montant.setBounds(140, 60, 100, 25);
		val.setBounds(280, 62, 70, 20);
		dest.setBounds(240, 130, 100, 20);
		labelName.setBounds(40, 30, 200, 25);
		labelFrequence.setBounds(40, 90, 200, 25);
		labelMontant.setBounds(40, 60, 200, 25);
		labelDest.setBounds(40, 130, 100, 20);
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
		ajouter.addActionListener(this);
		
		
	}
	
	private JButton ajouter;
	private JTextField montant, frequence, name;
	private JLabel labelMontant, labelFrequence, labelDest, labelName;
	private JComboBox dest, type, val;
	private JFrame frame;
	private Salaire salaire;


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ajouter) {
			int frq = 0;
			int mont = 0;
			boolean t = true;
		
			try {
				frq = Integer.parseInt(frequence.getText());
				mont = Integer.parseInt(montant.getText());
			}
			catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(frame, "Erreur!!");
				t = false;
			}		
			if (t) {
				salaire.setFrq(frq);
				salaire.setMontant(mont);
				frame.setVisible(false);
			}
			
		}
		
		}

}
