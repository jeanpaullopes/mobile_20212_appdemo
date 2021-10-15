package br.edu.uniritter.mobile.appdemo.repository.SQLite;

import android.provider.BaseColumns;

public final class AppDemoDBContracts {

    //deixe o construtor private por segurança

    private AppDemoDBContracts() {

    }

    //classe de definição das tabelas
    public static class tabelaUsuarios implements BaseColumns {
        public static final String TABLE_NAME = "usuarios";
        //public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_EMAIL = "email";
    }
}
