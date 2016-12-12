/**
 * Titre : Bataille Navale Description : TP stage de programmation Java
 * Copyright : Copyright (c) 2016 Christophe Brioua, Andy Dowlut, Michael Toth
 * Realise dans le cadre du Master DCISS de l'Universite Grenoble-Alpes
 *
 * @author Christophe Brioua, Andy Dowlut, Michael Toth
 * @version 1.0
 */
package batailleNavale;

public class Coordonnee {
        private int ligne;
        private int colonne;

        /**
         * Une instance de la classe Coordonnee represente une coordonnee dans une
         * grille de bataille navale. Cette classe doit en particulier permettre de
         * realiser facilement des conversions entre le systeme de coordonnees
         * utilise dans la bataille navale, ou les colonnes sont notees par des
         * lettres majuscules (de 'A' a 'Z') et les lignes par des nombres (de 1 a
         * 26), et les indices utilises en Java (de 0 a 25).
         */

        public Coordonnee(int ligne, int colonne) {

                // Permet d'obtenir une coordonnee de ligne i et de colonne j (indices
                // Java)

                ligne = this.ligne;
                colonne = this.colonne;
        }

        public Coordonnee(String s) {

                // Permet d'obtenir une coordonnee d'apres son expression donnee par s
                // dans le systeme de coordonnees de la bataille navale

                this.colonne = Integer.parseInt(s.substring(0, 1));
                this.ligne = Integer.parseInt(s.substring(1));
                if (ligne < 0 || colonne < 0 || ligne > 25 || colonne > 25)
                        throw new IllegalArgumentException("Valeur incorrecte");
                // exception si les coordonnees sont incorrectes (incompletes, etc.)
        }

        public String toString() {

                // Retourne une String exprimant this dans le systeme de coordonnee de
                // la bataille navale (exemple : "C6")

                String s = "";
                for (int i = 0; i < this.colonne; i++) {
                        for (int j = 0; j < this.ligne; j++) {
                                s += this.colonne + ", " + this.ligne + " ";
                        }
                        s += " \n";
                }
                return s;
        }

        public int getLigne() {

                // Accesseur en lecture pour l'indice de ligne

                return this.ligne;
        }

        public int getColonne() {

                // Accesseur en lecture pour l'indice de colonne

                return this.colonne;
        }

        public boolean equals(Object obj) {

                // Retourne true si et seulement si this est equivalent a obj
        		Coordonnee c = (Coordonnee) obj;
        		if(!(obj instanceof Coordonnee) || (this.ligne != c.ligne || this.colonne != c.colonne))
            	  	return false;
                return true;
        }

        public boolean voisine(Coordonnee c) {

                // Retourne true si et seulement si this est une coordonnee voisine
                // (verticalement ou horizontalement) de c

                if (this.ligne - c.ligne == 1 || this.ligne - c.ligne == -1 || this.colonne - c.colonne == 1 || this.colonne - c.colonne == -1)
                        return true;
                return false;
        }

        public int compareTo(Coordonnee c) {

                // Retourne le resultat de la comparaison de this et de c. Une
                // coordonnee est consideree inferieure a une autre, si elle se trouve
                // sur une ligne inferieure ou si elle se trouve sur la meme ligne mais
                // sur une colonne inferieure
        		
                if((this.ligne < c.ligne) || ((this.ligne == c.ligne) & (this.colonne < c.colonne)))
                	return -1;
                else if((this.ligne > c.ligne) || ((this.ligne == c.ligne) &(this.colonne < c.colonne)))
                	return 1;
                else
                	return 0;                
        }

        public static void main(String[] args) {
                // TODO Auto-generated method stub

        }

}