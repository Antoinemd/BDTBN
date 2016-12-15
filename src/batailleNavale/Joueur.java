package batailleNavale;

public abstract class Joueur {
	public final static int TOUCHE=1;
	public final static int COULE=2;
	public final static int A_L_EAU=3;
	private Joueur adversaire;
	private GrilleNavale grille;
	private String nom;
	
	/// Constructeur
	public Joueur(GrilleNavale g, String nom) {
		this.grille = g;
		this.nom = nom;
	}
	
	/// Méthodes
	public GrilleNavale getGrille() {
		return(this.grille);
	}
	
	public String getNom() {
		return(this.nom);
	}
	
	public void jouerAvec(Joueur j) {	// Constructeur de l'adversaire
		this.adversaire.grille = j.grille;
		this.adversaire.nom = j.nom;
		this.adversaire.adversaire = this;
	}
	
	public boolean defense(Coordonnee c) {
		if(this.grille.perdu()) {
			return false;
		}
		// Retour défense
		// Retour attaque
		return true;
	}
	
	public void attaque(Coordonnee c) {
		if (adversaire.defense(c)) {
			adversaire.debutAttaque();
		}
	}
	
	protected abstract void perdu();
	protected abstract void gagne();
	protected abstract void retourAttaque(Coordonnee c, int etat);
	protected abstract void retourDefense(Coordonnee c, int etat);
	public abstract void debutAttaque();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
