package com.compunetconnections.iyyamthavir;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentStart extends Fragment {
    TextView treatment, symptoms, prognosis, prevention, home,diagnosis;

    public FragmentStart() {
        // Required empty public constructor
    }
    @Override
  public  View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.activity_start_try, container, false);
        home = (TextView) view.findViewById(R.id.home);
        diagnosis = (TextView) view.findViewById(R.id.diagnosis);
        treatment = (TextView) view.findViewById(R.id.treatment);
        prognosis = (TextView) view.findViewById(R.id.prognosis);
        prevention = (TextView) view.findViewById(R.id.prevention);
        symptoms = (TextView) view.findViewById(R.id.symptoms);

        treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TreatmentActivity.class);
                startActivity(intent);
            }
        });
        diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrognosisActivity.class);
                startActivity(intent);
            }
        });
        symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SymptomsActivity.class);
                startActivity(intent);
            }
        });
        prognosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutusActivity.class);
                startActivity(intent);
            }
        });
        prevention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PreventionActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
