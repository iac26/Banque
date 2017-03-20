package Banque;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;

public class Index implements ActionListener, ListSelectionListener, WindowListener, MouseListener{
	public Index() {
		frame = new JFrame("Index");
		sal = new Salaires();
		Container c = frame.getContentPane();
		frame.setSize(200, 700);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		c.setLayout(null);
		ajouter = new JButton("Nouveau");
		ouvrir = new JButton("Ouvrir");
		salaires = new JButton("Salaires");
		supprimer = new JButton("Supprimer");
		ver = new JButton("");
		lst = new DefaultListModel();
		list = new JList(lst);
		list.setBounds(20, 20, 150, 500);
		ajouter.setBounds(45, 570, 100, 20);
		ouvrir.setBounds(45, 540, 100, 20);
		supprimer.setBounds(45, 600, 100, 20);
		salaires.setBounds(45, 630, 100, 20);
		ver.setBounds(10, 630, 20, 20);
		ouvrir.setEnabled(false);
		supprimer.setEnabled(false);
		ver.setBackground(Color.GREEN);
		c.add(ajouter);
		c.add(ouvrir);
		c.add(list);
		c.add(salaires);
		c.add(supprimer);
		c.add(ver);
		frame.getRootPane().setDefaultButton(ouvrir);
		ajouter.addActionListener(this);
		ouvrir.addActionListener(this);
		list.addListSelectionListener(this);
		salaires.addActionListener(this);
		frame.addWindowListener(this);
		list.addMouseListener(this);
		supprimer.addActionListener(this);
		ver.addMouseListener(this);
		Ver();

		
	}
	public void deVer() {
		VerState = false;
		ver.setBackground(Color.GREEN);
		list.setEnabled(true);
		supprimer.setBackground(null);
		ajouter.setEnabled(true);
		ajouter.setBackground(null);
		ouvrir.setBackground(null);
		if(!selEmpty){
			ouvrir.setEnabled(true);
			supprimer.setEnabled(true);
		}
		sal.deVer();
		for (int i = 0; i < gest.size(); i++){
			gest.get(i).deVer();
		}
	}
	public void Ver(){
		VerState = true;
		ver.setBackground(Color.RED);
		list.setEnabled(false);
		supprimer.setEnabled(false);
		supprimer.setBackground(Color.RED);
		ajouter.setEnabled(false);
		ajouter.setBackground(Color.RED);
		ouvrir.setEnabled(false);
		ouvrir.setBackground(Color.RED);
		sal.Ver();
		for (int i = 0; i < gest.size(); i++){
			gest.get(i).Ver();
		}
	}
	
	public static DefaultListModel lst ;
	private JButton ajouter, ouvrir, salaires, supprimer, ver; 
	private JList list;
	private Compte cpt; 
	private JFrame frame;
	private Salaires sal;
	private boolean VerState = false, selEmpty = true; 
	private ArrayList<Gestion> gest = new ArrayList<Gestion>(); 
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		ouvrir.setEnabled(true);
		supprimer.setEnabled(true);
		selEmpty = false;
		if (list.isSelectionEmpty()) {
			ouvrir.setEnabled(false);
			supprimer.setEnabled(false);
			selEmpty = true;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ouvrir) {
			System.out.println(list.getSelectedValue());
			cpt = Banque.cl.get(list.getSelectedValue()); 
			System.out.println(cpt);
			int v = cpt.getSolde();
			System.out.println(v);
			Gestion g = new Gestion(cpt);
			gest.add(g);
		}
		if(e.getSource() == ajouter){
			NouveauCompte nouveau = new NouveauCompte();
			System.out.println("ajouter");
		}
		if(e.getSource() == supprimer) {
			String name = String.valueOf(list.getSelectedValue());
			int resp = JOptionPane.showConfirmDialog(frame, "Supprimmer " + name + " ?");
			System.out.println(resp);
			if (resp == 0) {
				cpt = Banque.cl.get(name);
				Banque.cl.remove(name,cpt);
				lst.removeElement(name);
				Compte.saveAll();
				for (int i = 0; i < Banque.sals.size(); i++) {
					Salaire sal = Banque.sls.get(Banque.sals.getElementAt(i));
					if (cpt == sal.getCpt()) {
						sal.stop();
						Banque.sals.remove(i);
						Salaires.save();
					}
					
				}
			}
			
		}
		if(e.getSource() == salaires) {
			sal.toggle();
		}
		
		
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		for (Compte c : Banque.cl.values() ) {
			c.save();
		}
		Compte.saveAll();
		System.out.println("exit");
		System.exit(0);
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent ev) {
		if((ev.getSource() == list)&&(VerState == false)) {
			if(ev.getClickCount() == 2) {
				try {
					System.out.println(list.getSelectedValue());
					cpt = Banque.cl.get(list.getSelectedValue()); 
					System.out.println(cpt);
					int v = cpt.getSolde();
					System.out.println(v);
					Gestion g = new Gestion(cpt);
					gest.add(g);
				}catch(NullPointerException e){
					NouveauCompte nouveau = new NouveauCompte();
					System.out.println("ajouter");
				}
			}
		}
		if(ev.getSource() == ver){
			if((ev.getButton()== MouseEvent.BUTTON3)&&(VerState == false)){
				Auth a = new Auth("cp");
			}
			if(ev.getButton() == MouseEvent.BUTTON1){
				if(VerState == true){
					Auth a = new Auth("dv");
				}else if(VerState == false){
					Ver();
				}
			}	
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent ev) {

		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
