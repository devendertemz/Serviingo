package com.serviingo.serviingo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.serviingo.serviingo.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

 //import java.util.ArrayList;
 //import java.util.HashMap;


public class DatabaseController {
    public static SQLiteDatabase myDataBase;
    private static DataBaseHelper myDataBaseHelper;

    public static void openDataBase(Context mContext) {
        if (myDataBaseHelper == null) {
            myDataBaseHelper = new DataBaseHelper(mContext);
        }
        if (myDataBase == null) {
            myDataBase = myDataBaseHelper.getWritableDatabase();
        }
    }

    public static void removeTable(String tableName) {
        myDataBase.delete(tableName, null, null);
    }

    public static long insertData(ContentValues values, String tableName) {
        long id = -1;
        try {
            id = myDataBase.insert(tableName, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppUtils.print("====insertData " + id);
        return id;
    }

    public static long insertUpdateData(ContentValues values, String tableName, String columnName, String uniqueId) {
        try {
            if (checkRecordExist(tableName, columnName, uniqueId)) {
                return (long) myDataBase.update(tableName, values, columnName + "='" + uniqueId + "'", null);
            }
            return myDataBase.insert(tableName, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean checkRecordExist(String tableName, String columnName, String uniqueId) {
        boolean status = false;
        Cursor cursor = myDataBase.query(tableName, null, columnName + "='" + uniqueId + "'", null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                status = true;
            }
            cursor.close();
        }
        return status;
    }

    public static boolean isDataExit(String tableName) {
        long cnt = DatabaseUtils.queryNumEntries(myDataBase, tableName);
        AppUtils.print("isDataExit " + cnt);
        return cnt > 0;
    }

    public static boolean deleteRow(String tableName, String keyName, String keyValue) {
        try {
            return myDataBase.delete(tableName, new StringBuilder().append(keyName).append("=").append(keyValue).toString(), null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

  /*  public static ArrayList<JSONObject> fetchAllDataFromValues(String tableName) {
        String query = "SELECT * FROM " + tableName;
        AppUtils.print("===fetchAllDataFromValues" + query);
        Cursor cursor = myDataBase.rawQuery(query, null);
        ArrayList<JSONObject> arrayList = new ArrayList();
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                JSONObject jsonObject = new JSONObject();
                for (int j = 0; j < cursor.getColumnCount(); j++) {
                    try {
                        jsonObject.put(cursor.getColumnName(j), cursor.getString(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                arrayList.add(jsonObject);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }

    public static ArrayList<JSONObject> fetchDataByGroup(String tableName, String groupBy) {
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM " + tableName + " ORDER  BY " + groupBy + " DESC ", null);
        ArrayList<JSONObject> arrayList = new ArrayList();
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                JSONObject mJsonObject = new JSONObject();
                for (int j = 0; j < cursor.getColumnCount(); j++) {
                    try {
                        mJsonObject.put(cursor.getColumnName(j), cursor.getString(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                arrayList.add(mJsonObject);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }

    public static ArrayList<JSONObject> fetchDataFromId(String tableName, String key, String byValue) {
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM " + tableName + " Where " + key + " = " + byValue, null);
        ArrayList<JSONObject> arrayList = new ArrayList();
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                JSONObject mJsonObject = new JSONObject();
                for (int j = 0; j < cursor.getColumnCount(); j++) {
                    try {
                        mJsonObject.put(cursor.getColumnName(j), cursor.getString(j));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                arrayList.add(mJsonObject);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }

    public static String getDataUsingMultipleIds(String columnName, String columnName1, String fieldId, String tableName) {
        Cursor cursor = myDataBase.rawQuery("SELECT " + columnName + " FROM " + tableName + " Where " + columnName1 + " IN (" + fieldId + ")", null);
        ArrayList<String> mStrings = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                mStrings.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return TextUtils.join(", ", mStrings);
    }

    public static ArrayList<String> fetchAllDataSpecificColumn(String columnName, String tableName) {
        Cursor cursor = myDataBase.rawQuery("SELECT " + columnName + " FROM " + tableName, null);
        ArrayList<String> arrayList = new ArrayList();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return arrayList;
    }*/

    public static void updateData(String tableName, ContentValues mContentValues, String columnName, String columnValue) {
        myDataBase.update(tableName, mContentValues, columnName + " = '" + columnValue + "'", null);
    }
/*
    public static ArrayList<HashMap<String, String>> getCategoryData() {
        ArrayList<HashMap<String, String>> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from category", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                HashMap<String, String> hashlist = new HashMap();
                hashlist.put("category_id", cur.getString(cur.getColumnIndex(catColumn.category_id.toString())));
                hashlist.put("category_name", cur.getString(cur.getColumnIndex(catColumn.category_name.toString())));
                hashlist.put("app_icon", cur.getString(cur.getColumnIndex(catColumn.app_icon.toString())));
                CatList.add(hashlist);
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<HashMap<String, String>> getPushData() {
        ArrayList<HashMap<String, String>> PushList = new ArrayList();
        PushList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from push ORDER  BY " + TablePush.pushColumn.id + " DESC ", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                HashMap<String, String> hashlist = new HashMap();
                hashlist.put("message", cur.getString(cur.getColumnIndex(TablePush.pushColumn.message.toString())));
                hashlist.put("largeIcon", cur.getString(cur.getColumnIndex(TablePush.pushColumn.largeIcon.toString())));
                hashlist.put("title", cur.getString(cur.getColumnIndex(TablePush.pushColumn.title.toString())));
                hashlist.put("id", cur.getString(cur.getColumnIndex(TablePush.pushColumn.id.toString())));
                hashlist.put("add_date", cur.getString(cur.getColumnIndex(TablePush.pushColumn.add_date.toString())));
                hashlist.put("type", cur.getString(cur.getColumnIndex(TablePush.pushColumn.type.toString())));
                hashlist.put("datetime", cur.getString(cur.getColumnIndex(TablePush.pushColumn.datetime.toString())));
                PushList.add(hashlist);
                cur.moveToNext();
            }
        }
        cur.close();
        return PushList;
    }

    public static ArrayList<HashMap<String, String>> getSubCategoryData(String tableName, String key, String byValue) {
        ArrayList<HashMap<String, String>> SubCatList = new ArrayList();
        SubCatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * FROM " + tableName + " Where " + key + " = " + byValue, null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                HashMap<String, String> hashlist = new HashMap();
                hashlist.put("category_id", cur.getString(cur.getColumnIndex(subCatColumn.category_id.toString())));
                hashlist.put("sub_category_id", cur.getString(cur.getColumnIndex(subCatColumn.sub_category_id.toString())));
                hashlist.put("sub_category_name", cur.getString(cur.getColumnIndex(subCatColumn.sub_category_name.toString())));
                hashlist.put("icon", cur.getString(cur.getColumnIndex(subCatColumn.icon.toString())));
                SubCatList.add(hashlist);
                cur.moveToNext();
            }
        }
        cur.close();
        return SubCatList;
    }

    public static ArrayList<HashMap<String, String>> getFeaturedCategoryData() {
        ArrayList<HashMap<String, String>> FeaturedCatList = new ArrayList();
        FeaturedCatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * FROM category Where " + catColumn.featured_category + " = 2", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                HashMap<String, String> hashlist = new HashMap();
                hashlist.put("category_id", cur.getString(cur.getColumnIndex(catColumn.category_id.toString())));
                hashlist.put("category_name", cur.getString(cur.getColumnIndex(catColumn.category_name.toString())));
                hashlist.put("app_icon", cur.getString(cur.getColumnIndex(catColumn.app_icon.toString())));
                FeaturedCatList.add(hashlist);
                cur.moveToNext();
            }
        }
        cur.close();
        return FeaturedCatList;
    }

    public static ArrayList<String> getSchoolData() {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from school", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableSchool.schoolColumn.school_name.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getBranchData(String schoolId) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from branch where schoolId = '"+schoolId+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableBranch.branchCatColumn.branch_name.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getSchoolIdData() {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from school", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableSchool.schoolColumn.schoo_id.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }


    public static ArrayList<String> getBranchIdData(String schoolId) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from branch where schoolId = '"+schoolId+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableBranch.branchCatColumn.branch_id.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }


    public static ArrayList<String> getClassData(String branchId) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from classroom where branch_id = '"+branchId+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableClass.classroomColumn.class_name.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getClassIdData(String branchId) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from classroom where branch_id = '"+branchId+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableClass.classroomColumn.class_id.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getSectionData(String classId) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from section where class_id = '"+classId+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableSection.sectionCatColumn.section.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getSectionIdData(String classId) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from section where class_id = '"+classId+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableSection.sectionCatColumn.section_id.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getModeData(String sectionId) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from mode where section_id = '"+sectionId+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableMode.modeColumn.name.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getModeIdData(String sectionId) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from mode where section_id = '"+sectionId+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableMode.modeColumn.id.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getAmountData(String mode_id) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from amount where mode_id = '"+mode_id+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add("RM"+cur.getString(cur.getColumnIndex(TableAmount.amountCatColumn.realfees.toString()))
                +" - "+cur.getString(cur.getColumnIndex(TableAmount.amountCatColumn.discount.toString()))
                +"% = RM"+cur.getString(cur.getColumnIndex(TableAmount.amountCatColumn.price_last.toString()))
                +" for "+cur.getString(cur.getColumnIndex(TableAmount.amountCatColumn.name.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getAmountIdData(String mode_id) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from amount where mode_id = '"+mode_id+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableAmount.amountCatColumn.realId.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }

    public static ArrayList<String> getAmountFeesData(String mode_id) {
        ArrayList<String> CatList = new ArrayList();
        CatList.clear();
        Cursor cur = myDataBase.rawQuery("SELECT * from amount where mode_id = '"+mode_id+"'", null);
        Log.d("cur.getCount()", String.valueOf(cur.getCount()));
        if (cur != null && cur.moveToNext()) {
            for (int i = 0; i < cur.getCount(); i++) {
                CatList.add(cur.getString(cur.getColumnIndex(TableAmount.amountCatColumn.price_last.toString())));
                cur.moveToNext();
            }
        }
        cur.close();
        return CatList;
    }*/
}
