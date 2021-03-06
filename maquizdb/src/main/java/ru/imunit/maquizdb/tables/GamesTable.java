package ru.imunit.maquizdb.tables;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by imunit on 29.09.2015.
 */
public class GamesTable {

    public static final String TABLE_NAME = "games";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_AVG_GUESS_TIME = "avg_guess_time";
    public static final String COLUMN_BEST_GUESS_TIME = "best_guess_time";
    public static final String COLUMN_GUESS = "guess";
    public static final String COLUMN_CORRECT_GUESS = "correct_guess";
    public static final String COLUMN_LONGEST_FAST_ROW = "longest_fast_row";

    public static final String TABLE_CREATE =
            "create table if not exists `" + TABLE_NAME + "` ( `"
            + COLUMN_ID + "` integer primary key, `"
            + COLUMN_SCORE + "` integer not null, `"
            + COLUMN_AVG_GUESS_TIME + "` integer, `"
            + COLUMN_BEST_GUESS_TIME + "` integer, `"
            + COLUMN_GUESS + "` integer not null, `"
            + COLUMN_CORRECT_GUESS + "` integer not null, `"
            + COLUMN_LONGEST_FAST_ROW + "` integer not null default 0"
            + ");";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    public static void onUpgrade(int oldVersion, int newVersion, SQLiteDatabase db) {

    }

}
