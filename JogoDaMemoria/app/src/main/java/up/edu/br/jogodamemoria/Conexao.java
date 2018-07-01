package up.edu.br.jogodamemoria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aluno on 11/06/2018.
 */

public class Conexao extends SQLiteOpenHelper {

    private static Conexao conexao;

    public static Conexao getInstance(){
        return conexao;
    }

    public Conexao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        conexao = this;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String statement = "create table jogador ("+
                "id integer primary key autoincrement," +
                "nome varchar (255)," +
                "vitorias int (25)," +
                "derrotas int (25)," +
                "posicao int (25)," +
                "imagem blob" +
                ")";
        /*String startament2 = "create table partida ("+
                "id integer primary key autoincrement," +
                "Jogador1 varchar (255)," +
                "Jogador2 varchar (255)," +
                "Jogador1pontos int(2)," +
                "Jogador2pontos int(2)" +
                ")";*/

        sqLiteDatabase.execSQL(statement);
       // sqLiteDatabase.execSQL(startament2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String statement = "create table contato ("+
                " add endereco varchar(255);" +
                ")";
        //alterar versão para passar pelo update -- MainActvity onCreate
        sqLiteDatabase.execSQL(statement);
    }
}
