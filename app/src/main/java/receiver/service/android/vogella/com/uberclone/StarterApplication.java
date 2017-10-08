package receiver.service.android.vogella.com.uberclone;

import android.app.Application;
import android.service.autofill.SaveCallback;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by vanne on 10/6/2017.
 */

public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("10452ee8b2bbe61f7869a3270a4eb2ae9f1e2473")
                .clientKey("00ea939a349e1dc6bba123524efbeaa27b1726e9")
                .server("http://ec2-52-42-232-144.us-west-2.compute.amazonaws.com:80/parse/")
                .build()
        );

        //ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
