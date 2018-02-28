package com.example.rodrigo.a5tads_cachorrosmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import controle.Cachorro;
import controle.CachorroAdapter;
import openHelper.CachorroDB;

public class ListaRecyclerActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected List<Cachorro> cachorros;
    protected CachorroAdapter adapter;
    CachorroDB cachorroDB;
    Cachorro cachorro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        cachorro = new Cachorro();
        cachorroDB = CachorroDB.getInstance(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        cachorros = cachorroDB.getAll();
        recyclerView.setAdapter(adapter = new CachorroAdapter(this, cachorros, onClickCachorro()));
    }
    protected CachorroAdapter.CachorroOnClickListener onClickCachorro(){
        final Intent intent = new Intent(getBaseContext(), Editar_Excluir.class);
        return  new CachorroAdapter.CachorroOnClickListener(){
            @Override
            public void onClickCachorro(CachorroAdapter.CachorrosViewHolder holder, int idx){
                Cachorro p = cachorros.get(idx);
                intent.putExtra("Objeto",p);
                startActivity(intent);
            }
        };
    }
    public void carregarRecyclerView (List<Cachorro> cachorros){
        cachorros = cachorroDB.getAll();
        recyclerView.setAdapter(adapter=new CachorroAdapter(this,cachorros,onClickCachorro()));

    }
    @Override
    protected void onStart(){
        super.onStart();
        carregarRecyclerView(cachorroDB.getAll());
    }
}
