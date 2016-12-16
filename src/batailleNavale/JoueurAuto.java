package batailleNavale;

public class JoueurAuto extends Joueur {

	public JoueurAuto(GrilleNavale g, String nom) {
		super(g, nom);
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
		//System.out.println("Retour Attaque : Grille " + this.getNom() + ": \n" + this.getGrille());
	}
	
	protected void retourDefense(Coordonnee c, int etat) {
		System.out.println(this.getNom() + " est attaqué ! ");
		// Update Grid
		System.out.println("Retour Defense : Grille " + this.getNom() + " : \n" + this.getGrille());
	}
	
	public void debutAttaque() {
		System.out.println("A votre tour d'attaquer !" + this.getNom());
		Coordonnee c = new Coordonnee(0, 0);
		
		// Gestion du premier tir
		if (super.getGrilleAdversaire().getTableauTirsRecus().length < 1) { // On vérifie si on a déjà tiré sur l'adversaire
			do {
				c = new Coordonnee((int)(Math.random() * (super.getGrille().getTailleGrille())) + 1, (int)(Math.random() * (super.getGrille().getTailleGrille())) + 1);
				}
				while(super.getGrilleAdversaire().getEstDansTirsRecus(c));
				this.attaque(c);
		}
		// Gestion d'un tir où 'c' est dans l'eau
		for (int i = 0; i < super.getGrilleAdversaire().getTableauTirsRecus().length; i++) {
			if (super.getGrilleAdversaire().getTableauTirsRecus()[i] == c){
				if (super.getGrilleAdversaire().estALEau(c))
					do {
				c = new Coordonnee((int)(Math.random() * (super.getGrille().getTailleGrille())) + 1, (int)(Math.random() * (super.getGrille().getTailleGrille())) + 1);
				} 
				while(super.getGrilleAdversaire().getEstDansTirsRecus(c));
				this.attaque(c);
			}
		}
		// Gestion d'un tir où 'c' touche un bateau
//		if (super.getGrilleAdversaire().estTouche(c)) {
//			do {
//				c = super.getGrilleAdversaire()
//			}
//		}
			
	}

	public static void main(String[] args) {
		

		int[] tN = {1};
		GrilleNavale g1 = new GrilleNavale(5, tN);
		g1.placementAuto(tN);
		
		GrilleNavale g2 = new GrilleNavale(5, tN);
		g2.placementAuto(tN);
		
		Joueur j1 = new JoueurTexte(g1, "Kris");
		Joueur ia = new JoueurAuto(g2, "ia");
		j1.jouerAvec(ia);
		
		System.out.println("Grille " + j1.getNom() +" : \n" + g1 + "\n");
		System.out.println("Grille " + ia.getNom() +" : \n" + g2 + "\n");
		
		j1.debutAttaque();

	}

}
