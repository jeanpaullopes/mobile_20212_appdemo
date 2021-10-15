package br.edu.uniritter.mobile.appdemo.repository.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.appdemo.model.Usuario;
import br.edu.uniritter.mobile.appdemo.repository.UsuarioRepository;

public abstract class IUsuarioRepository {
    //implementação de Singleton
    protected static IUsuarioRepository repo;
    protected static Context context;

    public abstract List<Usuario> getAllUsuarios();
    public abstract Usuario getUsuario(int id);
    public  abstract void gravaUsuario(Usuario usuario);

    // recebe o context
    public static void setContext(Context contexto) {
        context = contexto;
    }

}
