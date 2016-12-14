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
	public String toString() { // Andy : je pars du principe que la grille est carrée
		String map = "";
		char c = 'A';
		char n = '0';
		for (int i = 0; i < this.taille + 1; i++) {		// on avance sur les lignes
			for (int j = 0; j < this.taille + 1; j++) {		// on avance sur les colonnes
				
				// Première colonne : on liste les chiffres
				if(j == 0) {
					if(n == '0') {	// On n'affiche pas le 0
						map += " " + " ";
						n++;
					}
					else {
					map += n + " ";	// Mais on affiche les autres chiffres !
					n++;
					}
				}
				
				// Première ligne : on liste les lettres
				if(i == 0 && j < this.taille) {
					map += c + " ";
					c++;
				}
				else if(i == 0 && j >= this.taille) {
					map += "\n";	// on retourne à la ligne à la fin de la liste de lettres
				}
				
				
				if (i > 0 && j > 0) {
					// TODO : metttre un . c'est vide, # pour un bout de navire,
					// X pour un tir qui a touché, O pour un tir dans l'eau
					// TODO : d'abord parcourir Navire[] pour placer les navires
					// # et les emplacements vides .
					// TODO : ensuite parcours TirReçu pour placer les tirs dans
					// l'eau O ou sur navire X
					if(j < this.taille)
						map += ". ";
					else
						map += ".\n";
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
			else
				nbNavires++;
		return canAdd;
	}

	public void placementAuto(int[] taillesNavires) {
	}

	private boolean estDansGrille(Coordonnee c) {
		if (c.getColonne() <= this.taille && c.getLigne() <= this.taille)
            return true;
		return false;
	}
	
	public Coordonnee[] getTirsRecus() { // Debug
        return this.tirsRecus;
	}

	public void setTirsRecus(Coordonnee[] tirs) {	// Debug
        this.tirsRecus = tirs;
	}

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
		Coordonnee c = new Coordonnee(4, 4);
		GrilleNavale plateau = new GrilleNavale(8);
		System.out.println(plateau.toString());
		plateau.ajouteDansTirsRecus(c);
	}

}
