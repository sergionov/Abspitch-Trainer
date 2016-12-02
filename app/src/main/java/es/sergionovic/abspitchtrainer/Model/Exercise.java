package es.sergionovic.abspitchtrainer.Model;

/**
 * Created by Sergio on 24/06/2015.
 */
public class Exercise implements IModel {

    String id_exercise;
    String name;
    String level_id;
    String type_id;

    public Exercise(String id_exercise) {
        this.id_exercise = id_exercise;
        initValues();
    }

    @Override
    public void initValues() {
        name = "";
        level_id = "";
        type_id = "";
    }

    @Override
    public boolean validate() {
        boolean ok;
        ok = !id_exercise.isEmpty() && !name.isEmpty() && !level_id.isEmpty() && !type_id.isEmpty();
        return ok;
    }

    public String getId_exercise() {
        return id_exercise;
    }

    public void setId_exercise(String id_exercise) {
        this.id_exercise = id_exercise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
}
