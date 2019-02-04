package com.alex.theguide.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static com.alex.theguide.model.MyDBHelper.COLUMN_FILE_NAME;
import static com.alex.theguide.model.MyDBHelper.COLUMN_FILE_TYPE;
import static com.alex.theguide.model.MyDBHelper.COLUMN_ID;
import static com.alex.theguide.model.MyDBHelper.COLUMN_IS_BLUE;
import static com.alex.theguide.model.MyDBHelper.COLUMN_IS_FOLDER;
import static com.alex.theguide.model.MyDBHelper.COLUMN_IS_ORANGE;
import static com.alex.theguide.model.MyDBHelper.COLUMN_MOD_DATE;
import static com.alex.theguide.model.MyDBHelper.COLUMN_PARENT_FOLDER_ID;
import static com.alex.theguide.model.MyDBHelper.DB_TABLE;

public class LocalDBRepository implements IRepository {

    private MyDBHelper dbHelper;

    public LocalDBRepository(MyDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void loadFileModels(Long parentFolderID, LoadFileModelsCallback callback) {
        LoadFileModelsTask loadFileModelsTask = new LoadFileModelsTask(callback);
        loadFileModelsTask.execute(parentFolderID);
    }


    class LoadFileModelsTask extends AsyncTask<Long, Void, List<FileModel>> {

        private final LoadFileModelsCallback callback;

        LoadFileModelsTask(LoadFileModelsCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<FileModel> doInBackground(Long... params) {
            List<FileModel> fileModels = new LinkedList<>();
            long parentFolderID = params[0];
            try {
                Cursor cursor = dbHelper.getReadableDatabase().query(DB_TABLE, null, COLUMN_PARENT_FOLDER_ID + "=" + parentFolderID, null, null, null, null);
                while (cursor.moveToNext()) {
                    FileModel fileModel = new FileModel();
                    fileModel.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                    fileModel.setFileName(cursor.getString(cursor.getColumnIndex(COLUMN_FILE_NAME)));

                    int valueIsFolder = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FOLDER));
                    fileModel.setIsFolder(checkByInteger(valueIsFolder));

                    String date = cursor.getString(cursor.getColumnIndex(COLUMN_MOD_DATE));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                    try {
                        fileModel.setModDate(dateFormat.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String fileType = cursor.getString(cursor.getColumnIndex(COLUMN_FILE_TYPE));
                    try {
                        fileModel.setFileType(FileType.findEnum(fileType));
                    } catch (NullPointerException ex) {
                        ex.printStackTrace();
                    }

                    int valueIsOrange = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ORANGE));
                    fileModel.setOrange(checkByInteger(valueIsOrange));

                    int valueIsBlue = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_BLUE));
                    fileModel.setBlue(checkByInteger(valueIsBlue));

                    fileModel.setParentFolder(cursor.getColumnIndex(COLUMN_PARENT_FOLDER_ID));

                    fileModels.add(fileModel);
                }
                cursor.close();
            } catch (SQLiteException exception) {
                callback.onError(exception);
            }
            return fileModels;
        }

        private boolean checkByInteger(int number) {
            return number != 0;
        }

        @Override
        protected void onPostExecute(List<FileModel> fileModelList) {
            if (callback != null) {
                callback.onLoad(fileModelList);
            }
        }
    }
}
