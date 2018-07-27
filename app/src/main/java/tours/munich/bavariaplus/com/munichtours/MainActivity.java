package tours.munich.bavariaplus.com.munichtours;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.os.ConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ViewPager vpager;
    BottomNavigationView navigation;
    CardView toolbarContainer;
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.navigation_top, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:

            return true;
            case R.id.app_bar_about:

            return true;
            case R.id.app_bar_settings:

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedpref = PreferenceManager.getDefaultSharedPreferences(this);
        Toast.makeText(this,sharedpref.getString("lang",""),Toast.LENGTH_SHORT).show();
        Locale selectedlng;
        if(sharedpref.getString("lang","").equals("sys")) {
            selectedlng = new Locale(ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0).getLanguage());
        }else{
            selectedlng = new Locale(sharedpref.getString("lang", ""));
        }
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = selectedlng;
        res.updateConfiguration(conf, dm);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.navigation_top);
        setSupportActionBar(myToolbar);
        vpager = findViewById(R.id.main_viewpager);
        MainFragmentAdapter fragadapter = new MainFragmentAdapter(this,getSupportFragmentManager());
        vpager.setAdapter(fragadapter);
        vpager.addOnPageChangeListener(vpagerpchange);
        toolbarContainer = findViewById(R.id.toolbarcontainer);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    vpager.setCurrentItem(1,true);
                    toolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                    toolbarContainer.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    vpager.setCurrentItem(0,true);
                    toolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                    toolbarContainer.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_profile:
                    vpager.setCurrentItem(2,true);
                    Log.v("ToolBarHeight","ToolbarContainerMeasuredHeight:"+toolbarContainer.getMeasuredHeight()+" ToolBarMeasuredHeightWithMargin:"+(toolbarContainer.getMeasuredHeight()+8));
                    toolbarContainer.animate().translationY(-(toolbarContainer.getMeasuredHeight()+30)).setInterpolator(new AccelerateInterpolator(2)).start();
                    toolbarContainer.setVisibility(View.GONE);
                    return true;
                default:
                    return false;
            }
        }
    };
    private ViewPager.OnPageChangeListener vpagerpchange = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int itemid = 0;
            switch(position){
                case 0:
                    itemid = R.id.navigation_dashboard;
                break;
                case 1:
                    itemid = R.id.navigation_home;
                    break;
                case 2:
                    itemid = R.id.navigation_profile;
                    break;
            }
            navigation.setSelectedItemId(itemid);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
