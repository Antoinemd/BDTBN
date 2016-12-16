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
                this.ligne = ligne;
                this.colonne = colonne;
        }

        public Coordonnee(String s) {
        	s = s.toUpperCase();
        	char colonne = s.charAt(0);	// on récupère le premier caractère de la string...
			this.colonne = (int)(colonne -'A');    // pour le convertir en int (cl)
			try {
				this.ligne = Integer.parseInt(s.substring(1)) - 1;
			} catch(StringIndexOutOfBoundsException | NumberFormatException e) {System.out.println("Expression incorrecte !");}
        }

        public String toString() {
                // Retourne une String exprimant this dans le systeme de coordonnee de
                // la bataille navale (exemple : "C6")
        		char c = (char) (this.colonne +'A');    
        		String s = "" + c + (this.ligne + 1);
        		return(s);
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
        		if(!(obj instanceof Coordonnee))
        			return false;
        		else {
        			Coordonnee c = (Coordonnee) obj;
        			if(this.ligne != c.ligne || this.colonne != c.colonne)
        				return false;
        		}
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
//                Coordonnee c = new Coordonnee(4, 4);
//                Coordonnee d = new Coordonnee(4, 4);
//                Coordonnee e = new Coordonnee(4, 5);
//                Coordonnee f = new Coordonnee(4, 6);
//                Coordonnee fbis = new Coordonnee(4, 6);
//                Coordonnee g = new Coordonnee(4, 7);
////                System.out.println("equals = " + f.equals(g));
////                System.out.println(c.voisine(d));
//                
//                Navire Kris = new Navire(c, 3, false);
////                Navire Mik = new Navire(d, 3, false);
//                
////                System.out.println("chevauche = " + Kris.chevauche(Mik));
////                System.out.println("contient = " + Kris.contient(d));
////                System.out.println("Kris : " + Kris.toString());
//                
////                System.out.println("Longueur PartiesTouchees Kris : " + Kris.getPartiesToucheesLength());
////                System.out.println("Longueur PartiesTouchees Mik : " + Mik.getPartiesToucheesLength());
////                System.out.println("Touche = " + Kris.touche(Mik));
////                System.out.println("Touche = " + Mik.touche(Kris));
//                
//                System.out.println("estTouché d ? " + Kris.estTouche(d) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("RecoitTir d = " + Kris.recoitTir(d));
//                System.out.println("estTouché d ? " + Kris.estTouche(d) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("estCoulé ? " + Kris.estCoule());
//                System.out.println(Kris.PTContent() + "\n");
//                
//                System.out.println("estTouché e ? " + Kris.estTouche(e) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("RecoitTir e = " + Kris.recoitTir(e));
//                System.out.println("estTouché e ? " + Kris.estTouche(e) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("estCoulé ? " + Kris.estCoule());
//                System.out.println(Kris.PTContent() + "\n");
//                
//                System.out.println("estTouché e ? " + Kris.estTouche(e) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("RecoitTir e = " + Kris.recoitTir(e));
//                System.out.println("estTouché e ? " + Kris.estTouche(e) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("estCoulé ? " + Kris.estCoule());
//                System.out.println(Kris.PTContent() + "\n");
//                
//                System.out.println("estTouché f ? " + Kris.estTouche(f) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("RecoitTir f = " + Kris.recoitTir(f));
//                System.out.println("estTouché f ? " + Kris.estTouche(f) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("estCoulé ? " + Kris.estCoule());
//                System.out.println(Kris.PTContent() + "\n");
//                
//                System.out.println("estTouché fbis ? " + Kris.estTouche(fbis) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("RecoitTir fbis = " + Kris.recoitTir(fbis));
//                System.out.println("estTouché fbis ? " + Kris.estTouche(fbis) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("estCoulé ? " + Kris.estCoule());
//                System.out.println(Kris.PTContent() + "\n");
//                
//                System.out.println("estTouché g ? " + Kris.estTouche(g) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("RecoitTir g = " + Kris.recoitTir(g));
//                System.out.println("estTouché g ? " + Kris.estTouche(g) + "/" + "nbTouches = " + Kris.getNbTouchees());
//                System.out.println("estCoulé ? " + Kris.estCoule());
//                System.out.println(Kris.PTContent() + "\n");
//                
//                
                Coordonnee a = new Coordonnee(0,0);
                Coordonnee b = new Coordonnee(0,1);
                Coordonnee c = new Coordonnee(1,1);
                Coordonnee d = new Coordonnee(1,2);
                Coordonnee e = new Coordonnee(2,2);
                Coordonnee f = new Coordonnee(2,3);
                Coordonnee g = new Coordonnee(3,3);
                Coordonnee h = new Coordonnee("D8");
                Coordonnee i = new Coordonnee("D9");
                Coordonnee j = new Coordonnee("D10");
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
                System.out.println(d);
                System.out.println(e);
                System.out.println(f);
                System.out.println(g);
                System.out.println(h);
                System.out.println(i);
                System.out.println(j);
                

        }

}