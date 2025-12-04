package Logica;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncriptadorContraseña {
    public static String generarSal() {
        SecureRandom random = new SecureRandom();
        byte[] sal = new byte[16];
        random.nextBytes(sal);
        return Base64.getEncoder().encodeToString(sal);
    }

    public static String encriptarContraseña(String contraseña, String sal) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(sal.getBytes());
            byte[] bytes = md.digest(contraseña.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar contraseña", e);
        }
    }
}