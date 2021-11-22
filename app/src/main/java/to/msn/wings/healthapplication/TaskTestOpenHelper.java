package to.msn.wings.healthapplication;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TaskTestOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "TaskDB.db";
    private static final String TABLE = "task_db";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "task_date";
    private static final String COLUMN_TASK_ONE = "task_one";
    private static final String COLUMN_TASK_TWO = "task_two";
    private static final String COLUMN_TASK_THREE = "task_three";
    private static final String COLUMN_MEMO_ONE = "memo_one";
    private static final String COLUMN_MEMO_TWO = "memo_two";
    private static final String COLUMN_MEMO_THREE = "memo_three";
    private static final String COLUMN_ACHIEVE = "achievement";

    public static final String TASK_TABLE =
            "create table " + TABLE + "(" +
                    COLUMN_ID + " integer primary key," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_TASK_ONE + " TEXT," +
                    COLUMN_TASK_TWO + " TEXT," +
                    COLUMN_TASK_THREE + " TEXT," +
                    COLUMN_MEMO_ONE + " TEXT," +
                    COLUMN_MEMO_TWO + " TEXT," +
                    COLUMN_MEMO_THREE + " TEXT," +
                    COLUMN_ACHIEVE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE;

    TaskTestOpenHelper(Context context) {
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
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

