package levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire.ICopieurContenuRepertoireService;

/**
 * CLASSE CopieurContenuRepertoireService :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * afficherListPaths, afficher liste de Paths, afficher list<Path>, <br/>
 * afficher list paths, afficher liste paths, <br/>
 * retourner contenu sous répertoire, <br/> 
 * retourner contenu sous repertoire,<br/>
 * path relatif, <br/>
 * path relatif de path enfant par rapport à path parent,<br/>
 * fournir Path RELATIF, <br/>
 * Path absolu du projet Eclipse courant, path courant, <br/>
 * PATH COURANT, Path Courant, <br/>
 * resolve, pathProjetCourant.resolve(pathRelatif), <br/>
 * ajouter un path, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 1 déc. 2018
 *
 */
public class CopieurContenuRepertoireService 
				implements ICopieurContenuRepertoireService {
	

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "line.separator".
	 */
	public static final String LINE_SEPARATOR 
		= "line.separator";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(CopieurContenuRepertoireService.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public CopieurContenuRepertoireService() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copierContenu(
			final File pRacineOrigine
				, final String pCheminDestination) {
		
		/* ne fait rien si pRacineOrigine == null. */
		if (pRacineOrigine == null) {
			return;
		}
				
		/* ne fait rien si pRacineOrigine n'existe pas. */
		if (!pRacineOrigine.exists()) {
			return;
		}
		
		/* ne fait rien si pRacineOrigine n'est pas un répertoire. */
		if (!pRacineOrigine.isDirectory()) {
			return;
		}
		
		/* récupère le contenu sous le répertoire pRacineOrigine. */
		final List<Path> contenuList 
			= this.recupererContenuSous(pRacineOrigine);

		System.out.println(this.afficherListPaths(contenuList));
		
		
		
		
	} // Fin de copierContenu(...).________________________________________
	
	
	
	/**
	 * <b>retourne la liste des Path de toute l'arborescence 
	 * <i>(répertoires et fichiers simples)</i> 
	 * sous pRacineOrigine</b>.<br/>
	 * <ul>
	 * <li><b>ne RETOURNE PAS pRacineOrigine</b>.</li>
	 * <li>retourne null si une IOException se produit 
	 * lors de la lecture de l'arborescence.</li>
	 * </ul>
	 * - retourne null si pRacineOrigine == null.<br/>
	 * - retourne null si pRacineOrigine n'existe pas.<br/>
	 * - retourne null si pRacineOrigine n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRacineOrigine : File : 
	 * répertoire dont on récupère le contenu.<br/> 
	 * 
	 * @return : List&lt;Path&gt; : 
	 * liste des Path de toute l'arborescence 
	 * <i>(répertoires et fichiers simples)</i> 
	 * sous pRacineOrigine.<br/>
	 */
	private List<Path> recupererContenuSous(
			final File pRacineOrigine) {
		
		/* retourne null si pRacineOrigine == null. */
		if (pRacineOrigine == null) {
			return null;
		}
				
		/* retourne null si pRacineOrigine n'existe pas. */
		if (!pRacineOrigine.exists()) {
			return null;
		}
		
		/* retourne null si pRacineOrigine n'est pas un répertoire. */
		if (!pRacineOrigine.isDirectory()) {
			return null;
		}

		final Path originePath = pRacineOrigine.toPath();
		
		/* retourne la liste des Path des Files sous originePath. */
		/* utilise un try-with-resources. */
		try (Stream<Path> stream 
				= Files.walk(originePath)) {

			return stream
					.filter(path -> !path.equals(originePath))
					.map(path -> path)
					.collect(Collectors.toList());
			
		} catch (IOException e) {
			return null;
		}
		
	} // Fin de recupererContenuSous(...)._________________________________
	

	
	/**
	 * <b>fournit le PATH RELATIF de pPathEnfant 
	 * par rapport à pPathParent</b>.<br/>
	 * pPathEnfant est donc "plus long" que pPathParent 
	 * et descend de lui.<br/>
	 * <ul>
	 * par exemple :<br/>
	 * <li>si pPathParent = "D:/eclipse/toto"</li>
	 * <li>si pPathEnfant = "D:/eclipse/toto/<b>tata/titi</b>"</li>
	 * <li><code>fournirPathRelatif(pPathParent, pPathEnfant)</code> 
	 * retourne <b>"tata/titi"</b>.</li>
	 * <li>utilise 
	 * <code>pPathParent.relativize(pPathEnfant);</code></li>
	 * </ul>
	 * - retourne null si pPathParent == null.<br/>
	 * - retourne null si pPathEnfant == null.<br/>
	 * <br/>
	 *
	 * @param pPathParent : Path.<br/>
	 * @param pPathEnfant : Path.<br/>
	 * 
	 * @return : Path : Path relatif.<br/>
	 */
	private Path fournirPathRelatif(
			final Path pPathParent
				, final Path pPathEnfant) {
		
		/* retourne null si pPathParent == null. */
		if (pPathParent == null) {
			return null;
		}
		
		/* retourne null si pPathEnfant == null. */
		if (pPathEnfant == null) {
			return null;
		}
		
		return pPathParent.relativize(pPathEnfant);
		
	} // Fin de fournirPathRelatif(...).___________________________________
	
	
	
	/**
	 * <b>retourne le Path absolu du projet Eclipse courant</b>.<br/>
	 * <ul>
	 * <li>utilise <code>Paths.get(".").toAbsolutePath();</code>.</li>
	 * </ul>
	 *
	 * @return : Path : 
	 * Path absolu du projet Eclipse courant.<br/>
	 */
	private Path fournirPathAbsoluPresentProjet() {
		
		final Path pathAbsoluPresentProjet 
			= Paths.get(".").toAbsolutePath();
		
		return pathAbsoluPresentProjet;
		
	} // Fin de fournirPathAbsoluPresentProjet().__________________________
	
	
	
	/**
	 * <b>fournit le PATH ABSOLU 
	 * pathPresentProjet/ + pPathRelatif</b>.<br/>
	 * <ul>
	 * <li>ajoute le path relatif au path du présent projet.</li>
	 * <li>utilise 
	 * <code>pathProjetCourant.resolve(pPathRelatif);</code>.</li>
	 * </ul>
	 * - retourne null si pPathRelatif == null.<br/>
	 * <br/>
	 *
	 * @param pPathRelatif : Path.<br/>
	 * 
	 * @return : Path : 
	 * Path absolu dans le présent projet Eclipse.<br/>
	 */
	private Path fournirPathAbsoluDansPresentProjet(
							final Path pPathRelatif) {
		
		/* retourne null si pPathRelatif == null. */
		if (pPathRelatif == null) {
			return null;
		}
		
		/* ajoute le path relatif au path du présent projet. */
		final Path pathAbsoluDansPresentProjet 
			= this.fournirPathAbsoluPresentProjet()
				.resolve(pPathRelatif);
		
		return pathAbsoluDansPresentProjet;
		
	} // Fin de fournirPathAbsoluDansPresentProjet(...).___________________
	
	
	
	/**
	 * <b>fournit une String pour l'affichage 
	 * d'une List&lt;Path&gt;</b>.<br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;Path&gt;.<br/>
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public String afficherListPaths(
			final List<Path> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final Path path : pList) {
			
			if (path != null) {
				stb.append(path.toString());
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
			
		}
		
		return stb.toString();
		
	} // Fin de afficherListPaths(...).____________________________________
	
	
	
} // FIN DE LA CLASSE CopieurContenuRepertoireService.-----------------------
