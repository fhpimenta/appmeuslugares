package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {
    private static DBGateway gw;
    private SQLiteDatabase db;

    public DBGateway(Context ctx) {
        CriaDB criadb = new CriaDB(ctx);
        this.db = criadb.getWritableDatabase();
    }

    public static DBGateway getInstance(Context ctx) {
        if (gw == null) {
            gw = new DBGateway(ctx);
        }
        return gw;
    }

    public SQLiteDatabase getDatabase() {
        return this.db;
    }
}
