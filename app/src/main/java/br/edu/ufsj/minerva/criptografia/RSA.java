package br.edu.ufsj.minerva.criptografia;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
	
	private PrivateKey privatekey;
	private PublicKey publickey;

	
	public void GenerateKeys() throws NoSuchAlgorithmException {
		
		KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");
		gerador.initialize(512);
		KeyPair keys = gerador.generateKeyPair();
		privatekey = keys.getPrivate();
		publickey = keys.getPublic();
		
		
	}
	
	public byte[] encrypt(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE,publickey);
		
		return cipher.doFinal(message.getBytes());
		
	}
	public byte[] decrypt(byte[] encrypt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE,privatekey);
		
		return cipher.doFinal(encrypt);
		
	}
	
	public PrivateKey getPrivateKey() {
		return privatekey;
	}
	
	public PublicKey getPublicKey() {
		return publickey;
	}
	
	public void setPublicKey(PublicKey chavePublica) {
		publickey = chavePublica;
	}
	
	public void setPrivateKey(PrivateKey chavePrivada) {
		privatekey = chavePrivada;
	}
	
	

	
	

}
