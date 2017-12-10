package Banque;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class ModifierSalaire implements ActionListener {
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
		labelFrequence = new JLabel("Periode");
		labelDest = new JLabel("Destinataire");
		labelSeconds = new JLabel("sec");
		labelPercent = new JLabel("");
		dest = new JComboBox(cdest);
		type = new JComboBox(choix);
		val = new JComboBox(choixVal);
		val.setSelectedItem(salaire.getVal());
		frequence.setEditable(false);
		name.setBounds(140, 30, 200, 25);
		type.setBounds(130, 130, 100, 20);
		ajouter.setBounds(240, 170, 100, 20);
		frequence.setBounds(140, 90, 100, 25);
		montant.setBounds(140, 60, 100, 25);
		labelSeconds.setBounds(240, 90, 100, 25);
		val.setBounds(270, 62, 80, 20);
		dest.setBounds(240, 130, 100, 20);
		labelName.setBounds(40, 30, 200, 25);
		labelFrequence.setBounds(40, 90, 200, 25);
		labelMontant.setBounds(40, 60, 200, 25);
		labelPercent.setBounds(240, 60, 30, 25);
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
		c.add(labelPercent);
		c.add(labelSeconds);
		frame.getRootPane().setDefaultButton(ajouter);
		ajouter.addActionListener(this);
		val.addActionListener(this);
		
		
	}
	
	private JButton ajouter;
	private JTextField montant, frequence, name;
	private JLabel labelMontant, labelFrequence, labelDest, labelName, labelPercent, labelSeconds;
	private JComboBox dest, type, val;
	private JFrame frame;
	private Salaire salaire;


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ajouter) {
			int mont = 0;
			boolean t = true;
		
			try {
				mont = Integer.parseInt(montant.getText());
			}
			catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(frame, "Erreur!!");
				t = false;
			}		
			if (t) {
				salaire.setMontant(mont);
				salaire.setVal(String.valueOf(val.getSelectedItem()));
				frame.setVisible(false);
			}
			
		}
		if(e.getSource() == val) {
			if (String.valueOf(val.getSelectedItem()).equals("rel") ) {
				labelPercent.setText("‰");
			} else {
				labelPercent.setText("");
			}
		
		}
		
	}

}
