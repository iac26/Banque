package Banque;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Banque {

	public static void main(String[] args) {	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
			System.out.println(UIManager.getLookAndFeel());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			
		}
		sals = new DefaultListModel();
		index = new Index();
		try {
			cpts = load("cpt.idx");
			System.out.println("reading cpt.idx "); 
			Auth.setCheck(cpts.get(0));
			for (int i = 1; i < cpts.size(); i++ ) {
				System.out.println("loading " + cpts.get(i));
				cptinf = load(cpts.get(i));
				//System.out.println(cptinf);
				String name = cptinf.get(1);
				Compte compte = new Compte(name);
				Banque.cl.put(name, compte);
				Index.lst.addElement(name);
				compte.setSolde(Integer.parseInt(cptinf.get(2)));
				for (int j = 3; j < cptinf.size(); j++) {
					compte.addHItem(cptinf.get(j));
				}
			}//Compte c, int m, int f, String t, String n, String nm
		}catch(IndexOutOfBoundsException ex){
			System.out.println("no accounts");
		}
		try {
			salaires = Banque.load("salaires.sls");
			//System.out.println(salaires.isEmpty());
			if (!salaires.isEmpty()) {
				for (int i = 1; i <= Integer.parseInt(salaires.get(0))*6; i = i + 6) {
					Compte cpt;
					String nm = salaires.get(i);
					int m = Integer.parseInt(salaires.get(i+1));
					int f = Integer.parseInt(salaires.get(i+2));
					String t = salaires.get(i+3);
					String n = salaires.get(i+4);
					String v = salaires.get(i+5);
					cpt = Banque.cl.get(nm);
					Salaire salaire = new Salaire(cpt,m,f,t,n,nm,v);
					salaire.start();
					String nme = t + " - " + n + ": " + nm;
					Banque.sls.put(nme, salaire);
					sals.addElement(nme);
					//System.out.println(nme);
				}
			}
		}catch(IndexOutOfBoundsException e){
				
		}
	}
	
	public static List load(String fn){
		List<String> files = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fn));
		} catch (FileNotFoundException e) {
			return files;
		}
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.replace(0, 1000, line);
		        line = br.readLine();
		        files.add(sb.toString());
		    }
		    
		} catch (IOException e) {
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
			}
		}
		return files;
	}
	
	public static String TextAlign(String m, int s, String sgn) {
		String message = m;
		String solde = String.valueOf(s);
		int l = message.length();
		int x = solde.length();
		int spaces = 70 - l - x;
		Calendar calendar = Calendar.getInstance();
		int min = calendar.get(Calendar.MINUTE);
		int hr = calendar.get(Calendar.HOUR_OF_DAY);
		String minutes = null;
		String hours = null;
		if (min < 10){
			minutes = "0"+String.valueOf(min);
		}else{
			minutes = String.valueOf(min);
		}
		if (hr < 10){
			hours = "0"+String.valueOf(hr);
		}else{
			hours = String.valueOf(hr);
		}
		String str = hours + ":" + minutes + " ";
		str = str+ message;
		for (int i = 0; i < spaces; i++){
			str = str + " " ;
		}
		str = str +  solde + sgn; 
		return str;
	}
	
	
	private static List<String> cpts = new ArrayList<String>();
	private static List<String> cptinf = new ArrayList<String>();
	public static HashMap<String, Compte> cl = new HashMap<String, Compte>();
	public static HashMap<String, Salaire> sls = new HashMap<String, Salaire>();
	private static List<String> salaires = new ArrayList<String>();
	public static DefaultListModel sals ;
	public static Index index;
}
