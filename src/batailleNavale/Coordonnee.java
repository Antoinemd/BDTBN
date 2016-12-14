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
        		if (ligne < 1 || colonne < 1 || ligne > 26 || colonne > 26)
        			throw new IllegalArgumentException("Valeur incorrecte");
        			// exception si les coordonnees sont incorrectes (incompletes, etc.)
                this.ligne = ligne;
                this.colonne = colonne;
        }

        public Coordonnee(String s) {

                // Permet d'obtenir une coordonnee d'apres son expression donnee par s
                // dans le systeme de coordonnees de la bataille navale
            	if (ligne < 1 || colonne < 1 || ligne > 26 || colonne > 26)
            		throw new IllegalArgumentException("Valeur incorrecte");
            		// exception si les coordonnees sont incorrectes (incompletes, etc.)
                this.colonne = Integer.parseInt(s.substring(0, 1));
                this.ligne = Integer.parseInt(s.substring(1));
        }

        public String toString() {

                // Retourne une String exprimant this dans le systeme de coordonnee de
                // la bataille navale (exemple : "C6")

                String s = "";
                String col = "";
                switch (this.colonne) {
                case 1 : col = "A"; break;
                case 2 : col = "B"; break;
                case 3 : col = "C"; break;
                case 4 : col = "D"; break;
                case 5 : col = "E"; break;
                case 6 : col = "F"; break;
                case 7 : col = "G"; break;
                case 8 : col = "H"; break;
                case 9 : col = "I"; break;
                case 10 : col = "J"; break;
                case 11 : col = "K"; break;
                case 12 : col = "L"; break;
                case 13 : col = "M"; break;
                case 14 : col = "N"; break;
                case 15 : col = "O"; break;
                case 16 : col = "P"; break;
                case 17 : col = "Q"; break;
                case 18 : col = "R"; break;
                case 19 : col = "S"; break;
                case 20 : col = "T"; break;
                case 21 : col = "U"; break;
                case 22 : col = "V"; break;
                case 23 : col = "W"; break;
                case 24 : col = "X"; break;
                case 25 : col = "Y"; break;
                case 26 : col = "Z"; break;
                }
                s += col + this.ligne;
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

        public boolean equals(Object obj) {	// À vérifier !!

                // Retourne true si et seulement si this est equivalent a obj
        		Coordonnee c = (Coordonnee) obj;
        		if(!(obj instanceof Coordonnee) || (this.ligne != c.ligne || this.colonne != c.colonne))
            	  	return false;
                return true;
        }

        public boolean voisine(Coordonnee c) {

            // Retourne true si et seulement si this est une coordonnee voisine
            // (verticalement ou horizontalement) de c

            if ((this.ligne - c.ligne == 1 || this.ligne - c.ligne == -1)
                            && (this.colonne - c.colonne == 1 || this.colonne - c.colonne == -1))
                    return false;
            else if (this.ligne - c.ligne == 1 || this.ligne - c.ligne == -1 || this.colonne - c.colonne == 1
                            || this.colonne - c.colonne == -1)
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
        		Coordonnee g = new Coordonnee(4, 7);
                Coordonnee c = new Coordonnee(4, 4);
                Coordonnee d = new Coordonnee(4, 4);
                Coordonnee e = new Coordonnee(4, 5);
                Coordonnee f = new Coordonnee(4, 6);
                System.out.println(c.voisine(d));
                
                Navire Kris = new Navire(c, 3, false);
                Navire Mik = new Navire(d, 3, false);
                System.out.println("chevauche = " + Kris.chevauche(Mik));
                System.out.println("contient = " + Kris.contient(d));
                System.out.println("Kris : " + Kris.toString());
                
                System.out.println("Longueur PartiesTouchees Kris : " + Kris.getPartiesToucheesLength());
                System.out.println("Longueur PartiesTouchees Mik : " + Mik.getPartiesToucheesLength());
                System.out.println("Touche = " + Kris.touche(Mik));
                System.out.println("Touche = " + Mik.touche(Kris));
                
                System.out.println("RecoitTir = " + Kris.recoitTir(d));
                System.out.println("estTouché ? " + Kris.estTouche() + "/" + "nbTouches = " + Kris.getNbTouchees());
                System.out.println("estCoulé ? " + Kris.estCoule());
                System.out.println(Kris.PTContent() + "\n");
                
                System.out.println("RecoitTir = " + Kris.recoitTir(e));
                System.out.println("estTouché ? " + Kris.estTouche() + "/" + "nbTouches = " + Kris.getNbTouchees());
                System.out.println("estCoulé ? " + Kris.estCoule());
                System.out.println(Kris.PTContent() + "\n");
                
                System.out.println("RecoitTir = " + Kris.recoitTir(f));
                System.out.println("estTouché ? " + Kris.estTouche() + "/" + "nbTouches = " + Kris.getNbTouchees());
                System.out.println("estCoulé ? " + Kris.estCoule());
                System.out.println(Kris.PTContent() + "\n");
                
                System.out.println("RecoitTir = " + Kris.recoitTir(f));
                System.out.println("estTouché ? " + Kris.estTouche() + "/" + "nbTouches = " + Kris.getNbTouchees());
                System.out.println("estCoulé ? " + Kris.estCoule());
                System.out.println(Kris.PTContent() + "\n");
                
                System.out.println("RecoitTir = " + Kris.recoitTir(g));
                System.out.println("estTouché ? " + Kris.estTouche() + "/" + "nbTouches = " + Kris.getNbTouchees());
                System.out.println("estCoulé ? " + Kris.estCoule());
                System.out.println(Kris.PTContent() + "\n");
                
                
                
                

        }

}