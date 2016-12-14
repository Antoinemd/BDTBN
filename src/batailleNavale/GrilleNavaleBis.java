package batailleNavale;

public class GrilleNavaleBis {
	
	private int taille;
	private int nbNavires;
	private Navire[] navires;
	private int nbTirsRecus;
	private Coordonnee[] tirsRecus;
	
	/// Constructeurs
	// public GrilleNavaleBis(int taille, int[] taillesNavires) {}
	
	public GrilleNavaleBis(int taille, int nbNavires) {
		this.taille = taille;
		this.nbNavires = nbNavires;
		this.navires = new Navire[this.nbNavires];
		this.nbTirsRecus = 0;
		this.tirsRecus = new Coordonnee[this.nbTirsRecus];
	}
	
	/// Méthodes
	// public String toString() {}
	
	public boolean ajouteNavire(Navire n) {
		if(this.nbNavires >= 0) {
			for(int i = 0; i < nbNavires; i++) {
				if(this.navires[i] != null) {
					if(this.navires[i].touche(n) || this.navires[i].chevauche(n)) {
						return false;
					}
				}
				else {
					this.nbNavires += 1;
					System.out.println("Nouveau navire n° : " + this.nbNavires);
					this.navires = new Navire[nbNavires];
					this.navires[this.navires.length-1] = n;
					System.out.println("longueur tableau navire : " + this.navires.length);
					return true;
				}
			}
		}
		return false;
	}
	
	// public void placementAuto(int[] taillesNavires) {}
	
	private boolean estDansGrille(Coordonnee c) {
		return(c.getLigne() <= this.taille && c.getColonne() <= this.taille);
	}
	// private boolean estDansTirsRecus(Coordonnee c) {}
	
	public boolean ajouteDansTirsRecus(Coordonnee c) {
		this.nbTirsRecus += 1;
		this.tirsRecus[nbTirsRecus] = c;
		return true;	// this est modifié, on renvoie true
	}
	
	public boolean recoitTir(Coordonnee c) {
		
	}
	
	public boolean estTouche(Coordonnee c) {
		for(int i = 0; i < this.navires.length; i++) {
			if(navires[i].estTouche())
				return true;
		}
		return false;
	}
	
	// public boolean estALEau(Coordonnee c) {}
	// public boolean estCoule(Coordonnee c) {}
	// public boolean perdu() {}

	public static void main(String[] args) {
		GrilleNavaleBis plateau = new GrilleNavaleBis(8, 1);
		Coordonnee a = new Coordonnee(1, 1);
		Coordonnee b = new Coordonnee(5, 5);
		System.out.println(plateau.nbNavires);
		
		Navire n1 = new Navire(a, 3, false);
		Navire n2 = new Navire(b, 3, false);
		Navire n3 = new Navire(b, 3, false);
		plateau.ajouteNavire(n1);
		System.out.println("nombre de navires sur le plateau : " + plateau.nbNavires);
		plateau.ajouteNavire(n2);
		System.out.println("nombre de navires sur le plateau : " + plateau.nbNavires);
		plateau.ajouteNavire(n3);
		System.out.println("nombre de navires sur le plateau : " + plateau.nbNavires);

	}

}
