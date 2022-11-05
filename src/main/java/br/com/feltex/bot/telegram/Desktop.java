package br.com.feltex.bot.telegram;

public class Desktop {

    private String nomeDesktop;
    private String nomeProcessador;
    private String fabricante;
    private String frequencia;
    private Integer qntDisco;
    private Long memoriaTotal;

    public String getNomeDesktop() {
        return nomeDesktop;
    }

    public void setNomeDesktop(String nomeDesktop) {
        this.nomeDesktop = nomeDesktop;
    }

    public String getNomeProcessador() {
        return nomeProcessador;
    }

    public void setNomeProcessador(String nomeProcessador) {
        this.nomeProcessador = nomeProcessador;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public Integer getQntDisco() {
        return qntDisco;
    }

    public void setQntDisco(Integer qntDisco) {
        this.qntDisco = qntDisco;
    }

    public Long getMemoriaTotal() {
        return memoriaTotal;
    }

    public void setMemoriaTotal(Long memoriaTotal) {
        this.memoriaTotal = memoriaTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome da máquina:  ").append(nomeDesktop.toUpperCase());
        sb.append("\n\nProcessador:  ").append(nomeProcessador.toUpperCase());
        sb.append("\n\nFabricante:  ").append(fabricante.toUpperCase());
        sb.append("\n\nFrequência:  ").append(frequencia);
        sb.append("\n\nQuantidade de discos:  ").append(qntDisco);
        sb.append("\n\nMemória total:  ").append(memoriaTotal);
        return sb.toString();
    }
    
}
