package es.sergionovic.abspitchtrainer.Model;

/**
 * Created by Sergio on 25/06/2015.
 */
public class ExerciseLevel implements IModel {

    String eLevel_id;
    String name;

    public ExerciseLevel(String eLevel_id) {
        this.eLevel_id = eLevel_id;
        initValues();
    }

    @Override
    public void initValues() {
        name = "";
    }

    @Override
    public boolean validate() {
        boolean ok;
        ok = !eLevel_id.isEmpty() && !name.isEmpty();
        return ok;
    }

    public String geteLevel_id() {
        return eLevel_id;
    }

    public void seteLevel_id(String eLevel_id) {
        this.eLevel_id = eLevel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
