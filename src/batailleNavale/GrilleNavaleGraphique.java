package batailleNavale;

import java.awt.Color;

public class GrilleNavaleGraphique extends GrilleNavale {
	private GrilleGraphique grille;

	public GrilleNavaleGraphique(int taille) {
		super(taille, 0);
	}
	
	public GrilleGraphique getGrilleGraphique() {
		return(this.grille);
	}
	
	public boolean ajouteNavire(Navire n) {
		int longueur;
		
		if(super.ajouteNavire(n)) {
			if(n.getEstHorizontal()) {
				longueur = (n.getFin().getColonne() - n.getDebut().getColonne()) + 1;
				for(int i= 0; i < longueur; i++ ) {
					Coordonnee currentC = new Coordonnee(n.getDebut().getLigne(), n.getDebut().getColonne() + i);
					this.grille.colorie(currentC, Color.green);
				}
			}
			else {
				longueur = (n.getFin().getLigne() - n.getDebut().getLigne()) + 1;
				for(int i= 0; i < longueur; i++ ) {
					Coordonnee currentC = new Coordonnee(n.getDebut().getLigne() + i, n.getDebut().getColonne());
					this.grille.colorie(currentC, Color.green);
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean recoitTir(Coordonnee c) {
		if(super.recoitTir(c)) {
			if(this.estTouche(c)) {
				this.grille.colorie(c, Color.red);
			}
			else {
				this.grille.colorie(c, Color.blue);
			}
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		GrilleGraphique plateau = new GrilleGraphique(10);

	}

}
