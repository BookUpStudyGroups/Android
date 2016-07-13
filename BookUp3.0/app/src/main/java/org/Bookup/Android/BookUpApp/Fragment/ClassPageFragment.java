package org.Bookup.Android.BookUpApp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.Bookup.Android.BookUpApp.Objects.ClassEntry;
import org.Bookup.Android.BookUpApp.R;


import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Patrick J. Flathers on 7/8/16.
 */

/*TODO: add a delete class button
TODO: add addStudyBuddy functionality
TODO: add addStudyGroup functionality

 */

public class ClassPageFragment extends Fragment {


    ParseUser pu;
    View view;
    ClassEntry mEntry;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_single_class, container, false);
        pu = ParseUser.getCurrentUser();
        ArrayList<ClassEntry> ce = getArguments().getParcelableArrayList("ce");
        mEntry = ce.get(getArguments().getInt("position"));
        TextView tv = (TextView) view.findViewById(R.id.ClassName);
        tv.setText(mEntry.getDept() + " " +mEntry.getNumber());
        return view;
    }






}
