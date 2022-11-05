package br.com.feltex.bot.telegram;

public class Dados {
    private Double usoProcessador;
    
    public void setUsoProcessador(Double usoProcessador) {
        this.usoProcessador = usoProcessador;
    }

    @Override
    public String toString() {
        return usoProcessador.toString();
    }
}