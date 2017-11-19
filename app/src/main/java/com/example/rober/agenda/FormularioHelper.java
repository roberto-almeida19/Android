package com.example.rober.agenda;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.rober.agenda.modelo.Contato;

/**
 * Created by rober on 19/11/2017.
 */

public class FormularioHelper {
    private EditText campoNome;
    private EditText campoEndereco;
    private EditText campoTelefone;
    private EditText campoApelido;
    private RatingBar campoNota;
    private Contato contato;


    public FormularioHelper(Formulario formulario){


     this.campoNome = (EditText) formulario.findViewById(R.id.formulario_nome);
     this.campoEndereco = (EditText) formulario.findViewById(R.id.formulario_endereco);
     this.campoTelefone = (EditText) formulario.findViewById(R.id.formulario_telefone);
     this.campoApelido = (EditText) formulario.findViewById(R.id.formulario_apelido);
     this.campoNota = (RatingBar) formulario.findViewById(R.id.formulario_nota);
     contato = new Contato();
    }


    public Contato pegaContato() {
        contato.setNome(campoNome.getText().toString());
        contato.setApelido(campoApelido.getText().toString());
        contato.setEndereco(campoEndereco.getText().toString());
        contato.setTelefone(campoTelefone.getText().toString());
        contato.setNota(Double.valueOf(campoNota.getProgress()));
    return contato;
    }

    public void preencheForm(Contato contato) {
        campoNome.setText(contato.getNome());
        campoApelido.setText(contato.getApelido());
        campoTelefone.setText(contato.getTelefone());
        campoEndereco.setText(contato.getEndereco());
        campoNota.setProgress(contato.getNota().intValue());
        this.contato = contato;
    }
}
