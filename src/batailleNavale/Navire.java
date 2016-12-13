package batailleNavale;

public class Navire {
	
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;
	private boolean estHorizontal;

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		this.debut = debut;
		if(estVertical) {
			this.fin = new Coordonnee(this.debut.getLigne() + longueur - 1, this.debut.getColonne());	// Pourquoi -1 ?
			this.estHorizontal = false;
		}
		else {
			this.fin = new Coordonnee(this.debut.getLigne(), this.debut.getColonne() + longueur - 1);	// Pourquoi -1 ?
			this.estHorizontal = true;
		}
		partiesTouchees = new Coordonnee[longueur];
		nbTouchees = 0;
	}
	
	public String toString() {
		String s = "";
		int longueur;
		s += "Navire (" + this.debut.toString() + ", ";
		if(this.estHorizontal) {
			longueur = (this.fin.getColonne() - this.debut.getColonne()) + 1;	// +1 pour compenser le longueur -1 incompris du constructeur Navire()
			s += longueur + ", Horizontal)";
		}
		else {
			longueur = (this.fin.getLigne() - this.debut.getLigne()) + 1;		// Pareil
			s += longueur + ", Vertical)";
		}
		return s;
	}
	
	public Coordonnee getDebut() {
		return this.debut;
	}
	
	public Coordonnee getFin() {
		return this.fin;
	}
	
	public boolean contient(Coordonnee c) {
		return((c.getLigne() == this.debut.getLigne()) && ((c.getColonne() >= this.debut.getColonne()) && ((c.getColonne() <= this.fin.getColonne()))) ||
				((c.getColonne() == this.debut.getColonne()) && ((c.getLigne() >= this.debut.getLigne()) && ((c.getLigne() <= this.fin.getLigne()))) )
				);
		// On regarde si la coordonnee c est sur la même ligne/colonne que this, puis on regarde si elle est comprise entre le début et la fin de this
			}
	
	public boolean touche(Navire n) {
		return false;
	}
	
	public boolean chevauche(Navire n) {
		return(((this.debut.getLigne() == n.debut.getLigne()) && (this.fin.getColonne() >= n.debut.getColonne()) && (this.debut.getColonne() <= n.fin.getColonne())) ||
				((this.debut.getColonne() == n.debut.getColonne()) && (this.fin.getLigne() >= n.debut.getLigne() && (this.debut.getLigne() <= n.fin.getLigne()))));
		// on regarde si le navire n est situé sur la même ligne / colonne que this, et si les indices de lignes/colonnes de l'un sont dans l'intervalle de début/fin de l'autre.
	}
	
	public boolean recoitTir(Coordonnee c) {
			// On agrandit le tableau partiesTouchees
			boolean cExist = false;		// cExiste vérifie si la coordonnée c figure déjà dans les parties touchées du navire
			if(this.contient(c)) {		// on vérifie si la coordonnée correspond à une partie du navire
				for(int i = 0; i < this.partiesTouchees.length; i ++) {
					if(this.partiesTouchees[i] == c)	// si c existe dans le tableau
						cExist = true;					// c existe (captain obvious !)
				}
				if(!cExist) {
					for(int i = 0; i < this.partiesTouchees.length; i++) {
						if(this.partiesTouchees[i] == null)	// on cherche le premier espace nul du tableau
							this.partiesTouchees[i] = c;	// on y ajoute c
							nbTouchees += 1;				// on incrémente le nombre de parties touchées
					}
				}
			}
			return (!cExist);
		}
	
	public boolean estTouche(Coordonnee c) {
		return(this.recoitTir(c));
	}
	
	public boolean estTouche() {
		if(this.nbTouchees > 0)
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
