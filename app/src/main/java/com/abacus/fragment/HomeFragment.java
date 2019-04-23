package com.abacus.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abacus.fragment.adddecimal.DecimalAddFragment;
import com.abacus.fragment.addition.AdditionFragment;
import com.abacus.fragment.addsub.AddSubFragment;
import com.abacus.fragment.calculator.CalculatorFragment;
import com.abacus.fragment.cuberoot.CubeRootFragment;
import com.abacus.fragment.division.DivisionFragment;
import com.abacus.fragment.multiply.MultiplyFragment;
import com.abacus.fragment.percentage.PercentageFragment;
import com.abacus.fragment.squareroot.SqureRootFragment;
import com.abacus.fragment.stopwatch.StopWatchFragment;
import com.abacus.fragment.table.TableFragment;
import com.abacus.model.GameType;
import com.abacus.adapter.GameTypeRecyclerAdapter;
import com.abacus.R;
import com.abacus.interfaceCallBack.ItemListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements ItemListener {

    RecyclerView recyclerView;
    ArrayList<GameType> mGameType;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mGameType = new ArrayList<GameType>();
        mGameType.add(new GameType("Addition", R.drawable.splash_copy_4));
        mGameType.add(new GameType("Add/Sub", R.drawable.maths));
        mGameType.add(new GameType("Decimal Add/Sub", R.drawable.maths_copy));
        mGameType.add(new GameType("Multiply", R.drawable.multiply));
        mGameType.add(new GameType("Long Multiplication", R.drawable.multiply));
        mGameType.add(new GameType("Division", R.drawable.splash_copy_2));
        mGameType.add(new GameType("Square Root", R.drawable.splash_copy_3));
        mGameType.add(new GameType("Cube root", R.drawable.square_root_copy));
        mGameType.add(new GameType("Table Practice", R.drawable.multiply_copy_2));
        mGameType.add(new GameType("Percentage", R.drawable.percentage));
        mGameType.add(new GameType("Stop Watch", R.drawable.stop));
        mGameType.add(new GameType("Calculator", R.drawable.calculator));

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        GameTypeRecyclerAdapter adapter = new GameTypeRecyclerAdapter(getActivity(), mGameType, this);
        recyclerView.setAdapter(adapter);
    return view;
    }



    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).
                commit();
    }

    @Override
    public void onItemClick(GameType item, int position) {
        if (position == 0) {
            replaceFragment(new AdditionFragment());

        }
        if (position == 1) {
            replaceFragment(new AddSubFragment());

        }

        if (position == 2) {
            replaceFragment(new DecimalAddFragment());

        }

        if (position == 3) {
            replaceFragment(new MultiplyFragment());

        }

        if (position == 4) {
            replaceFragment(new MultiplyFragment());

        }

        if (position == 5) {
            replaceFragment(new DivisionFragment());

        }
        if (position == 10) {
            replaceFragment(new StopWatchFragment());
            return;

        }
        if (position == 11) {
            replaceFragment(new CalculatorFragment());

        }

        if (position == 6) {
            replaceFragment(new SqureRootFragment());

        }

        if (position == 7) {
            replaceFragment(new CubeRootFragment());

        }

        if (position == 8) {
            replaceFragment(new TableFragment());

        }

        if (position == 9) {
            replaceFragment(new PercentageFragment());

        }







    }
}
