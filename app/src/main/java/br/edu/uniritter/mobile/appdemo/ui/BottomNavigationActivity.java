package br.edu.uniritter.mobile.appdemo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentResultOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import br.edu.uniritter.mobile.appdemo.R;
import br.edu.uniritter.mobile.appdemo.ui.fragments.FragmentOne;
import br.edu.uniritter.mobile.appdemo.ui.fragments.FragmentThree;
import br.edu.uniritter.mobile.appdemo.ui.fragments.FragmentTwo;

public class BottomNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        BottomNavigationView bnv = findViewById(R.id.bottomNavigationView);
        NavHostFragment nhf  = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);
        NavController nhc = nhf.getNavController();

        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuItem1) {
                    nhc.navigate(R.id.fragmentOne);
                    return true;
                }
                if (item.getItemId() == R.id.menuItem2) {
                    //nhc.navigate(R.id.fragmentTwo);
                    nhc.navigate(R.id.action_fragmentOne_to_fragmentTwo);
                    return true;
                }
                if (item.getItemId() == R.id.menuItem3) {
                    nhc.navigate(R.id.fragmentThree);
                    return true;
                }


                return false;
            }
        });

/*
        if (savedInstanceState == null) {
            Fragment fragment = new FragmentOne();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainerView3, fragment, FragmentOne.TAG)
                    .commit();
            fragment = new FragmentTwo();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainerView3, fragment, FragmentTwo.TAG)
                    .commit();
            fragment = new FragmentThree();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainerView3, fragment, FragmentThree.TAG)
                    .commit();
        }
*/

    }
}