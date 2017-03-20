package Banque;

import java.util.Timer;
import java.util.TimerTask;

public class Salaire {
	public Salaire(Compte c, int m, int f, String t, String n, String nm, String v) {
		cpt = c;
		montant = m;
		frq = f;
		type = t;
		name = n;
		cName = nm;
		val = v;
		on = true;
	    
		
	}
	
	
	public int getMontant() {
		return montant;
	}
	public int getFrq() {
		return frq;
	}
	public String getType() {
		return type;
	}
	public Compte getCpt() {
		return cpt;
	}
	public String getName(){
		return name;
	}
	public String getCName(){
		return cName;
	}
	public void setMontant(int m) {
		montant = m;
	}
	public void setFrq(int f) {
		frq = f;
	}
	public void stop() {
		on = false;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String v){
		val = v;
	}
	
	public void start() {
		
		Timer timer1 = new Timer();
		timer1.schedule(new TimerTask() {
			
			@Override
			public void run(){
				if (on) {
					String str = str = type + " - " +name;
					System.out.println("paying" + name + type);
					float solde = cpt.getSolde();
					if (type == "salaire") {
						if (val == "abs") {
							cpt.depot(montant);
							cpt.addHItem(Banque.TextAlign(str, montant, "+"));
						}else if ((val == "rel") && (solde > 0)) {
							
							float montf = montant;
							float mont = solde* (montf/1000);
							cpt.depot(Math.round(mont));
							cpt.addHItem(Banque.TextAlign(str, Math.round(mont), "+"));
						}else{
							cpt.addHItem(Banque.TextAlign(str, 0, "+"));
						}
						
					}else if (type == "impot") {
						if (val == "abs") {
							cpt.retrait(montant);
							cpt.addHItem(Banque.TextAlign(str, montant, "-"));
						}else if ((val == "rel") && (solde > 0)) {
							float montf = montant;
							float mont = solde* (montf/1000);
							cpt.retrait(Math.round(mont));
							cpt.addHItem(Banque.TextAlign(str, Math.round(mont), "-"));
						}else {
							cpt.addHItem(Banque.TextAlign(str, 0, "-"));
						}
						
					}
				}
			}
			
		},0, frq*60000 ); 
	}
	
	private Compte cpt;
	private int montant, frq, i;
	private String type, name, cName, val;
	private boolean on;

}
