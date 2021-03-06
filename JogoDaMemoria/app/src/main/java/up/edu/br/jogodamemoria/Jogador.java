package up.edu.br.jogodamemoria;

import java.io.Serializable;

public class Jogador implements Serializable{

    private Integer id;
    private String nome;
    private int posicao;
    private int vitorias;
    private int derrotas;
    private byte[] imagem;

    public String toString(){
        return nome;
    }

    @Override
    public int hashCode() {
        return id != null? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (id == null || ((Jogador)obj).getId() == null){
            return false;
        }
        return id.equals(((Jogador)obj).getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }


}
