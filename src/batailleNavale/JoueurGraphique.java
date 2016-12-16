package batailleNavale;

public class JoueurGraphique extends Joueur implements CaseClickListener {
	
	private GrilleGraphique grilleTirs;
	private boolean tirActif;

	public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
		
		this.tirActif = false;
	}

	private void setTirActif(boolean b) {
		this.tirActif = b;
		grilleTirs.setClicActive(b);
		grilleTirs.setCaseClickListener(this);
	}

	public GrilleNavaleGraphique getGrille() {
		return (GrilleNavaleGraphique) (super.getGrille());
	}

	public void jouerAvec(Joueur j) {
		;
	}

	public void debutAttaque() {
		;
	}

	protected void retourDefense(Coordonnee c, int etat) {
		if (etat==COULE ) {
			JOptionPane.showMessageDialog(grilleTirs, "Dommage, votrenavire a été coulé en "+c);
		}
	}

	protected void perdu() {
		;
	}

	protected void gagne() {
		;
	}

	protected void retourAttaque(Coordonnee c, int etat) {
		;
	}

	public void caseClick(Coordonnee c) {
		if (tirActif) {
			setTirActif(false);
			this.attaque(c);
		}
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
