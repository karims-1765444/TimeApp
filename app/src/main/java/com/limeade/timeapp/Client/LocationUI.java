package com.limeade.timeapp.Client;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;

import com.google.common.base.Function;
import com.limeade.timeapp.Manager.LocationInfo;

public class LocationUI {

    private LocationInfo locationInfo; // stores information about location
    private Button changeButton; // used to update to a new location
    private TextClock textClock; // displays the current time
    private EditText editText; // displays the editable location's name

    public LocationUI(TextClock textClock){
        this.textClock = textClock;
    }

    public LocationUI(final LocationInfo locationInfo, final Button changeButton, TextClock textClock, final EditText editText){
        this.locationInfo = locationInfo;
        this.changeButton = changeButton;
        this.textClock = textClock;
        this.editText = editText;
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Checks if new location is empty or same as current location.
            // if false, updates clock to new city
            public void onClick(View v) {
                editText.clearFocus();
                String newCity = editText.getText().toString();
                if(newCity.equals("")){
                    editText.setHint("Please enter a location.");
                } else if (!(locationInfo.getLocationName().equals(newCity))){
                    setUpClock(newCity);
                }
            }
        });
        // Toggles cursor and button visibility on focus change.
        // clicks on changeButton manually if user leaves edit text
        // without clicking on the button
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    changeButton.setVisibility(View.VISIBLE);
                    editText.setCursorVisible(true);
                } else {
                    changeButton.setVisibility(View.GONE);
                    editText.setCursorVisible(false);
                    changeButton.callOnClick();
                }
            }
        });
    }

    // Changes clock time zone to the new location's.
    // Used to update the clocks per user requests
    public void setUpClock(String newLocationName){
        locationInfo.setTimeZoneId(newLocationName, new Function<String, Void>() {
            public Void apply(String timeZoneId) {
                if (timeZoneId==null){
                    editText.setText(locationInfo.getLocationName());
                    return null;
                }
                textClock.setTimeZone(timeZoneId);
                return null;
            }
        });
    }

    // Changes clock time zone to the current location's.
    // Used when first setting up the clocks
    public void setUpClock(){
        setUpClock(locationInfo.getLocationName());
    }

    public TextClock getTextClock(){
        return textClock;
    }
}
