# TimeApp

An Android app used to show the local time in four different locations. The default locations are Bellevue, Denver, Gatineau, Oldenberg.
Locations can be changed into any other location (city, country, street address) by the user. This service is powered by two Google API's:
one to convert a location to its geocode and another to convert the geocode to a time zone to be used by the clocks.

Note: The location name to geocode Google API uses the Google Maps service to convert any address to a latitude and longitude. So, the user
can treat the application as if its using Google Maps. So, for example, a user could search for a street address and the API will deal with
it accordingly. Interestingly, there may be cases where this leads to unexpected behavior. For example, entering "London" in the app will
display the London time zone. Suppose a user types "Londons" instead, whether it is a typo or on purpose, the Google API service will 
search for that place and finds it in Tullahoma, TN 37388, United States. Therefore, some locations that the user may not know of
may actually be found in the real world. Of course, in the case where the user blatantly types a wrong location name (something like "asdfg"),
the API fails and the app deals with that accordingly by reverting to the original location that was shown. I believe this gives the users
the ability to enter more specific locations, so I left it. Bug? More like a feature to me.
