package es.sergionovic.abspitchtrainer.Model;

/**
 * Created by Sergio on 25/06/2015.
 */
public class ExerciseType implements IModel {

    String eType_id;
    String name;

    public ExerciseType(String eType_id) {
        this.eType_id = eType_id;
        initValues();
    }

    @Override
    public void initValues() {
        name = "";
    }

    @Override
    public boolean validate() {
        boolean ok;
        ok = !eType_id.isEmpty() && !name.isEmpty();
        return ok;
    }

    public String geteType_id() {
        return eType_id;
    }

    public void seteType_id(String eType_id) {
        this.eType_id = eType_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
