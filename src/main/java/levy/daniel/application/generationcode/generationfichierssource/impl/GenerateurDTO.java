package levy.daniel.application.generationcode.generationfichierssource.impl;

import java.io.File;
import java.nio.file.Path;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.generationcode.generationfichierssource.AbstractGenerateurFichierSource;
import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;

/**
 * CLASSE GenerateurDTO :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * nommer Interface DTO, nommer abstraction DTO, nommer DTO, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 18 juin 2019
 *
 */
public class GenerateurDTO extends AbstractGenerateurFichierSource {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe GenerateurDTO".
	 */
	public static final String CLASSE_GENERATEUR_DTO 
		= "Classe GenerateurDTO";
		
	/**
	 * path ABSOLU du REPERTOIRE des abstractions éventuelles 
	 * (Interface et Classe Abstraite) du DTO.<br/>
	 * <code><b>${projet}/src/main/java/levy/daniel/application/model/
	 * dto/metier/package</b></code><br/>
	 * 
	 */
	private transient Path repertoireAbstractionDTOAbsoluPath;

	/**
	 * path ABSOLU du REPERTOIRE des implémentations 
	 * (si Interface et/ou Classe Abstraite) du DTO.<br/>
	 * <code><b>${projet}/src/main/java/levy/daniel/application/model/
	 * dto/metier/package/impl</b></code><br/>
	 */
	private transient Path repertoireConcreteDTOAbsoluPath;
	
	/**
	 * path ABSOLU de l'INTERFACE du DTO.<br/>
	 * <code><b>${projet}/src/main/java/levy/daniel/application/model/
	 * dto/metier/package/I{$ConceptMetier}DTO.java</b></code><br/>
	 */
	private transient Path interfaceDTOAbsoluPath;
	
	/**
	 * path ABSOLU du DTO CONCRET.<br/>
	 * <code><b>${projet}/src/main/java/levy/daniel/application/model/
	 * dto/metier/package/impl/{$ConceptMetier}DTO.java</b></code><br/>
	 */
	private transient Path concreteDTOAbsoluPath;
	
	/**
	 * path RELATIF du REPERTOIRE des abstractions éventuelles 
	 * (Interface et Classe Abstraite) du DTO par rapport 
	 * au chemin des src/main/java du projet cible ECLIPSE.<br/>
	 * <code><b>levy/daniel/application/model/
	 * dto/metier/package</b></code><br/>
	 * 
	 */
	private transient Path repertoireAbstractionDTORelatifPath;
	
	/**
	 * package JAVA des abstractions éventuelles 
	 * (Interface et Classe Abstraite) du DTO par rapport 
	 * au chemin des src/main/java du projet cible ECLIPSE.<br/>
	 * avec des points au lieu des slashes.<br/>
	 * <code><b>levy.daniel.application.model.dto.metier.package</b></code><br/>
	 */
	private transient String cheminPackageJavaAbstractionDTO;

	/**
	 * path RELATIF du REPERTOIRE des implémentations 
	 * (si Interface et/ou Classe Abstraite) du DTO par rapport 
	 * au chemin des src/main/java du projet cible ECLIPSE.<br/>
	 * <code><b>levy/daniel/application/model/
	 * dto/metier/package/impl</b></code><br/>
	 */
	private transient Path repertoireConcreteDTORelatifPath;
	
	/**
	 * package JAVA des implémentations 
	 * (si Interface et Classe Abstraite) du DTO par rapport 
	 * au chemin des src/main/java du projet cible ECLIPSE.<br/>
	 * avec des points au lieu des slashes.<br/>
	 * <code><b>levy.daniel.application.model.dto.metier.package.impl</b></code><br/>
	 */
	private transient String cheminPackageJavaConcreteDTO;
	
	/**
	 * nom simple de l'INTERFACE du DTO sans extension.<br/>
	 * Par exemple : <code><b>ITeleversementDTO</b></code>.<br/>
	 */
	private transient String nomSimpleInterfaceDTO;
	
