package levy.daniel.application.apptechnic.generationcode.ecriveurs.model.dao.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.generationcode.ecriveurs.AbstractEcriveurFichiersJavaDetaille;

/**
 * CLASSE <b>EcriveurDaoInterface</b> :<br/>
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
 * @since 15 févr. 2018
 *
 */
public class EcriveurDaoInterface 
				extends AbstractEcriveurFichiersJavaDetaille {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ECRIVEUR_DAO_INTERFACE : String :<br/>
	 * "Classe EcriveurDaoInterface".<br/>
	 */
	public static final String CLASSE_ECRIVEUR_DAO_INTERFACE 
		= "Classe EcriveurDaoInterface";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
				= LogFactory.getLog(EcriveurDaoInterface.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR EcriveurDaoInterface() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public EcriveurDaoInterface() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<String> creerLignesImport() throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/imports_interface_idao.txt");
		
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
			= this.lireTemplate("dao/javadoc_interface_idao.txt");
		
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
					, VARIABLE_DATEDUJOUR
						, this.afficherDateDuJour());
		
		/* alimentation de this.javadoc. */
		this.javadoc = listSubst3;
		
		return this.javadoc;
		
	} // Fin de creerLignesJavaDoc(...).___________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutJavaDoc() {
		return " * INTERFACE";
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
			final File pFile) {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(INTERFACE);
		stb.append(this.nomSimpleFichierJava);
		stb.append('\n');
		stb.append(DECALAGE_CODE);
		stb.append("extends IDaoGenericJPASpring<");
		stb.append(this.nomInterfaceMetier);
		stb.append(", Long> {");
		
		this.ligneDeclaration = stb.toString();
		
		return this.ligneDeclaration;
		
	} // Fin de creerLigneDeclaration(...).________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirDebutDeclaration() {
		return INTERFACE;
	} // Fin de fournirDebutDeclaration()._________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void ecrireCodeHook(
			final File pFile) {
		
		try {
			
			/* crée tout le bloc comprenant les METHODES. */
			this.ecrireBlocMethodes(pFile);
			
		} catch (Exception e) {
					
			if (LOG.isFatalEnabled()) {
				LOG.fatal("Impossible d'écrire les méthodes", e);
			}
		}		

	} // Fin de ecrireCodeHook(...)._______________________________________
	
	
	
	/**
	 * method ecrireBlocMethodes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li><b>écriture</b> dans le fichier java.</li>
	 * <li><b>Crée tout le bloc comprenant les methodes</b>.</li>
	 * <ul>
	 * <li>Ecrit la méthode compareTo().</li>
	 * <li>Ecrit la méthode clone().</li>
	 * <li>Ecrit la méthode getEnTeteCsv().</li>
	 * <li>Ecrit la méthode toStringCsv().</li>
	 * <li>Ecrit la méthode getEnTeteColonne().</li>
	 * <li>Ecrit la méthode getValeurColonne().</li>
	 * <li>Ecrit la méthode getId().</li>
	 * <li>Ecrit la méthode setId().</li>
	 * <li>écrit les getters-setters.</li>
	 * </ul>
	 * </ul>
	 * ne fait rien si pFile est null.<br/>
	 * ne fait rien si pFile n'existe pas.<br/>
	 * ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier java.<br/>
	 * 
	 * @throws Exception
	 */
	private void ecrireBlocMethodes(
			final File pFile) throws Exception {
		
		/* ne fait rien si pFile est null. */
		if (pFile == null) {
			return;
		}

		/* ne fait rien si pFile n'existe pas. */
		if (!pFile.exists()) {
			return;
		}

		/* ne fait rien si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return;
		}

		/* Insère 3 lignes vides sous la ligne 
		 * de déclaration.*/
		final StringBuilder stb = new StringBuilder();
		
		stb.append(DECALAGE_CODE);
		stb.append("extends IDaoGenericJPASpring<");
		stb.append(this.nomInterfaceMetier);
		stb.append(", Long> {");
		
		final String ligneDec = stb.toString();
		
		this.insererLignesVidesSousLigneDansFichier(
				pFile, ligneDec, 3, CHARSET_UTF8);
		
		/* crée la méthode retrieveByIdMetier(...) */
		this.creerMethodeRetrieveById(pFile);
						
	} // Fin de ecrireBlocMethodes(...).___________________________________
	

	
	/**
	 * method creerMethodeRetrieveById(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Crée la Javadoc de la méthode retrieveByIdMetier(...)</li>
	 * <li>Crée le code de la méthode retrieveByIdMetier(...)</li>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	private void creerMethodeRetrieveById(
			final File pFile) throws Exception {
		
		/* Lecture du template. */
		final List<String> listeLignes 
			= this.lireTemplate("dao/interface_dao_retrievebyidmetier");
		
		/* substitutions. */
		final List<String> listSubst1 
			= this.substituerVariablesDansLigne(
				listeLignes
					, VARIABLE_NOM_INTERFACE_METIER
						, this.nomInterfaceMetier);
				
		/* *************** */
		/* ENREGISTREMENT. */
		/* *************** */
		final String ligneIdentifiant 
			= "		retrieveByIdMetier";
		
		if (this.existLigneCommencant(
				pFile, CHARSET_UTF8, ligneIdentifiant)) {
			return;
		}
		
		this.ecrireCode(listSubst1, pFile);

	} // Fin de creerMethodeRetrieveById(...)._____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerAttributId(
			final List<String> pListe) throws Exception {
		return;
	} // Fin de creerAttributId(...).______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void creerJavadocAttribut(
			final List<String> pListe
				, final String pNomAttribut
					, final String pTypeAttribut)
									throws Exception {
		return;
	} // Fin de creerJavadocAttribut(._____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeAttribut(List<String> pListe, String pNomAttribut, String pTypeAttribut) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void ecrireCodeConstructeurCompletBase(File pFile) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocCompareTo(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeCompareTo(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifiantCompareTo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocClone(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeClone(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocToString(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeToString(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetEnTeteCsv(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetEnTeteCsv(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocToStringCsv(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeToStringCsv(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetEnTeteColonne(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetEnTeteColonne(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetValeurColonne(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetValeurColonne(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerEntityGetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocSetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeSetId(List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocGetter(String pNomAttribut, String pTypeAttribut, List<String> pListe) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeEntityGetter(String pNomAttribut, String pTypeAttribut, List<String> pListeGetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeGetter(String pNomAttribut, String pTypeAttribut, List<String> pListeGetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifianteGetter(String pNomAttribut, String pTypeAttribut) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerJavadocSetter(String pNomAttribut, String pTypeAttribut, List<String> pListeSetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void creerCodeSetter(String pNomAttribut, String pTypeAttribut, List<String> pListeSetter)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String fournirLigneIdentifianteSetter(String pNomAttribut, String pTypeAttribut) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoInterface : 
	 * "L'INTERFACE ".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirTypeFichierJava() {
		return "L'INTERFACE ";		
	} // Fin de fournirTypeFichierJava(...)._______________________________

	
	
	/**
	 * {@inheritDoc}
	 * <br/>
	 * retourne pour un EcriveurDaoInterface : 
	 * "Classe EcriveurDaoInterface".<br/>
	 * <br/>
	 */
	@Override
	protected final String fournirNomClasse() {
		return CLASSE_ECRIVEUR_DAO_INTERFACE;
	} // Fin de fournirNomClasse().________________________________________

	
	
} // FIn DE LA CLASSE EcriveurDaoInterface.----------------------------------
