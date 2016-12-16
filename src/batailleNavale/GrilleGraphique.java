package batailleNavale;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Classe représentant un composant graphique "Grille". Une grille est composée
 * de JButton
 * 
 * @author jerome.david@upmf-grenoble.fr
 * 
 */
public class GrilleGraphique extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8857166149660579225L;

	/**
	 * La matrice des boutons (cases de la grille)
	 */
	private JButton[][] cases;

	private CaseClickListener listener;
	
	public GrilleGraphique() {
		this(10);
	}

	/**
	 * Initialise une grille carrée de taille donnée
	 * 
	 * @param taille
	 *            la taille de la grille
	 */
	public GrilleGraphique(int taille) {
		try {
			// Certains LookAndFeels ne colorient pas les boutons.
			// on choisi celui le plus simple (et le moins joli)
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		this.setLayout(new GridLayout(taille + 1, taille + 1));

		this.add(new JLabel());
		for (int i = 0; i < taille; i++) {
			JLabel lbl = new JLabel(String.valueOf((char) ('A' + i)));
			lbl.setHorizontalAlignment(JLabel.CENTER);
			this.add(lbl);
		}
		cases = new JButton[taille][taille];
		for (int i = 0; i < taille; i++) {
			JLabel lbl = new JLabel(String.valueOf(i + 1));
			lbl.setHorizontalAlignment(JLabel.CENTER);
			this.add(lbl);
			for (int j = 0; j < taille; j++) {
				cases[i][j] = new JButton();
				this.add(cases[i][j]);
				cases[i][j].addActionListener(this);
			}
		}
	}

	/**
	 * Colorie la case indiquée par la coordonnée
	 * 
	 * @param coord
	 *            la coordonnée de la case à colorier
	 * @param color
	 *            la couleur de la case
	 */
	public void colorie(Coordonnee cord, Color color) {
		cases[cord.getLigne()][cord.getColonne()].setBackground(color);
	}

	/**
	 * Colorie le rectangle compris entre les deux coordonnees
	 * 
	 * @param debut
	 *            Coordonnée du début de la zone à colorier (haut gauche)
	 * @param fin
	 *            Coordonnée de la fin de la zone à colorier (bas droit)
	 * @param color
	 *            la couleur de la case
	 */
	public void colorie(Coordonnee debut, Coordonnee fin, Color color) {
		for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
			for (int j = debut.getColonne(); j <= fin.getColonne(); j++) {
				cases[i][j].setBackground(color);
			}
		}

	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		d.setSize(d.width, d.width);
		return d;
	}

	public void setClicActive(boolean active) {
		this.setEnabled(false);
		for (JButton[] ligne : cases) {
			for (JButton bt : ligne) {
				bt.setEnabled(active);
			}
		}
		this.setEnabled(true);
	}

	/**
	 * Enregistre un écouteur de "clics" sur les boutons de la grille
	 * 
	 * @param l
	 */
	public void setCaseClickListener(CaseClickListener l) {
		listener = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (listener != null) {
			for (int i = 0; i < cases.length; i++) {
				for (int j = 0; j < cases[i].length; j++) {
					if (cases[i][j] == e.getSource()) {
						listener.caseClick(new Coordonnee(i, j));
					}
				}
			}
		}
	}
}