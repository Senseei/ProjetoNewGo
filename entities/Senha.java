package entities;

import exceptions.InvalidPasswordException;

public class Senha {

    private String conteudo;

    private Senha(String conteudo) {
        this.conteudo = conteudo.trim();
    }

    public Senha createPassword(String conteudo) throws InvalidPasswordException {
        validatePassword(conteudo);
        return new Senha(conteudo);
    }

    public void validatePassword(String conteudo) throws InvalidPasswordException {

        if (conteudo.length() < 10)
            throw new InvalidPasswordException("A senha é muito curta!");
        else if (conteudo.length() > 30)
            throw new InvalidPasswordException("A senha é muito longa, limite de caracteres: 30");


        boolean upperCaseLetter = false;
        boolean lowerCaseLetter = false;
        boolean number = false;
        boolean simbol = false;

        for (int i = 0, length = conteudo.length(); i < length; i++) {
            char c = conteudo.charAt(i);

            if (Character.isLetter(c) && Character.isUpperCase(c))
                upperCaseLetter = true;
            else if (Character.isLetter(c))
                lowerCaseLetter = true;
            else if (Character.isDigit(c))
                number = true;
            else
                simbol = true;
        }

        String error = "";
        if (!upperCaseLetter)
            error += "A senha precisa ter pelo menos uma letra maiuscula\n";
        else if (!lowerCaseLetter)
            error += "A senha precisa ter pelo menos uma letra minuscula!\n";
        else if (!number)
            error += "A senha precisa ter pelo menos um numero!\n";
        else if (!simbol)
            error += "A senha precisa ter pelo menos um simbolo ou caractere especial";

        if (!error.isEmpty())
            throw new InvalidPasswordException(error);
    }

    public void change(String newPassword) throws InvalidPasswordException {
        validatePassword(newPassword);
        conteudo = newPassword;
    }

    public int getStrength() {
        float strength = 75;
        strength += conteudo.length() - 10 * 1.25;

        return (int) strength;
    }

    public String getConteudo() {
        return conteudo;
    }
}