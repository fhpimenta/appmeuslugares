package br.edu.ifma.felipe.appmeuslugares;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dao.DAOMeusLugares;
import modelo.Lugar;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editNome;
    private EditText editDescricao;
    private TextView lblLatitude;
    private TextView txtLatitude;
    private TextView lblLongitude;
    private TextView txtLongitude;
    private Button btnMapa;
    private Button btnSalvar;
    private DAOMeusLugares dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editNome = findViewById(R.id.editNome);
        editDescricao = findViewById(R.id.editDescricao);
        lblLatitude = findViewById(R.id.lblLatitude);
        txtLatitude = findViewById(R.id.txtLatitude);
        lblLongitude = findViewById(R.id.lblLongitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        btnMapa = findViewById(R.id.btnMapa);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnMapa.setOnClickListener(this);
        btnSalvar.setOnClickListener(this);

        dao = new DAOMeusLugares(this);

        if (savedInstanceState != null) {
            editNome.setText(savedInstanceState.getString("nome"));
            editDescricao.setText(savedInstanceState.getString("descricao"));
            txtLatitude.setText(savedInstanceState.getString("latitude"));
            txtLongitude.setText(savedInstanceState.getString("longitude"));
        }

        Bundle params = getIntent().getExtras();
        if (null != params) {
            if (null != params.getString("latitude")) {
                lblLatitude.setVisibility(View.VISIBLE);
                txtLatitude.setVisibility(View.VISIBLE);
                txtLatitude.setText(params.getString("latitude"));
            }

            if (null != params.getString("longitude")) {
                lblLongitude.setVisibility(View.VISIBLE);
                txtLongitude.setVisibility(View.VISIBLE);
                txtLongitude.setText(params.getString("longitude"));
            }
        }
    }

    @Override
    public void onClick(View v) {
        int idBotaoClicado = v.getId();

        switch (idBotaoClicado) {
            case R.id.btnMapa:
                Intent itMapa = new Intent("MAPS");
                itMapa.addCategory("GET_LOCATION");
                startActivity(itMapa);
                break;
            case R.id.btnSalvar:
                salvar();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("nome", editNome.getText().toString());
        outState.putString("descricao", editDescricao.getText().toString());
        outState.putString("latitude", txtLatitude.getText().toString());
        outState.putString("longitude", txtLongitude.getText().toString());

    }

    private void salvar()
    {
        Lugar lugar = new Lugar();

        lugar.setNome(editNome.getText().toString());
        lugar.setDescricao(editDescricao.getText().toString());
        lugar.setLatitude(txtLatitude.getText().toString());
        lugar.setLongitude(txtLongitude.getText().toString());

        boolean result = dao.salvar(lugar);

        if (result) {
            Intent it = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Lugar salvo com sucesso!", Toast.LENGTH_LONG).show();
            sentNotification(lugar);
            startActivity(it);
        } else {
            Toast.makeText(this, "Erro ao tentar salvar.", Toast.LENGTH_LONG).show();
        }
    }

    private void sentNotification(Lugar lugar)
    {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.map)
                        .setContentTitle("Novo Local Adicionado")
                        .setContentText(lugar.getNome() + " foi adicionado. Veja mais!");
        Intent resultIntent = new Intent("MAPS");
        resultIntent.addCategory("VIEW");
        resultIntent.putExtra("lugar", lugar);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(FormActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(sound);
        mBuilder.setVibrate(new long[] {1000, 1000});
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }


}
