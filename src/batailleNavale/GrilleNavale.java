package batailleNavale;

public class GrilleNavale {

	/*
	 * La classe GrilleNavale represente une grille de bataille navale. Les
	 * attributs navires et nbNavires représentent les navires places sur la
	 * grille ainsi que leur nombre. L'attribut taille donne la taille de la
	 * grille (la grille est de forme carrée). Les tirsRecus et nbTirsRecus
	 * referencent les coordonnees où des tirs ont ete effectués ainsi que leur
	 * nombre.
	 */

	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;

	// 3.1 : Constructeurs
	public GrilleNavale(int taille, int[] taillesNavires) {
		this.taille = taille;
		for (int i = 0; i < taillesNavires.length; i++)
			taille++;

		// taille de la grille obtenue : taille
		// taillesNavires.length : nombre de navires
		// taille de chaque navire : taillesNavires[i]

		// TODO : Prendre en compte la taille des navires
	}

	public GrilleNavale(int taille) {
		this.taille = taille;
	}

	// 3.2 : Méthodes
	public String toString() { // Andy : je pars du principe que la grille est
								// carrée
		String map = " ";
		char c = 'A';
		char n = '0';
		for (int i = 0; i <= this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {

				if (j == 0) { // si on est au début de la ligne, on met le
								// numéro de la ligne
					if (n == '0') // mais on n'affiche pas le zéro
						map += "";
					else
						map += n;
					n++;
				}

				if (i == 0) { // si on est sur la première ligne, on met des
								// lettres
					map += c + " ";
					c++;
				}

				if (j >= this.taille - 1)
					map += "\n"; // retour à la ligne en fin de ligne.

				if (i > 0 && j > 0) {
					// TODO : metttre un . c'est vide, # pour un bout de navire,
					// X pour un tir qui a touché, O pour un tir dans l'eau
					// TODO : d'abord parcourir Navire[] pour placer les navires
					// # et les emplacements vides .
					// TODO : ensuite parcours TirReçu pour placer les tirs dans
					// l'eau O ou sur navire X
				}
			}
		}
		return map;
	}

	public boolean ajouteNavire(Navire n) {
		boolean canAdd = true;
		for (int i = 0; i < navires.length; i++)
			if (n.touche(navires[i]) || n.chevauche(navires[i]))
				canAdd = false;
		return canAdd;
	}

	public void placementAuto(int[] taillesNavires) {
	}

	// private boolean estDansGrille(Coordonnee c) {}

	private boolean estDansTirsRecus(Coordonnee c) {
		for (int i = 0; i < this.tirsRecus.length; i++)
			return (this.tirsRecus[i] == c);
		return false;
	}

	public boolean ajouteDansTirsRecus(Coordonnee c) {
		if (!(this.estDansTirsRecus(c))) {
			for (int i = 0; i < this.tirsRecus.length; i++)
				if (this.tirsRecus[i] == null) {
					this.tirsRecus[i] = c;
					return true;
				}
		}
		return false;
	}
	// public boolean recoitTir(Coordonnee c) {}
	// public boolean estTouche(Coordonnee c) {}
	// public boolean estALEau(Coordonnee c) {}
	// public boolean estCoule(Coordonnee c) {}
	// public boolean perdu() {}

	//// Main pour terster les différentes méthodes ////
	public static void main(String[] args) {
		GrilleNavale plateau = new GrilleNavale(8);
		System.out.println(plateau.toString());
	}

}
