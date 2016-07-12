package org.Bookup.Android.BookUpApp.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.Bookup.Android.BookUpApp.Objects.ClassEntry;
import org.Bookup.Android.BookUpApp.R;
import org.Bookup.Android.BookUpApp.Views.CustomListAdapter;


import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Patrick J. Flathers on 7/7/16.
 */



//Fragment that shows the classses currently enrolled in
public class HomeFragment extends Fragment {
    private View view;

    private ParseUser pu;
    private ClassEntry mEntry;
    private ArrayList<ClassEntry> ce;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ce = this.getArguments().getParcelableArrayList("ce");
        Log.d("THIS IS THE ARRAY LIST", Integer.toString(ce.size()));

        view = inflater.inflate(R.layout.activity_main, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.placeholder, new AddClassFragment(), "ADD");
                ft.commit();

            }
        });
        ListView lv = (ListView) view.findViewById(R.id.FragmentListView);
        lv.setEmptyView(view.findViewById(R.id.FragmentListView1));

        listSetup();
        return view;
    }



    public void onResume(){
        super.onResume();


        listSetup();
        setListViewOnClickListener();
    }


    // sets up the list
    private void listSetup(){

        pu = ParseUser.getCurrentUser();
        ListView lv = (ListView) view.findViewById(R.id.FragmentListView);
        lv.setEmptyView(view.findViewById(R.id.FragmentListView1));


        setAdapter(new String[ce.size()]);


    }


    //sets the apadter for the list of classes
    private void setAdapter(String [] length){
        ListView lv = (ListView) view.findViewById(R.id.FragmentListView);
        lv.setEmptyView(view.findViewById(R.id.FragmentListView1));
        CustomListAdapter cla = new CustomListAdapter(getActivity(), 1, length, ce, 0);
        lv.setAdapter(cla);
    }

    //set a Click listener. once there is a click, go to ClassePage
    private void setListViewOnClickListener(){
        ListView lv = (ListView) view.findViewById(R.id.FragmentListView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("ce", ce);
                bundle.putInt("position", position);

                Fragment classPageFragment = new ClassPageFragment();
                classPageFragment.setArguments(bundle);

                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.placeholder, classPageFragment, "CLASS");
                ft.commit();
            }
        });
    }
}
