package Banque;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Salaires implements ActionListener, ListSelectionListener, MouseListener {
	public Salaires(){	
		frame = new JFrame("Salaires");
		frame.setVisible(false);
		frame.setSize(300, 670);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		Container c = frame.getContentPane();
		c.setLayout(null);
		list = new JList(Banque.sals);
		nouveau = new JButton("Nouveau");
		modifier = new JButton("Modifier");
		supprimer = new JButton("Supprimer");
		nouveau.setBounds(100, 540, 120, 20);
		modifier.setBounds(100, 570, 120, 20);
		supprimer.setBounds(100,600,120,20);
		list.setBounds(20, 20, 250, 500);
		modifier.setEnabled(false);
		supprimer.setEnabled(false);
		frame.getRootPane().setDefaultButton(modifier);
		c.add(list);
		c.add(nouveau);
		c.add(modifier);
		c.add(supprimer);
		nouveau.addActionListener(this);
		modifier.addActionListener(this);
		supprimer.addActionListener(this);
		list.addListSelectionListener(this);
		list.addMouseListener(this);

	}
	public void hide() {
		frame.setVisible(false);
	}
	public void show() {
		frame.setVisible(true);
	}
	public void toggle(){
		if(frame.isVisible()){
			frame.setVisible(false);
		}else{
			frame.setVisible(true);
		}
	}
	
	public void Ver(){
		VerState = true;
		list.setEnabled(false);
		supprimer.setEnabled(false);
		supprimer.setBackground(Color.RED);
		nouveau.setEnabled(false);
		nouveau.setBackground(Color.RED);
		modifier.setEnabled(false);
		modifier.setBackground(Color.RED);
		
	}
	public void deVer(){
		VerState = false;
		list.setEnabled(true);
		supprimer.setBackground(null);
		nouveau.setEnabled(true);
		nouveau.setBackground(null);
		modifier.setBackground(null);
		if(!selEmpty){
			modifier.setEnabled(true);
			supprimer.setEnabled(true);
		}
	}
	
	public static void save() {
		System.out.println("saving salaires");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter( "salaires.sls", "ASCII");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
		} 
		writer.println(String.valueOf(Banque.sals.size()));
		for (int i = 0; i < Banque.sals.size(); i++) { 
			Salaire salaire = Banque.sls.get(Banque.sals.get(i));
			writer.println(salaire.getCName());
			writer.println(String.valueOf(salaire.getMontant()));
			writer.println(String.valueOf(salaire.getFrq()));
			writer.println(salaire.getType());
			writer.println(salaire.getName());
			writer.println(salaire.getVal());
		}
		writer.close();
	}
	private JList list;
	private boolean VerState, selEmpty = true; 
	private JButton nouveau, modifier, supprimer;
	private JFrame frame;


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nouveau) {
			NouveauSalaire nouveau = new NouveauSalaire();
		}
		if(e.getSource() == modifier) {
			Salaire sal = Banque.sls.get(String.valueOf(list.getSelectedValue()));
			ModifierSalaire nouveau = new ModifierSalaire(sal);
		}
		if(e.getSource() == supprimer) {
			String name = String.valueOf(list.getSelectedValue());
			int resp = JOptionPane.showConfirmDialog(frame, "Supprimmer " + name + " ?");
			System.out.println(resp);
			if (resp == 0) {
				Salaire sal = Banque.sls.get(name);
				sal.stop();
				Banque.sals.removeElement(list.getSelectedValue());
				Salaires.save();
			}
		}
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		modifier.setEnabled(true);
		supprimer.setEnabled(true);
		selEmpty = false;
		if(list.isSelectionEmpty()) {
			modifier.setEnabled(false);
			supprimer.setEnabled(false);
			selEmpty = true;
		}
	}
	@Override
	public void mouseClicked(MouseEvent ev) {
		if ((ev.getSource()== list)&& VerState == false){
			if(ev.getClickCount() == 2) {
				try {
					Salaire sal = Banque.sls.get(String.valueOf(list.getSelectedValue()));
					ModifierSalaire nouveau = new ModifierSalaire(sal);
				}catch(NullPointerException e){
					NouveauSalaire nouveau = new NouveauSalaire();
				}
			}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
