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
import br.edu.uniritter.mobile.appdemo.repository.SQLite.AppDemoDBContracts;
import br.edu.uniritter.mobile.appdemo.repository.SQLite.IUsuarioRepository;


/*

        Não esquecer de Incluir o SQLLite nas dependências do
        Gradle.app

 */
public class UsuarioRepositorySQLite extends IUsuarioRepository {

    private static final String TAG = "UsuarioRepository";
    private List<Usuario> mockupBanco;

    //para o uso SQLite
    private SQLiteDatabase db;

    //implementação de Singleton
    private static IUsuarioRepository repo;

    // construtor private para Singleton, ou seja ninguém consegue criar o repository
    private UsuarioRepositorySQLite() {
        super();
        AppDemoDbHelper dbHelper = new AppDemoDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }



    //implementação do Singleton
    public static IUsuarioRepository getInstance() {
        if (repo == null) {
            repo = new UsuarioRepositorySQLite();
        }
        return repo;
    }


    public void gravaUsuario(Usuario usuario) {
        //mockupBanco.add(usuario);
        if (usuario.getId() <= 0) {
            //db.query("insert into ...");
        } else {
            //db.execSQL("update .....");
        }
    }

    public List<Usuario> getAllUsuarios(){
        List<Usuario> ret = new ArrayList<>();
        String[] projection = {
                BaseColumns._ID,
                AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_NOME,
                AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_EMAIL
        };
        String orderBy = AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_NOME+" ASC"; // " DESC";
        Cursor cursor = db.query(
                AppDemoDBContracts.tabelaUsuarios.TABLE_NAME, // nome tabela
                projection, // projeção / colunas a mostrar
                null, // where
                null, // parametros do where
                null, // group by
                null, // having do group by
                orderBy); // order by

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nome = cursor.getString(
                    cursor.getColumnIndexOrThrow(AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_NOME));
            String email = cursor.getString(2);
            ret.add( new Usuario(id, nome, email));
        }
        return ret;
    }

    public Usuario getUsuario(int id){
        Usuario ret = null;
        String[] projection = {
                BaseColumns._ID,
                AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_NOME,
                AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_EMAIL
        };
        String selection = BaseColumns._ID+" = ?";
        String selectionArg[] = { ""+id };
        String orderBy = AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_NOME+" ASC";
        Cursor cursor = db.query(
                AppDemoDBContracts.tabelaUsuarios.TABLE_NAME, // nome tabela
                projection, // projeção / colunas a mostrar
                selection, // where
                selectionArg, // parametros do where
                null, // group by
                null, // having do group by
                orderBy); // order by
        while (cursor.moveToNext()) {
            String nome = cursor.getString(
                    cursor.getColumnIndexOrThrow(AppDemoDBContracts.tabelaUsuarios.COLUMN_NAME_NOME));
            String email = cursor.getString(2);
            ret =  new Usuario(id, nome, email);
        }
        return ret;
    }




}
