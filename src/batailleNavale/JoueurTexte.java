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
		System.out.println("GAME OVER " + this.getNom());
	}
	
	protected void gagne() {
		System.out.println("YOU WIN " + this.getNom());
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
		System.out.println("Le tir de " + this.getNom() + " en " + c + this.resultatAttaque(etat) + "\n");
		// Update Grid
		//System.out.println("Retour Attaque : Grille " + this.getNom() + " : \n" + this.getGrille());
	}
	
	protected void retourDefense(Coordonnee c, int etat) {
		System.out.println(this.getNom() + " est attaqu� ! ");
		// Update Grid
		System.out.println(" Retour Defense : Grille " + this.getNom() + " : \n" + this.getGrille());
	}
	
	public void debutAttaque(){
		Coordonnee c = new Coordonnee(0, 0);
		System.out.println("A votre tour d'attaquer !" + this.getNom());
		do {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Entrez une coordonn�e : ");
			try {
				String coordonneeS = sc.nextLine();		// Coordonn�e type A1
				c = new Coordonnee(coordonneeS);	// on cr�� une nouvelle coordonnee de ligne li et colonne cl
			} catch(IllegalArgumentException e) {System.out.println("Veuillez saisir une coordonn�e valide !");}
		} while(c.getColonne() < 1 || c.getColonne() > super.getGrille().getTailleGrille() || c.getLigne() < 1 || c.getLigne() > super.getGrille().getTailleGrille());
		this.attaque(c);
	}

	public static void main(String[] args) {
		int[] tN = {2, 3, 3, 4, 5};
		GrilleNavale g1 = new GrilleNavale(10, tN);
		g1.placementAuto(tN);
		
		GrilleNavale g2 = new GrilleNavale(10, tN);
		g2.placementAuto(tN);
		Joueur j1 = new JoueurTexte(g1, "Kris");
		Joueur j2 = new JoueurTexte(g2, "Mik");
		j1.jouerAvec(j2);
		
		System.out.println("Grille " + j1.getNom() +" : \n" + g1 + "\n");
		System.out.println("Grille " + j2.getNom() +" : \n" + g2 + "\n");
		
		j1.debutAttaque();
	}

}
