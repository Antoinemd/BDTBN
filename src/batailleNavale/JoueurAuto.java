package batailleNavale;

public class JoueurAuto extends Joueur {

	public JoueurAuto(GrilleNavale g, String nom) {
		super(g, nom);
	}
	
	protected void perdu() {
		System.out.println("GAME OVER");
	}
	
	protected void gagne() {
		System.out.println("YOU WIN");
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
		System.out.println("Votre tir en " + c + " est " + etat);
		// Update Grid
		System.out.println("Grille Attaquant : \n" + this.getGrille());
	}
	
	protected void retourDefense(Coordonnee c, int etat) {
		System.out.println("Le tir de votre adversaire en " + c + " est " + etat);
		System.out.println("Grille Attaqué : \n" + this.getGrille());
	}
	
	public void debutAttaque() {
		System.out.println("A votre tour d'attaquer !" + this.getNom());
		Coordonnee c = new Coordonnee((int)(Math.random() * (super.getGrille().getTailleGrille())), (int)(Math.random() * (super.getGrille().getTailleGrille())));
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
		Joueur ia = new JoueurAuto(g2, "ia");
		j1.jouerAvec(ia);
		
		j1.debutAttaque();

	}

}
