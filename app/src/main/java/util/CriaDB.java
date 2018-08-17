package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbmeuslugares.db";
    private static final int DB_VERSION = 1;
    private String comandosql;

    public CriaDB(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        comandosql = "CREATE TABLE meuslugares(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nome   VARCHAR(255)," +
                "descricao TEXT," +
                "latitude VARCHAR(255)," +
                "longitude   VARCHAR(255))";
        db.execSQL(comandosql);

        comandosql = "INSERT INTO meuslugares(nome, descricao, latitude,longitude) " +
                    "VALUES ('Instituto Federal do Maranhão', 'Local onde eu estudo'," +
                    "'-2.536022', '-44.278613')";
        db.execSQL(comandosql);

        comandosql = "INSERT INTO meuslugares(nome, descricao, latitude,longitude) " +
                "VALUES ('Minha Casa', 'Local onde eu moro'," +
                "'-2.551561', '-44.264548')";
        db.execSQL(comandosql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS meuslugares");
        onCreate(db);
    }
}
