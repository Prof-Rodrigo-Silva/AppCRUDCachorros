package com.example.rodrigo.a5tads_cachorrosmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import controle.Cachorro;
import openHelper.CachorroDB;

public class Editar_Excluir extends AppCompatActivity {

    public EditText nome, raca, sexo;
    TextView aliascodigo;

    private CachorroDB cachorroDB;
    private Cachorro cachorro;
    private String TAG = "editarTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__excluir);
        cachorro = new Cachorro();
        cachorroDB = CachorroDB.getInstance(this);
        nome = (EditText) findViewById(R.id.eT_Update_Delete_Nome);
        raca = (EditText) findViewById(R.id.eT_Update_Delete_Raca);
        sexo = (EditText) findViewById(R.id.eT_Update_Delete_Sexo);
        aliascodigo = (TextView) findViewById(R.id.textView);

    }
    public void salvar(View view){
        if(!nome.getText().toString().isEmpty() &&
                !sexo.getText().toString().isEmpty() &&
                !raca.getText().toString().isEmpty()){

            cachorro._id = Long.parseLong(aliascodigo.getText().toString());
            cachorro.nome = nome.getText().toString();
            cachorro.raca = raca.getText().toString();
            cachorro.sexo = sexo.getText().toString();
            Log.d(TAG, "Cachorro será salvo: " + cachorro.toString());
            cachorroDB.save(cachorro);
        }else {
            Toast.makeText(getBaseContext(),"Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getBaseContext(),"Atualizado!", Toast.LENGTH_SHORT).show();
    }
    public void excluir(View view){
        cachorro._id=Long.parseLong(aliascodigo.getText().toString());

        Log.d(TAG, "Cachorro será excluído: "+ cachorro.toString());

        cachorroDB.delete(cachorro);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Intent nova = getIntent();
        Cachorro registro = (Cachorro) nova.getSerializableExtra("Objeto");
        aliascodigo.setText(registro._id.toString());
        nome.setText(registro.nome);
        raca.setText(registro.raca);
        sexo.setText(registro.sexo);
    }
}
