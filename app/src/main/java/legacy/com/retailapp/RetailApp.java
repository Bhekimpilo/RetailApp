package legacy.com.retailapp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by IIS on 4/5/2016.
 */
public class RetailApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "YBsDmLJHIgmRVuHBcAIkdy9QvDeLi02YtiAxYHy3", "pRDYyRPrfaP3pLst99OfFvZdso1DQSeUAY3ZtgPe");
    }


}
