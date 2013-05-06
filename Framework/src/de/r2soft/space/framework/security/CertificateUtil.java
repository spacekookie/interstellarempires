/* 
 * Copyright (c) 2013 Leander Sabel
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.r2soft.space.framework.security;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import org.apache.log4j.Logger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import de.r2soft.space.framework.util.TimeUtil;

public class CertificateUtil {

	public static int CERTIFICATE_VALIDITY = 1;  	// Years

	private final Logger log = Logger.getLogger(getClass().getName());
	private static final String BC = org.bouncycastle.jce.provider.BouncyCastleProvider.PROVIDER_NAME;

	{ // Add bouncy castle security provider to java security.
		Security.addProvider(new BouncyCastleProvider());
	}

	public void generateCertificate(String username) throws OperatorCreationException,
			NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException,
			InvalidKeySpecException, CertificateException, InvalidKeyException, SignatureException {

		X500Name name = new X500Name(username);

		// Generate RSA key pair
		AsymmetricCipherKeyPair keyPair = generateKeypair();
		PublicKey publicKey = generatePublicKey(keyPair.getPublic());
		PrivateKey privateKey = generatePrivateKey(keyPair.getPrivate(), keyPair.getPublic());

		// Generate usage time and serial number
		Date notBefore = TimeUtil.getTimeNow();
		Date notAfter = TimeUtil.getTimeThen(CERTIFICATE_VALIDITY, 0, 0, 0);
		BigInteger serial = BigInteger.valueOf(TimeUtil.getTimeNow().getTime());

		X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(name, serial, notBefore,
				notAfter, name, publicKey);
		ContentSigner sigGen = new JcaContentSignerBuilder("SHA256WithRSAEncryption").setProvider(BC)
				.build(privateKey);
		X509Certificate cert = new JcaX509CertificateConverter().setProvider(BC).getCertificate(
				certGen.build(sigGen));

		// Verify success of creation
		cert.checkValidity(new Date());
		cert.verify(cert.getPublicKey());
	}

	/**
	 * Generate a secure key pair.
	 * 
	 * @return
	 */
	private AsymmetricCipherKeyPair generateKeypair() {
		RSAKeyPairGenerator gen = new RSAKeyPairGenerator();
		gen.init(new RSAKeyGenerationParameters(BigInteger.valueOf(3), new SecureRandom(), 1024, 80));
		return gen.generateKeyPair();
	}

	/**
	 * Generate a secure public key.
	 * 
	 * @param pub
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	private PublicKey generatePublicKey(AsymmetricKeyParameter pub) throws InvalidKeySpecException,
			NoSuchAlgorithmException {
		RSAKeyParameters publicKeyParameters = (RSAKeyParameters) pub;

		// Create RSA Key
		RSAPublicKeySpec rsa = new RSAPublicKeySpec(publicKeyParameters.getModulus(),
				publicKeyParameters.getExponent());
		return KeyFactory.getInstance("RSA").generatePublic(rsa);
	}

	/**
	 * Generate an RSA private key
	 * 
	 * @param priv
	 * @param pub
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	private PrivateKey generatePrivateKey(AsymmetricKeyParameter priv, AsymmetricKeyParameter pub)
			throws InvalidKeySpecException, NoSuchAlgorithmException {

		RSAPrivateCrtKeyParameters privateKeyParameters = (RSAPrivateCrtKeyParameters) priv;
		RSAKeyParameters publicKeyParameters = (RSAKeyParameters) pub;

		RSAPrivateCrtKeySpec rsaPrivateKeySpec = new RSAPrivateCrtKeySpec(
				publicKeyParameters.getModulus(), publicKeyParameters.getExponent(),
				privateKeyParameters.getExponent(), privateKeyParameters.getP(),
				privateKeyParameters.getQ(), privateKeyParameters.getDP(), privateKeyParameters.getDQ(),
				privateKeyParameters.getQInv());

		return KeyFactory.getInstance("RSA").generatePrivate(rsaPrivateKeySpec);
	}
}
