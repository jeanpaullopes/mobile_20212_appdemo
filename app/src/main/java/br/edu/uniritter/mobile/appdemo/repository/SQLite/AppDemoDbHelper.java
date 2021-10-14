package br.edu.uniritter.mobile.appdemo.repository.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.edu.uniritter.mobile.appdemo.model.Usuario;

public class AppDemoDbHelper extends SQLiteOpenHelper {
    public static final int VERSAO_DB = 1;
    public static final String NOME_DB = "appDemo.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UsuarioContract.tabelaUsuarios.TABLE_NAME + " (" +
                    UsuarioContract.tabelaUsuarios._ID + " INTEGER PRIMARY KEY," +
                    UsuarioContract.tabelaUsuarios.COLUMN_NAME_NOME + " TEXT," +
                    UsuarioContract.tabelaUsuarios.COLUMN_NAME_EMAIL + " TEXT)";
    private static final String SQL_INSERT_USUARIOS =
            "INSERT INTO " + UsuarioContract.tabelaUsuarios.TABLE_NAME + " values " +
                    "(1, 'Jean Paul', 'jean.paul@uniritter.edu.br'),"+
                    "(2, 'tony Stark', 'tony@stark.com'), "+
                    "(3, 'Steve Rogers', 'cap@avenger.org'),"+
                    "(4, 'Natasha Romanov', 'viuva@avenger.org')";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UsuarioContract.tabelaUsuarios.TABLE_NAME;


    public AppDemoDbHelper(Context context) {
        super(context,NOME_DB, null, VERSAO_DB);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_INSERT_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //aqui faria a atualização das estruturas de acordo com as versoes

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
