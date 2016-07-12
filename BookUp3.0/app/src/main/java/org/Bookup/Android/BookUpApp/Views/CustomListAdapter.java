package org.Bookup.Android.BookUpApp.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import org.Bookup.Android.BookUpApp.Objects.ClassEntry;
import org.Bookup.Android.BookUpApp.R;

import java.util.List;


/**
 * Created by Patrick J. Flathers on 6/19/16.
 */
public class CustomListAdapter extends ArrayAdapter<String> {

    private final Context context;
    private List<ClassEntry> ce;
    private int page;


    public CustomListAdapter(Context context, int resource, String[] objects, List<ClassEntry> ce, int page) {
        super(context, R.layout.single_main_list, objects);

        this.page = page;
        this.ce = ce;
        this.context = context;
    }


    //sets up the individual list view icons and text format
    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);







        View rowView=inflater.inflate(R.layout.single_main_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView2);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView3);



        if(page == 0) {
            txtTitle.setText(" " + ce.get(position).getTitle());
        }
        else if(page == 1){
            txtTitle.setText(" " + ce.get(position).getDept() + " " + ce.get(position).getNumber());
        }




        if("COSC".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.cosc);
        }
        else if("ASTRO".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.engs);
        }
        else if("BIOL".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.chem);
        }

       else if("CHEM".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.chem);
        }

        else if("CLST".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.engl);
        }
        else if("COLT".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.colt);
        }
        else if("EARS".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.ears);
        }

        else if("ECON".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.econ);
        }
        else if("ENGL".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.engl);
        }
        else if("ENGS".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.engs);
        }
        else if("ENVS".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.envs);
        }
        else if("GEOG".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.geog);
        }
        else if("HIST".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.engl);
        }

        else if("LING".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.spee);
        }

        else if("MATH".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.math);
        }
        else if("PSYC".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.psyc);
        }
        else if("QSS".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.qss);
        }
        else if("SART".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.sart);
        }
        else if("SPEE".equals(ce.get(position).getDept())){
            imageView.setImageResource(R.drawable.spee);
        }
        else {
            imageView.setImageResource(R.drawable.engl);
        }




        //imageView.setImageResource(R.drawable.cosc);
        extratxt.setText(" "+ ce.get(position).getProf() + ", " + ce.get(position).getPeriod());
        return rowView;

    };

}

