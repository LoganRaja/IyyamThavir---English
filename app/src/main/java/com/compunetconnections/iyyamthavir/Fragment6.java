package com.compunetconnections.iyyamthavir;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment6 extends Fragment {


    public Fragment6() {
        // Required empty public constructor
    }



    TextView art, bts, ictc,  ti;

    LinearLayout my_query;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.pptct_fragment_try, container, false);
        art = (TextView) v.findViewById(R.id.art);
        bts = (TextView) v.findViewById(R.id.bts);
        ictc = (TextView) v.findViewById(R.id.ictc);
        ti = (TextView) v.findViewById(R.id.ti_pptct);
        my_query = (LinearLayout) v.findViewById(R.id.my_query);


      /*  art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ARTActivity.class);
                startActivity(intent);
            }
        });
        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BTSActivity.class);
                startActivity(intent);
            }
        });
        ictc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ICTCActivity.class);
                startActivity(intent);
            }
        });
        sti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), STIActivity.class);
                startActivity(intent);
            }
        });*/
        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityWhatAreWe.class);
                intent.putExtra("type","pptct");
                startActivity(intent);
            }
        });
        ictc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FAQActivity.class);
                intent.putExtra("type","pptct");
                startActivity(intent);
            }
        });  bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Services.class);
                intent.putExtra("type","pptct");
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
                intent.putExtra("type","ictc");
                intent.putExtra("heading","pptct");
                startActivity(intent);
            }
        });

        my_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ART_Query_Activity.class);
                intent.putExtra("type","pptct");
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

        return v;
    }

}
