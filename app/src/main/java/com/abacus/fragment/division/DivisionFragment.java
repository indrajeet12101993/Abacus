package com.abacus.fragment.division;

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
import com.abacus.utils.UtilityMethods;
import com.abacus.viewmodel.DataViewModelDivision;

public class DivisionFragment extends Fragment {

    EditText et_digits1;
    EditText et_digits2;
    EditText et_digits3;
    EditText et_digits4;
    EditText et_sum;
    EditText et_time;
    Button btn_start;

    DataViewModelDivision mDataViewModelDivision;
    public DivisionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_division, container, false);

        mDataViewModelDivision = ViewModelProviders.of(getActivity()).get(DataViewModelDivision.class);

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

                    mDataViewModelDivision.digits1=digits1;
                    mDataViewModelDivision.digits2=digits2;
                    mDataViewModelDivision.digits3=digits3;
                    mDataViewModelDivision.digits4=digits4;
                    mDataViewModelDivision.sum=sum;
                    mDataViewModelDivision.time=time;

                    replaceFragment(new DivisionTimerFragment() );


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

        if(UtilityMethods.isEmpty(digits1)){
            et_digits1.setError(getString(R.string.requiredfield));
            et_digits1.requestFocus();
            return true;
        }
        if(UtilityMethods.isEmpty(digits2)){
            et_digits2.setError(getString(R.string.requiredfield));
            et_digits2.requestFocus();
            return true;
        }

        if(UtilityMethods.isEmpty(digits3)){
            et_digits3.setError(getString(R.string.requiredfield));
            et_digits3.requestFocus();
            return true;
        }

        if(UtilityMethods.isEmpty(digits4)){
            et_digits4.setError(getString(R.string.requiredfield));
            et_digits4.requestFocus();
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







