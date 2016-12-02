package es.sergionovic.abspitchtrainer.Model;

/**
 * Created by Sergio on 24/06/2015.
 */
public class Statistic implements IModel {

    String statistic_id;
    String date;
    String user_id;
    String exercise_id;
    boolean success;
    int response_time;

    public Statistic(String statistic_id) {
        this.statistic_id = statistic_id;
        initValues();
    }

    @Override
    public void initValues() {
        date = "";
        user_id = "";
        exercise_id = "";
        success = false;
        response_time = 0;
    }

    @Override
    public boolean validate() {
        boolean ok;
        ok = !user_id.isEmpty() && !date.isEmpty() && !user_id.isEmpty() && !exercise_id.isEmpty()
            && response_time != 0;
        return ok;
    }

    public String getStatistic_id() {
        return statistic_id;
    }

    public void setStatistic_id(String statistic_id) {
        this.statistic_id = statistic_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(String exercise_id) {
        this.exercise_id = exercise_id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getResponse_time() {
        return response_time;
    }

    public void setResponse_time(int response_time) {
        this.response_time = response_time;
    }
}
