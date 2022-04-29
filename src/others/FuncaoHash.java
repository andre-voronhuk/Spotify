package others;

import java.security.MessageDigest;

public class FuncaoHash {

    public static String gerarHash(String senha) {
        try {
           

            senha = senha.concat("@#Spotify$%"); //salt que sera adicionado no hash da senha
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

            byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));
            StringBuilder texto = new StringBuilder();
            for (byte b : hash) {
                texto.append(String.format("%02X", 0xFF & b));
            }
            return texto.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
