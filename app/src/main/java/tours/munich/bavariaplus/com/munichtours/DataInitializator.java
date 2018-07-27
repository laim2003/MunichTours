package tours.munich.bavariaplus.com.munichtours;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tours.munich.bavariaplus.com.munichtours.CardDataHandler;

public class DataInitializator{
    private ArrayList<CardDataHandler> carddataArrayList = new ArrayList<>();
    private ArrayList<CardListViewData> cardListViewDataArrayList = new ArrayList<>();
    HashMap<PlaceProperties,Boolean> marienPlatzProperties = new HashMap<>();
    private String[] placeArray;
    private Resources res;
    public void init(Activity context){
        carddataArrayList.add(new CardDataHandler(context,R.string.card_title_famous_places,R.string.card_subtitle_famous_places,CardDataHandler.TYPE_FAMOUS_PLACES));
        carddataArrayList.add(new CardDataHandler(context,R.string.card_title_discover_quarters,R.string.card_subtitle_discover_quarters,CardDataHandler.TYPE_DISCOVER_QUARTERS));
        carddataArrayList.add(new CardDataHandler(context,R.string.card_title_historical_places,R.string.card_subtitle_historical_places,CardDataHandler.TYPE_HISTORICAL_PLACES));
        carddataArrayList.add(new CardDataHandler(context,R.string.card_title_myplaces,R.string.card_subtitle_myplaces,CardDataHandler.TYPE_MY_PLACES));
        Log.v("DataInitalizator.init","Initialized new Data");
    }
    public void initCardChildList(Activity context){
        res = context.getResources();
        placeArray = context.getResources().getStringArray(R.array.places);
        marienPlatzProperties.put(PlaceProperties.PROPERTY_HOT,true);
        marienPlatzProperties.put(PlaceProperties.PROPERTY_LIVECAM,true);
        cardListViewDataArrayList.add(new CardListViewData(placeArray[0],res.getString(R.string.places_marienplatz_descr_short),R.drawable.dashboard_marienplatz,marienPlatzProperties));
        Log.v("DataInitializatorCard","initalized child recyclerview");
    }
    public ArrayList<CardDataHandler> getDashboardData(){
        return carddataArrayList;
    }
    public CardListViewData getChildListData(int pos){
        return cardListViewDataArrayList.get(pos);
    }
}
