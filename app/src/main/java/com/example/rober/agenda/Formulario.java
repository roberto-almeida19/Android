package com.example.rober.agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rober.agenda.dao.ContatoDAO;
import com.example.rober.agenda.modelo.Contato;

public class Formulario extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        this.helper = new FormularioHelper(this);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                Contato contato = helper.pegaContato();
                ContatoDAO contatoDAO = new ContatoDAO(this);
                contatoDAO.insereContato(contato);
                contatoDAO.close();

                Toast.makeText(Formulario.this,contato.getNome() +" foi adicionado!", Toast.LENGTH_SHORT).show();

                finish();
            break;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

}