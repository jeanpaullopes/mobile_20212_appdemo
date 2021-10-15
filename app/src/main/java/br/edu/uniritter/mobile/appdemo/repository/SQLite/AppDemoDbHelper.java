package br.edu.uniritter.mobile.appdemo.repository.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDemoDbHelper extends SQLiteOpenHelper {
    public static final int VERSAO_DB = 5;
    public static final String NOME_DB = "appDemo.db";

    private static final String SQL_CREATE_USUARIOS =
            "CREATE TABLE " + AppDemoDBContracts.tabelaUsuarios.TABLE_NAME + " (" +
                    AppDemoDBContracts.tabelaUsuarios._ID + " INTEGER PRIMARY KEY," +
                    AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_NOME + " TEXT," +
                    AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_EMAIL + " TEXT)";

    private static final String SQL_INSERT_USUARIOS =
            "INSERT INTO " + AppDemoDBContracts.tabelaUsuarios.TABLE_NAME + " values " +
                    "(1, 'Jean Paul', 'jean.paul@uniritter.edu.br'),"+
                    "(2, 'tony Stark', 'tony@stark.com'), "+
                    "(3, 'Steve Rogers', 'cap@avenger.org'),"+
                    "(4, 'Natasha Romanov', 'viuva@avenger.org')";


    private static final String SQL_DELETE_USUARIOS =
            "DROP TABLE IF EXISTS " + AppDemoDBContracts.tabelaUsuarios.TABLE_NAME;


    public AppDemoDbHelper(Context context) {
        super(context,NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_USUARIOS);
        sqLiteDatabase.execSQL(SQL_INSERT_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //aqui faria a atualização das estruturas de acordo com as versoes
        switch(oldVersion) {
            case 1:
                //aplica scripts para atualizar para a versão 2
            case 2:
                //aplica scripts para atualizar para a versão 3
            case 3:
                //aplica scripts para atualizar para a versão 4
            case 4:
                //aplica scripts para atualizar para a versão 5

        }

        //neste caso ele apaga e faz tudo do zero
        sqLiteDatabase.execSQL(SQL_DELETE_USUARIOS);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
