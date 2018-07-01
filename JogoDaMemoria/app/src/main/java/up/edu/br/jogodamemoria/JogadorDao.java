package up.edu.br.jogodamemoria;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class JogadorDao {
    public void salvar (Jogador jogador){
        SQLiteDatabase conn = Conexao.getInstance().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", jogador.getNome());
        values.put("imagem",jogador.getImagem());

        if(jogador.getId() == null){
            conn.insert("jogador", null, values);
        }else {
            conn.update("jogador",values,"id = ?", new String[] {jogador.getId().toString()});
        }
    }

    public List<Jogador> listar(){
        SQLiteDatabase conn = Conexao.getInstance().getReadableDatabase();
        Cursor c = conn.query("jogador",new String[]{"id","nome","posicao","vitorias","derrotas","imagem"}, null, null, null, null, "nome");

        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

        if(c.moveToFirst()){
            do{
                Jogador jogador = new Jogador();
                jogador.setId(c.getInt(0));
                jogador.setNome(c.getString(1));
                jogador.setPosicao(c.getInt(2));
                jogador.setVitorias(c.getInt(3));
                jogador.setDerrotas(c.getInt(4));
                jogador.setImagem(c.getBlob(5));
                jogadores.add(jogador);
            } while (c.moveToNext());
        }
        return jogadores;
    }

    public void vitoria(Jogador jogador){
        SQLiteDatabase conn = Conexao.getInstance().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("vitorias", jogador.getVitorias());

        conn.update("jogador",values,"id = ?", new String[] {jogador.getId().toString()});
    }

    public void derrota(Jogador jogador){
        SQLiteDatabase conn = Conexao.getInstance().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("derrotas", jogador.getDerrotas());

        conn.update("jogador",values,"id = ?", new String[] {jogador.getId().toString()});
    }

    public void excluir(Jogador jogador){
        SQLiteDatabase conn = Conexao.getInstance().getWritableDatabase();
        conn.delete("jogador","id = ?", new String[] {jogador.getId().toString()});
    }
}
