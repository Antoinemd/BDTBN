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
	
	public int getPartiesToucheesLength() {	// Pour debug
		return(partiesTouchees.length);
	}
	
	public boolean contient(Coordonnee c) {
		return((c.getLigne() == this.debut.getLigne()) && ((c.getColonne() >= this.debut.getColonne()) && ((c.getColonne() <= this.fin.getColonne()))) ||
				((c.getColonne() == this.debut.getColonne()) && ((c.getLigne() >= this.debut.getLigne()) && ((c.getLigne() <= this.fin.getLigne()))) )
				);
		// On regarde si la coordonnee c est sur la m�me ligne/colonne que this, puis on regarde si elle est comprise entre le d�but et la fin de this
			}
	
	public boolean touche(Navire n) {
		return false;
	}
	
	public boolean chevauche(Navire n) {
		return(((this.debut.getLigne() == n.debut.getLigne()) && (this.fin.getColonne() >= n.debut.getColonne()) && (this.debut.getColonne() <= n.fin.getColonne())) ||
				((this.debut.getColonne() == n.debut.getColonne()) && (this.fin.getLigne() >= n.debut.getLigne() && (this.debut.getLigne() <= n.fin.getLigne()))));
		// on regarde si le navire n est situ� sur la m�me ligne / colonne que this, et si les indices de lignes/colonnes de l'un sont dans l'intervalle de d�but/fin de l'autre.
	}
	
	public boolean recoitTir(Coordonnee c) {
			// On agrandit le tableau partiesTouchees
			boolean cExist = false;		// cExiste v�rifie si la coordonn�e c figure d�j� dans les parties touch�es du navire
			if(this.contient(c)) {		// on v�rifie si la coordonn�e correspond � une partie du navire
				for(int i = 0; i < this.partiesTouchees.length; i ++) {
					if(this.partiesTouchees[i] == c)	// si c existe dans le tableau
						cExist = true;					// c existe (captain obvious !)
				}
				if(!cExist) {
					for(int i = 0; i < this.partiesTouchees.length; i++) {
						if(this.partiesTouchees[i] == null)	// on cherche le premier espace nul du tableau
							this.partiesTouchees[i] = c;	// on y ajoute c
							nbTouchees += 1;				// on incr�mente le nombre de parties touch�es
					}
				}
			}
			return (!cExist);	// si c existe, le bateau ne peut pas �tre touch�. Si c n'existe pas encore, le bateau est touch�.
		}
	
	public boolean estTouche(Coordonnee c) {
		boolean cExist = false;
		for(int i = 0; i < this.partiesTouchees.length; i ++) {
			if(this.partiesTouchees[i] == c)	// si c existe dans le tableau
				cExist = true;					// c existe (captain obvious !)
		}
		return(cExist);
	}
	
	public boolean estTouche() {
		return(this.nbTouchees > 0);
	}
	
	public boolean estCoule() {
		// si le tableau partiesTouchees contient autant de valeur que nbTouchees, alors le navire est coul�
		return(this.partiesTouchees.length >= nbTouchees);
	}

	//// Main pour terster les diff�rentes m�thodes	////
	public static void main(String[] args) {

	}

}
