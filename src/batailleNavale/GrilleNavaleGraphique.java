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
		if(super.ajouteNavire(n)) {
					this.grille.colorie(n.getDebut(), n.getFin(), Color.green);
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
