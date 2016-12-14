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
	
	/// M�thodes
	// public String toString() {}
	
	public boolean ajouteNavire(Navire n) {	// OK ! On peut m�me ajouter des navires s'il y en a 0 initialement
		if(this.nbNavires >= 0) {
			for(int i = 0; i < this.navires.length; i++) {
				if(this.navires[i] != null && (n.chevauche(this.navires[i]) || n.touche(this.navires[i])))
					return false;
			}
			this.nbNavires += 1;
			System.out.println("Nouveau navire n� : " + this.nbNavires);
			Navire[] naviresbis = new Navire[nbNavires];
			for(int i = 0; i < this.navires.length; i++)
				naviresbis[i] = this.navires[i];
			this.navires = naviresbis;
			this.navires[this.navires.length-1] = n;
			System.out.println("longueur tableau navire : " + this.navires.length);
			return true;
		}
		return false;
	}
	
	// public void placementAuto(int[] taillesNavires) {}
	
	private boolean estDansGrille(Coordonnee c) { // OK !
		return(c.getLigne() <= this.taille && c.getColonne() <= this.taille);
	}
	
	private boolean estDansTirsRecus(Coordonnee c) {	// OK !
		for(int i = 0; i < this.tirsRecus.length; i++)
			if(c.equals(this.tirsRecus[i]))
				return true;
		return false;
	}
	
	public boolean ajouteDansTirsRecus(Coordonnee c) {	// Ok !
		this.nbTirsRecus += 1;
		Coordonnee[] tirsRecusbis = new Coordonnee[nbTirsRecus];
		for(int i = 0; i < this.tirsRecus.length; i++)
			tirsRecusbis[i] = this.tirsRecus[i];
		this.tirsRecus = tirsRecusbis;
		this.tirsRecus[this.tirsRecus.length-1] = c;
		return true;	// this est modifi�, on renvoie true
	}
	
	public boolean recoitTir(Coordonnee c) {	// OK ?!
		if (this.estDansTirsRecus(c))
			return false;
		this.ajouteDansTirsRecus(c);
		for (int i = 0; i < this.navires.length; i++) {
			if (this.navires[i].recoitTir(c))
				return true;
		}
		return false;	
	}
	
	public boolean estTouche(Coordonnee c) {	// OK ?!
		for(int i = 0; i < this.navires.length; i++) {
			if(navires[i].estTouche(c))
				return true;
		}
		return false;
	}
	
	public boolean estALEau(Coordonnee c) {		// semble OK
		if(!this.estTouche(c))
			return true;
		return false;
	}
	
	public boolean estCoule(Coordonnee c) {		// semble OK !
		if(this.estTouche(c)) {
			for(int i = 0; i < this.navires.length; i++)
				if(this.navires[i].estCoule())
					return true;
		}
		return false;
	}
	
	public boolean perdu() {
		for(int i = 0; i < this.navires.length; i++)
			if(!this.navires[i].estCoule())
				return false;
		return true;
	}
	
	/// Accesseurs
	public String getNavires() {	// OK !
		if(this.navires.length == 0)
			return "[]";
		String content = "[" + this.navires[0];
		for(int i = 1; i < this.navires.length; i++)
			content += ", " + this.navires[i];
		return content + "]";
	}
	
	public String getTirsRecus() {	// OK !
		if(this.tirsRecus.length == 0)
			return "[]";
		String content = "[" + this.tirsRecus[0];
		for(int i = 1; i < this.tirsRecus.length; i++)
			content += ", " + this.tirsRecus[i];
		return content + "]";
	}

	public static void main(String[] args) {
		GrilleNavaleBis plateau = new GrilleNavaleBis(8, 0);
		Coordonnee a = new Coordonnee(1, 1);
		Coordonnee b = new Coordonnee(5, 5);
		Coordonnee c = new Coordonnee(7, 7);
		Coordonnee d = new Coordonnee(9, 9);
		Coordonnee e = new Coordonnee(1, 2);
		Coordonnee f = new Coordonnee(1, 3);
		Coordonnee g = new Coordonnee(1, 4);
		System.out.println("NbNavire initial : " + plateau.nbNavires);
		
		/// Ajouter Navire OK !
		Navire n1 = new Navire(a, 3, false);
		Navire n2 = new Navire(b, 3, false);
		Navire n3 = new Navire(b, 3, false);
		plateau.ajouteNavire(n1);
		//System.out.println("nombre de navires sur le plateau : " + plateau.nbNavires);
		System.out.println("Liste des navires : " + plateau.getNavires());
		plateau.ajouteNavire(n2);
		//System.out.println("nombre de navires sur le plateau : " + plateau.nbNavires);
		System.out.println("Liste des navires : " + plateau.getNavires());
		plateau.ajouteNavire(n3);
		//System.out.println("nombre de navires sur le plateau : " + plateau.nbNavires);
		System.out.println("Liste des navires : " + plateau.getNavires());
		
		/// Est dans grille ? OK !
//		System.out.println("a est dans la grille ? " + plateau.estDansGrille(a));
//		System.out.println("b est dans la grille ? " + plateau.estDansGrille(b));
//		System.out.println("c est dans la grille ? " + plateau.estDansGrille(c));
//		System.out.println("c est dans la grille ? " + plateau.estDansGrille(d));
		
		/// Ajouter dans tirs recus OK !
//		System.out.println("TirsRecus : " + plateau.getTirsRecus());
//		plateau.ajouteDansTirsRecus(a);
//		System.out.println("TirsRecus : " + plateau.getTirsRecus());
//		plateau.ajouteDansTirsRecus(b);
//		System.out.println("TirsRecus : " + plateau.getTirsRecus());
		
		// Est dans Tirs recus ? OK !
//		System.out.println("a est dans tirs recus ? " + plateau.estDansTirsRecus(a));
//		System.out.println("b est dans tirs recus ? " + plateau.estDansTirsRecus(b));
//		System.out.println("c est dans tirs recus ? " + plateau.estDansTirsRecus(c));
		
		// RecoitTir(c)	// OK � priori
		System.out.println("Recoit tir en a ? " + plateau.recoitTir(a));
		System.out.println("Recoit tir en b ? " + plateau.recoitTir(b));
		System.out.println("Recoit tir en c ? " + plateau.recoitTir(c));
		System.out.println("Recoit tir en d ? " + plateau.recoitTir(d));
		System.out.println("Recoit tir en e ? " + plateau.recoitTir(e));
		System.out.println("Recoit tir en f ? " + plateau.recoitTir(f));
		System.out.println("Recoit tir en g ? " + plateau.recoitTir(g));
		System.out.println("TirsRecus : " + plateau.getTirsRecus());
		
		// Un des navires est Touch� en (c) ? // OK � priori
		System.out.println("Un navire est-il touch� en a ? " + plateau.estTouche(a));
		System.out.println("Un navire est-il touch� en b ? " + plateau.estTouche(b));
		System.out.println("Un navire est-il touch� en c ? " + plateau.estTouche(c));
		System.out.println("Un navire est-il touch� en d ? " + plateau.estTouche(d));
		System.out.println("Un navire est-il touch� en e ? " + plateau.estTouche(e));
		System.out.println("Un navire est-il touch� en f ? " + plateau.estTouche(f));
		System.out.println("Un navire est-il touch� en g ? " + plateau.estTouche(g));
		
		// Est � l'eau (c) ?	// semble OK
		System.out.println("Le tir en a est-il � l'eau ? " + plateau.estALEau(a));
		System.out.println("Le tir en b est-il � l'eau ? " + plateau.estALEau(b));
		System.out.println("Le tir en c est-il � l'eau ? " + plateau.estALEau(c));
		System.out.println("Le tir en d est-il � l'eau ? " + plateau.estALEau(d));
		System.out.println("Le tir en e est-il � l'eau ? " + plateau.estALEau(e));
		System.out.println("Le tir en f est-il � l'eau ? " + plateau.estALEau(f));
		System.out.println("Le tir en g est-il � l'eau ? " + plateau.estALEau(g));
		
		// Est coul� ?	// semble OK !
		System.out.println("est coul� en a ? " + plateau.estCoule(a));
		System.out.println("est coul� en b ? " + plateau.estCoule(b));
		System.out.println("est coul� en c ? " + plateau.estCoule(c));
		System.out.println("est coul� en d ? " + plateau.estCoule(d));
		System.out.println("est coul� en e ? " + plateau.estCoule(e));
		System.out.println("est coul� en f ? " + plateau.estCoule(f));
	}

}
