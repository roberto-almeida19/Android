package com.example.rober.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rober.agenda.dao.ContatoDAO;
import com.example.rober.agenda.modelo.Contato;

import java.util.List;

public class ListaContatoActivity extends AppCompatActivity  {

    private ListView  listaAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno);

         listaAgenda = (ListView) findViewById(R.id.lista_alunos);
            listaAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                    Contato contato = (Contato) listaAgenda.getItemAtPosition(position);
                    Intent intentForm = new Intent(ListaContatoActivity.this, Formulario.class);
                    intentForm.putExtra("contato",contato);
                    startActivity(intentForm);
                }
            });
        Button btnNovo =  (Button) findViewById(R.id.lista_adicionar);
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaContatoActivity.this, Formulario.class);
                startActivity(intent);
            }
        });
        registerForContextMenu(listaAgenda);

    }

    private void carregaLista() {
        ContatoDAO contatoDAO = new ContatoDAO(this);
        List<Contato> contatos = contatoDAO.buscaContatos();
        contatoDAO.close();


        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_expandable_list_item_1, contatos);
        listaAgenda.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Contato contato = (Contato)  listaAgenda.getItemAtPosition(info.position);
                ContatoDAO contatoDAO = new ContatoDAO(ListaContatoActivity.this);
                contatoDAO.deleta(contato);
                carregaLista();
                contatoDAO.close();

                Toast.makeText(ListaContatoActivity.this, "Deletar "+contato.getNome(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }


}
