package com.abacus.fragment.adddecimal;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.abacus.R;
import com.abacus.fragment.addition.AdditionTimerFragment;
import com.abacus.utils.UtilityMethods;
import com.abacus.viewmodel.DataViewModel;

public class DecimalAddFragment extends Fragment {
    EditText et_digits;
    EditText et_sum;
    EditText et_time;
    Button btn_start;

    DataViewModel mDataViewModel;

    public DecimalAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View view= inflater.inflate(R.layout.fragment_decimal_add, container, false);
        mDataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        et_digits=view.findViewById(R.id.et_digits);
        et_sum=view.findViewById(R.id.et_sum);
        et_time=view.findViewById(R.id.et_time);
        btn_start=view.findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!validation()){

                    String digits = et_digits.getText().toString();
                    String sum = et_sum.getText().toString();
                    String time = et_time.getText().toString();

                    mDataViewModel.digits=digits;
                    mDataViewModel.sum=sum;
                    mDataViewModel.time=time;

                    replaceFragment(new DecimalTimerFragment() );


                }

            }
        });

        return view;


    }

    private boolean validation() {
        String digits = et_digits.getText().toString();
        String sum = et_sum.getText().toString();
        String time = et_time.getText().toString();

        if(UtilityMethods.isEmpty(digits)){
            et_digits.setError(getString(R.string.requiredfield));
            et_digits.requestFocus();
            return true;
        }
        if(UtilityMethods.isEmpty(sum)){
            et_sum.setError(getString(R.string.requiredfield));
            et_sum.requestFocus();
            return true;
        }
        if(UtilityMethods.isEmpty(time)){
            et_time.setError(getString(R.string.requiredfield));
            et_time.requestFocus();
            return true;
        }

        return false;

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).
                commit();
    }


}
