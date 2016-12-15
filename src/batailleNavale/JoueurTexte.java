package batailleNavale;

import java.util.Scanner;

public class JoueurTexte extends Joueur {
	private Scanner sc;

	public JoueurTexte(GrilleNavale g, String nom) {
		super(g, nom);
	}
	
	public void jouerAvec(Joueur j) {
		super.jouerAvec(j);
	}
	
	protected void perdu() {
		;
	}
	
	protected void gagne() {
		;
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
		;
	}
	
	protected void retourDefense(Coordonnee c, int etat) {
		;
	}
	
	public void debutAttaque(){
		;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
