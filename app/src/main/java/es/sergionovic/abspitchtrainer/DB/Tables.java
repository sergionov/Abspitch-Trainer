package es.sergionovic.abspitchtrainer.DB;

import static es.sergionovic.abspitchtrainer.DB.DataTypes.DBT_BOOLEAN;
import static es.sergionovic.abspitchtrainer.DB.DataTypes.DBT_CODE;
import static es.sergionovic.abspitchtrainer.DB.DataTypes.DBT_DATE;
import static es.sergionovic.abspitchtrainer.DB.DataTypes.DBT_INTEGER;
import static es.sergionovic.abspitchtrainer.DB.DataTypes.DBT_TEXT;

/**
 * Created by Sergio on 24/06/2015.
 */
public class Tables {

    //region TABLA USUARIOS
    public static final String DB_USERS = "users";

    //campos
    public static final String DB_USERS_ID = "user_id";
    public static final String DB_USERS_NAME = "user_name";
    public static final String DB_USERS_AGE = "user_age";
    public static final String DB_USERS_PHOTO = "user_photo";
    //crear tabla
    public static final String DB_USERS_CREATE = "CREATE TABLE " + DB_USERS + " ("
            + DB_USERS_ID + " " + DBT_CODE + " PRIMARY KEY, "
            + DB_USERS_NAME + " " + DBT_TEXT + ", "
            + DB_USERS_AGE + " " + DBT_INTEGER + ", "
            + DB_USERS_PHOTO + " " + DBT_TEXT
            + ")";
    //borrar tabla
    public static final String DB_USERS_DROP = "DROP TABLE IF EXISTS " + DB_USERS;

    //endregion

    //region TABLA ESTADISTICAS
    public static final String DB_STATISTICS = "statistics";

    //campos
    public static final String DB_STATISTICS_ID = "statistic_id";
    public static final String DB_STATISTICS_USER_ID = "statistic_user_id";
    public static final String DB_STATISTICS_EXER_ID = "statistic_exer_id";
    public static final String DB_STATISTICS_DATE = "statistic_date";
    public static final String DB_STATISTICS_SUCCES = "statistic_success";
    public static final String DB_STATISTICS_TIME = "statistic_time";
    //crear tabla
    public static final String DB_STATISTICS_CREATE = "CREATE TABLE " + DB_STATISTICS + " ("
            + DB_STATISTICS_ID + " " + DBT_CODE + " PRIMARY KEY, "
            + DB_STATISTICS_USER_ID + " " + DBT_CODE + ", "
            + DB_STATISTICS_EXER_ID + " " + DBT_CODE + ", "
            + DB_STATISTICS_DATE + " " + DBT_DATE + ", "
            + DB_STATISTICS_SUCCES + " " + DBT_BOOLEAN + ", "
            + DB_STATISTICS_TIME + " " + DBT_INTEGER
            + ")";
    //borrar tabla
    public static final String DB_STATISTICS_DROP = "DROP TABLE " + DB_STATISTICS;

    //endregion

    //region TABLA EJERCICIOS
    public static final String DB_EXERCISES = "exercises";

    //campos
    public static final String DB_EXERCISES_ID = "exercise_id";
    public static final String DB_EXERCISES_NAME = "exercise_name";
    public static final String DB_EXERCISES_LEVELID = "exercise_levelid";
    public static final String DB_EXERCISES_TYPEID = "exercise_typeid";
    //crear tabla
    public static final String DB_EXERCISES_CREATE = "CREATE TABLE " + DB_EXERCISES + " ("
            + DB_EXERCISES_ID + " " + DBT_CODE + " PRIMARY KEY, "
            + DB_EXERCISES_NAME + " " + DBT_TEXT + ", "
            + DB_EXERCISES_LEVELID + " " + DBT_CODE + ", "
            + DB_EXERCISES_TYPEID + " " + DBT_CODE
            + ")";
    //borrar tabla
    public static final String DB_EXERCISES_DROP = "DROP TABLE IF EXISTS " + DB_EXERCISES;

    //endregion

    //region TABLA TIPO EJERCICIOS
    public static final String DB_EXERCISES_TYPE = "exercises_types";

    //campos
    public static final String DB_EXERCISES_TYPE_ID = "exercises_types_id";
    public static final String DB_EXERCISES_TYPE_NAME = "exercises_types_name";
    //crear tabla
    public static final String DB_EXERCISES_TYPE_CREATE = "CREATE TABLE " + DB_EXERCISES_TYPE + " ("
            + DB_EXERCISES_TYPE_ID + " " + DBT_CODE + " PRIMARY KEY, "
            + DB_EXERCISES_TYPE_NAME + " " + DBT_TEXT
            + ")";
    //borrar tabla
    public static final String DB_EXERCISES_TYPE_DROP = "DROP TABLE IF EXISTS " + DB_EXERCISES_TYPE;

    //endregion

    //region TABLA NIVEL EJERCICIOS
    public static final String DB_EXERCISES_LEVEL = "exercises_level";

    //campos
    public static final String DB_EXERCISES_LEVEL_ID = "exercises_level_id";
    public static final String DB_EXERCISES_LEVEL_NAME = "exercises_level_name";
    //crear tabla
    public static final String DB_EXERCISES_LEVEL_CREATE = "CREATE TABLE " + DB_EXERCISES_LEVEL + " ("
            + DB_EXERCISES_LEVEL_ID + " " + DBT_CODE + " PRIMARY KEY, "
            + DB_EXERCISES_LEVEL_NAME + " " + DBT_TEXT
            + ")";
    //borrar tabla
    public static final String DB_EXERCISES_LEVEL_DROP = "DROP TABLE IF EXISTS " + DB_EXERCISES_LEVEL;

    //endregion
}
