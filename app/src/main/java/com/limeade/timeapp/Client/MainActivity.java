package com.limeade.timeapp.Client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;

import com.limeade.timeapp.External.RequestManager;
import com.limeade.timeapp.Manager.LocationInfo;
import com.limeade.timeapp.R;

public class MainActivity extends AppCompatActivity {

    LocationUI[] locations; // array of locations currently displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setUpClocks();
    }

    // Displays intro message, initializes all location objects, and instantiates
    // RequestManager
    private void initUI() {
        RequestManager.instantiateRequestManager(getApplicationContext());

        IntroDialogFragment introDialog = new IntroDialogFragment();
        introDialog.show(getFragmentManager(), "GREET");

        LocationUI userLocation = new LocationUI((TextClock)findViewById(R.id.userTextClock));

        LocationInfo locationInfo1 = new LocationInfo(getString(R.string.DefaultCity1));
        Button changeButton1 = findViewById(R.id.changeButton1);
        EditText locationName1 = findViewById(R.id.cityLocation1);
        TextClock locationTextClock1 = findViewById(R.id.cityTextClock1);
        LocationUI location1 =  new LocationUI(locationInfo1, changeButton1, locationTextClock1, locationName1 );

        LocationInfo locationInfo2 = new LocationInfo(getString(R.string.DefaultCity2));
        Button changeButton2 = findViewById(R.id.changeButton2);
        EditText locationName2 = findViewById(R.id.cityLocation2);
        TextClock locationTextClock2 = findViewById(R.id.cityTextClock2);
        LocationUI location2 =  new LocationUI(locationInfo2, changeButton2, locationTextClock2, locationName2);

        LocationInfo locationInfo3 = new LocationInfo(getString(R.string.DefaultCity3));
        Button changeButton3 = findViewById(R.id.changeButton3);
        EditText locationName3 = findViewById(R.id.cityLocation3);
        TextClock locationTextClock3 = findViewById(R.id.cityTextClock3);
        LocationUI location3 =  new LocationUI(locationInfo3, changeButton3, locationTextClock3, locationName3);

        LocationInfo locationInfo4 = new LocationInfo(getString(R.string.DefaultCity4));
        Button changeButton4 = findViewById(R.id.changeButton4);
        EditText locationName4 = findViewById(R.id.cityLocation4);
        TextClock locationTextClock4 = findViewById(R.id.cityTextClock4);
        LocationUI location4 =  new LocationUI(locationInfo4, changeButton4, locationTextClock4, locationName4 );

        locations = new LocationUI[]{userLocation, location1, location2, location3, location4};
    }

    // Sets format for all clocks and activates them based on location name
    private void setUpClocks(){
        for (LocationUI city : locations){
            city.getTextClock().setFormat24Hour("HH:mm:ss (zzzz)");
            city.getTextClock().setFormat12Hour("hh:mm:ss a (zzzz)");
        }
        int numLocations = locations.length;
        for (int i = 1; i<numLocations; i++) {
            locations[i].setUpClock();
        }
    }
}
