package com.yunwltn98.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    // 각 프레그먼트를 멤버 변수로 만든다
    public Fragment firstFragment;
    public Fragment secondFragment;
    int Token = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("채팅 홈");

//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);

        getSupportActionBar().setTitle("홈");

        navigationView = findViewById(R.id.bottomNavigationView);

        firstFragment = new firstFragment();
        secondFragment = new secondFragment();

        // 탭바를 누르면 동작하는 코드
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Fragment fragment = null;

                if (itemId == R.id.firstFragment) {
                    fragment = firstFragment;
                    getSupportActionBar().setTitle("채팅");
                } else if (itemId == R.id.secondFragment) {
                    fragment = secondFragment;
                    getSupportActionBar().setTitle("내 정보");
                }

                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
            return true;
        } else {
            return false;
        }
    }

}

