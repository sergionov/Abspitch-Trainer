package es.sergionovic.abspitchtrainer.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static es.sergionovic.abspitchtrainer.DB.Tables.DB_EXERCISES_CREATE;
import static es.sergionovic.abspitchtrainer.DB.Tables.DB_EXERCISES_DROP;
import static es.sergionovic.abspitchtrainer.DB.Tables.DB_EXERCISES_LEVEL_CREATE;
import static es.sergionovic.abspitchtrainer.DB.Tables.DB_EXERCISES_LEVEL_DROP;
import static es.sergionovic.abspitchtrainer.DB.Tables.DB_EXERCISES_TYPE_CREATE;
import static es.sergionovic.abspitchtrainer.DB.Tables.DB_EXERCISES_TYPE_DROP;
import static es.sergionovic.abspitchtrainer.DB.Tables.DB_STATISTICS_CREATE;
import static es.sergionovic.abspitchtrainer.DB.Tables.DB_STATISTICS_DROP;
import static es.sergionovic.abspitchtrainer.DB.Tables.DB_USERS_CREATE;
import static es.sergionovic.abspitchtrainer.DB.Tables.DB_USERS_DROP;

/**
 * Created by Sergio on 24/06/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creacion de la tabla
        db.execSQL(DB_USERS_CREATE);
        db.execSQL(DB_EXERCISES_CREATE);
        db.execSQL(DB_STATISTICS_CREATE);
        db.execSQL(DB_EXERCISES_LEVEL_CREATE);
        db.execSQL(DB_EXERCISES_TYPE_CREATE);
    }

    public void onDelete(SQLiteDatabase db){
        //Se ejecuta la sentencia SQL de borrado de la tabla
        db.execSQL(DB_USERS_DROP);
        db.execSQL(DB_EXERCISES_DROP);
        db.execSQL(DB_STATISTICS_DROP);
        db.execSQL(DB_EXERCISES_LEVEL_DROP);
        db.execSQL(DB_EXERCISES_TYPE_DROP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Version 1 Creacion de la base de datos inicial

    }


    public SQLiteDatabase getInstance(){
        return getWritableDatabase();
    }

}
