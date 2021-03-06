package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import modelo.Lugar;
import util.DBGateway;

public class DAOMeusLugares {
    private final String TABELA = "meuslugares";
    private DBGateway gw;

    public DAOMeusLugares(Context ctx) {
        gw = DBGateway.getInstance(ctx);
    }

    public boolean salvar(Lugar lugar) {
        boolean retorno = false;
        ContentValues valores = new ContentValues();

        valores.put("nome", lugar.getNome());
        valores.put("descricao", lugar.getDescricao());
        valores.put("latitude", lugar.getLatitude());
        valores.put("longitude", lugar.getLongitude());

        long result = gw.getDatabase().insert(TABELA, null, valores);
        if (result > 0) {
            retorno = true;
        }

        return retorno;
    }

    public ArrayList<Lugar> getLugares()
    {
        ArrayList<Lugar> lugares = new ArrayList<>();
        String sql = "SELECT * FROM meuslugares ORDER BY id DESC";

        String[] campos = {"id", "nome", "descricao", "latitude", "longitude"};

        Cursor cs = gw.getDatabase().query("meuslugares", campos, null, null, null, null, "id DESC");

        while (cs.moveToNext()) {
            Lugar lugar = new Lugar();

            lugar.setId(cs.getInt(0));
            lugar.setNome(cs.getString(1));
            lugar.setDescricao(cs.getString(2));
            lugar.setLatitude(cs.getString(3));
            lugar.setLongitude(cs.getString(4));

            lugares.add(lugar);
        }

        cs.close();
        return lugares;
    }
}
