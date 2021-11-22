package to.msn.wings.healthapplication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class InitialTestOpenHelper extends SQLiteOpenHelper {
    // データベースのバージョン
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "InitialDB.db";
    private static final String TABLE = "initial_db";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BLOB_M = "morning_blob";
    private static final String COLUMN_BLOB_L = "lunch_blob";
    private static final String COLUMN_BLOB_D = "dinner_blob";
    private static final String COLUMN_BLOB_S = "snack_blob";
    private static final String COLUMN_DATE = "initial_date";
    private static final String COLUMN_WEIGHT = "initial_weight";
    private static final String COLUMN_RATIO = "initial_ratio";
    private static final String COLUMN_MEMO = "initial_memo";

    public static final String INITIAL_TABLE =
            "create table " + TABLE + "(" +
                    COLUMN_ID + " integer primary key," +
                    COLUMN_BLOB_M + " BLOB," +
                    COLUMN_BLOB_L + " BLOB," +
                    COLUMN_BLOB_D + " BLOB," +
                    COLUMN_BLOB_S + " BLOB," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_WEIGHT + " INTEGER," +
                    COLUMN_RATIO + " INTEGER," +
                    COLUMN_MEMO + " TEXT)";




    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE;


    InitialTestOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*
        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );
         */

        Log.d("debug", "onCreate(SQLiteDatabase db)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

