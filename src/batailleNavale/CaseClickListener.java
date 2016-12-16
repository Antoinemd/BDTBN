package batailleNavale;

/**
 * Interface qu'un écouteur de clic sur une grille doit implémenter
 * @author jerome.david@upmf-grenoble.fr
 *
 */
public interface CaseClickListener {

	/**
	 * Méthode appelée lorsqu'un clic sur une grille a été fait
	 * @param c la coordonnée de la case cliquée
	 */
	public void caseClick(Coordonnee c);
}