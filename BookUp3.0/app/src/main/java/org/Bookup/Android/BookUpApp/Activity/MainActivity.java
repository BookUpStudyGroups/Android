package org.Bookup.Android.BookUpApp.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import org.Bookup.Android.BookUpApp.Fragment.LoginFragment;
import org.Bookup.Android.BookUpApp.Fragment.MainFragment;
import org.Bookup.Android.BookUpApp.Globals;
import org.Bookup.Android.BookUpApp.Objects.ClassEntry;

import org.Bookup.Android.BookUpApp.R;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Patrick J. Flathers on 7/8/16.
 */

/*
NOTE:
    application uses many fragments rather than different activities in order to have less overhead.
 */



public class MainActivity extends AppCompatActivity {


    private ParseUser pu;
    private ClassEntry mEntry;
    private ArrayList<ClassEntry> ce;
    private FragmentManager fragMan;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mEntry = new ClassEntry();
        ce = new ArrayList<>();
        getTerm();
    }

    protected void onStart() {
        super.onStart();

        pu = ParseUser.getCurrentUser();
        setFrags();

        getClasses();

    }


    //Sets the Login fragment if the user is not logged in

    private void setFrags(){
        fragMan = getFragmentManager();
        if(pu != null){
            setHomeFrag();
        }
        else {
            fragmentTransaction = fragMan.beginTransaction();
            fragmentTransaction.replace(R.id.placeholder, new LoginFragment(), "LOGIN");
            fragmentTransaction.commit();
        }
    }


    //Sets the Home fragments as the default fragment
    public void setHomeFrag(){
        fragmentTransaction = fragMan.beginTransaction();


        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ce", ce);


        Fragment main = new MainFragment();
        {
            main = new MainFragment();
            main.setArguments(bundle);
            fragmentTransaction.replace(R.id.placeholder, main, "MAIN");
        }
        fragmentTransaction.commit();
    }


    //Currently just using canned login information to make login much faster
    public void logIn(View view){
        String UserEmail;
        String Passcode;

        UserEmail = "patrick.j.flathers.18@dartmouth.edu";
        UserEmail.toLowerCase();
        Passcode = "Password1";


        ParseUser.logInInBackground(UserEmail, Passcode, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                setHomeFrag();
            }
        });
    }



    //Overloaded onBackPressed() in order to bring user back to home fragment after they navigate away.
    //Cool stuff
    public void onBackPressed() {
        Fragment curr = fragMan.findFragmentByTag("ADD");
        Fragment curr2 = fragMan.findFragmentByTag("CLASS");

        if (curr != null && curr.isVisible()){
            ce = new ArrayList<>();
            try {
                Thread.sleep(1000); // this sleep is required or else the activity will load to quickly and stuff will be missing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getClasses();
        }
        else if (curr2 != null && curr2.isVisible()){
            setHomeFrag();
        }
        else{
            super.onBackPressed();
        }
    }

    // Gets classes from the Parse server and create ClassEntries that are added to ce Arraylist
    // Resets the homefragment in order to display changes if any.
    private void getClasses() {

        final ParseQuery<ParseObject> classQuery = ParseQuery.getQuery("Class");
        classQuery.whereEqualTo("students", pu);
        classQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> results, ParseException e) {
                if (e != null) {
                    // There was an error


                    Log.d("sup", "FAIL");
                } else {
                    // Bingo

                    String title;
                    String id;
                    String dept;
                    String prof;
                    String period;
                    Log.d("sup", "Success");


                    int i = 0;
                    for (ParseObject myClass : results) {
                        title = myClass.getString("title");
                        id = myClass.getObjectId();
                        dept = myClass.getString("department");
                        prof = myClass.getString("prof");
                        Log.d("PROF", prof);
                        period = myClass.getString("period");

                        mEntry = new ClassEntry();
                        mEntry.setTitle(title);
                        mEntry.setProf(prof);
                        mEntry.setId(id);
                        mEntry.setDept(dept);
                        mEntry.setPeriod(period);


                        try {
                            Double j = (Double) myClass.getNumber("number");
                            mEntry.setNumber(Double.toString(j));
                        } catch (ClassCastException e1) {
                            Integer j = (Integer) myClass.getNumber("number");
                            mEntry.setNumber(Integer.toString(j));
                        }


                        // long mid = classDB.insertEntry(mEntry);
                        //Log.d("ID", Long.toString(mid));

                        ce.add(mEntry);
                        Log.d("sup", mEntry.getDept());
                        i++;
                    }
                    setHomeFrag();
                }
            }


        });
    }



// gets the current Term form Parse server to display on the homepage.
    private void getTerm() {
        final ParseQuery<ParseObject> classQuery = ParseQuery.getQuery("TermDate");
        classQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> results, ParseException e) {
                if (e != null) {
                    // There was an error


                    Log.d("sup", "FAIL");
                } else {

                    for (ParseObject myClass : results) {

                        if(myClass.getBoolean("ShowWeeks")){
                            Globals.TERM = myClass.getString("Name");
                        }
                    }
                }
            }
        });
    }

    public void buddyClick(View v){
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));





    }
    public void groupClick(View v)  {

        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
    }





}
