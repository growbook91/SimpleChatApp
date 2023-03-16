package com.github.growbook91.simplechatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainMenuChatFragment fragmentChat = new MainMenuChatFragment();
    private MainMenuFriendFragment fragmentFriend = new MainMenuFriendFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, fragmentFriend).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());
        bottomNavigationView.setItemIconTintList(null);

    }

    class ItemSelectedListener implements BottomNavigationView.OnItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_chat:
                    transaction.replace(R.id.menu_frame_layout, fragmentChat).commitAllowingStateLoss();
                    break;
                case R.id.menu_friend:
                    transaction.replace(R.id.menu_frame_layout, fragmentFriend).commitAllowingStateLoss();
                    break;

            }

            return true;
        }

    }
}