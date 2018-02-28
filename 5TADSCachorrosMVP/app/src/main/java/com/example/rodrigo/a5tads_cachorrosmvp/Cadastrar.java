package com.example.rodrigo.a5tads_cachorrosmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import controle.Cachorro;
import openHelper.CachorroDB;

public class Cadastrar extends AppCompatActivity {

    public EditText nome, raca, sexo;
    Button cadastrar, cancelar;
    private CachorroDB cachorroDB;
    private Cachorro cachorro;
    private static String TAG = "cachorros_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        cachorro = new Cachorro();
        cachorroDB = CachorroDB.getInstance(this);
        nome = (EditText) findViewById(R.id.eTNome);
        raca = (EditText) findViewById(R.id.eTRaca);
        sexo = (EditText) findViewById(R.id.eTSexo);
        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        cancelar = (Button) findViewById(R.id.btnCancelar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nome.getText().toString().isEmpty() &&
                        !sexo.getText().toString().isEmpty() && !raca.getText().toString().isEmpty()){
                    if (cachorro._id ==null){
                        cachorro = new Cachorro();
                    }
                    cachorro.nome = nome.getText().toString();
                    cachorro.raca = raca.getText().toString();
                    cachorro.sexo = sexo.getText().toString();
                    Log.d(TAG, "Cachorro que será salvo: " +cachorro.toString());
                    cachorroDB.save(cachorro);
                }else {
                    Toast.makeText(getBaseContext(),"Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getBaseContext(),"Cadastrado!", Toast.LENGTH_SHORT).show();
            }});
    }
}
