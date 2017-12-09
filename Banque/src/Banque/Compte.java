package Banque;
import java.io.*;
import java.util.*;

public class Compte {
	public Compte(String s) {
		name = s;
		solde = 0;		
	}
	public void depot(int n) {
		solde += n;
	}
	public void retrait(int n) {
		solde -= n;
	}
	public int getSolde() {
		return solde;
	}
	public void setSolde(int n) {
		solde = n;
	}
	public String getName() {
		return name;
	}
	public void setPIN( int p){
		PIN = p;
	}
	public int getPIN(){
		return PIN;
	}
	public void addHItem(String s) {
		//history.add(s);
		history.add(s);
	}
	public String getHistory() {
		String str = "";
		for (int i = 0; i < history.size(); i++) {
			String s = history.get(i);
			str = str + s + "\n";
		}
		return str;
	}
	public void save() {
		System.out.println("saving-" + name);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter( this.getName()+ ".cpt", "ASCII");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
		} 
		writer.println("#Compte n: " + String.valueOf(this) + " / ce compte est au nom de " + this.getName() + " et il a un solde de : " + String.valueOf(this.getSolde()));
		writer.println(this.getName());
		writer.println(String.valueOf(this.getSolde()));
		String hist = this.getHistory();
		writer.print(hist);
		writer.close();
	}
	public static void saveAll() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter( "cpt.idx", "ASCII");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
		} 
		String str = Auth.getCheck() + "\n";
		for (int i = 0; i < Index.lst.getSize(); i++) {
			str = str + Index.lst.get(i) + ".cpt\n";
		}
		System.out.println(str);
		writer.print(str);
		writer.close();
	}


	private List<String> history = new ArrayList<String>();
	private String name;
	private int solde, PIN;
}
