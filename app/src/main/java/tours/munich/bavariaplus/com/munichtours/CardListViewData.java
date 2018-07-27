package tours.munich.bavariaplus.com.munichtours;

import android.view.View;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CardListViewData extends ArrayList<CardDataHandler.ChildCard>{
    private HashMap<PlaceProperties,Boolean> propertyMap;
    private boolean isHot = false;
    private boolean hasLivecam = false;
    private String title;
    private String subtitle;
    private int imageRes;
    CardListViewData(String title, String subtitle, int imageRes, HashMap<PlaceProperties, Boolean> properties){
        this.title = title;
        this.subtitle = subtitle;
        this.imageRes = imageRes;
        this.propertyMap = properties;
        if(propertyMap.get(PlaceProperties.PROPERTY_HOT)){
            this.isHot = true;
        }
        else if(propertyMap.get(PlaceProperties.PROPERTY_LIVECAM)){
            this.hasLivecam = true;
        }
    }

    public HashMap<PlaceProperties, Boolean> getPropertyMap() {
        return propertyMap;
    }
    public int isHot(){
        if(isHot){
            return View.VISIBLE;
        }
        else{
            return View.GONE;
        }
    }
    public int hasLivecam(){
        if(hasLivecam){
            return View.VISIBLE;
        }
        else{
            return View.GONE;
        }
    }
    public String getTitle(){
        return this.title;
    }
    public String getSubtitle() {
        return this.subtitle;
    }
    public int getImageRes() {
        return this.imageRes;
    }

    public void setHasLivecam(boolean hasLivecam) {
        this.hasLivecam = hasLivecam;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setImageResource(int imageRes) {
        this.imageRes = imageRes;
    }

    public void setPropertyMap(HashMap<PlaceProperties, Boolean> propertyMap) {
        this.propertyMap = propertyMap;
    }
}
