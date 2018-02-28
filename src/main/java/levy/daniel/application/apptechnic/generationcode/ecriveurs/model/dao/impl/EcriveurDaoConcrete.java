package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.dao.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;

/**
 * classe <b>EcriveurDaoConcrete</b> :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 28 févr. 2018
 *
 */
public class EcriveurDaoConcrete 
	extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_DAO_CONCRETE : String :<br/>
	 * "Classe EcriveurDaoConcrete".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_DAO_CONCRETE 
		= "Classe EcriveurDaoConcrete";
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(EcriveurDaoConcrete.class);

	
	// *************************METHODES************************************/
	
	
	
	 /**
	 * method CONSTRUCTEUR EcriveurDaoConcrete() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public EcriveurDaoConcrete() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() 
										throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/imports_concreteclass_concretedao.txt");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
					listeLignes
						, VARIABLE_PATH_REL_COUCHE_JAVA_STRING
							, this.pathRelCoucheJavaString);
		
		final List<String> listSubst2 
			= this.substituerVariablesDansLigne(
				listSubst1
					, VARIABLE_PATH_REL_SOUS_COUCHE_JAVA_STRING
						, this.pathRelSousCoucheJavaString);
		
		final List<String> listSubst3 
			= this.substituerVariablesDansLigne(
				listSubst2
					, VARIABLE_NOMSIMPLEABSTRACTCLASS
						, this.nomSimpleAbstractClass);
		
		final List<String> listSubst4 
			= this.substituerVariablesDansLigne(
				listSubst3
					, VARIABLE_PATH_REL_CONCEPT
						, this.pathRelConceptString);

		final List<String> listSubst5 
			= this.substituerVariablesDansLigne(
				listSubst4
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);

		final List<String> listSubst6 
			= this.substituerVariablesDansLigne(
				listSubst5
					, VARIABLE_PATH_REL_CONCEPT_IMPL
						, this.pathRelConceptImplString);
		
		final List<String> listSubst7 
			= this.substituerVariablesDansLigne(
				listSubst6
					, VARIABLE_NOM_CLASSE_METIER
						, this.nomClassMetier);

		/* alimentation de this.imports. */
		this.imports = listSubst7;
		
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
			= this.lireTemplate(
					"dao/javadoc_concreteclass_concretdao.txt");
		
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
					, VARIABLE_NOMSIMPLEABSTRACTCLASS
						, this.nomSimpleAbstractClass);
		
		final List<String> listSubst4 
			= this.substituerVariablesDansLigne(
				listSubst3
					, VARIABLE_NOM_CLASSE_METIER
						, this.nomClassMetier);

		
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
	protected String fournirDebutJavaDoc() {
		return " * CLASSE CONCRETE";
	} // Fin de fournirDebutJavaDoc()._____________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<String> creerLignesEntity(File pFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String creerLigneDeclaration(File pFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirDebutDeclaration() {
		// TODO Auto-generated method stub
		return null;
	}

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
		return CLASSE_ECRIVEUR_DAO_CONCRETE;
	} // Fin de fournirNomClasse().________________________________________


	
}
