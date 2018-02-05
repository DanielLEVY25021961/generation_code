package levy.daniel.application.util.encodeursdecodeurs;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * class EncodeurTest :<br/>
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
 * @since 16 juil. 2016
 *
 */
public class EncodeurTest {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(EncodeurTest.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR EncodeurTest() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 */
	public EncodeurTest() {
		super();
	}
	

	
	/**
	 * method encoderAvecNewString(
	 * String pString
	 * , Charset pCharsetEncodage
	 * , Charset pCharsetDecodage) :<br/>
	 * <ul>
	 * </ul>
	 * <br/>
	 *
	 * @param pString : String : 
	 * @param pCharsetEncodage : Charset : Charset utilisé pour encoder pString en tableau de byte[]
	 * @param pCharsetDecodage : Charset : Charset utilisé pour le décodage du tableau de byte[]
	 * @return : String :  .<br/>
	 */
	public final String encoderAvecNewString(
			final String pString
				, final Charset pCharsetEncodage
					, final Charset pCharsetDecodage) {
		
		final String resultat = new String(pString.getBytes(pCharsetEncodage), pCharsetDecodage);
		
		return resultat;
	}

	
	
	/**
	 * method transcoder() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pString
	 * @param pCharsetEncodage
	 * @param pCharsetDecodage
	 * @return : String :  .<br/>
	 */
	public final String transcoder(
			final String pString
				, final Charset pCharsetEncodage
					, final Charset pCharsetDecodage) {
		
		String resultat = null;
		
		try {
			
			resultat = transcoderAvecCharsetDecoder(
					pString, pCharsetEncodage, pCharsetDecodage);
			
		} catch (CharacterCodingException e) {
			
			resultat = new String(
					pString.getBytes(pCharsetEncodage), pCharsetDecodage);
			
		}
		
		return resultat;
		
	}
	
	
	
	/**
	 * method encoder(String pString, Charset pCharsetLecture, Charset pCharsetEcriture) :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pString
	 * @param pCharsetLecture
	 * @param pCharsetEcriture
	 * @return : byte[] :  .<br/>
	 */
	public final byte[] encoder(
			final String pString
				, final Charset pCharsetLecture
					, final Charset pCharsetEcriture){
        
		/* Encode la String en tableau de bytes 
		 * en utilisant le Charset de la plateforme. */
		final byte[] arrayByte = pString.getBytes();
		
		/* Met le tableau de bytes à encoder dans un ByteBuffer. */
        final ByteBuffer inputByteBuffer = ByteBuffer.wrap(arrayByte);
        
        /* Décode avec pCharsetLecture le ByteBuffer 
         * encapsulant le tableau de byte à encoder 
         * et stocke le résultat dans un CharBuffer.*/
        final CharBuffer dataCharBuffer 
        	= pCharsetLecture.decode(inputByteBuffer);
        
        /* Encode avec pCharsetEcriture le CharBuffer 
         * contenant le ByteBuffer décodé avec pCharsetLecture 
         * et stocke le résultat dans un ByteBuffer.*/
        final ByteBuffer outputBuffer 
        	= pCharsetEcriture.encode(dataCharBuffer);
        
        /* Transforme le ByteBuffer encodé en pCharsetEcriture 
         * en tableau de Byte. */
        final byte[] outputData = outputBuffer.array();
		
        return outputData;
        
    } // Fin de encode(
	 // byte[] pArrayByte
	 // , Charset pCharsetLecture
	 // , Charset pCharsetEcriture)._______________________________________

	
	
	/**
	 * method transcoderAvecCharsetDecoder(String pString, Charset pCharsetEncodage, Charset pCharsetDecodage) :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pString
	 * @param pCharsetLecture
	 * @param pCharsetEcriture
	 * @return : String :  .<br/>
	 * @throws CharacterCodingException 
	 */
	public final String transcoderAvecCharsetDecoder(
			final String pString
				, final Charset pCharsetLecture
					, final Charset pCharsetEcriture) 
							throws CharacterCodingException {
		
		/* Encode la String en tableau de bytes 
		 * en utilisant le Charset de la plateforme. */
		final byte[] arrayByte = pString.getBytes();
		
		/* Met le tableau de bytes à encoder dans un ByteBuffer. */
        final ByteBuffer inputByteBuffer = ByteBuffer.wrap(arrayByte);

        /* Instancie un CharsetDecoder chargé de lire le tableau de bytes 
         * tel qu'il a originellement été encodé (en pCharsetLecture). */
		final CharsetDecoder charsetDecoderLecture 
			= pCharsetLecture.newDecoder();
		
		/* Instancie un CharsetEncoder chargé 
		 * d'encoder le CharBuffer en pCharsetEcriture. */
		final CharsetEncoder charsetEncoderEcriture 
			= pCharsetEcriture.newEncoder();
		
		/* Décode avec pCharsetLecture le ByteBuffer 
         * encapsulant le tableau de byte à encoder 
         * et stocke le résultat dans un CharBuffer.*/
		final CharBuffer dataCharBuffer 
			= charsetDecoderLecture.decode(inputByteBuffer);
		
		/* Encode avec pCharsetEcriture le CharBuffer 
         * contenant le ByteBuffer décodé avec pCharsetLecture 
         * et stocke le résultat dans un ByteBuffer.*/
        final ByteBuffer outputBuffer 
		= charsetEncoderEcriture.encode(dataCharBuffer);
        
        /* Transforme le ByteBuffer encodé en pCharsetEcriture 
         * en tableau de Byte. */
        final byte[] outputData = outputBuffer.array();
        
        return new String(outputData, pCharsetEcriture);		
		
	}
	
	
	
} // FIN DE LA CLASSE EncodeurTest.------------------------------------------
