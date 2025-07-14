package com.todo.javiergrande.persistence;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Clase auxiliar para encriptar mediante aes los archivos de la persitencia para mayor seguridad
 */
public class EncryptionUtil {
    // Clave segura en Base64
    private static final String CLAVE_BASE64 = "s9O5FSdHfaDwTAYMJ6idMQ==";
    private static final SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(CLAVE_BASE64), "AES");
    // IV fijo de 16 bytes
    private static final String IV = "8gudu6fv0opvtko8";
    private static final String ALGORITMO = "AES/CBC/PKCS5Padding";

    /**
     * Metodo para encriptar un texto y transformarlo en un array de bytes
     * @param textoPlano: texto a encriptar
     * @return cadena de bytes con el contenido encriptado del texto plano
     */
    public static byte[] encrypt(String textoPlano) {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(textoPlano.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Error al cifrar: " + e.getMessage(), e);
        }
    }

    /**
     * Metodo para desencriptar una array de bytes y convertirlo en texto plano. Se usa primordialmente para desencriptar los
     * archivos de la persistencia al iniciar el programa
     * @param textoCifrado: cadena de bytes que represneta el texto cifrado
     * @return String con el texto plano
     */
    public static String decrypt(byte[] textoCifrado) {
        try {
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] bytesDescifrados = cipher.doFinal(textoCifrado);
            return new String(bytesDescifrados, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error al descifrar: " + e.getMessage(), e);
        }
    }
}