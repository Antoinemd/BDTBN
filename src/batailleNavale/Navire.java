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
		if((this.estHorizontal == true) && (this.debut.getLigne() == c.getLigne()))	// Navire horizontal, c sur la même ligne que le navire...
			for(int j = this.debut.getColonne(); j < this.fin.getColonne(); j++) // on parcourt colonne par colonne...
				if((c.getColonne() >= this.debut.getColonne()) && (c.getColonne() <= this.fin.getColonne())) // si on trouve c...
					return true;
		else if((this.estHorizontal == false) && (this.debut.getColonne() == c.getColonne())) // Navire vertical, c sur la même colonne que le navire...
			for(int i = this.debut.getLigne(); i < this.fin.getLigne(); i++)	// on parcourt alors ligne par ligne...
				if((c.getLigne() >= this.debut.getLigne()) && (c.getLigne() <= this.debut.getLigne())) // si on trouve c...
					return true;
		return false; // if(((this.estHorizontal == true) && (this.debut.ligne != c.ligne)) || ((this.estHorizontal == false) && (this.debut.colonne != c.colonne)))
			}
	
	public boolean touche(Navire n) {
		if((this.estHorizontal) && (n.estHorizontal) && ((n.debut.getLigne() <= this.debut.getLigne() + 1) || (n.debut.getLigne() >= this.debut.getLigne() - 1))) {	// Le navire N est-il a une ligne d'écart de this ?
			for(int i = this.debut.getColonne(); i < this.fin.getColonne(); i++) {	// Si oui, on regarde si chaque choordonnée de voisine est éventuellement voisine d'une coordonnée de n
				for(int j = n.debut.getColonne(); j < n.fin.getColonne(); j++) {
					if(i == j)	// si une coordonnée de this et une de n se trouvent sur la même colonne, elles sont voisines car à une ligne d'écart tout au plus
						return(true);
				}
				
			}
		}
		return true;
	}
	
	public boolean chevauche(Navire n) {
		return true;
	}
	
	public boolean recoitTir(Coordonnee c) {
		if(this.contient(c)) {
			// On agrandit le tableau partiesTouchees
			// On incrémente nbTouchees
			return true;
		}
		return false;
	}
	
	public boolean estTouche(Coordonnee c) {
		return(this.recoitTir(c));
	}
	
	public boolean estTouche() {
		if(nbTouchees > 0)
			return true;
		return false;
	}
	
	public boolean estCoule() {
		// si le tableau partiesTouchees contient autant de valeur que nbTouchees, alors le navire est coulé
		return true;
	}

	//// Main pour terster les différentes méthodes	////
	public static void main(String[] args) {

	}

}
