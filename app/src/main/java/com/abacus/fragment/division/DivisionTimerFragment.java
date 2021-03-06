package com.abacus.fragment.division;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abacus.R;
import com.abacus.viewmodel.DataViewModelDivision;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class DivisionTimerFragment extends Fragment {
    DataViewModelDivision dataViewModelMultiply;
    private TextView tv_multiply;
    private TextView tv_multiply1;
    private TextView texttimer;
    private TextView tv_Right;
    private TextView tv_Wrong;
    private LinearLayout linear;
    private Button btn_next;
    private Button btn_start_again;
    private EditText et_digits1;
    private EditText et_digits2;
    private EditText et_ans;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    int digits1 = 0;
    int digits2 = 0;
    int digits3 = 0;
    int digits4 = 0;
    int multiply = 0;
    float randommultiply=0.0f;
    int randomFirst=0;
    int randomSecond=0;
    int firsttime=0;
    int correct=0;
    int wromg=0;

    Thread t;
    ArrayList<Integer> mResult = new ArrayList<Integer>();
    TextToSpeech t1;



    public DivisionTimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_division_timer, container, false);
        tv_multiply = view.findViewById(R.id.tv_multiply);
        tv_multiply1 = view.findViewById(R.id.tv_multiply1);
        linear = view.findViewById(R.id.linear);
        btn_next = view.findViewById(R.id.btn_next);
        btn_start_again = view.findViewById(R.id.btn_start_again);
        et_digits1 = view.findViewById(R.id.et_digits1);
        et_digits2 = view.findViewById(R.id.et_digits2);
        texttimer = view.findViewById(R.id.texttimer);
        tv_Right = view.findViewById(R.id.tv_right);
        tv_Wrong = view.findViewById(R.id.tv_wrong);
        et_ans = view.findViewById(R.id.et_ans);
        btn_next = view.findViewById(R.id.btn_next);
        btn_start_again = view.findViewById(R.id.btn_start_again);
        dataViewModelMultiply = ViewModelProviders.of(getActivity()).get(DataViewModelDivision.class);
        t1 = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);

                }
            }
        });



        digits1 = Integer.valueOf(dataViewModelMultiply.digits1);
        digits2 = Integer.valueOf(dataViewModelMultiply.digits2);
        digits3 = Integer.valueOf(dataViewModelMultiply.digits3);
        digits4 = Integer.valueOf(dataViewModelMultiply.digits4);
        multiply = Integer.parseInt(dataViewModelMultiply.sum);
        Double time = Double.valueOf(dataViewModelMultiply.time);
        double timeinterval = time * 1000;
        final long timeinterval1 = (long) timeinterval;


        final Random rn = new Random();
        randomFirst = rn.nextInt((digits3 - 1) + 1) + 1;
        randomSecond = rn.nextInt((digits4 - 1) + 1) + 1;
        randommultiply =  randomFirst /  randomSecond;
        t1.speak(String.valueOf(randommultiply), TextToSpeech.QUEUE_FLUSH, null);
        tv_multiply.setText(String.valueOf(randomFirst) + "/" + String.valueOf(randomSecond));
     //   mResult.add(randommultiply);
        tv_Right.setText("Right: 0");
        tv_Wrong.setText("Wrong : 0");
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < multiply; i++) {
                    final int finalI = i;
                    Activity activity = getActivity();
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {



                                texttimer.setText(String.valueOf(finalI + 1) + "/" + dataViewModelMultiply.sum);

                                btn_next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if(firsttime==0){

                                            if (String.valueOf(randommultiply).equals(et_ans.getText().toString())) {
                                                tv_multiply.setText(String.valueOf(randomFirst) + "/" + String.valueOf(randomSecond)+" = "+ randommultiply);
                                                correct= correct+1;
                                                tv_Right.setText("Right"+" "+String.valueOf(correct));

                                            } else {
                                                tv_multiply.setText(String.valueOf(randomFirst) + "/" + String.valueOf(randomSecond)+" = "+ randommultiply);

                                                wromg=wromg+1;
                                                tv_Wrong.setText("Wrong"+" "+String.valueOf(wromg));

                                            }
                                            et_ans.setText("");

                                        }else{
                                            tv_multiply1.setText(String.valueOf(randomFirst) + "/" + String.valueOf(randomSecond));
                                            t1.speak(String.valueOf(randommultiply), TextToSpeech.QUEUE_FLUSH, null);
                                            if (String.valueOf(randommultiply).equals(et_ans.getText().toString())) {
                                                tv_multiply.setText(String.valueOf(randomFirst) + "/" + String.valueOf(randomSecond)+" = "+ randommultiply);
                                                correct= correct+1;
                                                tv_Right.setText("Right"+" "+String.valueOf(correct));
                                            } else {
                                                tv_multiply.setText(String.valueOf(randomFirst) + "/" + String.valueOf(randomSecond)+" = "+ randommultiply);

                                                wromg=wromg+1;
                                                tv_Wrong.setText("Wrong"+" "+String.valueOf(wromg));
                                            }

                                        }

                                        randomFirst = rn.nextInt((digits3 - 1) + 1) + 1;
                                        randomSecond = rn.nextInt((digits4 - 1) + 1) + 1;
                                        randommultiply = randomFirst / randomSecond;

                                        firsttime= firsttime+1;
                                        tv_multiply1.setText(String.valueOf(randomFirst) + "/" + String.valueOf(randomSecond));

                                        et_ans.setText("");


                                    }












                                });


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

                                String ans = et_ans.getText().toString();


                                if (finalI == multiply - 1) {
                                    if (t != null) {

                                        t.interrupt();
                                        t = null;
                                    }
                                    et_ans.setEnabled(false);
                                    btn_next.setEnabled(false);


                                }
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



        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).
                commit();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        if (t != null) {

            t.interrupt();
            t = null;
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (t != null) {

            t.interrupt();
            t = null;
        }

        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
    }
}





