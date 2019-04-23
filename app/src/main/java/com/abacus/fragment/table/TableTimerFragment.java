package com.abacus.fragment.table;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abacus.R;
import com.abacus.viewmodel.DataViewModel;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TableTimerFragment extends Fragment {

    DataViewModel mDataViewModel;
    private TextView tv_multiply1;
    private TextView texttimer;
    private LinearLayout linear;
    private Button btn_next;
    private Button btn_start_again;
    int multiplier=1;

    Thread t;
    ArrayList<Integer> mResult = new ArrayList<Integer>();
    TextToSpeech t1;
    public TableTimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view=inflater.inflate(R.layout.fragment_table_timer, container, false);



        btn_next = view.findViewById(R.id.btn_next);
        btn_start_again = view.findViewById(R.id.btn_start_again);
        texttimer = view.findViewById(R.id.texttimer);
        tv_multiply1 = view.findViewById(R.id.tv_multiply);




        mDataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);
        t1 = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);

                }
            }
        });
        int digits = Integer.valueOf(mDataViewModel.digits);
        final int sum = Integer.valueOf(mDataViewModel.sum);
        final int time1 = Integer.valueOf(mDataViewModel.time);
        Double time = Double.valueOf(mDataViewModel.time);
        double timeinterval = time * 1000;
        final long timeinterval1 = (long) timeinterval;


        t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < time1; i++) {
                    final int finalI = i;
                    Activity activity = getActivity();
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                texttimer.setText(String.valueOf(finalI + 1) + "/" + mDataViewModel.time);




                            }
                        });
                    }

                    try {
                        Thread.sleep(timeinterval1);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {


                            }
                        });
                    }

                }
            }
        });
        t.start();

        btn_start_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        tv_multiply1.setText(mDataViewModel.digits +" * " + multiplier+" =  " +" "+String.valueOf(Integer.valueOf(mDataViewModel.digits)*multiplier));
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                multiplier=multiplier+1;
                tv_multiply1.setText(mDataViewModel.digits +" * " + multiplier+" =  " +" "+String.valueOf(Integer.valueOf(mDataViewModel.digits)*multiplier));
                t1.speak(String.valueOf(Integer.valueOf(mDataViewModel.digits)*multiplier), TextToSpeech.QUEUE_FLUSH, null);



                if(Integer.parseInt(mDataViewModel.sum)==multiplier){
                    btn_next.setEnabled(false);
                    btn_start_again.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(),"Steps Completed",Toast.LENGTH_SHORT).show();
                }


            }





        });


        return view;
    }

}
