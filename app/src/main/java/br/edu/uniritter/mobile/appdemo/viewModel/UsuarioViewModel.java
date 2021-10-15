package br.edu.uniritter.mobile.appdemo.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.edu.uniritter.mobile.appdemo.model.Usuario;
import br.edu.uniritter.mobile.appdemo.repository.SQLite.IUsuarioRepository;
import br.edu.uniritter.mobile.appdemo.repository.UsuarioRepository;
import br.edu.uniritter.mobile.appdemo.repository.UsuarioRepositorySQLite;

public class UsuarioViewModel extends ViewModel {

    private static final String TAG= "UsuarioViewModel";

    // os dados para as Views
    private MutableLiveData<List<Usuario>> users;
    private MutableLiveData<Usuario> user;
    private IUsuarioRepository repository;


    public UsuarioViewModel() {

        repository = UsuarioRepository.getInstance();
        //repository = UsuarioRepositorySQLite.getInstance();
    }
    public LiveData<List<Usuario>> getUsuarios() {
        if (users == null) {
            users = new MutableLiveData<List<Usuario>>();
            loadUsers();

        }
        return users;
    }
    public LiveData<Usuario> getUsuario(int id) {
        //user = new MutableLiveData<>(UsuarioRepository.getInstance().getUsuario(id));
        user = new MutableLiveData<>(repository.getUsuario(id));

        return user;
    }
     public void salvaUsuario(int id, String nome, String email) {
        Usuario usu = new Usuario(id, nome, email);
        UsuarioRepository.getInstance().gravaUsuario(usu);
        users.getValue().add(usu);
        users.setValue(users.getValue());
        user.setValue(usu);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.users = null;
        this.user = null;
    }

    private void loadUsers() {
        //users.postValue(UsuarioRepository.getInstance().getAllUsuarios());
        users.postValue(UsuarioRepository.getInstance().getAllUsuarios());
    }

    public void trocaNome( String novoNome) {
        Usuario u = user.getValue();
        u.setNome(novoNome);
        Log.d(TAG, "trocaNome: "+user.getValue().getNome());

        //atualizando a ref. do MutableLiveData user
        user.setValue(u); //getValue().setNome(novoNome);

        Log.d(TAG, "trocaNome: "+user.getValue().getNome());

    }
}
