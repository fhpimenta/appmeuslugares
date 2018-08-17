package br.edu.ifma.felipe.appmeuslugares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dao.DAOMeusLugares;
import modelo.Lugar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listPlaces;
    private DAOMeusLugares dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new DAOMeusLugares(this);
        List<Lugar> lugares = dao.getLugares();

        listPlaces = findViewById(R.id.listPlaces);
        listPlaces.setHasFixedSize(true);
    }
}
