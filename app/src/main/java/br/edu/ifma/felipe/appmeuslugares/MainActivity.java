package br.edu.ifma.felipe.appmeuslugares;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import adapter.AdapterLugares;
import dao.DAOMeusLugares;
import modelo.Lugar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listPlaces;
    private FloatingActionButton addItem;
    private DAOMeusLugares dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new DAOMeusLugares(this);
        List<Lugar> lugares = dao.getLugares();

        listPlaces = findViewById(R.id.listPlaces);
        listPlaces.setHasFixedSize(true);

        AdapterLugares adapter = new AdapterLugares(lugares, this);

        listPlaces.setAdapter(adapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        listPlaces.setLayoutManager(layout);


        addItem = findViewById(R.id.fabAddItem);

    }
}
