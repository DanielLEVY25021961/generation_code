package levy.daniel.application.model.services.utilitaires.copieurarboresence.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.services.utilitaires.copieurarborescence.ICopieurArborescence;
import levy.daniel.application.model.services.utilitaires.copieurarborescence.impl.CopieurArborescence;

/**
 * CLASSE CopieurArborescenceTest :<br/>
 * Test JUnit de la classe CopieurArborescence.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Création de répertoire, écriture sur disque, <br/>
 * copie d'arborescence, racine, <br/>
 * copie ARBORESCENCE, recopie fichiers,<br/>
 * détruire arborescence, detruire arborescence,<br/>
 * detruire repertoire, détruire répertoire, <br/>
 * delete directory, <br/>
 * écraser arborescence, ecraser arborescence,<br/>
 * ecraser repertoire et contenu, <br/>
 * supprimer répertoire, supprimer repertoire, <br/>
 * supprimer arborescence et contenu, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 18 nov. 2018
 *
 */
public class CopieurArborescenceTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * "Classe CopieurArborescenceTest".<br/>
	 */
	public static final String CLASSE_COPIEURARBORESCENCE_TEST 
		= "Classe CopieurArborescenceTest";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * SAUT_LIGNE_JAVA : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA = '\n';
	
	/**
	 * METHODE_DETRUIRE_ARBORESCENCE : String :<br/>
	 * "méthode detruireArborescence(String pChemin)".<br/>
	 */
	public static final String METHODE_DETRUIRE_ARBORESCENCE 
		= "méthode detruireArborescence(String pChemin)";
	
	/**
	 * METHODE_VIDER_REPERTOIRE : String :<br/>
	 * "méthode viderRepertoireADetruire(File pFile)".<br/>
	 */
	public static final String METHODE_VIDER_REPERTOIRE 
		= "méthode viderRepertoireADetruire(File pFile)";

	/**
	 * METHODE_ECRASER_REP_ET_SOUS_ARBO : String :<br/>
	 * "méthode ecraserRepertoireEtSousArbo(File pFile)".<br/>
	 */
	public static final String METHODE_ECRASER_REP_ET_SOUS_ARBO 
		= "méthode ecraserRepertoireEtSousArbo(File pFile)";
	
	/**
	 * SEP_MOINS : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEP_MOINS = " - ";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(CopieurArborescenceTest.class);
	
	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public CopieurArborescenceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * teste la méthode copierArborescence(rep, destination)<br/>
	 * .<br/>
	 * <br/>
	 * @throws IOException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCopierArborescence() throws IOException {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE CopieurArborescenceTest - méthode testCopierArborescence() ********** ");
		}
		
		/* répertoire racine contenant l'arboresence à recopier 
		 * (tout sous src/main/java/levy/daniel/application du présent projet). */
		final String cheminRepertoire = "./src/main/java/levy/daniel/application";
		
		final File repertoire = new File(cheminRepertoire);
		
		/* Instanciation d'un CopieurArborescence. */
		final ICopieurArborescence copieur = new CopieurArborescence();
		
		/* chemin de recopie de l'arborescence. */
		final String cheminRootCopieArbo = "./data/copie_arborescence";
		
		/* Copie de l'arborescence sous 'repertoire' sous la nouvelle racine cheminRootCopieArbo. */
		copieur.copierArborescence(repertoire, cheminRootCopieArbo);
		
		final File fileRootCopieArbo = new File(cheminRootCopieArbo);
		
		assertTrue(
				"le répertoire de copie de l'arborescence doit exister : "
				, fileRootCopieArbo.exists());
		
		assertTrue(
				"le répertoire de copie de l'arborescence doit être un répertoire : "
				, fileRootCopieArbo.isDirectory());
		
		final String[] tableauFiles = fileRootCopieArbo.list();
		
		if (tableauFiles != null) {
			assertFalse(
					"la liste des sous-répertoire du répertoire de copie de l'arborescence ne doit pas être vide : "
					, tableauFiles.length == 0);
		}
		
		
		/* détruit l'arborescence copiée. */
		if (fileRootCopieArbo.exists()) {			
			ecraserRepertoireEtSousArbo(fileRootCopieArbo);			
		}
		
		assertFalse(
				"le répertoire de copie de l'arborescence ne doit plus exister : "
				, fileRootCopieArbo.exists());
		
	} // Fin de testCopierArborescence().__________________________________


	
	/**
	 * method detruireArborescence(
	 * String pChemin) :<br/>
	 * <ul>
	 * <li>Détruit le répertoire (et forcément toute l'arborescence sous lui) 
	 * situé au chemin pChemin.</li>
	 * <li>Retourne le boolean true si la destruction du répertoire 
	 * (et forcément de son contenu) s'est bien déroulée.</li>
	 * <li>Vide le contenu du répertoire si nécessaire avant de le supprimer 
	 * (Rappel : il est impossible de supprimer un répertoire 
	 * non vide en Java).</li>
	 * </ul>
	 * - retourne false si pChemin est blank.<br/>
	 * - retourne false si le répertoire à détruire n'existe pas.<br/>
	 * - retourne false si le File à détruire n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pChemin : String : Chemin du répertoire à détruire.<br/>
	 * 
	 * @return : boolean : true si le répertoire a été détruit.<br/>
	 */
	public static boolean detruireArborescence(
			final String pChemin) {
		
		/* bloc static synchronized. */
		synchronized (CopieurArborescenceTest.class) {
			
			/* retourne false si pChemin est blank. */
			if (StringUtils.isBlank(pChemin)) {
				return false;
			}
						
			final File repADetruire = new File(pChemin);
			
			/* retourne false si le répertoire à détruire n'existe pas. */
			if (!repADetruire.exists()) {
				return false;
			}
			
			/* retourne false si le File à détruire n'est pas un répertoire. */
			if (!repADetruire.isDirectory()) {
				return false;
			}
						
			/* Détruit le répertoire et retourne le boolean. */				
			try {
				
				/* Vide d'abord le contenu du répertoire. */
				viderRepertoireADetruire(repADetruire);
				
				/* Détruit le répertoire. */
				return repADetruire.delete();
				
			} catch (Exception e) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasseConcrete()
							, METHODE_DETRUIRE_ARBORESCENCE
								, e.getMessage());
				
				return false;
				
			}
				
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de detruireArborescence(
	 // String pChemin).___________________________________________________
	


	/**
	 * method viderRepertoire(
	 * File pRep) :<br/>
	 * <ul>
	 * <li>Vide tout le contenu du répertoire pRep sans écraser pRep.</li>
	 * <ul>
	 * <li>méthode récursive.</li>
	 * <li>Il est indispensable de vider tout le contenu d'un répertoire 
	 * avant de pouvoir supprimer celui-ci en Java.</li>
	 * </ul>
	 * </ul>
	 * - ne fait rien si pRep == null.<br/>
	 * - ne fait rien si pRep n'existe pas.<br/>
	 * - ne fait rien si pRep n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRep : File : Répertoire dont on veut vider 
	 * tout le contenu le contenu tout en le conservant.<br/>
	 */
	public static void viderRepertoire(
			final File pRep) {

		/* bloc static synchronized. */
		synchronized (CopieurArborescenceTest.class) {
			
			/* ne fait rien si pRep == null. */
			if (pRep == null) {
				return;
			}
						
			/* ne fait rien si pRep n'existe pas. */
			if (!pRep.exists()) {
				return;
			}
			
			/* ne fait rien si pRep n'est pas un répertoire. */
			if (!pRep.isDirectory()) {
				return;
			}
			
			/* Récupération des File dans pRep. */
			final File[] filesContenus = pRep.listFiles();
			
			if (filesContenus == null) {
				return;
			}
			
			/* ForEach (boucle) sur les File de pRep. ******/
			for (final File file : filesContenus) {
				
				/* Sort Si pRep est vide. */
				if (filesContenus.length == 0) {
					return;
				}
				
				/* APPEL RECURSIF si file est un répertoire. */
				if (file.isDirectory()) {
					viderRepertoire(file);
				}
				
				file.delete();
				
			} // Fin de ForEach (boucle) sur les File de pRep. ******__
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de viderRepertoire(
	 // File pRep).________________________________________________________
	
	

	/**
	 * method viderRepertoireADetruire(
	 * File pRep) :<br/>
	 * <ul>
	 * <li>Vide tout le contenu du répertoire pRep sans écraser pRep.</li>
	 * <li>Retourne un boolean à true si le 
	 * contenu du répertoire a bien été effacé.</li>
	 * <br/>
	 * <ul>
	 * <li>méthode récursive.</li>
	 * <li>Il est indispensable de vider tout le contenu d'un répertoire 
	 * avant de pouvoir supprimer celui-ci en Java.</li>
	 * </ul>
	 * </ul>
	 * 
	 * - retourne false si pRep == null.<br/>
	 * - retourne false si pRep n'existe pas.<br/>
	 * - retourne false si pRep n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRep : File : Répertoire dont on veut vider 
	 * tout le contenu le contenu tout en le conservant.<br/>
	 * 
	 * @return : boolean : true si le contenu du répertoire a été vidé.<br/>
	 */
	public static boolean viderRepertoireADetruire(
			final File pRep) {
				
		/* bloc static synchronized. */
		synchronized (CopieurArborescenceTest.class) {
						
			/* retourne false si pRep == null. */
			if (pRep == null) {
				return false;
			}
			
			/* retourne false si pRep n'existe pas. */
			if (!pRep.exists()) {
				return false;
			}
			
			/* retourne false si pRep n'est pas un répertoire. */
			if (!pRep.isDirectory()) {
				return false;
			}
			
			/* Récupération des File dans pRep. */
			final File[] filesContenus = pRep.listFiles();
			
			if (filesContenus == null) {
				return true;
			}
			
			/* Sort Si pRep est vide. */
			if (filesContenus.length == 0) {
				return true;
			}

			boolean resultat = false;
			
			/* Si pRep non vide. */
			/* ForEach (boucle) sur les File de pRep. ******/
			for (final File file : filesContenus) {
				
				/* Appel récursif si file est un répertoire. */
				if (file.isDirectory()) {
					
					/* APPEL RECURSIF. */
					viderRepertoireADetruire(file);
					
					
				} // Fin de if (!file.isDirectory()).___________
				
				/* Destruction du file dans tous les cas. */					
				try {
					
					resultat = Files.deleteIfExists(file.toPath());
					
				} catch (Exception e) {
					
					/* LOG de niveau INFO. */
					final String message 
					= "Impossible de détruire le fichier '" 
					+ file.getName() 
					+ "' : ";
					
					loggerInfo(
							fournirNomClasseConcrete()
								, METHODE_VIDER_REPERTOIRE
									, message + e.getMessage());
					
					return false;
					
				}
									
			} // Fin du ForEach (boucle) sur les File de pRep.___
			
			return resultat;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de viderRepertoireADetruire(
	 // File pRep).________________________________________________________
	

	
	/**
	 * method ecraserRepertoireEtSousArbo(
	 * File pRep) :<br/>
	 * <ul>
	 * <li>Vide tout le contenu du répertoire pRep et ECRASE pRep.</li>
	 * <br/>
	 * <ul>
	 * <li>méthode récursive.</li>
	 * <li>Il est indispensable de vider tout le contenu d'un répertoire 
	 * avant de pouvoir supprimer celui-ci en Java.</li>
	 * </ul>
	 * </ul>
	 * <br/>
	 * - retourne false si pRep est null.<br/>
	 * - retourne false si pRep n'existe pas sur le disque.<br/>
	 * - retourne false si pRep n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRep : File : répertoire à écraser avec tout son contenu.<br/>
	 * 
	 * @return : boolean : true si pRep a bien été écrasé 
	 * ainsi que tout son contenu.<br/>
	 */
	public static boolean ecraserRepertoireEtSousArbo(
			final File pRep) {
		
		/* bloc static synchronized. */
		synchronized (CopieurArborescenceTest.class) {
			
			/* retourne false si pRep est null. */
			if (pRep == null) {
				return false;
			}
			
			/* retourne false si pRep n'existe pas sur le disque. */
			if (!pRep.exists()) {
				return false;
			}
			
			/* retourne false si pRep n'est pas un répertoire. */
			if (!pRep.isDirectory()) {
				return false;
			}
			
			boolean resultat = false;
			
			/* Vidage de tout le contenu du répertoire. */
			final boolean vidageRepertoire 
				= viderRepertoireADetruire(pRep);
			
			if (!vidageRepertoire) {
				return false;
			}
			
			final Path pathRep = pRep.toPath();
			
			try {
				
				Files.deleteIfExists(pathRep);
				resultat = true;
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, METHODE_ECRASER_REP_ET_SOUS_ARBO
								, ioe);
				
				return false;
			}
			
			return resultat;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de ecraserRepertoireEtSousArbo(
	 // File pRep).________________________________________________________
	

	
	/**
	 * Retourne le nom de la présente classe.<br/>
	 * "Classe CopieurArborescenceTest".<br/>
	 * <br/>
	 *
	 * @return : String : "Classe CopieurArborescenceTest".<br/>
	 */
	private static String fournirNomClasseConcrete() {
		return CLASSE_COPIEURARBORESCENCE_TEST;
	} // Fin de fournirNomClasseConcrete().________________________________
	

	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage) :<br/>
	 * <ul>
	 * <li>Créée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pMessage == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS
			+ pMessage;
			
			LOG.info(message);
		}
		
	} // Fin de la classe loggerInfo(
	 // String pClasse
	 // , String pMethode
	 // , String pMessage).________________________________________________
	

	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage
	 * , String pComplement) :<br/>
	 * <ul>
	 * <li>Créée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 * @param pComplement : String : Complément au message de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage
						, final String pComplement) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null 
				|| pMessage == null || pComplement == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS
			+ pMessage
			+ pComplement;
			
			LOG.info(message);
		}
		
	} // Fin de loggerInfo(
	 // String pClasse
	 // , String pMethode
	 // , String pMessage
	 // , String pComplement)._____________________________________________
	
	
	
	/**
	 * method loggerError(
	 * String pClasse
	 * , String pMethode
	 * , Exception pException) :<br/>
	 * <ul>
	 * <li>Créée un message de niveau ERROR dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pException : Exception : Exception transmise 
	 * par la méthode appelante.<br/>
	 */
	private static void loggerError(
			final String pClasse
				, final String pMethode
					, final Exception pException) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pException == null) {
			return;
		}
		
		/* LOG de niveau ERROR. */			
		if (LOG.isErrorEnabled()) {
			
			final String message 
			= pClasse 
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS 
			+ pException.getMessage();
			
			LOG.error(message, pException);
			
		}
		
	} // Fin de loggerError(
	 // String pClasse
	 // , String pMethode
	 // , Exception pException).___________________________________________
	
	
	
} // FIN DE LA CLASSE CopieurArborescenceTest.-------------------------------
