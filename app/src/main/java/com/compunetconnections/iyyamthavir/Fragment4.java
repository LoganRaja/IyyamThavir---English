package com.compunetconnections.iyyamthavir;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment {


    public Fragment4() {
        // Required empty public constructor
    }



    TextView art, bts, ictc,  ti;
    LinearLayout my_query;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.sti_fragment_try, container, false);
        art = (TextView) v.findViewById(R.id.art);
        bts = (TextView) v.findViewById(R.id.bts);
        ictc = (TextView) v.findViewById(R.id.ictc);
        ti = (TextView) v.findViewById(R.id.ti);
        my_query = (LinearLayout) v.findViewById(R.id.my_query);



        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityWhatAreWe.class);
                intent.putExtra("type","sti");
                startActivity(intent);
            }
        });
        ictc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FAQActivity.class);
                intent.putExtra("type","sti");
                startActivity(intent);
            }
        });  bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Services.class);
                intent.putExtra("type","sti");
                startActivity(intent);

                /*new AlertDialog.Builder(getActivity())
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*/
            }
        });
        ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                intent.putExtra("type","sti");
                startActivity(intent);
            }
        });

        my_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ART_Query_Activity.class);
                intent.putExtra("type","sti");
                startActivity(intent);
               /* new AlertDialog.Builder(getActivity())
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();*/
            }
        });

        /*ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("COMING SOON")
                        .setMessage("This feature will be available soon.")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .setNegativeButton(android.R.string.ok, null).show();
            }
        });*/
       /* ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TIActivity.class);
                startActivity(intent);
            }
        });*/

        return v;
    }

}
