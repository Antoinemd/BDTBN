package batailleNavale;

public class Navire {
	
	/*Les navires de la bataille navale sont representes par des instances de la classe Navire.
	 * Les coordonnees debut et fin marquent l'emplacement du navire, on suppose que debut est toujours inf�rieure � fin.
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
		if((this.estHorizontal == true) && (this.debut.getLigne() == c.getLigne()))	// Navire horizontal, c sur la m�me ligne que le navire...
			for(int j = this.debut.getColonne(); j < this.fin.getColonne(); j++) // on parcours colonne par colonne...
				if((c.getColonne() >= this.debut.getColonne()) && (c.getColonne() <= this.fin.getColonne())) // si on trouve c...
					return true;
		else if((this.estHorizontal == false) && (this.debut.getColonne() == c.getColonne())) // Navire vertical, c sur la m�me colonne que le navire...
			for(int i = this.debut.getLigne(); i < this.fin.getLigne(); i++)	// on parcourt alors ligne par ligne...
				if((c.getLigne() >= this.debut.getLigne()) && (c.getLigne() <= this.debut.getLigne())) // si on trouve c...
					return true;
		return false; // if(((this.estHorizontal == true) && (this.debut.ligne != c.ligne)) || ((this.estHorizontal == false) && (this.debut.colonne != c.colonne)))
			}
	
	public boolean touche(Navire n) {
		return true;
	}
	
	public boolean chevauche(Navire n) {
		return true;
	}
	
	public boolean recoitTir(Coordonnee c) {
		if(this.contient(c)) {
			// On agrandit le tableau partiesTouchees
			// On incr�mente nbTouchees
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
		// si le tableau partiesTouchees contient autant de valeur que nbTouchees, alors le navire est coul�
		return true;
	}

	//// Main pour terster les diff�rentes m�thodes	////
	public static void main(String[] args) {

	}

}
