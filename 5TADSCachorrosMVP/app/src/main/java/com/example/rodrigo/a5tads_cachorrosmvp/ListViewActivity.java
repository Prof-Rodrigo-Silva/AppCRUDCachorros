package com.example.rodrigo.a5tads_cachorrosmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

import controle.Cachorro;
import controle.ListAdapter;
import openHelper.CachorroDB;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, Serializable{

    CachorroDB cachorroDB;
    Cachorro cachorro;
    ListView lista;
    private String TAG = "cachorro_TAg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        cachorro = new Cachorro();
        cachorroDB = CachorroDB.getInstance(this);
        lista=(ListView) findViewById(R.id.listviewview);
        lista.setOnItemClickListener(this);
        carregarListView(cachorroDB.getAll());
    }
    public void carregarListView(List<Cachorro> cachorros){
        controle.ListAdapter dadosAdapter = new ListAdapter(this, cachorros);

        lista.setAdapter(dadosAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Long codigo;
        cachorro = (Cachorro) parent.getAdapter().getItem(i);
        Log.d(TAG, cachorro.toString());

        Intent nova = new Intent(this, Editar_Excluir.class);
        nova.putExtra("Objeto", cachorro);
        startActivity(nova);
    }
    protected void onStart(){
        super.onStart();
        carregarListView(cachorroDB.getAll());
    }
}