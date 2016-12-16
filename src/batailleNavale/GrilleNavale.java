package batailleNavale;

public class GrilleNavale {
	
	private int taille;
	private int nbNavires;
	private Navire[] navires;
	private int nbTirsRecus;
	private Coordonnee[] tirsRecus;
	
	/// Constructeurs
	public GrilleNavale(int taille, int[] taillesNavires) {
		this.taille = taille;
		this.nbNavires = 0;
		this.navires = new Navire[this.nbNavires];
		this.nbTirsRecus = 0;
		this.tirsRecus = new Coordonnee[this.nbTirsRecus];
		
		if(taillesNavires.length > 1) {
			int k = 0; // interm�diaire pour intervertir deux valeurs
			boolean permut;	// on inverse ou non
			do {
				permut = false;
				for(int i = 0; i < taillesNavires.length - 1; i++) {
					if(taillesNavires[i] < taillesNavires[i + 1]) {
						k = taillesNavires[i];
						taillesNavires[i] = taillesNavires[i + 1];
						taillesNavires[i + 1] = k;
						permut = true;
					}
				}	
			} while(permut);
		}
	}
	
	public GrilleNavale(int taille, int nbNavires) {
		this.taille = taille;
		this.nbNavires = nbNavires;
		this.navires = new Navire[this.nbNavires];
		this.nbTirsRecus = 0;
		this.tirsRecus = new Coordonnee[this.nbTirsRecus];
	}
	
	/// M�thodes
	public String toString() {
		String map = "\t";
		for (int i = 0; i < this.taille; i++){
		char c = (char) (i + 'A');
		map += c + "\t";
		}
		
		for (int i = 0; i < this.taille; i++) {
			map += "\n" + (i + 1);
			for (int j = 0; j < this.taille; j++){
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
					try {
					if (navires[k].contient(currentC) && dejaTire == false) {
						map += "\t #";
						dejaTire = true;
					}
					} catch(Exception e) {/*System.out.println("Null Pointer Exception");*/}
				if (dejaTire == false) {
					map += "\t .";
				}
			}
		}
		return (map);
	}
	
	public boolean ajouteNavire(Navire n) {	// OK ! On peut m�me ajouter des navires s'il y en a 0 initialement
		// Ajouter une exception si le Navire si retrouve en dehors de la grille*
		if(!((this.estDansGrille(n.getDebut())) && (this.estDansGrille(n.getFin()))))
			return false;
		
		if(this.nbNavires >= 0) {
			for(int i = 0; i < this.navires.length; i++) {
				if(this.navires[i] != null && (n.chevauche(this.navires[i]) || n.touche(this.navires[i])))
					return false;
			}
			this.nbNavires += 1;
			//System.out.println("Nouveau navire n� : " + this.nbNavires);
			Navire[] naviresbis = new Navire[nbNavires];
			for(int i = 0; i < this.navires.length; i++)
				naviresbis[i] = this.navires[i];
			this.navires = naviresbis;
			this.navires[this.navires.length-1] = n;
			//System.out.println("longueur tableau navire : " + this.navires.length);
			return true;
		}
		return false;
	}
	
	public void placementAuto(int[] taillesNavires) {
		int i = 0;
		long start = System.currentTimeMillis();	// Timer pour sortir de la boucle si on n'arrive pas � cr�er le plateau de jeu
		long end = 1000;

			while(i < taillesNavires.length && (System.currentTimeMillis()-start) < end) {
				int nbNaviresInit = this.nbNavires;	// Nomre de navires avant la cr�ation d'un nouveau navire qui sera plac� al�atoirement
				boolean estVertical = (Math.random() < 0.5);
				Coordonnee c = new Coordonnee((int)(Math.random() * (taille - taillesNavires[i])), (int)(Math.random() * (taille - taillesNavires[i])));
				Navire n = new Navire(c, taillesNavires[i], estVertical);
				this.ajouteNavire(n);
				if(this.nbNavires > nbNaviresInit)
					i++;
			}
			if(i < taillesNavires.length && (System.currentTimeMillis()-start) >= end)
				System.out.println("Trop de navires / Navires trop grands par rapport au plateau de jeu. Veuillez red�marrer le jeu.");
	}
	