	/**
	 * nom simple ddu DTO CONCRET sans extension.<br/>
	 * Par exemple : <code><b>TeleversementDTO</b></code>.<br/>
	 */
	private transient String nomSimpleConcreteDTO;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(GenerateurDTO.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 * 
	 * @throws Exception
	 */
	public GenerateurDTO() throws Exception {
		this(null, null);
		// TODO Auto-generated constructor stub
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * CONSTRUCTEUR COMPLET.
	 * <ul>
	 * calcule les attributs imposés :
	 * <li>calcule <code><b>this.nomSimplePackageConceptMetier</b></code>.</li>
	 * <li>calcule <code><b>this.nomConceptMetier</b></code>.</li>
	 * <li>calcule <code><b>this.attributsPrivateMap</b></code>.</li>
	 * <li>calcule les attributs spécifiques au DESIGN PATTERN 
	 * (DTO, DAO, ...) grâce à un HOOK.</li>
	 * </ul>
	 * 
	 * @param pProjetCiblePath : Path : 
	 * path ABSOLU du projet CIBLE Eclipse dans lequel on va générer le code
	 * @param pConceptMetier : File : 
	 * concept métier pour lequel on va générer les classes 
	 * DESIGN PATTERN (DTO, DAO, ...).
	 * 
	 * @throws Exception 
	 */
	public GenerateurDTO(
			final Path pProjetCiblePath
				, final File pConceptMetier) throws Exception {
		
		super(pProjetCiblePath, pConceptMetier);

	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	 /**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("GenerateurDTO [");
		
		stb.append("projetCiblePath=");
		if (this.projetCiblePath != null) {			
			stb.append(this.projetCiblePath.toString());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);
		
		stb.append("conceptMetier=");
		if (this.conceptMetier != null) {			
			stb.append(this.conceptMetier.getAbsolutePath());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("nomSimplePackageConceptMetier=");
		if (this.nomSimplePackageConceptMetier != null) {			
			stb.append(this.nomSimplePackageConceptMetier);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);
		
		stb.append("nomConceptMetier=");
		if (this.nomConceptMetier != null) {			
			stb.append(this.nomConceptMetier);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("repertoireAbstractionDTOAbsoluPath=");
		if (this.repertoireAbstractionDTOAbsoluPath != null) {			
			stb.append(this.repertoireAbstractionDTOAbsoluPath.toString());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);
		
		stb.append("repertoireConcreteDTOAbsoluPath=");
		if (this.repertoireConcreteDTOAbsoluPath != null) {			
			stb.append(this.repertoireConcreteDTOAbsoluPath);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);
		
		stb.append("interfaceDTOAbsoluPath=");
		if (this.interfaceDTOAbsoluPath != null) {			
			stb.append(this.interfaceDTOAbsoluPath);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);
		
		stb.append("concreteDTOAbsoluPath=");
		if (this.concreteDTOAbsoluPath != null) {			
			stb.append(this.concreteDTOAbsoluPath);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);
		
		stb.append("repertoireAbstractionDTORelatifPath=");
		if (this.repertoireAbstractionDTORelatifPath != null) {			
			stb.append(this.repertoireAbstractionDTORelatifPath);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("repertoireConcreteDTORelatifPath=");
		if (this.repertoireConcreteDTORelatifPath != null) {			
			stb.append(this.repertoireConcreteDTORelatifPath);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("nomSimpleInterfaceDTO=");
		if (this.nomSimpleInterfaceDTO != null) {			
			stb.append(this.nomSimpleInterfaceDTO);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("nomSimpleConcreteDTO=");
		if (this.nomSimpleConcreteDTO != null) {			
			stb.append(this.nomSimpleConcreteDTO);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("cheminPackageJavaAbstractionDTO=");
		if (this.cheminPackageJavaAbstractionDTO != null) {			
			stb.append(this.cheminPackageJavaAbstractionDTO);
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("cheminPackageJavaConcreteDTO=");
		if (this.cheminPackageJavaConcreteDTO != null) {			
			stb.append(this.cheminPackageJavaConcreteDTO);
		} else {
			stb.append(NULL);
		}

		stb.append(']');
		
		return stb.toString();
		
	} // Fin de toString().________________________________________________

	
	
	/**
	 * .<br/>
	 * <br/>
	 * : void :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public void genererInterfaceEtClasseConcrete() throws Exception {
		
		this.genererInterface();
		
		this.genererClasseConcrete();
		
	}


	
	/**
	 * .<br/>
	 * <br/>
	 * : void :  .<br/>
	 * 
	 * @throws Exception 
	 */
	private void genererInterface() throws Exception {
		
		/* crée d'abord le fichier vide sur disque si nécessaire. */
		this.creerFichierVideEtArborescenceSurDisque(
				this.interfaceDTOAbsoluPath.toFile());
		
	}

	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @throws Exception
	 */
	private void genererClasseConcrete() throws Exception {
		
		/* crée d'abord le fichier vide sur disque si nécessaire. */
		this.creerFichierVideEtArborescenceSurDisque(
				this.concreteDTOAbsoluPath.toFile());
		
	}
	
	
	
	/**
	 * {@inheritDoc}
	 * <ul>
	 * <li>calcule <code><b>this.repertoireAbstractionDTOAbsoluPath</b></code></li>
	 * <li>calcule <code><b>this.interfaceDTOAbsoluPath</b></code></li>
	 * <li>calcule <code><b>this.repertoireConcreteDTOAbsoluPath</b></code></li>
	 * <li>calcule <code><b>this.concreteDTOAbsoluPath</b></code></li>
	 * <li>calcule <code><b>this.repertoireAbstractionDTORelatifPath</b></code></li>
	 * <li>calcule <code><b>this.repertoireConcreteDTORelatifPath</b></code></li>
	 * <li>calcule <code><b>this.nomSimpleInterfaceDTO</b></code></li>
	 * <li>calcule <code><b>this.nomSimpleConcreteDTO</b></code></li>
	 * <li>calcule <code><b>this.cheminPackageJavaAbstractionDTO</b></code></li>
	 * </ul>
	 */
	@Override
	protected final void calculerAttributsImposesHook() throws Exception {
		
		/* délègue à un ArboresceurProjetCible le soin de 
		 * calculer les chemins du projet cible. */
		ArboresceurProjetCible.selectionnerProjetCible(this.projetCiblePath);
		
		/* ${projet}/src/main/java/levy/daniel/application/model/dto/metier */
		final Path repertoireMetierDTOProjetCiblePath 
			= ArboresceurProjetCible.getCoucheModelDTOMetierMainPath();
		
		/* ${projet}/src/main/java/levy/daniel/application/model/dto/metier/
		 * package */
		this.repertoireAbstractionDTOAbsoluPath 
			= repertoireMetierDTOProjetCiblePath
				.resolve(this.nomSimplePackageConceptMetier)
					.normalize();
		
		/* ${projet}/src/main/java/levy/daniel/application/model/dto/metier/
		 * package/I{$ConceptMetier}DTO.java */
		this.interfaceDTOAbsoluPath 
			= this.repertoireAbstractionDTOAbsoluPath
				.resolve(this.nommerinterfaceDesignPattern())
					.normalize();
		
		/* ${projet}/src/main/java/levy/daniel/application/model/dto/metier/
		 * package/impl */
		this.repertoireConcreteDTOAbsoluPath 
			= this.repertoireAbstractionDTOAbsoluPath.resolve(IMPL)
				.normalize();
		
		/* ${projet}/src/main/java/levy/daniel/application/model/dto/metier/
		 * package/impl/{$ConceptMetier}DTO.java */
		this.concreteDTOAbsoluPath 
			= this.repertoireConcreteDTOAbsoluPath
				.resolve(this.nommerConcreteDesignPattern())
					.normalize();

		final Path pathSrcMainJava 
			= ArboresceurProjetCible.getSrcMainJavaPath();
		
		/* levy/daniel/application/model/dto/metier/package */
		this.repertoireAbstractionDTORelatifPath 
			= pathSrcMainJava
				.relativize(this.repertoireAbstractionDTOAbsoluPath)
					.normalize();
		
		/* levy/daniel/application/model/dto/metier/package/impl */
		this.repertoireConcreteDTORelatifPath 
			= pathSrcMainJava
				.relativize(this.repertoireConcreteDTOAbsoluPath)
					.normalize();
		
		this.nomSimpleInterfaceDTO = this.nommerSimpleinterfaceDesignPattern();
		
		this.nomSimpleConcreteDTO = this.nommerSimpleConcreteDesignPattern();
		
		this.cheminPackageJavaAbstractionDTO 
			= this.transformerPathEnCheminJava(
					this.repertoireAbstractionDTORelatifPath);
		
		this.cheminPackageJavaConcreteDTO 
			= this.transformerPathEnCheminJava(
					this.repertoireConcreteDTORelatifPath);
		
	} // Fin de calculerAttributsImposesHook().____________________________



	/**
	 * retourne une String pour le nom de l'Interface du DTO.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * <code><b>ITeleversementDTO.java</b></code> 
	 * pour un Concept Metier "Televersement.java".<br/>
	 * <br/>
	 * - retourne null si this.nomConceptMetier == null.<br/>
	 * <br/>
	 */
	private String nommerinterfaceDesignPattern() {
		
		/* retourne null si this.nomConceptMetier == null. */
		if (this.nomConceptMetier != null) {
			
			final String resultat = "I" + this.nomConceptMetier + "DTO.java";			
			return resultat;
			
		}
		
		return null;
		
	} // Fin de nommerinterfaceDesignPattern().____________________________



	/**
	 * retourne une String pour le nom du DTO concret.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * <code><b>TeleversementDTO.java</b></code> 
	 * pour un Concept Metier "Televersement.java".<br/>
	 * <br/>
	 * - retourne null si this.nomConceptMetier == null.<br/>
	 * <br/>
	 */
	private String nommerConcreteDesignPattern() {
		
		/* retourne null si this.nomConceptMetier == null. */
		if (this.nomConceptMetier != null) {
			
			final String resultat = this.nomConceptMetier + "DTO.java";			
			return resultat;
			
		}
		
		return null;
		
	} // Fin de nommerConcreteDesignPattern()._____________________________



	/**
	 * retourne une String pour le nom SIMPLE 
	 * de l'Interface du DTO sans Extension.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * <code><b>ITeleversementDTO</b></code> 
	 * pour un Concept Metier "Televersement.java".<br/>
	 * <br/>
	 * - retourne null si this.nomConceptMetier == null.<br/>
	 * <br/>
	 */
	private String nommerSimpleinterfaceDesignPattern() {
		
		/* retourne null si this.nomConceptMetier == null. */
		if (this.nomConceptMetier != null) {
			
			final String resultat = "I" + this.nomConceptMetier + "DTO";			
			return resultat;
			
		}
		
		return null;
		
	} // Fin de nommerSimpleinterfaceDesignPattern().______________________



	/**
	 * retourne une String pour le nom SIMPLE 
	 * du DTO concret sans Extension.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * <code><b>TeleversementDTO</b></code> 
	 * pour un Concept Metier "Televersement.java".<br/>
	 * <br/>
	 * - retourne null si this.nomConceptMetier == null.<br/>
	 * <br/>
	 */
	private String nommerSimpleConcreteDesignPattern() {
		
		/* retourne null si this.nomConceptMetier == null. */
		if (this.nomConceptMetier != null) {
			
			final String resultat = this.nomConceptMetier + "DTO";			
			return resultat;
			
		}
		
		return null;
		
	} // Fin de nommerSimpleConcreteDesignPattern()._______________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirClassePourMessage() {
		return CLASSE_GENERATEUR_DTO;
	} // Fin de fournirClassePourMessage().________________________________
	
	
		
} // FIN DE LA CLASSE GenerateurDTO.-----------------------------------------
