package com.abacus.fragment.calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.abacus.R;

public class CalculatorFragment extends Fragment {
    EditText mEditText;

    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_calculator, container, false);


        Button button0, button1, button2, button3, button4, button5, button6,
                button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
                buttonMul, button10, buttonC, buttonEqual;


        final float[] mValueOne = new float[1];
        final float[] mValueTwo = new float[1];

        final boolean[] Addition = new boolean[1];
        final boolean[] Subtract=new boolean[1];
        final boolean[] Multiplication = new boolean[1];
        final boolean[] Division = new boolean[1];


        button0 = (Button)view.findViewById(R.id.button0);
        button1 = (Button)view.findViewById(R.id.button1);
        button2 = (Button)view.findViewById(R.id.button2);
        button3 = (Button)view.findViewById(R.id.button3);
        button4 = (Button)view.findViewById(R.id.button4);
        button5 = (Button)view.findViewById(R.id.button5);
        button6 = (Button)view.findViewById(R.id.button6);
        button7 = (Button)view.findViewById(R.id.button7);
        button8 = (Button)view.findViewById(R.id.button8);
        button9 = (Button)view.findViewById(R.id.button9);
        button10 = (Button)view.findViewById(R.id.button10);
        buttonAdd = (Button)view.findViewById(R.id.buttonadd);
        buttonSub = (Button)view.findViewById(R.id.buttonsub);
        buttonMul = (Button)view.findViewById(R.id.buttonmul);
        buttonDivision = (Button)view.findViewById(R.id.buttondiv);
        buttonC = (Button)view.findViewById(R.id.buttonC);
        buttonEqual = (Button)view.findViewById(R.id.buttoneql);
        mEditText = (EditText)view.findViewById(R.id.et_digits);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + "0");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEditText == null) {
                    mEditText.setText("");
                } else {
                    mValueOne[0] = Float.parseFloat(mEditText.getText() + "");
                    Addition[0] = true;
                    mEditText.setText(null);
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne[0] = Float.parseFloat(mEditText.getText() + "");
                Subtract[0] = true;
                mEditText.setText(null);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne[0] = Float.parseFloat(mEditText.getText() + "");
                Multiplication[0] = true;
                mEditText.setText(null);
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne[0] = Float.parseFloat(mEditText.getText() + "");
                Division[0] = true;
                mEditText.setText(null);
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo[0] = Float.parseFloat(mEditText.getText() + "");

                if (Addition[0] == true) {
                    mEditText.setText(mValueOne[0] + mValueTwo[0] + "");
                    Addition[0] = false;
                }

                if (Subtract[0]== true) {
                    mEditText.setText(mValueOne[0] - mValueTwo[0] + "");
                    Subtract[0] = false;
                }

                if (Multiplication[0] == true) {
                    mEditText.setText(mValueOne[0] * mValueTwo[0] + "");
                    Multiplication[0] = false;
                }

                if (Division[0] == true) {
                    mEditText.setText(mValueOne[0] / mValueTwo[0] + "");
                    Division[0] = false;
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText(mEditText.getText() + ".");
            }
        });
        return view;
    }
}



