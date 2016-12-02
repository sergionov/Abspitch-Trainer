package es.sergionovic.abspitchtrainer.DB;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

import es.sergionovic.abspitchtrainer.Model.Statistic;
import es.sergionovic.abspitchtrainer.Model.User;

import static es.sergionovic.abspitchtrainer.DB.DBI.*;
import static es.sergionovic.abspitchtrainer.DB.Tables.*;

/**
 * Created by Sergio on 25/06/2015.
 */
public class DataBaseHandler {

    static DataBaseHelper DB;

    public static void initDB(Context context) {

        DB = new DataBaseHelper(context, "AbsPitchDB", null, 1);

        SQLiteDatabase db = DB.getWritableDatabase();

        db.execSQL("INSERT INTO " + DB_EXERCISES_LEVEL + " (" + DB_EXERCISES_LEVEL_ID + ", " + DB_EXERCISES_LEVEL_NAME
                + ") VALUES ('1','Facil') ");
        db.execSQL("INSERT INTO " + DB_EXERCISES_LEVEL + " (" + DB_EXERCISES_LEVEL_ID + ", " + DB_EXERCISES_LEVEL_NAME
                + ") VALUES ('2','Medio') ");
        db.execSQL("INSERT INTO " + DB_EXERCISES_LEVEL + " (" + DB_EXERCISES_LEVEL_ID + ", " + DB_EXERCISES_LEVEL_NAME
                + ") VALUES ('3','Avanzado') ");
        db.execSQL("INSERT INTO " + DB_EXERCISES_LEVEL + " (" + DB_EXERCISES_LEVEL_ID + ", " + DB_EXERCISES_LEVEL_NAME
                + ") VALUES ('4','Experto') ");

        db.execSQL("INSERT INTO " + DB_EXERCISES_TYPE + " (" + DB_EXERCISES_TYPE_ID + ", " + DB_EXERCISES_TYPE_NAME
                + ") VALUES ('1','Oido Absoluto') ");
        db.execSQL("INSERT INTO " + DB_EXERCISES_TYPE + " (" + DB_EXERCISES_TYPE_ID + ", " + DB_EXERCISES_TYPE_NAME
                + ") VALUES ('2','Intervalos') ");
        db.execSQL("INSERT INTO " + DB_EXERCISES_TYPE + " (" + DB_EXERCISES_TYPE_ID + ", " + DB_EXERCISES_TYPE_NAME
                + ") VALUES ('3','Escalas') ");
        db.execSQL("INSERT INTO " + DB_EXERCISES_TYPE + " (" + DB_EXERCISES_TYPE_ID + ", " + DB_EXERCISES_TYPE_NAME
                + ") VALUES ('4','Acordes') ");
        db.execSQL("INSERT INTO " + DB_EXERCISES_TYPE + " (" + DB_EXERCISES_TYPE_ID + ", " + DB_EXERCISES_TYPE_NAME
                + ") VALUES ('5','Ritmo') ");

        db.close();
    }

    public static void newExercise(Context context, String id_exec, String name, String level, String type) {
        DB = new DataBaseHelper(context, "AbsPitchDB", null, 1);

        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL("INSERT INTO " + DB_EXERCISES + " (" + DB_EXERCISES_ID + ", " + DB_EXERCISES_NAME
                + ", " + DB_EXERCISES_LEVELID + ", " + DB_EXERCISES_TYPEID
                + ") VALUES ('" + id_exec + "', '" + name + "', '" + level + "', '" + type + "') ");
        db.close();
    }

    public static void newStatistic(Context context, String id_statis, String user, String exerc, String date,
                                    boolean success, String time) {
        DB = new DataBaseHelper(context, "AbsPitchDB", null, 1);
        int flag = (success) ? 1 : 0;

        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL("INSERT INTO " + DB_STATISTICS + " (" + DB_STATISTICS_ID + ", " + DB_STATISTICS_USER_ID
                + ", " + DB_STATISTICS_EXER_ID + ", " + DB_STATISTICS_DATE + ", " + DB_STATISTICS_SUCCES
                + ", " + DB_STATISTICS_TIME
                + ") VALUES ('" + id_statis + "', '" + user + "', '" + exerc + "', '" + date + "', '"
                + flag + "', '" + time + "') ");
        db.close();
    }

    public static void newUser(Context context, String user_id, String user_name, String user_age, String user_photo) {
        DB = new DataBaseHelper(context, "AbsPitchDB", null, 1);

        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL("INSERT INTO " + DB_USERS + " (" + DB_USERS_ID + ", " + DB_USERS_NAME
                + ", " + DB_USERS_AGE + ", " + DB_USERS_PHOTO +
                ") VALUES ('" + user_id + "', '" + user_name + "', '" + user_age + "', '" + user_photo + "') ");
        db.close();
    }

    public static void updateUserPhoto(Context context, String user_id, String user_name, String user_age, String user_photo) {
        DB = new DataBaseHelper(context, "AbsPitchDB", null, 1);

        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL("UPDATE " + DB_USERS + " SET user_name='" + user_name + "' WHERE user_id=" + user_id + "");
        db.execSQL("UPDATE " + DB_USERS + " SET user_age='" + user_age + "' WHERE user_id=" + user_id + "");
        db.execSQL("UPDATE " + DB_USERS + " SET user_photo='" + user_photo + "' WHERE user_id=" + user_id + "");
        db.close();
    }

    public static void updateUser(Context context, String user_id, String user_name, String user_age) {
        DB = new DataBaseHelper(context, "AbsPitchDB", null, 1);

        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL("UPDATE " + DB_USERS + " SET user_name='" + user_name + "' WHERE user_id=" + user_id + "");
        db.execSQL("UPDATE " + DB_USERS + " SET user_age='" + user_age + "' WHERE user_id=" + user_id + "");
        db.close();
    }

    public static User getUser(Context context, String user_id) {
        DB = new DataBaseHelper(context, "AbsPitchDB", null, 1);

        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + DB_USERS + " WHERE " + DB_USERS_ID + "='" + user_id + "'", null);

        User user = null;

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya mas registros
            do {
                user = fetchInUser(c);
            } while (c.moveToNext());
        }

        return user;
    }

    public static List<Statistic> getStatistic(Context context) {
        DB = new DataBaseHelper(context, "AbsPitchDB", null, 1);
        SQLiteDatabase db = DB.getReadableDatabase();

        List<Statistic> statisticList = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM " + DB_STATISTICS, null);

        if (c.moveToFirst()) {
            do {
                Statistic statistic = fetchInStatistic(c);
                statisticList.add(statistic);
            } while (c.moveToNext());
        }

        return statisticList;
    }

    public static User fetchInUser(Cursor c) {
        User user = new User(c.getString(DBI_USERS_ID));
        user.setName(c.getString(DBI_USERS_NAME));
        user.setAge(c.getInt(DBI_USERS_AGE));
        user.setPhoto(c.getString(DBI_USERS_PHOTO));

        return user;
    }

    public static Statistic fetchInStatistic(Cursor c) {
        Statistic statistic = new Statistic(c.getString(DBI_STATISTICS_ID));
        statistic.setDate(c.getString(DBI_STATISTICS_DATE));
        statistic.setUser_id(c.getString(DBI_STATISTICS_USER_ID));
        statistic.setExercise_id(c.getString(DBI_STATISTICS_EXER_ID));
        statistic.setSuccess(c.getInt(DBI_STATISTICS_SUCCES) > 0);
        statistic.setResponse_time(c.getInt(DBI_STATISTICS_TIME));
        return statistic;
    }

}
