package com.serviingo.serviingo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    DataBaseHelper(Context context) {
        super(context, "mrnmrsekart", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
      /*  db.execSQL(TableCategory.SQL_CREATE_CATEGORY);
        db.execSQL(TableSubCategory.SQL_CREATE_SUB_CATEGORY);
        db.execSQL(TableFavourite.SQL_CREATE_FAVOURITE);
        db.execSQL(TableSchool.SQL_CREATE_SCHOOL);
        db.execSQL(TableBranch.SQL_CREATE_BRANCH);
        db.execSQL(TablePush.SQL_CREATE_PUSH);
        db.execSQL(TableClass.SQL_CREATE_CLASSROOM);
        db.execSQL(TableSection.SQL_CREATE_SECTION);
        db.execSQL(TableMode.SQL_CREATE_MODE);
        db.execSQL(TableAmount.SQL_CREATE_AMOUNT);
        db.execSQL(TableFeatured.SQL_CREATE_FEATURED);*/
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
           /* db.execSQL("DROP TABLE IF EXISTS " + TableCategory.category);
            db.execSQL("DROP TABLE IF EXISTS " + TableSubCategory.sub_category);
            db.execSQL("DROP TABLE IF EXISTS " + TableFavourite.favourite);
            db.execSQL("DROP TABLE IF EXISTS " + TableSchool.school);
            db.execSQL("DROP TABLE IF EXISTS " + TableBranch.branch);
            db.execSQL("DROP TABLE IF EXISTS " + TablePush.push);
            db.execSQL("DROP TABLE IF EXISTS " + TableClass.classroom);
            db.execSQL("DROP TABLE IF EXISTS " + TableSection.section);
            db.execSQL("DROP TABLE IF EXISTS " + TableMode.mode);
            db.execSQL("DROP TABLE IF EXISTS " + TableAmount.amount);
            db.execSQL("DROP TABLE IF EXISTS " + TableFeatured.featured);*/
            onCreate(db);
        }
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
