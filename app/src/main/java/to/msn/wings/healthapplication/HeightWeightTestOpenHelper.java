package to.msn.wings.healthapplication;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class HeightWeightTestOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "HeightWeightDB.db";
    private static final String TABLE = "heightweight_db";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_BMI = "bmi";

    public static final String HEIGHTWEIGHT_TABLE =
            "create table " + "TABLE" + "(" +
                    COLUMN_ID + " integer primary key," +
                    COLUMN_HEIGHT + " TEXT," +
                    COLUMN_WEIGHT + " TEXT," +
                    COLUMN_BMI + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE;

    HeightWeightTestOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        /*
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

