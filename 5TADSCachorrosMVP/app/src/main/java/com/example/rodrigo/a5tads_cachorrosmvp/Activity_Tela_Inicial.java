package com.example.rodrigo.a5tads_cachorrosmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import controle.SimplesAdapterCRUD;

public class Activity_Tela_Inicial extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(new SimplesAdapterCRUD(this));
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:{
                Intent intent = new Intent(this, Cadastrar.class);
                startActivity(intent);
                break;
            }
            /*case 2:{
                Intent intent = new Intent(this, Editar_Excluir.class);
                startActivity(intent);
                break;
            }*/
            case 1:{
                Intent intent = new Intent(this, ListViewActivity.class);
                startActivity(intent);
                break;
            }
            case 2:{
                Intent intent = new Intent(this,ListaRecyclerActivity.class);
                startActivity(intent);
                break;
            }
        }

    }
}
