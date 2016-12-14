package batailleNavale;

public class GrilleNavale {

	/*
	* La classe GrilleNavale represente une grille de bataille navale. Les
	* attributs navires et nbNavires repr�sentent les navires places sur la
	* grille ainsi que leur nombre. L'attribut taille donne la taille de la
	* grille (la grille est de forme carr�e). Les tirsRecus et nbTirsRecus
	* referencent les coordonnees o� des tirs ont ete effectu�s ainsi que leur
	* nombre.
	*/

	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;

	// 3.1 : Constructeurs
	public GrilleNavale(int taille, int[] taillesNavires) {
		if(taillesNavires.length==0)throw new IllegalArgumentException();
		if(taille<=1)throw new IllegalArgumentException();
		
		this.taille = taille;
	    this.nbNavires = 0;
	    this.nbTirsRecus = 0;
	    this.tirsRecus = new Coordonnee[(taille * taille)];
	    this.navires = new Navire[taillesNavires.length];
	    placementAuto(taillesNavires);
	}

	public GrilleNavale(int taille) {
		//if(nbNavires<=0)throw new IllegalArgumentException();
        //if(taille<=0)throw new IllegalArgumentException();
        this.taille = taille;
        this.navires = new Navire[nbNavires];
        
        this.nbNavires = 0;
        this.nbTirsRecus = 0;
        this.tirsRecus = new Coordonnee[(taille * taille)];
	}

	// 3.2 : M�thodes
	public String toString() { // Andy : je pars du principe que la grille est carr�e
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
//		char n = '0';
//		for (int i = 0; i < this.taille + 1; i++) {		// on avance sur les lignes
//			for (int j = 0; j < this.taille + 1; j++) {		// on avance sur les colonnes
//				
//				// Premi�re colonne : on liste les chiffres
//				if(j == 0) {
//					if(n == '0') {	// On n'affiche pas le 0
//						map += " " + " ";
//						n++;
//					}
//					else {
//					map += n + " ";	// Mais on affiche les autres chiffres !
//					n++;
//					}
//				}
//				
//				// Premi�re ligne : on liste les lettres
//				if(i == 0 && j < this.taille) {
//					map += c + " ";
//					c++;
//				}
//				else if(i == 0 && j >= this.taille) {
//					map += "\n";	// on retourne � la ligne � la fin de la liste de lettres
//				}
//				
//				
//				if (i > 0 && j > 0) {
//					// TODO : metttre un . c'est vide, # pour un bout de navire,
//					// X pour un tir qui a touch�, O pour un tir dans l'eau
//					// TODO : d'abord parcourir Navire[] pour placer les navires
//					// # et les emplacements vides .
//					// TODO : ensuite parcours TirRe�u pour placer les tirs dans
//					// l'eau O ou sur navire X
//					if(j < this.taille) {
//						for(int k = 0; k < this.tirsRecus.length; k++) {
//							if(this.tirsRecus[k] != null)							
//								if(this.tirsRecus[k].getLigne() == i && this.tirsRecus[k].getColonne() == j)
//									map += "X" + " ";
//						}
//						map += ". ";
//					}
//					else  {
//						map += ".\n";
//					}
//				}
//			}
//		}
//		return map;
//	}

	public boolean ajouteNavire(Navire n) {
		
		//On verifie que le bateau n est bien dans la grille
        if(!estDansGrille(n.getDebut())    && !estDansGrille(n.getFin()))throw new IllegalArgumentException();
        //On verifie que le nombre maximum de bateau n'est pas atteint
        //if(nbNavires==navires.length){System.out.print("Vous avez d�ja le nombre maximum de bateau");throw new IllegalArgumentException();}
        
        //Si c'est le premier navire on le rentre dans le tableau
        if(this.nbNavires==0){
            this.nbNavires += 1;
            this.navires = new Navire[nbNavires];
            this.navires[0] = n;
            return true;
        }
        
        // Si le bateau touche ou chevauche un bateau existant on relance la g�n�ration
        for (int i = 0; i < this.nbNavires; i++)
            if (this.navires[i].chevauche(n) || this.navires[i].touche(n)) {
                return false;
                }
            else {	// si le bateau est valide on incremente nbNavires
            	this.nbNavires += 1;
            	this.navires = new Navire[nbNavires];
            	this.navires[nbNavires] = n;
            }
        return true;
    }

	public void placementAuto(int[] taillesNavires) {
		for (int i = 0; i < taillesNavires.length; i++) {
            //On g�n�re des coordonn�es al�atoire valide avec la taille de la grille
            Coordonnee coord = new Coordonnee((int) (Math.random() * (taille - taillesNavires[i]))+1,(int) (Math.random() * (taille - taillesNavires[i])+1));
            //on g�n�re le boolean qui indiquera si le bateau est vertical ou horizontal
            boolean bool = Math.random() < 0.5;
            
            //Tant que le bateau n'a pas �t� accept� (qu'il ne chevauche, ni ne touche un autre bateau) on continue de g�n�rer des coordonn�es
            while(!ajouteNavire( new Navire(coord, taillesNavires[i], bool))){
                coord = new Coordonnee((int) (Math.random() * (taille - taillesNavires[i])),(int) (Math.random() * (taille - taillesNavires[i])));
                bool = Math.random() < 0.5;
            }
        }
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
		for (int i = 0; i < nbTirsRecus; i++)
			if(this.tirsRecus[i].equals(c))
					return true;
		return false;
	}

	public boolean ajouteDansTirsRecus(Coordonnee c) {
		tirsRecus[nbTirsRecus] = c;
        nbTirsRecus += 1;
        return false;
	}
	public boolean recoitTir(Coordonnee c) {
		//On verifie si un tir n'a pas d�ja �t� effectu� a ces coordonn�es
        if(estDansTirsRecus(c))throw new IllegalArgumentException();
        // si il n'a pas encore �tait touch� en c
        if (!estTouche(c)) {
            //on update le tableau partiesTouchees du navire
            for (int i = 0; i < this.nbNavires; i++){
                this.navires[i].recoitTir(c);
            }
            
            ajouteDansTirsRecus(c);
            return true;
        }
        return false;
    }
	
	public boolean estTouche(Coordonnee c) {
		for (int i = 0; i < this.nbNavires; i++){
	           if (this.navires[i].estTouche(c))
	               return true;
	       }
	       return false;
	   }
	
	public boolean estALEau(Coordonnee c) {
		if (!estTouche(c))
            return true;
        return false;
    }
	
	public boolean estCoule(Coordonnee c) {
		if (estTouche(c)) {
	           for (int j = 0; j < nbNavires; j++)
	               if (navires[j].estCoule())
	                   return true;
	       }
	       return false;
	   }
	
	public boolean perdu() {
		for (int j = 0; j < nbNavires; j++)
            if (!navires[j].estCoule())
                return false;
        return true;
    }

	//// Main pour terster les diff�rentes m�thodes ////
	public static void main(String[] args) {
		//int[] tailleNav = { 2, 4 };
		Coordonnee[] tirs = { new Coordonnee(4,4), new Coordonnee(6,6) };
		Coordonnee c = new Coordonnee(4, 4);
		Coordonnee d = new Coordonnee(2, 2);
		Coordonnee e = new Coordonnee(2, 7);
		Navire n = new Navire(d, 3, false);
		Navire o = new Navire(e, 4, true);
		GrilleNavale plateau = new GrilleNavale(8);
		plateau.ajouteNavire(n);
		plateau.ajouteNavire(o);
		System.out.println(plateau.toString());
		plateau.ajouteDansTirsRecus(c);
		System.out.println(plateau.toString());
	}

}