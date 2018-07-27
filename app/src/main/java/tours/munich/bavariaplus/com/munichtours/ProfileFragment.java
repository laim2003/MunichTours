package tours.munich.bavariaplus.com.munichtours;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ProfileFragment extends Fragment {
    public Context mContext;
    public ImageButton settingsbtn;
    public View.OnClickListener settingsbtnclick = new View.OnClickListener(){
        public void onClick(View v){
            Intent startac = new Intent(mContext,SettingsActivity.class);
            startActivity(startac);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragview = inflater.inflate(R.layout.fragment_profile, container, false);
        this.mContext = this.getContext();
        settingsbtn = fragview.findViewById(R.id.settingsbutton);
        settingsbtn.setOnClickListener(settingsbtnclick);
        return fragview;
    }
}
