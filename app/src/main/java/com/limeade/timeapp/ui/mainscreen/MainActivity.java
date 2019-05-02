package com.limeade.timeapp.ui.mainscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;

import com.limeade.timeapp.R;
import com.limeade.timeapp.services.location.LocationInfo;
import com.limeade.timeapp.services.RequestManager;
import com.limeade.timeapp.ui.location.LocationUI;
import com.limeade.timeapp.ui.mainscreen.fragment.IntroDialogFragment;

public class MainActivity extends AppCompatActivity {

    LocationUI[] locations; // array of locations currently displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setUpListeners();
        setUpClocks();
    }

    // Displays intro message, initializes all location objects, and instantiates
    // RequestManager
    private void initUI() {
        Log.d("XYZ", "init UI called");

        RequestManager.instantiateRequestManager(getApplicationContext());

        IntroDialogFragment introDialog = new IntroDialogFragment();
        introDialog.show(getFragmentManager(), "GREET");

        LocationUI userLocation = new LocationUI(findViewById(R.id.userTextClock));

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
        Log.d("XYZ", "init UI done");

    }

    private void setUpListeners(){
        Log.d("XYZ", "set up listeners called");
        for (LocationUI locationUI : locations) {
            if (locationUI.equals(locations[0])){
                continue;
            }
            locationUI.getChangeButton().setOnClickListener(v -> {
                // Checks if new location is empty or same as current location.
                // if false, updates clock to new city
                locationUI.getEditText().clearFocus();
                String newCity = locationUI.getEditText().getText().toString();
                if (newCity.equals("")) {
                    locationUI.getEditText().setHint("Please enter a location.");
                } else if (!(locationUI.getLocationInfo().getLocationName().equals(newCity))) {
                    locationUI.setUpClock(newCity);
                }
            });
            Log.d("XYZ", "set up changebutton");

            locationUI.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
                // Toggles cursor and button visibility on focus change.
                if (hasFocus){
                    locationUI.getChangeButton().setVisibility(View.VISIBLE);
                    locationUI.getEditText().setCursorVisible(true);
                } else {
                    locationUI.getChangeButton().setVisibility(View.GONE);
                    locationUI.getEditText().setCursorVisible(false);
                }
            });
            Log.d("XYZ", "set up edittext");

        }
        Log.d("XYZ", "set up listeners");
    }

    // Sets format for all clocks and activates them based on location name
    private void setUpClocks(){
        Log.d("XYZ", "set up clocks called");

        for (LocationUI city : locations){
            city.getTextClock().setFormat24Hour("HH:mm:ss (zzzz)");
            city.getTextClock().setFormat12Hour("hh:mm:ss a (zzzz)");
        }
        int numLocations = locations.length;
        for (int i = 1; i<numLocations; i++) {
            locations[i].setUpClock();
        }
        Log.d("XYZ", "set up clocks");

    }
}
