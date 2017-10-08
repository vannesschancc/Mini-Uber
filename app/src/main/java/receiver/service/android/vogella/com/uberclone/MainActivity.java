package receiver.service.android.vogella.com.uberclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    String logInTag = "LogIn";

    public void redirectActivity() {
        if (ParseUser.getCurrentUser().get("riderOrDriver").equals("rider")) {
            Intent intent = new Intent(getApplicationContext(), RiderActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), ViewRequestActivity.class);
            startActivity(intent);
        }
    }
    public void getStarted(View view) {
        Switch userTypeSwitch = (Switch) findViewById(R.id.userTypeSwitch);
        Log.i("Switch value", String.valueOf(userTypeSwitch.isChecked()));

        String userType = "rider";

        if (userTypeSwitch.isChecked()) {
            userType = "driver";
        }

        ParseUser.getCurrentUser().put("riderOrDriver", userType);
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                redirectActivity();
            }
        });
        Log.i("Info", "Redirecting as" + userType);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //If no info, then parse user will work as sign up
        if(ParseUser.getCurrentUser() == null) {
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Log.i("Info", "Anonymous login sucessful");
                    } else {
                        Log.i("Info", "Anoymous login failed");
                    }
                }
            });
        } else {
            if (ParseUser.getCurrentUser().get("riderOrDriver") != null) {
                Log.e("Info", "Redirecting as " + ParseUser.getCurrentUser().getCurrentUser().get("riderOrDriver"));
                redirectActivity();
            }

        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
