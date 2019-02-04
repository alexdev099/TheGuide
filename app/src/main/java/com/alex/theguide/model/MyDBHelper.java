package com.alex.theguide.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "fileModelsDB";
    static final String DB_TABLE = "fileModels";

    static final String COLUMN_ID = "_id";
    static final String COLUMN_FILE_NAME = "fileName";
    static final String COLUMN_IS_FOLDER = "isFolder";
    static final String COLUMN_MOD_DATE = "modDate";
    static final String COLUMN_FILE_TYPE = "fileType";
    static final String COLUMN_IS_ORANGE = "isOrange";
    static final String COLUMN_IS_BLUE = "isBlue";
    static final String COLUMN_PARENT_FOLDER_ID = "parentFolder";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDataBase(db, 0, DB_VERSION);
        fillingDB(db);
    }

    //TODO delete this method before next commit
    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    private void updateMyDataBase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("create table " + DB_TABLE + "(" +
                    COLUMN_ID + " integer primary key autoincrement," +
                    COLUMN_FILE_NAME + " text," +
                    COLUMN_IS_FOLDER + " numeric," +
                    COLUMN_MOD_DATE + " text," +
                    COLUMN_FILE_TYPE + " text," +
                    COLUMN_IS_ORANGE + " numeric," +
                    COLUMN_IS_BLUE + " numeric," +
                    COLUMN_PARENT_FOLDER_ID + " numeric" + ")");
        }
    }

    private void fillingDB(SQLiteDatabase db) {
        insertRecord(db, "Family Shared", 1, "June 5, 2014", "", 0, 0, 0);
        insertRecord(db, "For Work", 1, "July 2, 2014", "", 1, 0, 0);
        insertRecord(db, "WorkPowerpoint.pptx", 0, "July 2, 2014", ".pptx", 0, 0, 0);
        insertRecord(db, "Speech.docx", 0, "July 1, 2014", ".docx", 0, 1, 0);
        insertRecord(db, "Tom''s Folder", 1, "July 1, 2014", "", 0, 0, 0);
        insertRecord(db, "DSC119.jpg", 0, "July 1, 2014", ".jpg", 1, 1, 0);

        insertRecord(db, "Images", 1, "July 3, 2014", "", 0, 0, 2);
        insertRecord(db, "Icons", 1, "July 3, 2014", "", 1, 0, 2);
        insertRecord(db, "MySchedule.doc", 0, "July 4, 2014", ".doc", 0, 1, 2);
        insertRecord(db, "MyNotes.doc", 0, "July 4, 2014", ".doc", 1, 1, 2);

        insertRecord(db, "Back.png", 0, "July 5, 2014", ".png", 0, 0, 8);
        insertRecord(db, "Up.png", 0, "July 5, 2014", ".png", 1, 1, 8);
        insertRecord(db, "Favorites.png", 0, "July 5, 2014", ".png", 1, 0, 8);
        insertRecord(db, "Forward.png", 0, "July 5, 2014", ".png", 0, 1, 8);
    }

    private void insertRecord(SQLiteDatabase db, String fileName, int isFolder, String modDate, String fileType, int isOrange, int isBlue, int parentFolderID) {
        db.execSQL("INSERT INTO " + DB_TABLE + "(" + COLUMN_FILE_NAME + ", " + COLUMN_IS_FOLDER + ", " + COLUMN_MOD_DATE + ", " +
                COLUMN_FILE_TYPE + ", " + COLUMN_IS_ORANGE + ", " + COLUMN_IS_BLUE + ", " + COLUMN_PARENT_FOLDER_ID +
                ") VALUES(" +
                "\'" + fileName + "\', " +
                "\'" + isFolder + "\', " +
                "\'" + modDate + "\', " +
                "\'" + fileType + "\', " +
                isOrange + ", " +
                isBlue + ", " + parentFolderID + ")"
        );
    }
}
