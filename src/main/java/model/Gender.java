package model;

/**
 * Created by tmurzenkov on 9/3/2015.
 */
public enum Gender {
    MALE("MALE"), FEMALE("FEMALE");
    private String gender;

    Gender(String gender){
        this.gender = gender;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
