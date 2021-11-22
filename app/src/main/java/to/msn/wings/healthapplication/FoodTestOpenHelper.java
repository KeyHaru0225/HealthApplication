package to.msn.wings.healthapplication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FoodTestOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "FoodDB.db";
    private static final String TABLE = "food_db";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "food_date";
    private static final String COLUMN_BLOOB_M = "morning_blob";
    private static final String COLUMN_BLOOB_L = "lunch_blob";
    private static final String COLUMN_BLOOB_D = "dinner_bloob";
    private static final String COLUMN_BLOOB_S = "snack_blob";
    private static final String COLUMN_MEMO_ONE = "memo_one";
    private static final String COLUMN_MEMO_TWO = "memo_two";
    private static final String COLUMN_MEMO_THREE = "memo_three";
    private static final String COLUMN_MEMO_FOUR = "memo_four";

    public static final String FOOD_TABLE =
            "create table " + TABLE + "(" +
                    COLUMN_ID + " integer primary key," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_BLOOB_M + " BLOB," +
                    COLUMN_BLOOB_L + " BLOB," +
                    COLUMN_BLOOB_D + " BLOB," +
                    COLUMN_BLOOB_S + " BLOB," +
                    COLUMN_MEMO_ONE + " TEXT," +
                    COLUMN_MEMO_TWO + " TEXT," +
                    COLUMN_MEMO_THREE + " TEXT," +
                    COLUMN_MEMO_FOUR + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE;

    FoodTestOpenHelper(Context context) {
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
