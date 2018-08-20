package br.edu.ifma.felipe.appmeuslugares;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import adapter.AdapterLugares;
import dao.DAOMeusLugares;
import modelo.Lugar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        addItem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int idBotaoClicado = v.getId();

        switch (idBotaoClicado) {
            case R.id.fabAddItem:
                Intent it = new Intent("FORM");
                it.addCategory("SAVE");
                startActivity(it);
                break;
        }
    }


}
