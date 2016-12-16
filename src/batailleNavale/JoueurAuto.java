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
		
			// Premier Tir
			//if (super.getGrilleAdversaire().getTableauTirsRecus().length < 1) {
				do {
					c = new Coordonnee((int)(Math.random() * (super.getGrille().getTailleGrille())), (int)(Math.random() * (super.getGrille().getTailleGrille())));
				} while(super.getGrilleAdversaire().getEstDansTirsRecus(c));
				this.attaque(c);
			//}
//			// Plus de 1 tir
//			else if(super.getGrilleAdversaire().getTableauTirsRecus().length >= 1) {
//				//! Tir
//				for(int i = 0; i < super.getGrilleAdversaire().getTableauTirsRecus().length; i++) {
//					if(super.getGrilleAdversaire().estTouche(super.getGrilleAdversaire().getTableauTirsRecus()[i])	// On cherche une coordonnée qui a touché un Navire
//							&& !super.getGrilleAdversaire().estTouche(super.getGrilleAdversaire().getTableauTirsRecus()[i])) {	// Mais un navire qui n'est pas coulé
//						do {
//							;	// Si la ligne au dessus contient un navire touché, on tire à la ligne d'en dessous
//							;	// Si la ligne en dessous contient un navire touché, on tire à la ligne au dessus
//							;	// Si la colonne à gauche contient un navire touché, on tire sur la colonne à droite
//							;	// Si la colonne à droite contient un navire touché, on tire sur la colonne à gauche
//							;	// sinon on tire aléatoireent à une case près
//						} while(super.getGrilleAdversaire().getEstDansTirsRecus(c));
//					}
//					else {	// Sinon, si on touche un navire coulé ou si on est à l'eau, on tire aléatoirement
//						do {
//							c = new Coordonnee((int)(Math.random() * (super.getGrille().getTailleGrille())) + 1, (int)(Math.random() * (super.getGrille().getTailleGrille())) + 1);
//						} while(super.getGrilleAdversaire().getEstDansTirsRecus(c));
//						this.attaque(c);
//					}
//				}	
//			}
	}

	public static void main(String[] args) {
		

		int[] tN = {2, 3, 3, 4, 5};
		GrilleNavale g1 = new GrilleNavale(2, tN);
		g1.placementAuto(tN);
		
		GrilleNavale g2 = new GrilleNavale(2, tN);
		g2.placementAuto(tN);
		
		Joueur j1 = new JoueurTexte(g1, "Kris");
		Joueur ia = new JoueurAuto(g2, "ia");
		j1.jouerAvec(ia);
		
		System.out.println("Grille " + j1.getNom() +" : \n" + g1 + "\n");
		System.out.println("Grille " + ia.getNom() +" : \n" + g2 + "\n");
		
		j1.debutAttaque();

	}

}