package br.edu.uniritter.mobile.appdemo.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.appdemo.model.Usuario;
import br.edu.uniritter.mobile.appdemo.repository.SQLite.AppDemoDbHelper;
import br.edu.uniritter.mobile.appdemo.repository.SQLite.UsuarioContract;


/*

        Não esquecer de Incluir o SQLLite nas dependências do
        Gradle.app

 */
public class UsuarioRepository {

    private static final String TAG = "UsuarioRepository";
    private List<Usuario> mockupBanco;
    private static UsuarioRepository repo;
    private SQLiteDatabase db;
    private static Context context;



    private UsuarioRepository() {
        super();
        AppDemoDbHelper dbHelper = new AppDemoDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Usuario> getAllUsuarios(){
        if (mockupBanco == null) {
            mockupBanco = new ArrayList<>();
        }
        if(mockupBanco.isEmpty()) {
            mockupBanco.add( new Usuario(1, "Jean Paul", "jean.paul@uniritter.edu.br"));
            mockupBanco.add( new Usuario(2, "tony Stark", "tony@stark.com"));
            mockupBanco.add( new Usuario(3, "Steve Rogers", "cap@avenger.org"));
            mockupBanco.add( new Usuario(4, "Natasha Romanov", "viuva@avenger.org"));
        }
        return mockupBanco;
    }

    public static UsuarioRepository getInstance() {
        if (repo == null) {
            repo = new UsuarioRepository();
        }
        return repo;
    }
    public static void setContext(Context contexto) {
        context = contexto;
    }
    public Usuario getUsuario(int id) {
        Usuario ret = null;
        for(Usuario u : mockupBanco) {
            if (u.getId() == id) {
                Log.d(TAG, "getUsuario: "+u.getNome());
                ret = u;
            }
        }

        return ret;
    }

    public List<Usuario> getAllUsuariosSQLite(){
        List<Usuario> ret = new ArrayList<>();
        String[] projection = {
                BaseColumns._ID,
                UsuarioContract.tabelaUsuarios.COLUMN_NAME_NOME,
                UsuarioContract.tabelaUsuarios.COLUMN_NAME_EMAIL
        };
        String orderBy = UsuarioContract.tabelaUsuarios.COLUMN_NAME_NOME+" ASC";
        Cursor cursor = db.query(
                UsuarioContract.tabelaUsuarios.TABLE_NAME, // nome tabela
                projection, // projeção / colunas a mostrar
                null, // where
                null, // parametros do where
                null, // group by
                null, // having do group by
                orderBy); // order by
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nome = cursor.getString(
                    cursor.getColumnIndexOrThrow(UsuarioContract.tabelaUsuarios.COLUMN_NAME_NOME));
            String email = cursor.getString(2);
            ret.add( new Usuario(id, nome, email));
        }
        return ret;
    }
    public Usuario getUsuarioSQLite(int id){
        Usuario ret = null;
        String[] projection = {
                BaseColumns._ID,
                UsuarioContract.tabelaUsuarios.COLUMN_NAME_NOME,
                UsuarioContract.tabelaUsuarios.COLUMN_NAME_EMAIL
        };
        String selection = BaseColumns._ID+" = ?";
        String selectionArg[] = { ""+id };
        String orderBy = UsuarioContract.tabelaUsuarios.COLUMN_NAME_NOME+" ASC";
        Cursor cursor = db.query(
                UsuarioContract.tabelaUsuarios.TABLE_NAME, // nome tabela
                projection, // projeção / colunas a mostrar
                selection, // where
                selectionArg, // parametros do where
                null, // group by
                null, // having do group by
                orderBy); // order by
        while (cursor.moveToNext()) {
            String nome = cursor.getString(
                    cursor.getColumnIndexOrThrow(UsuarioContract.tabelaUsuarios.COLUMN_NAME_NOME));
            String email = cursor.getString(2);
            ret =  new Usuario(id, nome, email);
        }
        return ret;
    }




}
