package com.endryckbarros.olxapp.model;

import com.endryckbarros.olxapp.helper.ConfuguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.List;

public class Anuncio implements Serializable {

    private String idAnuncio;
    private String estado;
    private String categoria;
    private String titulo;
    private String descricao;
    private String valor;
    private List<String> fotos;

    public Anuncio() {
        DatabaseReference anuncioRef = ConfuguracaoFirebase.getFirebase().child("Meus_Anuncios");
        setIdAnuncio(anuncioRef.push().getKey());
    }

    public void salvarAnuncio(){
        String idUsuario = ConfuguracaoFirebase.getUserId();
        DatabaseReference anuncioRef = ConfuguracaoFirebase.getFirebase().child("Meus_Anuncios");
        anuncioRef.child(idUsuario).child(getIdAnuncio()).setValue(this);
        salvarAnuncioPublico();
    }

    public void salvarAnuncioPublico(){
        DatabaseReference anuncioRef = ConfuguracaoFirebase.getFirebase().child("Anuncios");
        anuncioRef.child(getEstado()).child(getCategoria()).child(getIdAnuncio()).setValue(this);
    }

    public void removerAnuncio(){
        String idUsuario = ConfuguracaoFirebase.getUserId();
        DatabaseReference anuncioRef = ConfuguracaoFirebase.getFirebase()
                .child("Meus_Anuncios")
                .child(idUsuario)
                .child(getIdAnuncio());

        anuncioRef.removeValue();
        removerAnuncioPublico();
    }

    public void removerAnuncioPublico(){
        DatabaseReference anuncioRef = ConfuguracaoFirebase.getFirebase()
                .child("Anuncios")
                .child(getEstado())
                .child(getCategoria())
                .child(getIdAnuncio());

        anuncioRef.removeValue();
    }

    public String getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(String idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}
