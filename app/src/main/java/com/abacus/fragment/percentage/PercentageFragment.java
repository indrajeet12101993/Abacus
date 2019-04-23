package com.abacus.fragment.percentage;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.abacus.utils.DataViewModelMultiply;
import com.abacus.R;
import com.abacus.utils.UtilityMethods;

public class PercentageFragment extends Fragment {

    EditText et_digits1;
    EditText et_digits2;
    EditText et_digits3;
    EditText et_digits4;
    EditText et_sum;
    EditText et_time;
    Button btn_start;

    DataViewModelMultiply mDataViewModelMultiply;
    public PercentageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_percentage, container, false);

        mDataViewModelMultiply = ViewModelProviders.of(getActivity()).get(DataViewModelMultiply.class);

        et_digits1=view.findViewById(R.id.et_digits1);
        et_digits2=view.findViewById(R.id.et_digits2);
        et_digits3=view.findViewById(R.id.et_digits3);
        et_digits4=view.findViewById(R.id.et_digits4);
        et_sum=view.findViewById(R.id.et_sum);
        et_time=view.findViewById(R.id.et_time);
        btn_start=view.findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validation()){

                    String digits1 = et_digits1.getText().toString();
                    String digits2 = et_digits2.getText().toString();
                    String digits3 = et_digits3.getText().toString();
                    String digits4 = et_digits4.getText().toString();
                    String sum = et_sum.getText().toString();
                    String time = et_time.getText().toString();

                    mDataViewModelMultiply.digits1=digits1;
                    mDataViewModelMultiply.digits2=digits2;
                    mDataViewModelMultiply.digits3=digits3;
                    mDataViewModelMultiply.digits4=digits4;
                    mDataViewModelMultiply.sum=sum;
                    mDataViewModelMultiply.time=time;


                    replaceFragment(new PercentageTimerFragment());


                }

            }
        });

        return view;
    }

    private boolean validation() {
        String digits1 = et_digits1.getText().toString();
        String digits2 = et_digits2.getText().toString();
        String digits3 = et_digits3.getText().toString();
        String digits4 = et_digits4.getText().toString();
        String sum = et_sum.getText().toString();
        String time = et_time.getText().toString();

        if (UtilityMethods.isEmpty(digits1)) {
            et_digits1.setError(getString(R.string.requiredfield));
            et_digits1.requestFocus();
            return true;
        }
        if (UtilityMethods.isEmpty(digits2)) {
            et_digits2.setError(getString(R.string.requiredfield));
            et_digits2.requestFocus();
            return true;
        }

        if (UtilityMethods.isEmpty(digits3)) {
            et_digits3.setError(getString(R.string.requiredfield));
            et_digits3.requestFocus();
            return true;
        }

        if (UtilityMethods.isEmpty(digits4)) {
            et_digits4.setError(getString(R.string.requiredfield));
            et_digits4.requestFocus();
            return true;
        }

        if (UtilityMethods.isEmpty(sum)) {
            et_sum.setError(getString(R.string.requiredfield));
            et_sum.requestFocus();
            return true;
        }
        if (UtilityMethods.isEmpty(time)) {
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
