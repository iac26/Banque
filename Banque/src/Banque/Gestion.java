package Banque;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Timer;

public class Gestion implements ActionListener, WindowListener {


	public Gestion(Compte n) {
		cpt = n;
		frame = new JFrame(cpt.getName());
		frame.setVisible(true);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		Container c = frame.getContentPane();
		c.setLayout(null);
		depot = new JButton("Deposer");
		retrait = new JButton("Retirer");
		virement = new JButton("Versement");
		solde = new JTextArea();
		histoire = new JTextArea();
		infos = new JTextArea();
		scroll = new JScrollPane(histoire);
		save = new JButton("s");
		/*final JScrollBar verticalBar = scroll.getVerticalScrollBar();
	    AdjustmentListener downScroller = new AdjustmentListener() {
	        @Override
	        public void adjustmentValueChanged(AdjustmentEvent e) {
	            Adjustable adjustable = e.getAdjustable();
	            adjustable.setValue(adjustable.getMaximum());
	            verticalBar.removeAdjustmentListener(this);
	        }
	    };
	    verticalBar.addAdjustmentListener(downScroller);*/
		save.setBounds(10, 420, 20 ,20);
		depot.setBounds(100, 420, 100, 20);
		retrait.setBounds(290, 420, 100, 20);
		virement.setBounds(480, 420, 100, 20);
		infos.setBounds(20, 20, 650, 380);
		histoire.setLineWrap(true);
	    histoire.setEditable(false);
	    histoire.setVisible(true);
		solde.setBounds(50, 360, 590, 20);
		scroll.setBounds(50, 30, 590, 300); // last field 300
		scroll.setViewportBorder(null);
		scroll.setBorder(null);
		solde.setEditable(false);
		infos.setEditable(false);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//c.add(histoire);
		//c.add(save);
		c.add(depot);
		c.add(retrait);
		c.add(virement);
		c.add(solde);
		c.add(scroll);
		c.add(infos);
		refresh();
		depot.addActionListener(this);
		retrait.addActionListener(this);
		virement.addActionListener(this);
		save.addActionListener(this);
		frame.addWindowListener(this);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run(){
				int s = cpt.getSolde();
				String sgn = null;
				if(s < 0){
					sgn = "-";
				}else{
					sgn = "+";
				}
				solde.setText(Banque.TextAlign("Solde:", Math.abs(s), sgn));
				histoire.setText(cpt.getHistory());
			}
			
		},0, 500);
	}
	
	public void refresh() {
		histoire.setText(cpt.getHistory());
	}
	public void close() {
		frame.setVisible(false);
	}
	public void Ver(){
		VerState = true;	
		depot.setEnabled(false);
		depot.setBackground(Color.RED);
		retrait.setEnabled(false);
		retrait.setBackground(Color.RED);
		virement.setEnabled(false);
		virement.setBackground(Color.RED);
	}
	public void deVer(){
		VerState = false;
		depot.setEnabled(true);
		depot.setBackground(null);
		retrait.setEnabled(true);
		retrait.setBackground(null);
		virement.setEnabled(true);
		virement.setBackground(null);
	}
	
	private Compte cpt;
	private JTextArea infos, solde, histoire; 
	private JButton depot, retrait, virement, save;
	private JScrollPane scroll;
	private PrintWriter writer;
	private JScrollBar vert;
	private boolean first;
	private JFrame frame;
	private boolean VerState;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == depot) {
			Depot dep = new Depot(cpt);
		}
		if(e.getSource() == retrait) {
			Retrait ret = new Retrait(cpt);
		}
		if(e.getSource() == virement) {
			Versement ver = new Versement(cpt);
		}
		if(e.getSource() == save) {
			cpt.save();
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		cpt.save();
		System.out.println("savingOnClose");
		
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

	

}
