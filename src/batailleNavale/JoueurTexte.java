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
		this.perdu();
		System.out.println("GAME OVER");
	}
	
	protected void gagne() {
		this.gagne();
		System.out.println("YOU WIN");
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
		this.retourAttaque(c, etat);
		System.out.println("Votre tir en " + c + " est " + etat);
	}
	
	protected void retourDefense(Coordonnee c, int etat) {
		this.retourDefense(c, etat);
		System.out.println("Le tir de votre adversaire en " + c + " est " + etat);
	}
	
	public void debutAttaque(){
		System.out.println("A votre tour d'attaquer !");
		
		Scanner sc = new Scanner(System.in);
		int li = sc.nextInt();
		int cl = sc.nextInt();
		Coordonnee c = new Coordonnee(li, cl);
		this.attaque(c);
	}

	public static void main(String[] args) {
		int[] tN = {2, 3, 3, 4, 5};
		GrilleNavale g1 = new GrilleNavale(10, tN);
		g1.placementAuto(tN);
		System.out.println("Grille joueur 1 : \n" + g1 + "\n");
		
		GrilleNavale g2 = new GrilleNavale(10, tN);
		g2.placementAuto(tN);
		System.out.println("Grille joueur 2 : \n" + g2 + "\n");
		Joueur j1 = new JoueurTexte(g1, "Kris");
		Joueur j2 = new JoueurTexte(g2, "Mik");
		
		j1.debutAttaque();
		g1.placementAuto(tN);
		g2.placementAuto(tN);
	}

}
