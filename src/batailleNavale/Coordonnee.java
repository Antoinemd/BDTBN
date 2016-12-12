package batailleNavale;

public class Coordonnee {
	
	/*Une instance de la classe Coordonnee représente une coordonnee dans une grille de bataille
	navale. Cette classe doit en particulier permettre de realiser facilement des conversions entre le
	systeme de coordonnees utilise dans la bataille navale, où les colonnes sont notées par des lettres
	majuscules (de 'A' à 'Z') et les lignes par des nombres (de 1 à 26), et les indices utilises en Java
	(de 0 à 25).*/

	private int ligne;
	private int colonne;
	
	public Coordonnee(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}
	
	// public Coordonee(String s) {}
	// public String toString() {}
	public int getLigne() {
		return this.ligne;
	}
	public int getColonne() {
		return this.colonne;
	}
	// public boolean equals(Object obj) {}
	// public boolean voisine(Coordonnee c) {}
	
	
	//// Main pour terster les différentes méthodes	////
	public static void main(String[] args) {
		System.out.println("coucou le monde bis !");
	}

}
