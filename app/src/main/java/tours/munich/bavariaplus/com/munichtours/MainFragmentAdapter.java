package tours.munich.bavariaplus.com.munichtours;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Lukas on 30.05.2018.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    Activity act;
    public MainFragmentAdapter(Activity act,FragmentManager fm){
        super(fm);
        this.act = act;
    }
    @Override
    public Fragment getItem(int pos){
        if(pos == 0) {
            return new DashboardFragment();
        }
        else{
            return new ProfileFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
