/**
 * CLASSE package-info :<br/>
 * Ce package contient toutes les 
 * <b>classes source de l'application</b>.<br/>
 * Les <b>4 package source</b> situés directement sous le projet Eclipse 
 * exigés PAR CONVENTION par MAVEN (sans artefact) sont :<br/>
 * <ol>
 * <li><b>src/main/java</b> qui doit contenir toutes les 
 * <b>sources</b> (.java).</li>
 * <li><b>src/main/resources</b> qui doit contenir toutes 
 * les <b>ressource</b>s (.properties, Log4j2.xml, ...).</li>
 * <li><b>src/test/java</b> qui doit contenir toutes les <b>sources des tests JUnit</b> (xxxTest.java).</li>
 * <li><b>src/test/resources</b> qui doit contenir toutes les <b>ressources des tests JUnit</b> (jeux d'essai).</li>
 * </ol>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/repertoires_source_MAVEN.png" 
 * alt="repertoires_source_maven" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * le <b>GroupId</b> MAVEN définit les paths de la racine des 
 * sources Java <b>racineSourcesJavaPath</b> 
 * (srcMainJavaPath + groupIdPathRelatif) et 
 * de la racine des tests JUnit <b>racineTestsJavaPath</b> 
 * (srcTestJavaPath + groupIdPathRelatif).<br/>
 * <br/>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/groupId_sous_src_main_java.png" 
 * alt="racine des sources et des tests" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * Les 4 <b>couches applicatives</b> d'une application 
 * respectant Model-Vues-Controllers (MVC) sont :
 * <ul>
 * <li><b>model</b> qui contient toute la logique métier 
 * ne devant dépendre d'aucune technologie.</li>
 * <li><b>vues</b> qui contient toutes les vues 
 * (Swing, Javafx, JSP, Thymeleaf, ...).</li>
 * <li><b>controllers</b> qui contient tous 
 * les controllers (Observer, Servlet, ...) interceptant les vues.</li>
 * <li><b>apptechnic</b> qui contient tout le code d'infrastructure 
 * indépendant de la logique métier (Exceptions, managers, ...).</li>
 * </ul>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/couches_applicatives.png" 
 * alt="couches applicatives" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * COUCHE MODEL :
 * </span></b>
 * </p>
 * la couche model contient toute la <b>logique métier</b> de l'application.<br/>
 * la couche model est découpée en 5 sous-couches pour se conformer à 
 * une <b>architecture n-tiers</b> : <br/>
 * <ul>
 * <li><b>services</b> qui contient les <i>points d'entrée</i> (façades)
 *  vers la logique métier.<br/> 
 *  Les SERVICES sont appelés par les CONTROLLERS.<br/>
 *  les SERVICES dépendent éventuellement de l'utilisation de frameworks 
 *  (annotations pour SPRING ou JPA par exemple).</li>
 * <li><b>dto</b> qui contient les Data Transfer Object (DTO). 
 * Les DTO permettent de capturer le contenu d'une VUE.<br/> 
 * Ils sont <i>transverses</i> car autorisés à voyager 
 * dans toutes les couches.<br/>
 * Les DTO sont de purs objets Java qui ne doivent pas 
 * dépendre de la technologie utilisée (Web, standalone, ..).</li>
 * <li><b>metier</b> qui contient les OBJETS METIER 
 * aussi appelés OBJETS DU DOMAINE (Compte, User, Eleve, ...).<br/> 
 * Ils sont <i>transverses</i> car autorisés à voyager 
 * dans toutes les couches.<br/>
 * Les OBJETS METIER sont de purs objets Java qui ne doivent pas 
 * dépendre de la technologie utilisée (Web, standalone, ..).
 * </li>
 * <li><b>persistence</b> qui contient les <b>entities</b> 
 * et les Data Access Objects (<b>DAO</b>).<br/>
 * les ENTITIES dépendent des frameworks utilisés (JPA, JAXB, ...).<br/>
 * les DAO dépendent des frameworks utilisés 
 * (SPRING et JPA, JPA seul, JAXB, ...).<br/>
 * La couche PERSISTENCE est chargée de converser avec le 
 * <b>stockage des données</b>.
 * </li>
 * <li><b>utilitaires</b> qui contient les utilitaires utilisables 
 * par toute l'application.</li>
 * </ul>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/couche_model.png" 
 * alt="couche MODEL" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>dto</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/sous-couche_DTO.png" 
 * alt="sous-couche DTO" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>metier</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/sous-couche_metier.png" 
 * alt="sous-couche METIER" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>persistence</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/sous-couche_persistence.png" 
 * alt="sous-couche PERSISTENCE" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>services</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/sous-couche_services.png" 
 * alt="sous-couche SERVICES" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>utilitaires</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/sous-couche_utilitaires.png" 
 * alt="sous-couche UTILITAIRES" border="1" align="center" />
 * </div>
 * 
 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * COUCHE VUES :
 * </span></b>
 * </p>
 * <div>
 * <p>
 * La couche vue est <b>sensible à la technologie (desktop ou web)</b>
 * </p>
 * <p>On peut avoir des JSP, des Thymeleaf, ... en mode web MVC, 
 * et des Vues Swing ou Javafx en mode standalone (desktop).</p>
 * <p>On a donc deux sous-couches : 
 * <ul>
 * <li><b>desktop</b></li>
 * <li><b>web</b></li>
 * </ul>
 * </p>
 * </div>
 * 
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/couche_vues.png" 
 * alt="couche VUES" border="1" align="center" />
 * </div>

 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * COUCHE CONTROLLERS :
 * </span></b>
 * </p>
 * <div>
 * <p>
 * La couche controllers est <b>sensible à la technologie (desktop ou web)</b>
 * </p>
 * <p>On peut avoir des Servlets, Controllers annotés ... en mode web MVC, 
 * et des Observable en mode standalone (desktop).</p>
 * <p>On a donc deux sous-couches : 
 * <ul>
 * <li><b>desktop</b></li>
 * <li><b>web</b></li>
 * </ul>
 * </p>
 * </div>
 * 
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/couche_controllers.png" 
 * alt="couche CONTROLLERS" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * COUCHE APPTECHNIC :
 * </span></b>
 * </p>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/couche_apptechnic.png" 
 * alt="couche APPTECHNIC" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * REPERTOIRES EXTERNES :
 * </span></b>
 * </p>
 * <div>
 * <img src="../../../../../../javadoc/images/arboresceurprojet/repertoires_externes.png" 
 * alt="REPERTOIRES EXTERNES" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * 
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
 * @since 16 nov. 2018
 *
 */
package levy.daniel.application;
