package com.todo.javiergrande.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionUtilTest {

    @Test
    void testCifradoYDescifrado() {
        String original = "Tarea secreta importante";
        byte[] cifrado = EncryptionUtil.encrypt(original);
        String descifrado = EncryptionUtil.decrypt(cifrado);

        assertEquals(original, descifrado);
    }

    @Test
    void testTextoVacio() {
        String original = "";
        byte[] cifrado = EncryptionUtil.encrypt(original);
        String descifrado = EncryptionUtil.decrypt(cifrado);

        assertEquals(original, descifrado);
    }

    @Test
    void testCaracteresEspeciales() {
        String original = "Contraseña: ¡✓@#€/&%&$%!";
        byte[] cifrado = EncryptionUtil.encrypt(original);
        String descifrado = EncryptionUtil.decrypt(cifrado);

        assertEquals(original, descifrado);
    }
}