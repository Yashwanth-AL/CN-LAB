// package Lab7;

import java.math.BigInteger;
import java.util.*;

class RSA {
    private BigInteger prk; // Private key (d)
    private BigInteger puk; // Public key (e)
    private BigInteger mod; // Modulus (n)

    // Generate RSA keys
    void getKeys(int bitlen) {
        Random r = new Random();
        
        // Generate two large prime numbers p and q
        BigInteger p = BigInteger.probablePrime(bitlen, r);
        BigInteger q = BigInteger.probablePrime(bitlen, r);
        
        // Calculate modulus n = p * q
        mod = p.multiply(q);
        
        // Calculate Euler's totient function phi(n) = (p-1) * (q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        
        // Choose public key e such that 1 < e < phi(n) and gcd(e, phi(n)) = 1
        puk = BigInteger.probablePrime(bitlen / 2, r);
        while (!phi.gcd(puk).equals(BigInteger.ONE) || puk.compareTo(phi) >= 0) {
            puk = BigInteger.probablePrime(bitlen / 2, r);
        }
        
        // Calculate private key d as the modular inverse of e modulo phi(n)
        prk = puk.modInverse(phi);
        
        // System.out.println("Public Key: (e = " + puk + ", n = " + mod + ")");
        // System.out.println("Private Key: (d = " + prk + ", n = " + mod + ")");
    }

    // Encrypt a message
    BigInteger encrypt(BigInteger message) {
        return message.modPow(puk, mod);
    }

    // Decrypt a ciphertext
    BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(prk, mod);
    }
}

class Main {
    public static void main(String[] args) {
        RSA rsa = new RSA();
        
        // Generate 512-bit RSA keys
        rsa.getKeys(512);
        
        // Read input from the user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the message to be encrypted: ");
        String inputMessage = sc.nextLine();
        
        // Convert the message to a BigInteger
        BigInteger message = new BigInteger(inputMessage.getBytes());
        
        // Encrypt the message
        BigInteger ciphertext = rsa.encrypt(message);
        System.out.println("Encrypted message: " + ciphertext);
        
        // Decrypt the ciphertext
        BigInteger decryptedMessage = rsa.decrypt(ciphertext);
        String originalMessage = new String(decryptedMessage.toByteArray());
        System.out.println("Decrypted message: " + originalMessage);
        
        // Close the scanner
        sc.close();
    }
}