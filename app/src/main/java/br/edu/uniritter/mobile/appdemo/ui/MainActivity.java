package br.edu.uniritter.mobile.appdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.uniritter.mobile.appdemo.R;
import br.edu.uniritter.mobile.appdemo.databinding.ActivityMainBinding;
import br.edu.uniritter.mobile.appdemo.model.Usuario;
import br.edu.uniritter.mobile.appdemo.repository.SQLite.UsuarioContract;
import br.edu.uniritter.mobile.appdemo.repository.UsuarioRepository;
import br.edu.uniritter.mobile.appdemo.viewModel.UsuarioViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UsuarioRepository.setContext(this);
        UsuarioViewModel viewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);
        //troquei para dataBinding
        //setContentView(R.layout.activity_main);
        ActivityMainBinding layout = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel.getUsuarios().observe(this,users ->{
            //aqui vamos atualizar a UI
            //exemplo para uso em recyclerView/adapter
            //adapter.addHolidayList(currencyPojos);
            //adapter.notifyDataSetChanged();
        });
        Context context = this;
        SharedPreferences prefs = context.getSharedPreferences("br.edu.uniritter.mobile.appdemo.PREFERENCES",MODE_PRIVATE);
        int id = prefs.getInt("id",1);



        viewModel.getUsuario(id).observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                layout.setUsuario(usuario);
            }
        });

        Button btn = findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.trocaNome("prof. malvado Jean Paul");

            }
        });

        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = findViewById(R.id.editTextNumber);
                salvarNoSharedPreferences(Integer.parseInt(et.getText().toString()));

            }
        });

    }

    private void salvarNoSharedPreferences(int id) {
        Context context = this;
        SharedPreferences prefs = context.getSharedPreferences("br.edu.uniritter.mobile.appdemo.PREFERENCES",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("id", id);
        //não se esquecer de gravar
        editor.commit();
    }
}