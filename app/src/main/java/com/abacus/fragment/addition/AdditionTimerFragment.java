package com.abacus.fragment.addition;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
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


import com.abacus.viewmodel.DataViewModel;
import com.abacus.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class AdditionTimerFragment extends Fragment {

    DataViewModel mDataViewModel;
    private TextView tv_sum;
    private LinearLayout linear;
    private Button btn_check;
    private Button btn_start_again;
    private EditText et_digits;
    private EditText et_digits1;
    int min = 0;
    int max = 9;
    Thread t;
    ArrayList<Integer> mResult = new ArrayList<Integer>();
    TextToSpeech t1;
    public AdditionTimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addition_timer, container, false);
        tv_sum = view.findViewById(R.id.tv_sum);
        linear = view.findViewById(R.id.linear);
        btn_check = view.findViewById(R.id.btn_check);
        btn_start_again = view.findViewById(R.id.btn_start_again);
        et_digits = view.findViewById(R.id.et_digits);
        et_digits1 = view.findViewById(R.id.et_digits1);
        mDataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);
        t1=new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);

                }
            }
        });

        int digits = Integer.valueOf(mDataViewModel.digits);
        final int sum = Integer.valueOf(mDataViewModel.sum);
        Double time = Double.valueOf(mDataViewModel.time);
        double timeinterval = time * 1000;
        final long timeinterval1 = (long) timeinterval;

        if (digits == 1) {
            min = 0;
            max = 9;
        }
        if (digits == 2) {
            min = 10;
            max = 99;
        }
        if (digits == 3) {
            min = 100;
            max = 999;
        }
        if (digits == 4) {
            min = 1000;
            max = 9999;
        }
        if (digits == 5) {
            min = 10000;
            max = 99999;
        }
        if (digits == 6) {
            min = 100000;
            max = 999999;
        }
        if (digits == 7) {
            min = 1000000;
            max = 9999999;
        }
        if (digits == 7) {
            min = 10000000;
            max = 99999999;
        }
        if (digits == 8) {
            min = 100000000;
            max = 999999999;
        }
        if (digits == 9) {
            min = 100000000;
            max = 999999999;
        }

        final Random rn = new Random();
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < sum; i++) {
                    final int finalI = i;
                    Activity activity = getActivity();
                    if (activity != null) {
                        activity.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                int randomNum = rn.nextInt((max - min) + 1) + min;
                                t1.speak( String.valueOf(randomNum), TextToSpeech.QUEUE_FLUSH, null);
                                tv_sum.setText(String.valueOf(randomNum));
                                mResult.add(randomNum);


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

                                if (finalI == sum - 1) {
                                    if (t != null) {

                                        t.interrupt();
                                        t = null;
                                    }
                                    linear.setVisibility(View.VISIBLE);
                                    tv_sum.setVisibility(View.GONE);


                                }
                            }
                        });
                    }


                }
            }
        });
        t.start();

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String uservalue =  et_digits.getText().toString();
              int sum=0;

              for(int i=0;i<mResult.size();i++){

                  sum=sum+mResult.get(i);


              }

              if(!uservalue.isEmpty()){
                  int integerresult = Integer.parseInt(uservalue);

                   if(sum==integerresult){

                       et_digits1.setText("Congrats You Win the match: "+" "+sum);
                       et_digits1.requestFocus();

                   }
                   else{

                       et_digits1.setText(" Result missmatch! Correct answer is: "+" "+sum);
                       et_digits1.requestFocus();

                   }

              }else {
                  et_digits.setError(getString(R.string.requiredfield));
                  et_digits.requestFocus();
              }

            }
        });

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