	private boolean estDansGrille(Coordonnee c) { 	// OK !
		return(c.getLigne() >= 0 && c.getColonne() >= 0 && c.getLigne() < this.taille && c.getColonne() < this.taille);
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
			content += ",\n " + this.navires[i];
		return content + "]";
	}
	
	public int getTailleNavires() {
		return(this.navires.length);
	}
	
	public String getTirsRecus() {	// OK !
		if(this.tirsRecus.length == 0)
			return "[]";
		String content = "[" + this.tirsRecus[0];
		for(int i = 1; i < this.tirsRecus.length; i++)
			content += ", " + this.tirsRecus[i];
		return content + "]";
	}

	public int getTailleGrille() {
		return(this.taille);
	}
	
	public boolean getEstDansTirsRecus(Coordonnee c) {
		return(this.estDansTirsRecus(c));
	}
	
	public Coordonnee[] getTableauTirsRecus() { // getter pour r�cuperer le tableau des tirsRecus
		return (this.tirsRecus);
	}
	
	public boolean getEstDansGrille(Coordonnee c) {
		return(this.estDansGrille(c));
	}
	
	
	public static void main(String[] args) {
//		GrilleNavaleBis plateau = new GrilleNavaleBis(8, 0);
//		System.out.println(plateau);
//		Coordonnee a = new Coordonnee(1, 1);
//		Coordonnee b = new Coordonnee(5, 5);
//		Coordonnee bb = new Coordonnee(5, 6);
//		Coordonnee bbb = new Coordonnee(5, 7);
//		Coordonnee c = new Coordonnee(7, 7);
//		Coordonnee d = new Coordonnee(9, 9);
//		Coordonnee e = new Coordonnee(1, 2);
//		Coordonnee f = new Coordonnee(1, 3);
//		Coordonnee g = new Coordonnee(1, 4);
//		System.out.println("NbNavire initial : " + plateau.nbNavires);
//		
//		/// Ajouter Navire OK !
//		Navire n1 = new Navire(a, 3, false);
//		Navire n2 = new Navire(b, 3, false);
//		Navire n3 = new Navire(b, 3, false);
//		plateau.ajouteNavire(n1);
//		//System.out.println("nombre de navires sur le plateau : " + plateau.nbNavires);
//		System.out.println("Liste des navires : " + plateau.getNavires());
//		plateau.ajouteNavire(n2);
//		//System.out.println("nombre de navires sur le plateau : " + plateau.nbNavires);
//		System.out.println("Liste des navires : " + plateau.getNavires());
//		plateau.ajouteNavire(n3);
//		//System.out.println("nombre de navires sur le plateau : " + plateau.nbNavires);
//		System.out.println("Liste des navires : " + plateau.getNavires());
//		
//		/// Est dans grille ? OK !
////		System.out.println("a est dans la grille ? " + plateau.estDansGrille(a));
////		System.out.println("b est dans la grille ? " + plateau.estDansGrille(b));
////		System.out.println("c est dans la grille ? " + plateau.estDansGrille(c));
////		System.out.println("c est dans la grille ? " + plateau.estDansGrille(d));
//		
//		/// Ajouter dans tirs recus OK !
////		System.out.println("TirsRecus : " + plateau.getTirsRecus());
////		plateau.ajouteDansTirsRecus(a);
////		System.out.println("TirsRecus : " + plateau.getTirsRecus());
////		plateau.ajouteDansTirsRecus(b);
////		System.out.println("TirsRecus : " + plateau.getTirsRecus());
//		
//		// Est dans Tirs recus ? OK !
////		System.out.println("a est dans tirs recus ? " + plateau.estDansTirsRecus(a));
////		System.out.println("b est dans tirs recus ? " + plateau.estDansTirsRecus(b));
////		System.out.println("c est dans tirs recus ? " + plateau.estDansTirsRecus(c));
//		
//		// RecoitTir(c)	// OK � priori
//		System.out.println("Recoit tir en a ? " + plateau.recoitTir(a));
//		System.out.println("Recoit tir en b ? " + plateau.recoitTir(b));
//		System.out.println("Recoit tir en c ? " + plateau.recoitTir(c));
//		System.out.println("Recoit tir en d ? " + plateau.recoitTir(d));
//		System.out.println("Recoit tir en e ? " + plateau.recoitTir(e));
//		System.out.println("Recoit tir en f ? " + plateau.recoitTir(f));
//		System.out.println("Recoit tir en g ? " + plateau.recoitTir(g));
//		System.out.println("TirsRecus : " + plateau.getTirsRecus());
//		
//		// Un des navires est Touch� en (c) ? // OK � priori
//		System.out.println("Un navire est-il touch� en a ? " + plateau.estTouche(a));
//		System.out.println("Un navire est-il touch� en b ? " + plateau.estTouche(b));
//		System.out.println("Un navire est-il touch� en c ? " + plateau.estTouche(c));
//		System.out.println("Un navire est-il touch� en d ? " + plateau.estTouche(d));
//		System.out.println("Un navire est-il touch� en e ? " + plateau.estTouche(e));
//		System.out.println("Un navire est-il touch� en f ? " + plateau.estTouche(f));
//		System.out.println("Un navire est-il touch� en g ? " + plateau.estTouche(g));
//		
//		// Est � l'eau (c) ?	// semble OK
//		System.out.println("Le tir en a est-il � l'eau ? " + plateau.estALEau(a));
//		System.out.println("Le tir en b est-il � l'eau ? " + plateau.estALEau(b));
//		System.out.println("Le tir en c est-il � l'eau ? " + plateau.estALEau(c));
//		System.out.println("Le tir en d est-il � l'eau ? " + plateau.estALEau(d));
//		System.out.println("Le tir en e est-il � l'eau ? " + plateau.estALEau(e));
//		System.out.println("Le tir en f est-il � l'eau ? " + plateau.estALEau(f));
//		System.out.println("Le tir en g est-il � l'eau ? " + plateau.estALEau(g));
//		
//		// Est coul� ?	// semble OK !
//		System.out.println("est coul� en a ? " + plateau.estCoule(a));
//		System.out.println("est coul� en b ? " + plateau.estCoule(b));
//		System.out.println("est coul� en c ? " + plateau.estCoule(c));
//		System.out.println("est coul� en d ? " + plateau.estCoule(d));
//		System.out.println("est coul� en e ? " + plateau.estCoule(e));
//		System.out.println("est coul� en f ? " + plateau.estCoule(f));
//		
//		// Perdu ?	// OK !
//		System.out.println("Perdu ? " + plateau.perdu());
//		plateau.recoitTir(bb);
//		plateau.recoitTir(bbb);
//		System.out.println("TirsRecus : " + plateau.getTirsRecus());
//		System.out.println("Perdu ? " + plateau.perdu());
//		
//		System.out.println(plateau);
		
		/// Plateau al�atoire
		int tN[] = {2, 3, 3, 4, 5};
		GrilleNavale plateauAl = new GrilleNavale(10, tN);
		System.out.println(plateauAl);
		plateauAl.placementAuto(tN);
		System.out.println(plateauAl);
		System.out.println(plateauAl.getNavires());
		
//		Coordonnee a = new Coordonnee(1, 1);
//		Coordonnee b = new Coordonnee(2, 3);
//		Coordonnee c = new Coordonnee(5, 6);
//		Coordonnee d = new Coordonnee(4, 9);
//		Coordonnee e = new Coordonnee(7, 7);
//		Coordonnee f = new Coordonnee(6, 2);
//		Coordonnee g = new Coordonnee(1, 7);
//		Coordonnee h = new Coordonnee(3, 1);
//		Coordonnee i = new Coordonnee(4, 4);
//		plateauAl.recoitTir(a);
//		plateauAl.recoitTir(b);
//		plateauAl.recoitTir(c);
//		plateauAl.recoitTir(d);
//		plateauAl.recoitTir(e);
//		plateauAl.recoitTir(f);
//		plateauAl.recoitTir(g);
//		plateauAl.recoitTir(h);
//		plateauAl.recoitTir(i);
//		System.out.println(plateauAl);
		
	}

}
