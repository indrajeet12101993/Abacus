package com.abacus.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.abacus.fragment.addition.AdditionFragment;
import com.abacus.fragment.HomeFragment;
import com.abacus.R;
import com.abacus.fragment.addsub.AddSubFragment;
import com.abacus.fragment.calculator.CalculatorFragment;
import com.abacus.fragment.cuberoot.CubeRootFragment;
import com.abacus.fragment.adddecimal.DecimalAddFragment;
import com.abacus.fragment.division.DivisionFragment;
import com.abacus.fragment.multiply.MultiplyFragment;
import com.abacus.fragment.percentage.PercentageFragment;
import com.abacus.fragment.squareroot.SqureRootFragment;
import com.abacus.fragment.stopwatch.StopWatchFragment;
import com.abacus.fragment.table.TableFragment;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AdditionFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_addition);
        }




        addFragment();


    }

    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment mFragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, mFragment).commit();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_addition:
                replaceFragment( new AdditionFragment());

                break;

            case R.id.nav_add:
                replaceFragment( new AddSubFragment());
                break;

            case R.id.nav_decimal:
                replaceFragment( new DecimalAddFragment());

                break;

            case R.id.nav_multiply:
                replaceFragment( new MultiplyFragment());

                break;

            case R.id.nav_longmultiplication:
                replaceFragment( new MultiplyFragment());

                break;

            case R.id.nav_division:
                replaceFragment( new DivisionFragment());

                break;

            case R.id.nav_squareroot:
                replaceFragment( new SqureRootFragment());

                break;

            case R.id.nav_cuberoot:
                replaceFragment( new CubeRootFragment());

                break;

            case R.id.nav_table:
                replaceFragment( new TableFragment());

                break;

            case R.id.nav_percentage:
                replaceFragment( new PercentageFragment());

                break;

            case R.id.nav_stopwatch:
                replaceFragment( new StopWatchFragment());

                break;

            case R.id.nav_calculator:
                replaceFragment( new CalculatorFragment());

                break;
        }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }


}