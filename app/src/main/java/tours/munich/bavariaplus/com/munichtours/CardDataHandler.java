package tours.munich.bavariaplus.com.munichtours;


import android.app.Activity;
import android.media.Image;

public class CardDataHandler {
    private String title;
    private String subtitle;
    public final static int TYPE_FAMOUS_PLACES = 0;
    public final static int TYPE_HISTORICAL_PLACES = 1;
    public final static int TYPE_MY_PLACES = 2;
    public final static int TYPE_DISCOVER_QUARTERS = 3;
    public CardDataHandler(Activity context,int title, int subtitle,int type){
        this.title = context.getResources().getString(title);
        this.subtitle = context.getResources().getString(subtitle);
    }
    public String getTitle(){
        return this.title;
    }
    public String getSubtitle(){
        return this.subtitle;
    }
    public Activity getContext(){
        return this.getContext();
    }
    public class ChildCard{
        private String title;
        private int image;
        private String id;
        public ChildCard(int title,int imageRes,String id){
            this.title = getContext().getResources().getString(title);
            this.image = imageRes;
            this.id = id;
            requestData(id);
        }
        private void requestData(String id){

        }
    }
}
