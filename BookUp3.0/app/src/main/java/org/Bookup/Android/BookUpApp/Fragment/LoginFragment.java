package org.Bookup.Android.BookUpApp.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.Bookup.Android.BookUpApp.Activity.MainActivity;

import com.parse.ParseUser;
import org.Bookup.Android.BookUpApp.R;

/**
 * Created by Patrick J. Flathers on 7/7/16.
 */
public class LoginFragment extends Fragment {

    private View view;
    private EditText mEmailEdit;
    private EditText mPasscodeEdit;



    // simple login
    //TODO: add create account and Password Reset Functionality
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    public void onStart(){
        super.onStart();
        Button btnSign = (Button) view.findViewById(R.id.signupbutt);
        btnSign.setEnabled(true);

        //Check if user already logged in
        ParseUser currentUser = ParseUser.getCurrentUser();


        if (currentUser != null) {
            MainActivity my = (MainActivity) getActivity();
            my.setHomeFrag();
        } else{  // show the login screen
            mEmailEdit= (EditText) view.findViewById(R.id.email_add);
            mPasscodeEdit= (EditText) view.findViewById(R.id.email_pass);

        }
    }
}