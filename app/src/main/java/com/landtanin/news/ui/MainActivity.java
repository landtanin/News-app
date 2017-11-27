package com.landtanin.news.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.landtanin.news.R;
import com.landtanin.news.ui.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----------------------------------------------------------------------

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,
                            MainFragment.newInstance(), "NewsFragment")
                    .commit();
        }

        // ----------------------------------------------------------------------

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {

//        if (item.getItemId() == R.id.....) {
//
//            // do things according to selected option
//
//        } else if (item.getItemId() == R.id....) {
//
//            // do things according to selected option
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
