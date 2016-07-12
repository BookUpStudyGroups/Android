package org.Bookup.Android.BookUpApp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Patrick J. Flathers on 6/19/16.
 */

//Basic object to store all the class information
public class ClassEntry implements Parcelable {


    private String dept;
    private String title;
    private String number;
    private long sqlID;
    private String id;
    private String prof;
    private String period;



    public long getSqlID() {
        return sqlID;
    }
    public void setSqlID(long sqlID) {
        this.sqlID = sqlID;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public void setProf(String prof) {
        this.prof = prof;
    }
    public void setPeriod(String period) {
        this.period = period;
    }



    public ClassEntry(){

    }

    public ClassEntry(String dept, String title, String id, String prof, String period){
        this.dept = dept;
        this.title = title;
        this.id = id;
        this.prof = prof;
        this.period = period;
    }


    //Created to be able to take a Class Entry and pass it around Fragments

    protected ClassEntry(Parcel in) {
        dept = in.readString();
        title = in.readString();
        id = in.readString();
        prof = in.readString();
        period = in.readString();
    }

    public static final Creator<ClassEntry> CREATOR = new Creator<ClassEntry>() {
        @Override
        public ClassEntry createFromParcel(Parcel in) {
            return new ClassEntry(in);
        }

        @Override
        public ClassEntry[] newArray(int size) {
            return new ClassEntry[size];
        }
    };

    public String getTitle(){
        return title;
    }

    public String getId(){
        return id;
    }
    public String getDept(){
        return dept;
    }
    public String getProf() {
        return prof;
    }
    public String getPeriod() {
        return period;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dept);
        parcel.writeString(title);
        parcel.writeString(id);
        parcel.writeString(prof);
        parcel.writeString(period);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
