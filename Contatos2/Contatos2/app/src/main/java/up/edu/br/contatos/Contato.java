package up.edu.br.contatos;

import java.io.Serializable;

/**
 * Created by Aluno on 16/05/2018.
 */

public class Contato implements Serializable{
    private Integer id;
    private String nome;
    private String tipo;
    private String numero;
    private byte[] imagem;

    @Override
    public boolean equals(Object obj) {
        if (id == null || ((Contato)obj).getId() == null){
            return false;
        }
        return id.equals(((Contato)obj).getId());
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0;
    }

    public String toString(){
        return nome;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
