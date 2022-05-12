package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;

public class DBMS extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME="student.db";
    private static final Integer DATABASE_VERSION=1;
    private static  final String DB_PATH_SUFFIX="/database/";
    Integer Version;
    Context ctx;


    public DBMS(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
      public void onCreate(SQLiteDatabase db) {
          db.execSQL(StudentTable.CREATE_TABLE);
      }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      }

      public SQLiteDatabase openDataBase(){
          Log.e("version","outside" +Version);
          File DBFile =ctx.getDatabasePath(DATABASE_NAME);

          return SQLiteDatabase.openDatabase(DBFile.getPath(),null,SQLiteDatabase.NO_LOCALIZED_COLLATORS|SQLiteDatabase.CREATE_IF_NECESSARY);
      }


      public void dropTable(String tableName)
      {
          SQLiteDatabase DB=this.getWritableDatabase();
          try{
             DB.execSQL("DELETE FROM "+tableName+"");

          }catch(Exception e)
          {
              e.printStackTrace();
              DB.close();
          }
      }

      public void saveMasterTable(ContentValues contentValues,String tableName)
      {
          SQLiteDatabase DB=this.getWritableDatabase();
          long idsds=DB.insert(tableName,null,contentValues);
          Log.d("LOG",idsds +"id");
          DB.close();
      }

      public long saveHourseHolder(StudentTable StudentTable)
      {
          SQLiteDatabase DB=this.getWritableDatabase();
          long ids=0;
          try{
              if(DB!=null&&!DB.isReadOnly())
              {
                  ContentValues values=new ContentValues();
                  values.put("name",StudentTable.getName());
                  values.put("email",StudentTable.getEmail());
                  values.put("password",StudentTable.getPassword());
                  values.put("address",StudentTable.getAddress());

                  ids= DB.insert("StudentTable",null,values);
                  DB.close();
              }
          }catch(Exception e)
          {
              e.printStackTrace();
              DB.close();
          }
          return ids;
      }

  }
