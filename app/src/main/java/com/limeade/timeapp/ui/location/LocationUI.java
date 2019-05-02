package com.limeade.timeapp.ui.location;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;

import com.limeade.timeapp.services.location.LocationInfo;

/**
 *  A class that handles the UI of a location
 */

public class LocationUI {

    private LocationInfo locationInfo; // stores information about location
    private Button changeButton; // used to update to a new location
    private TextClock textClock; // displays the current time
    private EditText editText; // displays the editable location's name

    public LocationUI(TextClock textClock){
        this.textClock = textClock;
    }

    // Initializes fields and sets listeners
    public LocationUI(final LocationInfo locationInfo, final Button changeButton, TextClock textClock, final EditText editText){
        this.locationInfo = locationInfo;
        this.changeButton = changeButton;
        this.textClock = textClock;
        this.editText = editText;
    }

    public Button getChangeButton() {
        return changeButton;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public EditText getEditText() {
        return editText;
    }

    // Sets on-screen clock timezone to that of the new location name,
    // or reverts location name to original on failure
    public void setUpClock(String newLocationName) {
        locationInfo.setUpClock(newLocationName, timeZoneId ->{
            if(timeZoneId == null){
                editText.setText(locationInfo.getLocationName());
                return null;
            }
            textClock.setTimeZone(timeZoneId);
            return null;
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
