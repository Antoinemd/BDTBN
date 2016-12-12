package batailleNavale;

public class Navire {
	
	/*Les navires de la bataille navale sont representes par des instances de la classe Navire.
	 * Les coordonnees debut et fin marquent l'emplacement du navire, on suppose que debut est toujours inférieure à fin.
	 * partiesTouchees contient les coordonnees des parties du navire qui ont ete touchees et nbTouchees indique combien de parties ont ete touchees.
	3.*/
	
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {}
	
	// public Coordonnee getDebut() {}
	// public Coordonnee getFin() {}
	// public boolean contient(Coordonnee c) {}
	// public boolean touche(Navire n) {}
	// public boolean chevauche(Navire n) {}
	// public boolean recoitTir(Coordonnee c) {}
	// public boolean estTouche(Coordonnee c) {}
	// public boolean estTouche() {}
	// public boolean estCoule() {}

	//// Main pour terster les différentes méthodes	////
	public static void main(String[] args) {

	}

}
