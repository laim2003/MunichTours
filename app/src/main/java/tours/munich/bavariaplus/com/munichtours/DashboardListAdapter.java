package tours.munich.bavariaplus.com.munichtours;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DashboardListAdapter extends ArrayAdapter<CardDataHandler> {
    Activity _context;
    ArrayList<CardDataHandler> list;
    public final static String TAG = "DashBoardListAdapter";
    public DashboardListAdapter(Activity context,ArrayList<CardDataHandler> list){
        super(context,R.layout.dashboard_cardlist_item,list);
        this.list = list;
        this._context = context;
        Log.v(TAG,"New instance called");
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        final CardDataHandler card = list.get(position);
        View listItemView = convertView;
        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.dashboard_cardlist_item, parent, false);
        }
        TextView title = listItemView.findViewById(R.id.card_title);
        TextView subtitle = listItemView.findViewById(R.id.card_subtitle);
        RecyclerView list = listItemView.findViewById(R.id.list);
        title.setText(card.getTitle());
        subtitle.setText(card.getSubtitle());
        CardViewListAdapter cvladapter = new CardViewListAdapter(getContext(),this._context);
        list.setAdapter(cvladapter);
        Log.v(TAG,"Returning itemview");
        return listItemView;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
