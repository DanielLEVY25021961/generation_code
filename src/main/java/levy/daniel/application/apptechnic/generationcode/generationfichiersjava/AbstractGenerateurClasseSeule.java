package levy.daniel.application.apptechnic.generationcode.generationfichiersjava;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.ecriveurs.IEcriveur;

/**
 * CLASSE ABSTRAITE <b>AbstractGenerateurClasseSeule</b> :<br/>
 * .<br/>
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
 * @since 4 févr. 2018
 *
 */
public abstract class AbstractGenerateurClasseSeule 
						extends AbstractGenerateurFichiersJava {

	// ************************ATTRIBUTS************************************/
	/**
	 * CLASSE_ABSTRACT_GENERATEUR_CLASSE_SEULE : String :<br/>
	 * "Classe AbstractGenerateurClasseSeule".<br/>
	 */
	public static final String CLASSE_ABSTRACT_GENERATEUR_CLASSE_SEULE 
		= "Classe AbstractGenerateurClasseSeule";


	/**
	 * ecriveurConcreteClass : IEcriveur :<br/>
	 * EcriveurMetierConcreteClass.<br/>
	 */
	protected transient IEcriveur ecriveurConcreteClass;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractGenerateurClasseSeule.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractGenerateurClasseSeule() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractGenerateurClasseSeule() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	/**
	 * {@inheritDoc}
	 * <ul>
	 * <li>alimente this.nomSimpleConcreteClass.</li>
	 * </ul>
	 */
	@Override
	protected void alimenterAttributs() {
		
		/* alimente this.nomSimpleConcreteClass. */
		this.alimenterNomSimpleConcreteClass(nomClassMetier);

	} // Fin de alimenterAttributs().______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * <ul>
	 * <li>génère et alimente this.concreteClass.</li>
	 * </ul>
	 */
	@Override
	protected final void genererFichiersJava() throws IOException {
		
		/* Génère la classe vide de l'objet métier. */
		this.genererConcreteClass(this.nomSimpleConcreteClass);
		
	} // Fin de genererFichiersJava()._____________________________________
	


	
	
	/**
	 * method alimenterNomSimpleConcreteClass(
	 * String pNomObjetMetier) :<br/>
	 * <ul>
	 * <li>Fournit le Nom simple de la classe concrète à générer.</li>
	 * <li><b>alimente this.nomSimpleConcreteClass</b>.</li>
	 * <li>Par exemple "ProfilSimple" pour une classe métier.</li>
	 * <li>Par exemple "DaoProfilSimple" pour une classe Dao.</li>
	 * </ul>
	 *
	 * @param pNomObjetMetier : String.<br/>
	 */
	protected abstract void alimenterNomSimpleConcreteClass(
			String pNomObjetMetier);
	

	
	/**
	 * method genererConcreteClass(
	 * String pNomConcreteClass) :<br/>
	 * <ul>
	 * <li>Génère le fichier vide pNomConcreteClass.java 
	 * sous packageSousCouche.impl.</li>
	 * <li>alimente this.concreteClass.</li>
	 * <li>Ne génère le fichier vide que si il n'existe pas déjà.</li>
	 * <li>Par exemple : genererConcreteClass("ProfilSimple") 
	 * génère model/metier/profil/impl/ProfilSimple.java</li>
	 * </ul>
	 * ne fait rien si pNomConcreteClass est blank.<br/>
	 * <br/>
	 *
	 * @param pNomConcreteClass : String : 
	 * Nom de l'objet métier comme "ProfilSimple" 
	 * pour ProfilSimple.java.<br/>
	 * 
	 * @throws IOException
	 */
	private void genererConcreteClass(
			final String pNomConcreteClass) 
					throws IOException {
		
		/* ne fait rien si pNomConcreteClass est blank. */
		if (!StringUtils.isBlank(pNomConcreteClass)) {
			
			final String nomFichier = pNomConcreteClass + ".java";
			
			this.concreteClass 
				= this.gestionnaireFiles
				.creerFichierDansPackage(
						nomFichier, this.sousPackageImpl);
		}
				
	} // Fin de genererConcreteClass(...).___________________________________
	

	
} // FIN DE LA CLASSE AbstractGenerateurClasseSeule.-------------------------
