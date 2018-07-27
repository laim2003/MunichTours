package tours.munich.bavariaplus.com.munichtours;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.ListView;

/**
 * Created by Lukas on 30.05.2018.
 */

public class DashboardFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
        Log.v("DashboardFragment","DashBoardFragment created");
        View itemview =  inflater.inflate(R.layout.fragment_dashboard,parent,false);
        DataInitializator data = new DataInitializator();
        data.init(getActivity());
        DashboardListAdapter adapter = new DashboardListAdapter(this.getActivity(),data.getDashboardData());
        ListView cardlist =  itemview.findViewById(R.id.cards_list);
        cardlist.setAdapter(adapter);
        Log.v("DashBoardFragment","cardlist adapter setUp");
        return itemview;
    }
}
