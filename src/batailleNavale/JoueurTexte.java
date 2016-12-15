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
		System.out.println("Le tir de " + this.getNom() + " en " + c + this.resultatAttaque(etat));
		// Update Grid
		System.out.println("Grille Attaquant : \n" + this.getGrille());
	}
	
	protected void retourDefense(Coordonnee c, int etat) {
		System.out.println(this.getNom() + " est attaqué ! ");
	}
	
	public void debutAttaque(){
		System.out.println("A votre tour d'attaquer !" + this.getNom());
		Scanner sc = new Scanner(System.in);
		String cls = sc.nextLine();
		int li = sc.nextInt();
		// int cli = Integer.parseInt(cls.substring(0, 1));
		char caractere = cls.charAt(0);
		int cli = (int)(caractere - 'A');
		Coordonnee c = new Coordonnee(li, cli);
		this.attaque(c);
	}

	public static void main(String[] args) {
		int[] tN = {1};
		GrilleNavale g1 = new GrilleNavale(10, tN);
		g1.placementAuto(tN);
		System.out.println("Grille joueur 1 : \n" + g1 + "\n");
		
		GrilleNavale g2 = new GrilleNavale(10, tN);
		g2.placementAuto(tN);
		System.out.println("Grille joueur 2 : \n" + g2 + "\n");
		Joueur j1 = new JoueurTexte(g1, "Kris");
		Joueur j2 = new JoueurTexte(g2, "Mik");
		j1.jouerAvec(j2);
		
		j1.debutAttaque();
	}

}
