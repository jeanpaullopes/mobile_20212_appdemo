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
public class UsuarioRepository extends IUsuarioRepository {

    private static final String TAG = "UsuarioRepository";
    private List<Usuario> mockupBanco;


    // construtor private para Singleton, ou seja ninguém consegue criar o repository
    private UsuarioRepository() {
        super();
    }

    //implementação do Singleton
    public static IUsuarioRepository getInstance() {
        if (repo == null) {
            repo = new UsuarioRepository();
        }
        return repo;
    }

    public List<Usuario> getAllUsuarios(){
        if (mockupBanco == null) {
            mockupBanco = new ArrayList<>();
        }
        if(mockupBanco.isEmpty()) {
            mockupBanco.add( new Usuario(1, "Outro nome", "jean.paul@uniritter.edu.br"));
            mockupBanco.add( new Usuario(2, "tony Stark", "tony@stark.com"));
            mockupBanco.add( new Usuario(3, "Steve Rogers", "cap@avenger.org"));
            mockupBanco.add( new Usuario(4, "Natasha Romanov", "viuva@avenger.org"));
        }
        return mockupBanco;
    }
    public Usuario getUsuario(int id) {
        if (mockupBanco == null) {
            mockupBanco = new ArrayList<>();
        }
        if(mockupBanco.isEmpty()) {
            mockupBanco.add( new Usuario(1, "Outro nome", "jean.paul@uniritter.edu.br"));
            mockupBanco.add( new Usuario(2, "tony Stark", "tony@stark.com"));
            mockupBanco.add( new Usuario(3, "Steve Rogers", "cap@avenger.org"));
            mockupBanco.add( new Usuario(4, "Natasha Romanov", "viuva@avenger.org"));
        }
        Usuario ret = null;
        for(Usuario u : mockupBanco) {
            if (u.getId() == id) {
                Log.d(TAG, "getUsuario: "+u.getNome());
                ret = u;
            }
        }

        return ret;
    }

    public void gravaUsuario(Usuario usuario) {
        //mockupBanco.add(usuario);
        if (usuario.getId() <= 0) {
            //db.query("insert into ...");
        } else {
            //db.execSQL("update .....");
        }
    }

}
