package batailleNavale;

public class Navire {
	
	/*Les navires de la bataille navale sont representes par des instances de la classe Navire.
	 * Les coordonnees debut et fin marquent l'emplacement du navire, on suppose que debut est toujours inférieure à fin.
	 * partiesTouchees contient les coordonnees des parties du navire qui ont ete touchees et nbTouchees indique combien de parties ont ete touchees.
	3.*/
	
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;
	private boolean estHorizontal;

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		this.debut = debut;
		if(estVertical) {
			this.fin = new Coordonnee(this.debut.getLigne() + longueur-1, this.debut.getColonne());
			this.estHorizontal = false;
		}
		else {
			this.fin = new Coordonnee(this.debut.getLigne(), this.debut.getColonne() + longueur-1);
			this.estHorizontal = true;
		}
	}
	
	public Coordonnee getDebut() {
		return this.debut;
	}
	
	public Coordonnee getFin() {
		return this.fin;
	}
	
	public boolean contient(Coordonnee c) {
		if((this.estHorizontal == true) && (this.debut.ligne == c.ligne))	// Navire horizontal, c sur la même ligne que le navire...
			for(int j = 0; j < this.fin.colonne; j++)
				if((c.colonne >= this.debut.colonne) && (c.colonne <= this.fin.colonne)) // si c est compris entre le début et la fin du navire...
					return true;
		else if((this.estHorizontal == false) && (this.debut.colonne == c.colonne)) // Navire vertical, c sur la même colonne que le navire...
			for(int i = 0; i < this.fin.ligne; i++)
				if((c.ligne >= this.debut.ligne) && (c.ligne <= this.debut.ligne)) // si c est compris entre le début et la fin du navire...
					return true;
		return false; // if(((this.estHorizontal == true) && (this.debut.ligne != c.ligne)) || ((this.estHorizontal == false) && (this.debut.colonne != c.colonne)))
			}
	
	public boolean touche(Navire n) {
		
	}
	// public boolean chevauche(Navire n) {}
	// public boolean recoitTir(Coordonnee c) {}
	// public boolean estTouche(Coordonnee c) {}
	// public boolean estTouche() {}
	// public boolean estCoule() {}

	//// Main pour terster les différentes méthodes	////
	public static void main(String[] args) {

	}

}
