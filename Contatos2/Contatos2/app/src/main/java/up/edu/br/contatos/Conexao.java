package up.edu.br.contatos;

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
        String statement = "create table contato ("+
                "id integer primary key autoincrement," +
                "nome varchar (255)," +
                "tipo varchar(50)," +
                "telefone varchar(20), " +
                "imagem blob" +
                ")";

        sqLiteDatabase.execSQL(statement);
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
