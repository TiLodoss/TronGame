package other;

/**
 * Classe Const
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Classe regroupant des constantes generales
 *
 */
public class Const {
	public final static int DIR_LEFT=0, DIR_RIGHT=1, DIR_TOP=2, DIR_BOTTOM=3; //directions de mouvement
	public final static int C_NONE=-1, C_PLAYER=0, C_IA1=1, C_IA2=2, C_IA3 = 3; //couleurs des tuiles
	public final static int IA_LVL0 = 0, IA_LVL1 = 1, IA_LVL2 = 2; //niveaux de difficulte IA 
	public final static int NB_IA = 3; //nombre d'IA
	public final static int ENT_ALIVE =0, ENT_DEAD = 1; //etat d'un joueur/IA (vivant, mort)
	public final static int NB_MAXROUNDS = 3; //nombre de rounds (au meilleur de 3)
	

}
