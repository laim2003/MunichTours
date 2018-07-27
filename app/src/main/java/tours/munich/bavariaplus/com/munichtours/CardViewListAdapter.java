package tours.munich.bavariaplus.com.munichtours;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CardViewListAdapter extends RecyclerView.Adapter<CardViewListAdapter.ItemViewHolder> {
    private LayoutInflater inflater;
    private Activity c;
    public CardViewListAdapter(Context context,Activity activity){
        this.c = activity;
        inflater = LayoutInflater.from(c);
        Log.v("CardViewListAdapter","New instance of CardViewListAdapter called");
    }
    @Override
    public CardViewListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.card_recyclerview_item,parent,false);
        ItemViewHolder holder = new ItemViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardViewListAdapter.ItemViewHolder holder, int position) {
        DataInitializator data = new DataInitializator();
        data.initCardChildList(c);
        CardListViewData item = data.getChildListData(position);
        holder.title.setText(item.getTitle());
        holder.subtitle.setText(item.getSubtitle());
        holder.thumbnail.setImageResource(item.getImageRes());
        holder.liveIcon.setVisibility(item.hasLivecam());
        holder.hotIcon.setVisibility(item.isHot());
        Log.v("onBindView","New view bound to item");
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView subtitle;
        ImageView thumbnail;
        LinearLayout hotIcon;
        LinearLayout liveIcon;
        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            subtitle = itemView.findViewById(R.id.item_subtitle);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            hotIcon = itemView.findViewById(R.id.property_hot_container);
            liveIcon = itemView.findViewById(R.id.property_live_container);
        }
    }
}
