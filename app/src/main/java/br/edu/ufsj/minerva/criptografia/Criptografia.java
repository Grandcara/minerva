package br.edu.ufsj.minerva.criptografia;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class Criptografia {

	public static void main(String[] args) throws Exception {
		
		String senha = "o iago é doido";
		
		RSA rsa = new RSA();
		rsa.GenerateKeys();
		
		PrivateKey chavePrivada = rsa.getPrivateKey();
		PublicKey chavePublica = rsa.getPublicKey();
		
		byte[] encriptado = rsa.encrypt(senha);		
		byte[] decriptado = rsa.decrypt(encriptado);
		
		//---------------------------------------------------------------------------------
		//Assinatura com ed25519 (curva eliptica)
		//chave publica
		String publickey = "D75A980182B10AB7D54BFED3C964073A0EE172F3DAA62325AF021A68F707511A";
		byte[] sk = publickey.getBytes();
		Arrays.fill(sk, (byte)0);
		byte[] pk = Ed25519.publickey(sk);
		System.out.println(pk);
		
		//menssagem
		byte[] message = "This is a secret message".getBytes();
		
		//assinatura
		byte[] signature = Ed25519.signature(message, sk, pk);
		
		//verificando a assinatura
		boolean test = Ed25519.checkvalid(signature,message,pk);
		System.out.println(test);

		

	}

}
