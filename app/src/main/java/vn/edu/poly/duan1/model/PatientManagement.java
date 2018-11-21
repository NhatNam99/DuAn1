package vn.edu.poly.duan1.model;

import android.widget.RadioButton;
import android.widget.Spinner;

public class PatientManagement {

    public String Name;
    public String Room;
    public int Code;
    public String Gender;
    public int Age;
    public String Blood;
    public String Doctor;
    public String Nurse;
    public String Symptom;
    public String Diagnose;
    public String Treatment;
    public String HealthStatus;

    public PatientManagement() {

    }

//    public PatientManagement(String name, String room, String code, String gender, String age, String blood, String doctor, String nurse, String symptom, String diagnose, String treatment, String healthStatus) {
//        Name = name;
//        Room = room;
//        Code = code;
//        Gender = gender;
//        Age = age;
//        Blood = blood;
//        Doctor = doctor;
//        Nurse = nurse;
//        Symptom = symptom;
//        Diagnose = diagnose;
//        Treatment = treatment;
//        HealthStatus = healthStatus;
//    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public String getNurse() {
        return Nurse;
    }

    public void setNurse(String nurse) {
        Nurse = nurse;
    }

    public String getSymptom() {
        return Symptom;
    }

    public void setSymptom(String symptom) {
        Symptom = symptom;
    }

    public String getDiagnose() {
        return Diagnose;
    }

    public void setDiagnose(String diagnose) {
        Diagnose = diagnose;
    }

    public String getTreatment() {
        return Treatment;
    }

    public void setTreatment(String treatment) {
        Treatment = treatment;
    }

    public String getHealthStatus() {
        return HealthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        HealthStatus = healthStatus;
    }
}

