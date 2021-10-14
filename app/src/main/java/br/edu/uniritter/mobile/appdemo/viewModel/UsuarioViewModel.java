package br.edu.uniritter.mobile.appdemo.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.edu.uniritter.mobile.appdemo.model.Usuario;
import br.edu.uniritter.mobile.appdemo.repository.UsuarioRepository;

public class UsuarioViewModel extends ViewModel {

    private static final String TAG= "UsuarioViewModel";
    private MutableLiveData<List<Usuario>> users;
    private MutableLiveData<Usuario> user;

    public LiveData<List<Usuario>> getUsuarios() {
        if (users == null) {
            users = new MutableLiveData<List<Usuario>>();
            loadUsers();
        }
        return users;
    }
    public LiveData<Usuario> getUsuario(int id) {
        //user = new MutableLiveData<>(UsuarioRepository.getInstance().getUsuario(id));
        user = new MutableLiveData<>(UsuarioRepository.getInstance().getUsuarioSQLite(id));

        return user;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        this.users = null;
    }

    private void loadUsers() {
        //users.postValue(UsuarioRepository.getInstance().getAllUsuarios());
        users.postValue(UsuarioRepository.getInstance().getAllUsuariosSQLite());
    }

    public void trocaNome( String novoNome) {
        Usuario u = user.getValue();
        u.setNome(novoNome);
        Log.d(TAG, "trocaNome: "+user.getValue().getNome());
        user.setValue(u); //getValue().setNome(novoNome);
        Log.d(TAG, "trocaNome: "+user.getValue().getNome());

    }
}
