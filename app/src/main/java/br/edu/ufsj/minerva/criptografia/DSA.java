package br.edu.ufsj.minerva.criptografia;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class DSA {
	
	private PrivateKey priKey;
	private PublicKey pubKey;
	
	public void GenerateKeys() throws NoSuchAlgorithmException {
		
		KeyPairGenerator gerador = KeyPairGenerator.getInstance("DSA");
		SecureRandom secureRan = new SecureRandom();
		gerador.initialize(512,secureRan);  
		KeyPair keys = gerador.generateKeyPair();  
		pubKey = keys.getPublic();  
		priKey = keys.getPrivate();
		
		
	}
	
	
	public byte[] assinar(String menssage) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		
		Signature signature = Signature.getInstance("DSA");
		signature.initSign(priKey);
		signature.update(menssage.getBytes());
		byte[] assinatura = signature.sign();
		
		return assinatura;
	}
	
	public boolean verificaAssinatura(byte[] assinatura, String message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		
		Signature signature = Signature.getInstance("DSA");
		signature.initVerify(pubKey);  
		signature.update(message.getBytes());  
		       
		if (signature.verify(assinatura)) {  
		         return true;  
		} else {  
		         return false;
		}
	}
	
	public PrivateKey getPrivateKey() {
		return priKey;
	}
	
	public PublicKey getPublicKey() {
		return pubKey;
	}

}
