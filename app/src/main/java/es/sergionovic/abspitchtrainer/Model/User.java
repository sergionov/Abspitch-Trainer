package es.sergionovic.abspitchtrainer.Model;

/**
 * Created by Sergio on 24/06/2015.
 */
public class User implements IModel{

    String user_id;
    String name;
    int age;
    String photo;

    public User(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public void initValues() {
        name = "";
        age = 0;
        photo = "";
    }

    @Override
    public boolean validate() {
        boolean ok;
        ok = !user_id.isEmpty() && !name.isEmpty() && age >= 0 && !photo.isEmpty();
    return ok;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
