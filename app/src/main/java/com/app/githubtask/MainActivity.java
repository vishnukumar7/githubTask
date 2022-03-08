package com.app.githubtask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.githubtask.databinding.ActivityMainBinding;
import com.app.githubtask.ui.dashboard.DashboardFragment;
import com.app.githubtask.ui.home.HomeFragment;
import com.app.githubtask.ui.notifications.NotificationsFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private ActivityMainBinding binding;
    private Fragment content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loadFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(this);
    }

    private void loadFragment(Fragment fragment) {
        content = fragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item and false if the item should not be
     * selected. Consider setting non-selectable items as disabled preemptively to make them
     * appear non-interactive.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                if (!(content instanceof HomeFragment))
                    loadFragment(new HomeFragment());
                break;
            case R.id.navigation_dashboard:
                if (!(content instanceof DashboardFragment))
                    loadFragment(new DashboardFragment());
                break;
            case R.id.navigation_notifications:
                if (!(content instanceof NotificationsFragment))
                    loadFragment(new NotificationsFragment());
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(!(content instanceof HomeFragment)){
            binding.bottomNavigation.setSelectedItemId(R.id.navigation_home);
            loadFragment(new HomeFragment());
        }else{
            finish();
        }
    }
}