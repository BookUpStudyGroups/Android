package org.Bookup.Android.BookUpApp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.Bookup.Android.BookUpApp.Objects.ClassEntry;

import org.Bookup.Android.BookUpApp.Views.CustomListAdapter;

import org.Bookup.Android.BookUpApp.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick J. Flathers on 7/7/16.
 */


// This Fragment is designed to add classes to the parse database

public class AddClassFragment extends Fragment {

    private View view;
    private ParseUser pu;
    private ClassEntry mEntry;
    private ArrayList<ClassEntry> ce;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_class, container, false);
        pu = ParseUser.getCurrentUser();
        setOnclickListener();
        return view;
    }


    public void onStart(){
        super.onStart();
        ce = new ArrayList<>();
        getClasses();
    }




// sets the listview adapter to show all current classes
    private void setAdapter(String [] length){
        ListView lv = (ListView) view.findViewById(R.id.addClassListView);
        lv.setEmptyView(view.findViewById(R.id.FragmentListView1));


        if (ce.size() != 0) {

            CustomListAdapter cla = new CustomListAdapter(getActivity(), 1, length, ce, 0);
            lv.setAdapter(cla);
        }

    }



    //have to get classes again here incase there is a change in the server after main activity was created, slow but it happens.
    //in the future could be stored locally in Mainactivity arraylist and only update if there are changes.

    private void getClasses() {

        final ParseQuery<ParseObject> classQuery = ParseQuery.getQuery("Class");
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

                        ce.add(mEntry);
                        Log.d("sup", mEntry.getDept());
                    }
                }
                setAdapter(new String[ce.size()]);
            }


        });
    }

    // sets onClick listener to add class to user if clicked.
    private void setOnclickListener() {
        ListView lv = (ListView) view.findViewById(R.id.addClassListView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ClassEntry entry = ce.get(position);


                final ParseQuery<ParseObject> classQuery = ParseQuery.getQuery("Class");
                classQuery.getInBackground(entry.getId(), new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {
                        if (e != null) {
                            // There was an error
                            Log.d("sup", "FAIL");
                        }
                        else{
                            ParseRelation pr = parseObject.getRelation("students");
                            pr.add(pu);
                            parseObject.saveInBackground();
                            getActivity().onBackPressed();
                        }
                    }
                });
            }
        });
    }







}
