package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.dao.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;


/**
 * classe <b>EcriveurDaoAbstract</b> :<br/>
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
 * @since 24 févr. 2018
 *
 */
public class EcriveurDaoAbstract 
	extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_DAO_ABSTRACT : String :<br/>
	 * "Classe EcriveurDaoAbstract".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_DAO_ABSTRACT 
		= "Classe EcriveurDaoAbstract";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(EcriveurDaoAbstract.class);


	// *************************METHODES************************************/

	
	
	
	 /**
	 * method CONSTRUCTEUR EcriveurDaoAbstract() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public EcriveurDaoAbstract() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/imports_abstractclass_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_PATH_REL_COUCHE_JAVA_STRING
							, this.pathRelCoucheJavaString);
		
		final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_PATH_REL_CONCEPT
						, this.pathRelConceptString);
		
		final List<String> listSubst3 
			= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
		
		/* alimentation de this.imports. */
		this.imports = listSubst3;
		
		return this.imports;
		
	} // Fin de creerLignesImport()._______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesJavaDoc(
			final File pFile) throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/javadoc_abstractclass_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEFICHIERJAVA
						, this.nomSimpleFichierJava);
		
		final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_CONCEPT_MODELISE
						, this.conceptModelise);

		final List<String> listSubst3 
			= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
		
		final List<String> listSubst4 
			= this.substituerVariablesDansLigne(
				listSubst3
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);

		
		final List<String> listSubst5 
			= this.substituerVariablesDansLigne(
				listSubst4
					, VARIABLE_DATEDUJOUR
						, this.afficherDateDuJour());
		
		/* alimentation de this.javadoc. */
		this.javadoc = listSubst5;
		
		return this.javadoc;
		
	} // Fin de creerLignesJavaDoc(...).___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutJavaDoc() {
		return " * CLASSE ABSTRAITE";
	} // Fin de fournirDebutJavaDoc()._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesEntity(
			final File pFile) throws Exception {
		return null;
	} // Fin de creerLignesEntity(...).____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String creerLigneDeclaration(
			final File pFile) throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate(
					"dao/declaration_abstractclass_abstractdao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOMSIMPLEFICHIERJAVA
						, this.nomSimpleFichierJava);
		
		final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
		
		final List<String> listSubst3 
			= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOMSIMPLEINTERFACE
						, this.nomSimpleInterface);

		final String declaration 
			= this.retournerStringAPartirDeListe(listSubst3);
		
		this.ligneDeclaration = declaration;
		
		return this.ligneDeclaration;
		
	} // Fin de creerLigneDeclaration(...).________________________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirDebutDeclaration() {
		return ABSTRACT_CLASS;
	} // Fin de fournirDebutDeclaration()._________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeHook(File pFile) {

		// TODO Auto-generated method stub

	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirTypeFichierJava() {
		return "LA CLASSE ";		
	} // Fin de fournirTypeFichierJava(...)._______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirNomClasse() {

		// TODO Auto-generated method stub
		return null;
	}


	
} // FIn DE LA CLASSE EcriveurDaoAbstract.---------------------------------
