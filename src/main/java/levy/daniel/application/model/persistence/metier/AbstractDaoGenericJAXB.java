package levy.daniel.application.model.persistence.metier;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE AbstractDaoGenericJAXB :<br/>
 * <ul>
 * <li><b>DAO ABSTRAIT GENERIQUE</b> pour <b>JAXB</b>.</li>
 * <li>
 * Comporte l'implémentation des méthodes <b>CRUD</b> factorisables 
 * pour <b>tous les objets métier</b>.
 * </li>
 * </ul>
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
 * @since 7 sept. 2018
 * 
 * @param <T> : Type paramétré : Classe réelle d'un Objet métier.<br/>
 * @param <ID> : Type paramétré : type de l'ID en base d'un Objet métier 
 * (ID, Integer, String, ...).<br/>
 */
public abstract class AbstractDaoGenericJAXB<T, ID extends Serializable> 
										implements IDaoGenericJAXB<T, ID> {

	// ************************ATTRIBUTS************************************/

	/**
	 * context : JAXBContext :<br/>
	 * context JAXB.<br/>
	 */
	protected transient JAXBContext context;
		
	/**
	 * marshaller : Marshaller :<br/>
	 * Objet vers XML.<br/>
	 */
	protected transient Marshaller marshaller;

	/**
	 * unmarshaller : Unmarshaller :<br/>
	 * XML vers Objet.<br/>
	 */
	protected transient Unmarshaller unmarshaller;

	
	/**
	 * fichierXML : File :<br/>
	 * fichier XML dans lequel écrire les entities JAXB.<br/>
	 */
	protected File fichierXML;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AbstractDaoGenericJAXB.class);

	// *************************METHODES************************************/
	

	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * 
	 * @throws JAXBException
	 */
	public AbstractDaoGenericJAXB() throws JAXBException {

		this(null);
		
	} // Fin de  CONSTRUCTEUR D'ARITE NULLE._______________________________


	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <br/>
	 *
	 * @param pFile : java.io.File : 
	 * le fichier XML dans lequel écrire les entities JAXB.<br/>
	 * 
	 * @throws JAXBException
	 */
	public AbstractDaoGenericJAXB(
			final File pFile) throws JAXBException {
		
		super();
		
		this.fichierXML = pFile;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	



	/* CREATE ************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T create(
			final T pObject) 
			throws JAXBException, IOException {
		
		return this.create(pObject, this.fichierXML);
		
	} // Fin de create(...)._______________________________________________
	
	
	
	/**
	 * <b>stocke l'objet métier pObject 
	 * dans un fichier XML pFile</b> 
	 * et retourne l'objet persisté.<br/>
	 * <ul>
	 * <li>crée sur disque le fichier pFile si il n'existe pas.</li>
	 * <li>récupère ou crée la liste des objets métier 
	 * déjà stockés dans le fichier XML pFile.</li>
	 * <li>ajoute l'objet métier pObject à la liste 
	 * si pObject n'est pas déjà stocké (<b>gestion des doublons</b>).</li>
	 * <li>enregistre la nouvelle liste dans le fichier XML pFile.</li>
	 * <li>retourne null si pObject existe déjà dans le stockage.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * - retourne null si pFile == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * @param pFile : java.io.File : 
	 * le fichier XML dans lequel écrire les entities JAXB.<br/>
	 *  
	 * @return : T : 
	 * l'objet métier persisté dans le stockage.<br/>
	 * 
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public final T create(
			final T pObject
				, final File pFile) 
						throws JAXBException, IOException {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		List<T> listeObjetsMetier = null;
		
		/* récupère ou crée la liste des objets métier déjà stockés 
		 * déjà stockés dans le fichier XML pFile.*/
		if (pFile.exists()) {
			listeObjetsMetier = this.findAll(pFile);
		} else {
			listeObjetsMetier = new ArrayList<T>();
		}
		  		
		if (listeObjetsMetier != null) {
			
			/* ajoute l'objet métier pObject à la liste 
			 * si pObject n'est pas déjà stocké (gestion des doublons). */
			if (!listeObjetsMetier.contains(pObject)) {
				listeObjetsMetier.add(pObject);
			} else {
				return null;
			}
						
			/* enregistre la nouvelle liste dans le fichier XML pFile. */
			this.enregistrer(listeObjetsMetier, pFile);
			
			return pObject;
		} 
			
		return null;
				
	} // Fin de create(...)._______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void persist(
			final T pObject) 
			throws JAXBException, IOException {
		
		this.persist(pObject, this.fichierXML);
		
	} // Fin de create(...)._______________________________________________
	
	
	
	/**
	 * <b>stocke l'objet métier pObject 
	 * dans un fichier XML pFile</b> 
	 *  mais ne retourne rien.<br/>
	 * <ul>
	 * <li>crée sur disque le fichier pFile si il n'existe pas.</li>
	 * <li>récupère ou crée la liste des objets métier 
	 * déjà stockés dans le fichier XML pFile.</li>
	 * <li>ajoute l'objet métier pObject à la liste 
	 * si pObject n'est pas déjà stocké (<b>gestion des doublons</b>).</li>
	 * <li>enregistre la nouvelle liste dans le fichier XML pFile.</li>
	 * <li>ne fait rien si pObject existe déjà dans le stockage.</li>
	 * </ul>
	 * - ne fait rien si pObject == null.<br/>
	 * - ne fait rien si pFile == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * @param pFile : java.io.File : 
	 * le fichier XML dans lequel écrire les entities JAXB.<br/>
	 *  
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public final void persist(
			final T pObject
				, final File pFile) 
						throws JAXBException, IOException {
		
		/* ne fait rien si pObject == null. */
		if (pObject == null) {
			return;
		}
		
		/* ne fait rien si pFile == null. */
		if (pFile == null) {
			return;
		}
		
		List<T> listeObjetsMetier = null;
		
		/* récupère ou crée la liste des objets métier déjà stockés 
		 * déjà stockés dans le fichier XML pFile.*/
		if (pFile.exists()) {
			listeObjetsMetier = this.findAll(pFile);
		} else {
			listeObjetsMetier = new ArrayList<T>();
		}
		  		
		if (listeObjetsMetier != null) {
			
			/* ajoute l'objet métier pObject à la liste 
			 * si pObject n'est pas déjà stocké (gestion des doublons). */
			if (!listeObjetsMetier.contains(pObject)) {
				listeObjetsMetier.add(pObject);
			} else {
				return;
			}
						
			/* enregistre la nouvelle liste dans le fichier XML pFile. */
			this.enregistrer(listeObjetsMetier, pFile);
			
		} 
							
	} // Fin de create(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ID createReturnId(
			final T pObject) 
							throws IOException, JAXBException {
		
		return this.createReturnId(pObject, this.fichierXML);
		
	} // Fin de createReturnId(...)._______________________________________
	
	
	
	/**
	 * <b>stocke l'objet métier pObject 
	 * dans un fichier XML pFile</b> 
	 * et retourne l'objet persisté.<br/>
	 * <ul>
	 * <li>crée sur disque le fichier pFile si il n'existe pas.</li>
	 * <li>récupère ou crée la liste des objets métier 
	 * déjà stockés dans le fichier XML pFile.</li>
	 * <li>ajoute l'objet métier pObject à la liste 
	 * si pObject n'est pas déjà stocké (<b>gestion des doublons</b>).</li>
	 * <li>enregistre la nouvelle liste dans le fichier XML pFile.</li>
	 * <li>retourne null si pObject existe déjà dans le stockage.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * - retourne null si pFile == null.<br/>
	 * <br/>
	 *
	 * @param pObject : T : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * @param pFile : java.io.File : 
	 * le fichier XML dans lequel écrire les entities JAXB.<br/>
	 *  
	 * @return : T : 
	 * l'objet métier persisté dans le stockage.<br/>
	 * 
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public abstract ID createReturnId(
			T pObject
				, File pFile) 
							throws IOException, JAXBException;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Iterable<T> save(
			final Iterable<T> pList) 
						throws IOException, JAXBException {
		
		return this.save(pList, this.fichierXML);
		
	} // Fin de save(...)._________________________________________________
	
	
	
	/**
	 * <b>stocke sur disque l'itérable d'objets métier pList 
	 * dans un fichier XML pFile</b> et retourne un Iterable 
	 * des objets persistés.<br/>
	 * <ul>
	 * <li>crée sur disque le fichier pFile si il n'existe pas.</li>
	 * <li>itère sur chaque élément de l'itérable.</li>
	 * <li>stocke chaque élément de l'itérable dans le fichier XML 
	 * <i>si il ne crée pas de doublon</i> 
	 * (<b>gestion des doublons</b>).</li>
	 * <li>ne fait rien et continue la sauvegarde si un objet 
	 * dans l'itérable existe déjà dans le stockage.</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * - retourne null si pFile == null.<br/>
	 * <br/>
	 *
	 * @param pList : Iterable&lt;T&gt; : 
	 * itérable d'objets métier à persister dans le stockage.<br/>
	 * @param pFile : File : fichier XML.<br/>
	 * 
	 * @return Iterable&lt;T&gt; : 
	 * itérable d'objets métier persistés dans le stockage.<br/>
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	public final Iterable<T> save(
			final Iterable<T> pList
				, final File pFile) 
						throws IOException, JAXBException {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		final Iterator<T> ite = pList.iterator();
		
		final List<T> resultat = new ArrayList<T>();
		
		/* itère sur chaque élément de l'itérable. */
		while (ite.hasNext()) {
			
			final T motif = ite.next();
			
			/* stocke chaque élément de l'itérable dans le fichier XML. */
			/* ne crée pas de doublon. */
			final T motifPersiste = this.create(motif, pFile);
			
			if (motifPersiste != null) {
				resultat.add(motifPersiste);
			}
		}
		
		return resultat;
		
	} // Fin de save(...)._________________________________________________
	
	
	
	/**
	 * <b>Enregistre sur disque dans le fichier XML pFile 
	 * la liste de MODEL (objets métier) pList</b>.<br/>
	 * <ul>
	 * <li>Crée pFile sur disque si il n'existe pas.</li>
	 * <li>Remplace pFile si il existe déjà.</li>
	 * <li>crée une entity JAXB de type Table à partir 
	 * de la liste d'objets métier.</li>
	 * <li>enregistre sur disque dans un fichier XML 
	 * l'entity JAXB de type Table.</li>
	 * </ul>
	 * - ne fait rien si pList == null.<br/>
	 * - ne fait rien si pFile == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;T&gt;.<br/>
	 * @param pFile : java.io.File.<br/>
	 * 
	 * @throws JAXBException
	 */
	protected abstract void enregistrer(
			List<T> pList
				, File pFile) throws JAXBException;
	
	
	



	/* READ *************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T retrieve(
			final T pObject) 
						throws IOException, JAXBException {
		
		return this.retrieve(pObject, this.fichierXML);
		
	} // Fin de retrieve(...)._____________________________________________

	
	
	/**
	 * <b>retourne un objet métier stocké dans le fichier XML 
	 * pFile</b> et retourne l'objet métier persisté.<br/>
	 * <ul>
	 * <li>recherche l'objet métier par son égalité métier 
	 * (equals()).</li>
	 * <li>retourne null si l'objet métier n'est pas 
	 * stocké dans le fichier XML pFile.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile n'existe pas.<br/>
	 * - retourne null si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pObject : T : objet métier à rechercher.<br/>
	 * @param pFile : File : fichier XML.<br/>
	 * 
	 * @return : T : objet métier recherché.<br/>
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	public final T retrieve(
			final T pObject, 
				final File pFile) 
						throws IOException, JAXBException {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne null si pFile n'existe pas. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* retourne null si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return null;
		}
		
		/* récupère la liste des objets métier stockés 
		 * dans le fichier XML. */
		final List<T> listeObjetsMetier 
			= this.findAll(pFile);
		
		if (listeObjetsMetier != null) {
			
			for (final T motif : listeObjetsMetier) {
				
				if (motif.equals(pObject)) {
					return motif;
				}
			}
			
		}
				
		/* retourne null si l'objet métier n'est pas 
		 * stocké dans le fichier XML pFile. */
		return null;
		
	} // Fin de retrieve(...)._____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T findById(final ID pId) 
						throws IOException, JAXBException {
		
		return this.findById(pId, this.fichierXML);
		
	} // Fin de findById(...)._____________________________________________
	
	
	
	/**
	 * <b>retourne un objet métier recherché par son index (0-based) 
	 * dans la liste des objets métier modélisant le fichier XML 
	 * pFile</b>.<br/>
	 * <ul>
	 * <li>recherche l'objet métier par son index (0-based)
	 * dans la liste d'objets métier modélisant le fichier XML 
	 * pFile.</li>
	 * <li>retourne null si l'index n'existe pas dans la liste.</li>
	 * </ul>
	 * - retourne null si pId == 0.<br/>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile n'existe pas.<br/>
	 * - retourne null si pFile n'est pas un fichier simple.<br/>
	 * <br/> 
	 *
	 * @param pId : Long : 
	 * index (0-based) de l'objet métier dans la liste modélisant 
	 * le fichier XML.<br/> 
	 * @param pFile : File : fichier XML.<br/>
	 * 
	 * @return : IMotif : 
	 * objet métier recherché par son index (0-based).<br/>
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	public abstract T findById(
			ID pId, 
				File pFile) 
						throws IOException, JAXBException;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ID retrieveId(
			final T pObject) 
						throws IOException, JAXBException {
		return this.retrieveId(pObject, this.fichierXML);
	} // Fin de retrieveId(...).___________________________________________
	
	

	/**
	 * <b>retourne l'index (0-based) de l'objet métier pObject 
	 * dans la liste des objet métier stockés dans le fichier XML 
	 * pFile</b>.<br/>
	 * <ul>
	 * <li>recherche l'objet métier par son égalité métier 
	 * (equals()).</li>
	 * <li>retourne 0 si l'objet métier n'est pas 
	 * stocké dans le fichier XML pFile.</li>
	 * </ul>
	 * - retourne 0 si pObject == null.<br/>
	 * - retourne 0 si pFile == null.<br/>
	 * - retourne 0 si pFile n'existe pas.<br/>
	 * - retourne 0 si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pObject : T : objet métier à rechercher.<br/>
	 * @param pFile : File : fichier XML.<br/>
	 * 
	 * @return : ID : index (0-based).<br/>
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	public abstract ID retrieveId(
			T pObject, 
				File pFile) 
						throws IOException, JAXBException;
	
	
	/**
	 * <b>retourne le liste des objets métier stockés dans le fichier XML 
	 * <code>this.fichierXML</code> qui remplissent une condition métier</b>.<br/>
	 * <ul>
	 * <li>retourne une <b>liste vide</b> si 
	 * <i>aucune correspondance n'est trouvée</i>.</li>
	 * <li>récupère la liste des objets métier 
	 * stockés dans le fichier XML.</li>
	 * <li>ajoute au résultat tous les objets métier 
	 * remplissant la condition métier.</li>
	 * </ul>
	 * - retourne null si pString est blank.<br/>
	 * - retourne null si <code>this.fichierXML</code> == null.<br/>
	 * - retourne null si <code>this.fichierXML</code> 
	 * n'existe pas.<br/>
	 * - retourne null si <code>this.fichierXML</code> 
	 * n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * String devant être contenue dans le nom des IMotif.<br/>
	 * 
	 * @return List&lt;T&gt; : 
	 * liste des T stockés dans le fichier XML 
	 * dont le nom contient pString.<br/>
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	@Override
	public final List<T> findByMetier(
			final String pString) 
						throws IOException, JAXBException {
		
		return this.findByMetier(pString, this.fichierXML);
		
	} // Fin de findByMetier(...)._________________________________________
	
	
	
	/**
	 * <b>retourne le liste des objets métier stockés dans le fichier XML 
	 * pFile qui remplissent une condition métier</b>.<br/>
	 * <ul>
	 * <li>retourne une <b>liste vide</b> si 
	 * <i>aucune correspondance n'est trouvée</i>.</li>
	 * <li>récupère la liste des objets métier 
	 * stockés dans le fichier XML.</li>
	 * <li>ajoute au résultat tous les objets métier 
	 * remplissant la condition métier.</li>
	 * </ul>
	 * - retourne null si pString est blank.<br/>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile n'existe pas.<br/>
	 * - retourne null si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * String devant remplir une condition métier.<br/>
	 * @param pFile : File : fichier XML.<br/>
	 * 
	 * @return List&lt;IMotif&gt; : 
	 * liste des T stockés dans le fichier XML 
	 * remplissant une condition métier.<br/>
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	public abstract List<T> findByMetier(
			String pString, 
				File pFile) 
						throws IOException, JAXBException;
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<T> findAll() 
					throws JAXBException, IOException {
		
		return this.findAll(this.fichierXML);
		
	} // Fin de findAll()._________________________________________________
	
	
	
	/**
	 * <b>Récupére la liste de tous les MODEL</b> 
	 * stockés dans le fichier XML 
	 * correspondant à une Entity JAXB.<br/>
	 * <ul>
	 * <li>récupère la modélisation du fichier XML 
	 * sous forme d'entité JAXB.</li>
	 * <li>récupère la liste des entités JAXB contenues 
	 * dans la modélisation du fichier XML.</li>
	 * <li>convertit la liste d'entités JAXB en liste 
	 * d'objets métier.</li>
	 * </ul>
	 * - return null si pFile == null.<br/>
	 * - return null si pFile n'existe pas.<br/>
	 * - return null si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @return List&lt;T&gt; : 
	 * liste de tous les Objets métier dans le stockage.<br/>
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	public abstract List<T> findAll(
			File pFile) 
					throws JAXBException, IOException;
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<T> findAllMax(
			final int pStartPosition
				, final int pMaxResult) 
							throws JAXBException, IOException {
		
		return this.findAllMax(
				pStartPosition, pMaxResult, this.fichierXML);
		
	} // Fin de findAllMax(...).___________________________________________
	
	
	
	/**
	 * <b>retourne la liste des pMax objets métier 
	 * persistés dans le stockage</b> à partir de la 
	 * position pStartPosition (0-based).<br/>
	 * <ul>
	 * <li>retourne par exemple les 50 objets métier stockés 
	 * à partir du 100ème.</li>
	 * <li>récupère la liste des objets métier stockés.</li>
	 * <li>retourne null si pStartPosition est hors indexes.</li>
	 * <li>ajoute un objet métier au résultat si son index 
	 * est >= pStartPosition, < (pStartPosition + pMaxResult)  
	 * et dans les indexes.</li>
	 * </ul>
	 * - return null si pFile == null.<br/>
	 * - return null si pFile n'existe pas.<br/>
	 * - return null si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pStartPosition : int : index (0-based) de départ.<br/>
	 * @param pMaxResult : int : 
	 * nombre maximum d'objets métier à retourner.<br/>
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @return : List&lt;T&gt; : 
	 * liste des pMax objets métier persistés dans le stockage 
	 * à partir de pStartPosition (0-based).<br/>
	 * 
	 * @throws IOException
	 * @throws JAXBException
	 */
	public final List<T> findAllMax(
			final int pStartPosition
				, final int pMaxResult
					, final File pFile) 
							throws JAXBException, IOException {
		
		/* return null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* return null si pFile n'existe pas. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* return null si pFile n'est pas un fichier simple. */
		if (pFile.isDirectory()) {
			return null;
		}
		
		List<T> resultat = null;
		
		/* récupère la liste des objets métier stockés 
		 * dans le fichier XML. */
		final List<T> listeObjetsMetier 
			= this.findAll(pFile);
		
		if (listeObjetsMetier != null) {
			
			final int taille = listeObjetsMetier.size();
			
			resultat = new ArrayList<T>();
			
			/* retourne null si pStartPosition est en dehors 
			 * de la liste des objets métier. */
			if (pStartPosition > taille - 1) {
				return null;
			}
			
			for (int index = pStartPosition;
					index < pStartPosition + pMaxResult;
												index ++) {
				
				try {
					
					final T objet = listeObjetsMetier.get(index);
					
					/* ajoute un objet métier au résultat 
					 * si son index est >= pStartPosition,
					 *  < (pStartPosition + pMaxResult) 
					 *  et dans les indexes. */
					resultat.add(objet);
					
				} catch (Exception e) {
					break;
				}				
				
			}
						
		}

		return resultat;
		
	} // Fin de findAllMax(...).___________________________________________
	
	
	
	/* UPDATE *************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T update(
			final ID pIndex
				, final T pObjectModifie) 
							throws JAXBException, IOException {
		
		return this.update(pIndex, pObjectModifie, this.fichierXML);
		
	} // Fin de update(...)._______________________________________________
	
	

	/**
	 * <b>modifie sur disque dur dans le fichier XML pFile 
	 * l'objet d'index (0-based) pIndex avec les valeurs 
	 * contenues dans pObjectModifie</b>.<br/>
	 * <ul>
	 * <li><b>pIndex doit correspondre à l'index (0-based) 
	 * de l'objet métier à modifier</b>.</li>
	 * <li>récupère la liste des objets métier stockés 
	 * dans le fichier XML.</li>
	 * <li>substitue pObjectModifie à l'objet métier situé à pIndex.</li>
	 * <li>enregistre la liste modifiée sur disque.</li>
	 * </ul>
	 * - retourne null si pIndex == null ou 0.<br/>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile n'existe pas.<br/>
	 * - retourne null si pFile n'est pas un fichier simple.<br/> 
	 * - retourne null si pIndex est en dehors 
	 * de la liste des objets métier.<br/>
	 * <br/>
	 *
	 * @param pIndex : ID : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * @param pObjectModifie : T : 
	 * Objet métier modifié.<br/>
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @return T : objet métier modifié.<br/>
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	public abstract T update(
			ID pIndex
				, T pObjectModifie
					, File pFile) 
							throws JAXBException, IOException;
	
	
	
	/* DELETE *************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean delete(
			final T pObject) 
						throws JAXBException, IOException {
		
		return this.delete(pObject, this.fichierXML);
		
	} // Fin de delete(...)._______________________________________________
	
	
	
	/**
	 * <b>retire l'objet métier pObject dans le fichier XML 
	 * <code>this.fichierXML</code></b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 * <ul>
	 * <li>récupère la liste des objets métier 
	 * stockés dans le fichier XML.</li>
	 * <li>retire pObject de la liste si il existe.</li>
	 * <li>enregistre la liste modifiée sur disque.</li>
	 * <li>retourne false si pObject n'est pas stocké 
	 * dans le fichier XML.</li>
	 * </ul>
	 * - retourne false si pObject == null.<br/>
	 * - retourne false si <code>this.fichierXML</code> == null.<br/>
	 * - retourne false si <code>this.fichierXML</code> n'existe pas.<br/>
	 * - retourne false si <code>this.fichierXML</code> n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pObject : T : objet métier à détruire.<br/>
	 * @param pFile : java.io.File : fichier XML.<br/> 
	 * 
	 * @return : boolean : 
	 * true si l'objet métier a été détruit.<br/>
	 * 
	 * @throws IOException 
	 * @throws JAXBException 
	 */
	public final boolean delete(
			final T pObject
				, final File pFile) 
						throws JAXBException, IOException {
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		/* retourne false si pFile == null. */
		if (pFile == null) {
			return false;
		}
		
		/* retourne false si pFile n'existe pas. */
		if (!pFile.exists()) {
			return false;
		}
		
		/* retourne false si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return false;
		}
		
		/* récupère la liste des objets métier stockés 
		 * dans le fichier XML. */
		final List<T> listeObjetsMetier 
			= this.findAll(pFile);
		
		boolean resultat = false;
		
		if (listeObjetsMetier != null) {
			
			final List<T> listeModifiee 
				= new ArrayList<T>();
			
			for (final T objetMetier : listeObjetsMetier) {
				
				/* retire pObject de la liste si il existe. */
				if (!objetMetier.equals(pObject)) {
					listeModifiee.add(objetMetier);
				} else {
					resultat = true;
				}
			}
			
			/* enregistre la liste modifiée sur disque. */
			this.enregistrer(listeModifiee, pFile);
			
			return resultat;
			
		}

		return false;
		
	} // Fin de delete(...)._______________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void deleteById(final ID pIndex) 
						throws JAXBException, IOException {
		
		this.deleteById(pIndex, this.fichierXML);
		
	} // Fin de deleteById(...).___________________________________________
	
	
	
	/**
	 * <b>retire l'objet métier d'index (0-based) pIndex 
	 * dans le fichier XML 
	 * pFile</b>.<br/>
	 * <ul>
	 * <li>ne fait rien si pIndex est en dehors 
	 * de la liste des objets métier.</li>
	 * <li>récupère la liste des objets métier stockés 
	 * dans le fichier XML.</li>
	 * <li>récupère l'objet métier à retirer par index.</li>
	 * <li>ne fait rien si l'objet métier d'index pIndex 
	 * n'existe pas.</li>
	 * <li>retire l'objet de la liste si il existe.</li>
	 * <li>enregistre la liste modifiée sur disque.</li>
	 * </ul>
	 * - ne fait rien si pIndex == 0.<br/>
	 * - ne fait rien si pFile == null.<br/>
	 * - ne fait rien si pFile n'existe pas.<br/>
	 * - ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pIndex : ID : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	public abstract void deleteById(
			ID pIndex
				, File pFile) 
						throws JAXBException, IOException;
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean deleteByIdBoolean(
								final ID pIndex) 
									throws JAXBException, IOException {
		
		return this.deleteByIdBoolean(pIndex, this.fichierXML);
		
	} // Fin de deleteByIdBoolean(...).____________________________________
	
	
	
	/**
	 * <b>retire l'objet métier d'index (0-based) pIndex 
	 * dans le fichier XML 
	 * pFile</b>.<br/>
	 * retourne true si le retrait à bien été effectué.<br/>
	 * <ul>
	 * <li>retourne false si pIndex est en dehors 
	 * de la liste des objets métier.</li>
	 * <li>récupère la liste des objets métier stockés 
	 * dans le fichier XML.</li>
	 * <li>récupère l'objet métier à retirer par index.</li>
	 * <li>retourne false si l'objet métier d'index pIndex 
	 * n'existe pas.</li>
	 * <li>retire l'objet de la liste si il existe.</li>
	 * <li>enregistre la liste modifiée sur disque.</li>
	 * </ul>
	 * - retourne false si pIndex == 0.<br/>
	 * - retourne false si pFile == null.<br/>
	 * - retourne false si pFile n'existe pas.<br/>
	 * - retourne false si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pIndex : ID : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @return boolean : true si le retrait à bien été effectué.<br/>
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	public abstract boolean deleteByIdBoolean(
			ID pIndex
				, File pFile) 
						throws JAXBException, IOException;


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void deleteAll() 
					throws JAXBException, IOException {
		
		this.deleteAll(this.fichierXML);
		
	} // Fin de deleteAll()._______________________________________________
	
	
	
	/**
	 * <b>retire tous les objets métier dans le stockage</b>.<br/>
	 * <ul>
	 * <li>récupère la liste des objets métier stockés 
	 * dans le fichier XML pFile.</li>
	 * <li>retire chaque objet métier.</li>
	 * </ul>
	 * - ne fait rien si pFile == null.<br/>
	 * - ne fait rien si pFile n'existe pas.<br/>
	 * - ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	public final void deleteAll(
			final File pFile) 
					throws JAXBException, IOException {
		
		/* ne fait rien si pFile == null. */
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

		/* récupère la liste des objets métier stockés 
		 * dans le fichier XML. */
		final List<T> listeObjetsMetier 
			= this.findAll(pFile);
		
		if (listeObjetsMetier != null) {

			for (final T objet : listeObjetsMetier) {
				/* retire chaque objet métier. */
				listeObjetsMetier.remove(objet);
			}
		}
		
	} // Fin de deleteAll(...).____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean deleteAllBoolean() 
					throws JAXBException, IOException {
		
		return this.deleteAllBoolean(this.fichierXML);
		
	} // Fin de deleteAllBoolean().________________________________________
	
	
	
	/**
	 * <b>retire tous les objets métier dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 * <ul>
	 * <li>récupère la liste des objets métier stockés 
	 * dans le fichier XML pFile.</li>
	 * <li>retire chaque objet métier.</li>
	 * </ul>
	 * - retourne false si pFile == null.<br/>
	 * - retourne false si pFile n'existe pas.<br/>
	 * - retourne false si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 * 
	 * @param pFile : java.io.File : fichier XML.<br/>
	 *
	 * @return : boolean : 
	 * true si le retrait a bien été effectué.<br/>
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	public final boolean deleteAllBoolean(
			final File pFile) 
					throws JAXBException, IOException {
		
		/* retourne false si pFile == null. */
		if (pFile == null) {
			return false;
		}
		
		/* retourne false si pFile n'existe pas. */
		if (!pFile.exists()) {
			return false;
		}
		
		/* retourne false si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return false;
		}

		/* récupère la liste des objets métier stockés 
		 * dans le fichier XML. */
		final List<T> listeObjetsMetier 
			= this.findAll(pFile);
		
		if (listeObjetsMetier != null) {

			for (final T objet : listeObjetsMetier) {
				/* retire chaque objet métier. */
				listeObjetsMetier.remove(objet);
			}
			
			return true;
		}
		
		return false;
		
	} // Fin de deleteAllBoolean(...)._____________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void deleteIterable(
			final Iterable<T> pList) 
						throws JAXBException, IOException {
		
		this.deleteIterable(pList, this.fichierXML);
		
	} // Fin de deleteIterable().__________________________________________
	
	
	
	/**
	 * <b>retire tous les objets de l'itérable pList déjà persistés 
	 * dans le stockage</b>.<br/>
	 * <ul>
	 * <li>ne fait rien et continue le processus de retrait 
	 * si un objet de l'itérable n'est pas persisté.</li>
	 * <li>récupère la liste des objets métier stockés</li>
	 * <li>retire chaque objet de l'itérable de la liste.</li>
	 * </ul>
	 * - ne fait rien si pList == null.<br/>
	 * - ne fait rien si pFile == null.<br/>
	 * - ne fait rien si pFile n'existe pas.<br/>
	 * - ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pList : Iterable&lt;T&gt; : 
	 * itérable d'objets métier à retirer dans le stockage.<br/>
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	public final void deleteIterable(
			final Iterable<T> pList
				, final File pFile) 
						throws JAXBException, IOException {
		
		/* ne fait rien si pList == null. */
		if (pList == null) {
			return;
		}
		
		/* ne fait rien si pFile == null. */
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

		/* récupère la liste des objets métier stockés 
		 * dans le fichier XML. */
		final List<T> listeObjetsMetier 
			= this.findAll(pFile);
		
		if (listeObjetsMetier != null) {
			
			/* retire chaque objet de l'itérable de la liste. */
			for (final T objetARetirer : pList) {
				listeObjetsMetier.remove(objetARetirer);
			}
		}
		
	} // Fin de deleteIterable(...)._______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean deleteIterableBoolean(
			final Iterable<T> pList) 
						throws JAXBException, IOException {
		
		return this.deleteIterableBoolean(pList, this.fichierXML);
		
	} // Fin de deleteIterableBoolean().___________________________________
	
	
	
	/**
	 * <b>retire tous les objets de l'itérable pList déjà persistés 
	 * dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 * <ul>
	 * <li>ne fait rien et continue le processus de retrait 
	 * si un objet de l'itérable n'est pas persisté.</li>
	 * <li>récupère la liste des objets métier stockés</li>
	 * <li>retire chaque objet de l'itérable de la liste.</li>
	 * <li>retourne true dès qu'un élément est retiré.</li>
	 * </ul>
	 * - retourne false si pList == null.<br/>
	 * - retourne false si pFile == null.<br/>
	 * - retourne false si pFile n'existe pas.<br/>
	 * - retourne false si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pList : Iterable&lt;IMotif&gt; : 
	 * itérable d'objets à retirer du stockage.<br/>
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @return : boolean : true si le retrait a bien été effectué.<br/>
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	public final boolean deleteIterableBoolean(
			final Iterable<T> pList
				, final File pFile) 
						throws JAXBException, IOException {
		
		/* retourne false si pList == null. */
		if (pList == null) {
			return false;
		}
		
		/* retourne false si pFile == null. */
		if (pFile == null) {
			return false;
		}
		
		/* retourne false si pFile n'existe pas. */
		if (!pFile.exists()) {
			return false;
		}
		
		/* retourne false si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return false;
		}

		/* récupère la liste des objets métier stockés 
		 * dans le fichier XML. */
		final List<T> listeObjetsMetier 
			= this.findAll(pFile);
		
		boolean resultat = false;
		
		if (listeObjetsMetier != null) {
			
			/* retire chaque objet de l'itérable de la liste. */
			for (final T objetARetirer : pList) {
				
				/* retourne true dès qu'un élément est retiré. */
				if (listeObjetsMetier.remove(objetARetirer) 
						&& !resultat) {
					resultat = true;
				}
			}
		}

		return resultat;
		
	} // Fin de deleteIterableBoolean(...).________________________________
	

	
	/* TOOLS *************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean exists(
			final T pObject) 
						throws JAXBException, IOException {
		
		return this.exists(pObject, this.fichierXML);
		
	} // Fin de exists(...)._______________________________________________
	
	
	
	/**
	 * <b>retourne true si l'objet métier pObject 
	 * existe dans le fichier XML pFile</b>.<br/>
	 * <ul>
	 * <li>récupère la liste des objets métier stockés 
	 * dans le fichier XML.</li>
	 * <li>retourne false si la liste ne contient pas 
	 * l'objet métier.</li>
	 * <li>retourne true si la liste contient l'objet métier.</li>
	 * </ul>
	 * - retourne false si pObject == null.<br/>
	 * - retourne false si pFile == null.<br/>
	 * - retourne false si pFile n'existe pas.<br/>
	 * - retourne false si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pObject : T : objet métier à rechercher.<br/>
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @return boolean : 
	 * true si l'objet métier existe dans le fichier XML.<br/>
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	public final boolean exists(
			final T pObject
				, final File pFile) 
						throws JAXBException, IOException {
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		/* retourne false si pFile == null. */
		if (pFile == null) {
			return false;
		}
		
		/* retourne false si pFile n'existe pas. */
		if (!pFile.exists()) {
			return false;
		}
		
		/* retourne false si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return false;
		}
		
		/* récupère la liste des objets métier stockés 
		 * dans le fichier XML. */
		final List<T> listeObjetsMetier 
			= this.findAll(pFile);
		
		boolean resultat = false;
		
		if (listeObjetsMetier != null) {
			
			for (final T objetMetier : listeObjetsMetier) {
				
				/* retourne false si la liste ne contient 
				 * pas l'objet métier. */
				if (objetMetier.equals(pObject)) {
					
					/* retourne true si la liste 
					 * contient l'objet métier. */
					resultat = true;
				}
			}
		}
		
		return resultat;
		
	} // Fin de exists(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean exists(
			final ID pIndex) 
					throws JAXBException, IOException {
		return false;
	}


	
	/**
	 * <b>retourne true si l'objet métier pObject 
	 * d'identifiant ou d'index (0-based) pIndex 
	 * existe dans le stockage</b>.<br/>
	 *
	 * @param pIndex : ID : 
	 * identifiant ou index (0-based) de l'objet métier à 
	 * trouver dans le stockage.<br/>
	 * @param pFile : java.io.File : fichier XML.<br/>
	 * 
	 * @return boolean : 
	 * true si l'objet métier existe dans le stockage.<br/>
	 *
	 * @throws JAXBException
	 * @throws IOException : boolean :  .<br/>
	 */
	public abstract boolean exists(
			ID pIndex
				, File pFile) 
						throws JAXBException, IOException;
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Long count() 
			throws IOException, JAXBException {
		return this.count(this.fichierXML);
	} // Fin de count().___________________________________________________
	

	
	/**
	 * <b>retourne le nombre d'objets métier</b> 
	 * stockés dans le fichier XML pFile.<br/>
	 * <ul>
	 * <li>récupère le liste des objets métier stockés 
	 * dans le fichier XML.</li>
	 * <li>retourne le nombre d'éléments de la liste 
	 * sous forme de Long.</li>
	 * </ul>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile n'existe pas.<br/>
	 * - retourne null si pFile n'est pas un fichier simple.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier XML.<br/>
	 * 
	 * @return : Long : 
	 * nombre d'enregistrements dans le stockage.<br/>
	 * 
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public final Long count(
			final File pFile) 
					throws IOException, JAXBException {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne null si pFile n'existe pas. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* retourne null si pFile n'est pas un fichier simple. */
		if (!pFile.isFile()) {
			return null;
		}
		
		/* récupère le liste des objets métier 
		 * stockés dans le fichier XML. */
		final List<T> liste = this.findAll(pFile);
		
		Long resultat = null;
		
		if (liste != null) {
			
			/* retourne le nombre d'éléments de la liste 
			 * sous forme de Long. */
			resultat = Long.valueOf(liste.size());
			
		}
				
		return resultat;
		
	} // Fin de ID count(...).___________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void ecrireStockageDansConsole() 
					throws JAXBException, IOException {
		
		this.ecrireListeObjetsMetierXMLConsole();
		
	} // Fin de ecrireStockageDansConsole()._______________________________
	
	
	
	/**
	 * <b>écrit à la console le contenu du fichier XML 
	 * <code>this.fichierXML</code></b>.<br/>
	 * <ul>
	 * <li>utilise <code>marshaller.marshal(
	 * pTableEntitiesJAXB, System.out)</code>.</li>
	 * </ul>
	 * - ne fait rien si <code>this.fichierXML</code> == null.<br/>
	 * - ne fait rien si <code>this.fichierXML</code> n'existe pas.<br/>
	 * - ne fait rien si <code>this.fichierXML</code> n'est pas un fichier simple.<br/>
	 * - ne fait rien si il est impossible de récupérer 
	 * la liste d'enregistrements dans <code>this.fichierXML</code>.<br/>
	 * <br/>
	 *
	 * @throws JAXBException
	 * @throws IOException
	 */
	@Override
	public final void ecrireListeObjetsMetierXMLConsole() 
					throws JAXBException, IOException {
		
		this.ecrireListeObjetsMetierXMLConsole(this.fichierXML);
		
	} // Fin de ecrireListeObjetsMetierXMLConsole()._______________________
	
	

	/**
	 * <b>écrit à la console le contenu du fichier XML pFile</b>.<br/>
	 * <ul>
	 * <li>utilise <code>marshaller.marshal(
	 * pTableEntitiesJAXB, System.out)</code>.</li>
	 * </ul>
	 * - ne fait rien si pFile == null.<br/>
	 * - ne fait rien si pFile n'existe pas.<br/>
	 * - ne fait rien si pFile n'est pas un fichier simple.<br/>
	 * - ne fait rien si il est impossible de récupérer 
	 * la liste d'enregistrements dans pFile.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier XML.<br/>
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	@Override
	public final void ecrireListeObjetsMetierXMLConsole(
			final File pFile) 
					throws JAXBException, IOException {
		
		/* ne fait rien si pFile == null. */
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
		
		final List<T> liste = this.findAll(pFile);
		
		/* ne fait rien si il est impossible de récupérer 
		 * la liste d'enregistrements dans pFile.*/
		if (liste != null) {
			this.ecrireListeObjetsMetierXMLConsole(liste);
		}
		
	} // Fin de ecrireListeObjetsMetierXMLConsole(...).____________________
	
	
	
	/**
	 * Affiche à la console la liste de MODEL 
	 *(objets métier) pList sous forme de fichier XML.<br/>
	 * <ul>
	 * <li>ne fait rien si pList == null.</li>
	 * </ul>
	 *
	 * @param pList : List&lt;T&gt;
	 * 
	 * @throws JAXBException
	 */
	@Override
	public abstract void ecrireListeObjetsMetierXMLConsole(
			List<T> pList) throws JAXBException;
	


	/**
	 * method getFichierXML() :<br/>
	 * Getter du fichier XML dans lequel écrire les entities JAXB.<br/>
	 * <br/>
	 *
	 * @return fichierXML : File.<br/>
	 */
	@Override
	public File getFichierXML() {
		return this.fichierXML;
	} // Fin de getFichierXML().___________________________________________


	
	/**
	* method setFichierXML(
	* File pFichierXML) :<br/>
	* Setter du fichier XML dans lequel écrire les entities JAXB.<br/>
	* <br/>
	*
	* @param pFichierXML : File : valeur à passer à fichierXML.<br/>
	*/
	@Override
	public void setFichierXML(
			final File pFichierXML) {
		this.fichierXML = pFichierXML;
	} // Fin de setFichierXML(...).________________________________________
	

	
	
} // FIN DE LA CLASSE AbstractDaoGenericJAXB.--------------------------------
