Please use your places/maps api keys
you can put api key in app/build.gradle by asigning placesApiKey/debugMapsApiKey values

also you will need yo use your debug.keystore, used to generate Google API keys
you can enter the path to the keystore in  this line "storeFile file('<enter path to your keystore>')"
in the same build.gradle file.


You can use mock places response by pasting it in getPlacesJSON() method 
in data/net/MockDownloader class 
You will also need to uncomment //return new MockDownloader(); line 
in dagger/modules/RetrofitModule class