# A weather forecast app

A weather forecast app done using Android's new UI toolkit Jetpack Compose. This project utilizes the clean architecture approach with the MVVM architectural
pattern.


The app is in unfinished state. Things to be done:
* Detail screen
* Settings screen

In order to build the project you'll have to provide:
* **google-services.json** file to the *app* folder, or alternatively disable firebase features altogether by either deleting or commenting out the corresponding code
* api keys in the file *local.properties* as follows:
    * **LOCATION_IQ_API_KEY=***<location_iq_api_key>* (obtained at https://locationiq.com)
    * **OPEN_WEATHER_API_KEY=***<open_weather_api_key>* (obtained at https://openweathermap.org)

Libraries used: 
* Hilt
* Timber
* Firebase 
* Retrofit
* Moshi
* Room
* Accompanist
* Coil
* Lottie

Some screenshots:

![1](./screenshots/1.png)
![2](./screenshots/2.png)
![3](./screenshots/3.png)
![4](./screenshots/4.png)
![5](./screenshots/5.png)
![6](./screenshots/6.png)
![7](./screenshots/7.png)
![8](./screenshots/8.png)
![9](./screenshots/9.png)
