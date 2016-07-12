package org.Bookup.Android.BookUpApp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by alexwolf on 5/14/16.
 */
public class BookUp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        //Initialize Parse Server
//
//        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
//                        .applicationId("rLEJCpuVNM98AqXD2s49TvVJAO1eCYULyhpE824l")
//                        .clientKey("uoJAfVJZwcRhzhUjAkuzb09ZidHD7Q8MwbJ8kxia")
//                        .build()
//        );


        // lets try to initialize parse client
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this,"rLEJCpuVNM98AqXD2s49TvVJAO1eCYULyhpE824l", "uoJAfVJZwcRhzhUjAkuzb09ZidHD7Q8MwbJ8kxia" );
      //  ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);



    }
}
