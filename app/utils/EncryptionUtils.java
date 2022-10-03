package utils;

import play.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

/**
 * Created by victordasilva on 12/2/18.
 */
public abstract class EncryptionUtils {
	protected final static String deskey = "3#s<KT;9mB@";
	protected final static String desedekey = "&;k(Qs`Mo8{g;JUb!N44QWH+";
	private static String charsetName = "UTF8";
	private static String DESAlgorithm = "DES";
	private static String DESedeAlgorithm = "DESede";

	/**
	 * Return the DES algorithm key
	 *
	 * @return a String with the DES algorithm key
	 */
	public static String getDesKey() {
		return deskey;
	}

	/**
	 * Return the Triple-DES algorithm key
	 *
	 * @return a String with the Triple-DES algorithm key
	 */
	public static String getDesedeKey() {
		return desedekey;
	}

	/**
	 * This method is used to encrypt a String using DES algorithm with the key defined internally
	 *
	 * @param data the String to encrypt
	 * @return an encrypted String
	 * @see #deskey
	 */
	public static String encryptDES(String data) {
		if (data == null) {
			return null;
		}
		try {
			DESKeySpec desKeySpec = new DESKeySpec(deskey.getBytes(charsetName));
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DESAlgorithm);
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			byte[] dataBytes = data.getBytes(charsetName);
			Cipher cipher = Cipher.getInstance(DESAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] dataBytesEncrypted = cipher.doFinal(dataBytes);
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(dataBytesEncrypted);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method is used to decrypt a String encrypted with DES algorithm to plain text using the key defined internally
	 *
	 * @param data the encrypted String
	 * @return a plain String
	 * @see #deskey
	 */
	public static String decryptDES(String data) throws Exception {
		if (data == null) {
			return null;
		}
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] dataBytes = decoder.decodeBuffer(data);
			DESKeySpec desKeySpec = new DESKeySpec(deskey.getBytes(charsetName));
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DESAlgorithm);
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			Cipher cipher = Cipher.getInstance(DESAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] dataBytesDecrypted = (cipher.doFinal(dataBytes));
			return new String(dataBytesDecrypted);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method is used to encrypt a String using Triple-DES algorithm with the key defined internally
	 *
	 * @param data the String to encrypt
	 * @return an encrypted String
	 * @see #desedekey
	 */
	public static String encryptDESede(String data) {
		if (data == null) {
			return null;
		}
		try {
			DESedeKeySpec desKeySpec = new DESedeKeySpec(desedekey.getBytes(charsetName));
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DESedeAlgorithm);
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			byte[] dataBytes = data.getBytes(charsetName);
			Cipher cipher = Cipher.getInstance(DESedeAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] dataBytesEncrypted = cipher.doFinal(dataBytes);
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(dataBytesEncrypted);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method is used to decrypt a String encrypted with Triple-DES algorithm to plain text using the key defined internally
	 *
	 * @param data the encrypted String
	 * @return a plain String
	 * @see #desedekey
	 */
	public static String decryptDESede(String data) throws Exception {
		if (data == null) {
			return null;
		}
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] dataBytes = decoder.decodeBuffer(data);
			DESedeKeySpec desKeySpec = new DESedeKeySpec(desedekey.getBytes(charsetName));
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DESedeAlgorithm);
			SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
			Cipher cipher = Cipher.getInstance(DESedeAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] dataBytesDecrypted = (cipher.doFinal(dataBytes));
			return new String(dataBytesDecrypted);
		} catch (Exception e) {
			Logger.debug(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * This method checks if a string is 3DES encrypted
	 *
	 * @param data the String to check
	 * @return true if the passed string is 3DES encrypted, false otherwise
	 */
	public static boolean isDESedeEncrypted(String data) {
		try {
			if (data.length() < 4)
				return false;
			decryptDESede(data);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * This method checks if a string is DES encrypted
	 *
	 * @param data the String to check
	 * @return true if the passed string is DES encrypted, false otherwise
	 */
	public static boolean isDESEncrypted(String data) {
		try {
			if (data.length() < 4)
				return false;
			decryptDES(data);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.out.println("EncryptionUtils uses:");
			System.out.println("   encrypt {string}");
			System.out.println("   decrypt {string}");
			return;
		}
		if (args[0].equalsIgnoreCase("encrypt"))
			System.out.println(EncryptionUtils.encryptDESede(args[1]));
		if (args[0].equalsIgnoreCase("decrypt"))
			System.out.println(EncryptionUtils.decryptDESede(args[1]));
	}
}

