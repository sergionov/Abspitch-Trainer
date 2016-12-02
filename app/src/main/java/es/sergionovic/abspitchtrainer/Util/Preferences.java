package es.sergionovic.abspitchtrainer.Util;

import android.content.Context;
import android.content.SharedPreferences;

import es.sergionovic.abspitchtrainer.DB.DataBaseHandler;
import es.sergionovic.abspitchtrainer.Model.User;

/**
 * Created by Sergio on 25/06/2015.
 */
public class Preferences {



    public static int exercisesNumber(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref",Context.MODE_PRIVATE);
        return preferences.getInt("exerciseNumber", 0);
    }

    public static int updateExercisesNumber(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("exerciseNumber", exercisesNumber(context) + 1);
        editor.apply();
        return exercisesNumber(context) + 1;
    }

    public static int statisticNumber(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref",Context.MODE_PRIVATE);
        return preferences.getInt("statisticNumber", 0);
    }

    public static int updateStatisticNumber(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("statisticNumber", statisticNumber(context) + 1);
        editor.apply();
        return exercisesNumber(context) + 1;
    }

    public static User getUser(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref",Context.MODE_PRIVATE);
        User user = DataBaseHandler.getUser(context, preferences.getString("user_id", "0"));
        return user;
    }

    public static String getUserName(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        return preferences.getString("user_name", "");
    }

    public static String getUserAge(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        return preferences.getString("user_age", "");
    }

    public static void setUser(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id", "1");
        editor.apply();
    }

    public static void setUserName(Context context, String name){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_name", name);
        editor.apply();
    }

    public static void setUserAge(Context context, String age){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_age", age);
        editor.apply();
    }

    public static void setUserPhoto(Context context, String photo){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_photo", photo);
        editor.apply();
    }

    public static String getUserPhoto(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        return preferences.getString("user_photo", "");
    }

    public static void setUserCreated(Context context, boolean created){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("user_created", created);
        editor.apply();
    }

    public static boolean getUserCreated(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        return preferences.getBoolean("user_created", false);
    }

    public static int userNumber(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref",Context.MODE_PRIVATE);
        return preferences.getInt("userNumber", 0);
    }

    public static int lastUserNumber(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref",Context.MODE_PRIVATE);
        return preferences.getInt("lastUserNumber", userNumber(context));
    }

    public static int updateUserNumberNumber(Context context){
        SharedPreferences preferences = context.getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userNumber", exercisesNumber(context) + 1);
        editor.apply();
        return exercisesNumber(context) + 1;
    }
}
