package batailleNavale;

public class Navire {

	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;
	private boolean estHorizontal;

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		this.debut = debut;
		if (estVertical) {
			this.fin = new Coordonnee(this.debut.getLigne() + longueur - 1, this.debut.getColonne()); // Pourquoi
																										// -1
																										// ?
			this.estHorizontal = false;
		} else {
			this.fin = new Coordonnee(this.debut.getLigne(), this.debut.getColonne() + longueur - 1); // Pourquoi
																										// -1
																										// ?
			this.estHorizontal = true;
		}
		partiesTouchees = new Coordonnee[longueur];
		nbTouchees = 0;
	}

	public String toString() {	// OK !
		String s = "";
		int longueur;
		s += "Navire (" + this.debut.toString() + ", ";
		if (this.estHorizontal) {
			longueur = (this.fin.getColonne() - this.debut.getColonne()) + 1; // +1 pour compenser le -1 du constructeur
			s += longueur + ", Horizontal)";
		} else {
			longueur = (this.fin.getLigne() - this.debut.getLigne()) + 1; // Pareil
			s += longueur + ", Vertical)";
		}
		return s;
	}

	/// Accesseurs
	public Coordonnee getDebut() {	// OK !
		return this.debut;
	}

	public Coordonnee getFin() { // OK !
		return this.fin;
	}

	public int getPartiesToucheesLength() { // Pour debug
		return (partiesTouchees.length);
	}
	
	public int getNbTouchees() {
		return this.nbTouchees;
	}
	
	public String PTContent() {		// Contenu du tableau de coordonnees partiesTouchees
		if(this.partiesTouchees.length == 0)
			return "[]";
		String content = "[" + this.partiesTouchees[0];
		for(int i = 1; i < this.partiesTouchees.length; i++)
			content += ", " + this.partiesTouchees[i];
		return content + "]";
		
	}
	
	/// Méthodes
	public boolean contient(Coordonnee c) {	// OK !
		return ((c.getLigne() == this.debut.getLigne())
				&& ((c.getColonne() >= this.debut.getColonne()) && ((c.getColonne() <= this.fin.getColonne())))
				|| ((c.getColonne() == this.debut.getColonne())
						&& ((c.getLigne() >= this.debut.getLigne()) && ((c.getLigne() <= this.fin.getLigne())))));
		// On regarde si la coordonnee c est sur la même ligne/colonne que this,
		// puis on regarde si elle est comprise entre le début et la fin de this
	}

	public boolean touche(Navire n) {	// OK !
		// this doit toucher n
		if(!(
				(this.fin.getLigne() == n.debut.getLigne() - 1 && this.fin.getColonne() == n.debut.getColonne() - 1) ||
				(this.debut.getLigne() == n.fin.getLigne() + 1 && this.fin.getColonne() == n.debut.getColonne() - 1) ||
				(this.fin.getLigne() == n.debut.getLigne() - 1 && this.debut.getColonne() == n.fin.getColonne() + 1) ||
				(this.debut.getLigne() == n.fin.getLigne() + 1 && this.debut.getColonne() == n.fin.getColonne() +1)
				)) {
			
				return(
				(this.fin.getColonne() + 1 >= n.debut.getColonne()
				&&
				n.fin.getColonne() +1 >= this.debut.getColonne())	// Les colonnes fonctionnes \o/
				&&
				(this.fin.getLigne() + 1 >= n.debut.getLigne()		// Pas les lignes /o\
				&&
				n.fin.getLigne() + 1 >= this.debut.getLigne())
				);
		}
		else
			return(false);
	}

	public boolean chevauche(Navire n) {	// OK !
		return (((this.fin.getColonne() >= n.debut.getColonne()) && (this.debut.getColonne() <= n.fin.getColonne())
				&& (this.fin.getLigne() >= n.debut.getLigne() && this.debut.getLigne() <= n.fin.getLigne())));
		// on regarde si le navire n est situé sur la même ligne / colonne que
		// this, et si les indices de lignes/colonnes de l'un sont dans
		// l'intervalle de début/fin de l'autre.
	}

	public boolean recoitTir(Coordonnee c) {
			if (this.contient(c)) {	// est-ce que this contient c ?
				if(!(this.estTouche(c))) {	// n'a pas été touché en c ?
					if(this.nbTouchees < this.partiesTouchees.length) {
						this.partiesTouchees[nbTouchees] = c;	// ajoute l'endroit où le navire a été touché
						this.nbTouchees += 1;	// augmente le nb de touches
					}
					return true;	// le tir a été reçu
				}
			}
			return false;	// sinon, si ne contient pas c / a été touché en c alors ne reçoit pas le tir
	}

	public boolean estTouche(Coordonnee c) {		// Est-ce que le tir en coordonnées c touche le navire ?
		// On cherche a savoir si c existe dans partiesTouchees
		for(int i = 0; i < this.nbTouchees; i++) {
			if(this.partiesTouchees[i] != null)
				if(this.partiesTouchees[i].equals(c))
					return true;
		}
		return false;
	}

	public boolean estTouche() {
		return (this.nbTouchees > 0);
	}

	public boolean estCoule() {
		// si le tableau partiesTouchees contient autant de valeur que
		// nbTouchees, alors le navire est coulé
		return (this.nbTouchees >= this.partiesTouchees.length);
	}

	//// Main pour terster les différentes méthodes ////
	public static void main(String[] args) {
		Coordonnee a = new Coordonnee(2, 2);
		Coordonnee b = new Coordonnee(8, 8);
		Navire n = new Navire(a, 3, true);
		System.out.println("n recoit un tir en a : " + n.recoitTir(a));
		System.out.println("n est touché en a : " + n.estTouche(a));
		System.out.println("n est touché en b : " + n.estTouche(b));

	}

}
