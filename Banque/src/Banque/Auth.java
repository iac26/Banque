package Banque;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Auth implements ActionListener{
	public Auth(String t){
		tpe = t;
		frame = new JFrame();
		Container c = frame.getContentPane();
		frame.setVisible(true);
		frame.setSize(175, 290);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		c.setLayout(null);
		B0 = new JButton("0");
		B1 = new JButton("1");
		B2 = new JButton("2");
		B3 = new JButton("3");
		B4 = new JButton("4");
		B5 = new JButton("5");
		B6 = new JButton("6");
		B7 = new JButton("7");
		B8 = new JButton("8");
		B9 = new JButton("9");
		BOK = new JButton(">");
		BCORR = new JButton("<");
		pass = new JPasswordField();
		pass.setEditable(false);
		pass.setFont(pass.getFont().deriveFont(30.0f));
		if(tpe == "cp"){
			pass.setEchoChar((char) 0);
		}
		pass.setBounds(20, 20, 130, 40 );
		B1.setBounds(20, 70, 40, 40);
		B2.setBounds(65, 70, 40, 40);
		B3.setBounds(110, 70, 40, 40);
		B4.setBounds(20, 115, 40, 40);
		B5.setBounds(65, 115, 40, 40);
		B6.setBounds(110, 115, 40, 40);
		B7.setBounds(20, 160, 40, 40);
		B8.setBounds(65, 160, 40, 40);
		B9.setBounds(110, 160, 40, 40);
		B0.setBounds(65, 205, 40, 40);
		BOK.setBounds(110, 205, 40, 40);
		BCORR.setBounds(20, 205, 40, 40);
		BOK.setBackground(Color.GREEN);
		BCORR.setBackground(Color.RED);
		c.add(B1);
		c.add(B2);
		c.add(B3);
		c.add(B4);
		c.add(B5);
		c.add(B6);
		c.add(B7);
		c.add(B8);
		c.add(B9);
		c.add(B0);
		c.add(BOK);
		c.add(BCORR);
		c.add(pass);
		B1.addActionListener(this);
		B2.addActionListener(this);
		B3.addActionListener(this);
		B4.addActionListener(this);
		B5.addActionListener(this);
		B6.addActionListener(this);
		B7.addActionListener(this);
		B8.addActionListener(this);
		B9.addActionListener(this);
		B0.addActionListener(this);
		BOK.addActionListener(this);
		BCORR.addActionListener(this);
		code = "";
		ok = false;
	}
	
	public static void setCheck(String s){
		check = s; 
	}
	public static String getCheck(){
		
		return check;
	}
	
	private JFrame frame;
	private JButton B1, B2, B3, B4, B5, B6, B7, B8, B9, B0, BOK, BCORR;
	private JPasswordField pass;
	private String code;
	private static String check = "1234"; 
	private boolean test;
	private boolean ok;
	private String tpe;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		pass.setBackground(null);
		if (ok){
			System.out.println("reset");
			code = "";
			ok = false;
		}
		if (e.getSource() == B1){
			code = code + "1";
		}
		if (e.getSource() == B2){
			code = code + "2";
		}
		if (e.getSource() == B3){
			code = code + "3";
		}
		if (e.getSource() == B4){
			code = code + "4";
		}
		if (e.getSource() == B5){
			code = code + "5";
		}
		if (e.getSource() == B6){
			code = code + "6";
		}
		if (e.getSource() == B7){
			code = code + "7";
		}
		if (e.getSource() == B8){
			code = code + "8";
		}
		if (e.getSource() == B9){
			code = code + "9";
		}
		if (e.getSource() == B0){
			code = code + "0";
		}
		if (e.getSource() == BOK){
			System.out.println(code);
			System.out.println(check);
			if(tpe == "dv"){
				if (code.equals(check)){
					test = true;
					pass.setBackground(Color.GREEN);
					Banque.index.deVer();
					frame.setVisible(false);
				}else{
					test = false;
					pass.setBackground(Color.RED);
				}
			}else if(tpe == "cp"){
				check = code; 
				frame.setVisible(false);
			}
			ok = true;
			System.out.println(test);
			System.out.print(ok);
		}
		if (e.getSource() == BCORR){
			try {
				code = code.replace(code.substring(code.length()-1), "");
			}catch(IndexOutOfBoundsException ex ) {
				pass.setBackground(Color.RED);
			}
		}
		pass.setText(code);		
	}
}
