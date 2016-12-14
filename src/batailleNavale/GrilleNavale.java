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
		nbNavires = 12;
		navires = new Navire[nbNavires];
		nbTirsRecus = 0;
		tirsRecus = new Coordonnee[this.taille*this.taille];
		for (int i = 0; i < taillesNavires.length; i++)
			taille++;

		// taille de la grille obtenue : taille
		// taillesNavires.length : nombre de navires
		// taille de chaque navire : taillesNavires[i]

		// TODO : Prendre en compte la taille des navires
	}

	public GrilleNavale(int taille) {
		this.taille = taille;
		nbNavires = 12;
		navires = new Navire[nbNavires];
		nbTirsRecus = 0;
		tirsRecus = new Coordonnee[this.taille*this.taille];
	}

	// 3.2 : Méthodes
	public String toString() { // Andy : je pars du principe que la grille est carrée
		String map = "\t";
		for (int i = 0; i < this.taille; i++){
		char c = (char) (i + 'A');
		map += c + "\t";
		}
		
		for (int i = 1; i < this.taille + 1; i++) {
			map += "\n" + i;
			for (int j = 1; j < this.taille + 1; j++){
				boolean dejaTire = false;
				Coordonnee currentC = new Coordonnee(i, j);
				for (int k = 0; k < nbTirsRecus; k++) {
					if (tirsRecus[k].equals(currentC)) {
						if (estTouche(currentC)) {
							map += "\t X";
							dejaTire = true;
						}
						else if (estALEau(currentC)) {
							map += "\t o";
							dejaTire = true;
						}
					}
				}
				for (int k = 0; k < this.nbNavires; k++)
					if (navires[k].contient(currentC) && dejaTire == false) {
						map += "\t #";
						dejaTire = true;
					}
				if (dejaTire == false) {
					map += "\t .";
				}
			}
		}
		return (map);
	}

	public boolean ajouteNavire(Navire n) {
		boolean canAdd = true;
		for (int i = 0; i < navires.length; i++)
			if(navires[i] != null) {
				if (n.touche(navires[i]) || n.chevauche(navires[i]))
					canAdd = false;
			}
			else {
				for (int j = 0; j < this.navires.length; j++) {
					if(this.navires[i] == null) {
						this.navires[j] = n;
						nbNavires++;
					}
				}
			}
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
			if(this.tirsRecus[i] != null)
				if(this.tirsRecus[i].equals(c))
					return true;
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
	public boolean recoitTir(Coordonnee c) {
		if(!(estDansTirsRecus(c)))
			for(int i = 0; i < this.navires.length; i++) {
				if(this.navires[i].recoitTir(c))
					return true;
			}
		return false;
	}
	public boolean estTouche(Coordonnee c) {
		for(int i = 0; i < this.navires.length; i++) {
			if(this.navires[i].estTouche())
				return true;
		}
		return false;
	}
	
	public boolean estALEau(Coordonnee c) {
		if(!(estDansTirsRecus(c)))
			for(int i = 0; i < this.navires.length; i++) {
				if(this.navires[i].recoitTir(c))
					return false;
			}
		return true;
	}
	
	public boolean estCoule(Coordonnee c) {
		for(int i = 0; i < this.navires.length; i++) {
			if(this.navires[i].estCoule()) {
				nbNavires--;
				return true;
			}
		}
		return false;
	}
	
	public boolean perdu() {
		return(this.nbNavires == 0);
	}

	//// Main pour terster les différentes méthodes ////
	public static void main(String[] args) {
		Coordonnee c = new Coordonnee(4, 4);
		Coordonnee debn = new Coordonnee(2, 2);
		Navire n = new Navire(debn, 3, false);
		GrilleNavale plateau = new GrilleNavale(8);
		//System.out.println(plateau.toString());
		
		plateau.ajouteDansTirsRecus(c);
		plateau.ajouteNavire(n);
		System.out.println(n);
		//System.out.println(plateau.toString());
	}

}
