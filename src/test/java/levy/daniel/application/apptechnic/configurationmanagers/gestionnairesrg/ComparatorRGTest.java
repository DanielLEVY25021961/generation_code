package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * class ComparatorRGTest :<br/>
 * Test JUnit de la classe ComparatorRG.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 12 oct. 2017
 *
 */
public class ComparatorRGTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * TIRET : String :<br/>
	 * " - ".<br/>
	 */
	public static final String TIRET = " - ";
	
	
	/**
	 * SAUT_LIGNE : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE = '\n';
	
	/**
	 * AFFICHAGE_GENERAL : Boolean :<br/>
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ComparatorRGTest.class);


	// *************************METHODES************************************/
	

	
	 /**
	 * method CONSTRUCTEUR ComparatorRGTest() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public ComparatorRGTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method testExtraireNUmeroFin() :<br/>
	 * <ul>
	 * Teste le ComparatorRG.
	 * <li>garantit que le comparator trie en utilisant 
	 * le numéro de RG.</li>
	 * <li>test visuel.</li>
	 * </ul>
	 */
	@Test
	public void testCompare() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************

		/* Instanciation d'un comparateur de RG qui trie sur les numéros des RG. */
		final ComparatorRG comp = new ComparatorRG();
		
		final Map<String, String> map 
			= new ConcurrentHashMap<String, String>();
		
		map.put("RG_INTERRO_VILLE_03", "la ville doit être renseignée");
		map.put("RG_INTERRO_VILLE_04", "la ville ne doit contenir que des lettres");
		map.put("RG_INTERRO_NBREJOURS_06", "le nombre de jours < 10");
		map.put("RG_INTERRO_NBREJOURS_05", "le nombre de jours entier");
		map.put("RG_INTERRO_PAYS_01", "le pays doit être renseigné");
		map.put("RG_INTERRO_PAYS_02", "le pays ne doit contenir que des lettres");

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("AFFICHAGE DE LA MAP NON TRIEE : ");
			System.out.println(this.afficherMapStringString(map));
		}
		
		
		final SortedMap<String, String> mapTriee = new TreeMap<>(comp);
		
		mapTriee.putAll(map);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("AFFICHAGE DE LA MAP TRIEE : ");
			System.out.println(this.afficherMapStringString(mapTriee));
		}
		
		assertNotNull("La map triée ne doit pas être null : "
					, mapTriee);
		
	} // Fin de testCompare()._____________________________________________


	
	/**
	 * method afficherMapStringString(
	 * Map&lt;String, String&gt; pMap) :<br/>
	 * <ul>
	 * <li>Affiche à la console une Map&lt;String, String&gt;.</li>
	 * </ul>
	 * retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;String, String&gt;
	 * 
	 * @return : String.<br/>
	 */
	public String afficherMapStringString(
			final Map<String, String> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		final Set<Entry<String, String>> entrySet = pMap.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String key = entry.getKey();
			final String value = entry.getValue();
			
			stb.append(key);
			stb.append(TIRET);
			stb.append(value);
			stb.append(SAUT_LIGNE);
		}
		
		return stb.toString();
		
	} // Fin de afficherMapStringString(...).______________________________
	
	
	
} // FIN DE LA CLASSE ComparatorRGTest.--------------------------------------
