# XploreMysuru - The Tour Guide App for "Mysore, Karnataka, India"

This App has been developed as part of the **Udacity Android Basics Nanodegree Course** for the Exercise Project **"Tour Guide App"**. App guides the user in exploring the **"City of Palaces"/"Mysore"**.

---

## App Compatibility

Android device running with Android OS 4.0.4 (API Level 15) or above. Best experienced on Android Nougat 7.1 and above. Designed for Phones and NOT for Tablets.

---

## Rubric followed for the Project

* Contains at least 4 lists of relevant attractions for a location.
* Navigates between lists using a central screen / Navigation Drawer / View Pager / Bottom Navigation View.
* Each list contains information about restaurants / historical sites or similar.
* At least one list includes pictures of the location.
* Contains Java objects for storing location information.
* Uses Custom Adapter to populate the layout with the views based on the instance of the Custom class.
* Images are stored as drawables in multiple densities.
* Strings are stored in strings.xml resource file.

---

## Design Workflow

### The Home Screen or the Main Activity of the App

<p align="center">
<img src="https://user-images.githubusercontent.com/26028981/51127857-8fc2cc00-184c-11e9-996d-0946ab3ffb2b.png" width="50%"/>
</p>

The [MainActivity][] of the App has a `BottomNavigationView` to navigate between the lists of attraction for the city. Lists of **Attractions** comprises of **Places**, **Parks**, **Hotels**, **Restaurants** and **Shops**, which are loaded using Fragments.

### Bottom Navigation Menu : Places - [PlaceListFragment][] 

|Portrait & Landscape view - Collapsed Items|Portrait view - Expanded Items|
|---|---|
|![place_portrait](https://user-images.githubusercontent.com/26028981/51127857-8fc2cc00-184c-11e9-996d-0946ab3ffb2b.png)<br/>![place_landscape](https://user-images.githubusercontent.com/26028981/51127860-8fc2cc00-184c-11e9-9cc3-367f67e3025b.png)|![place_item_expand_1](https://user-images.githubusercontent.com/26028981/51127862-8fc2cc00-184c-11e9-8a2c-2dd247e78035.png)<br/>![place_item_expand_2](https://user-images.githubusercontent.com/26028981/51127863-905b6280-184c-11e9-9445-8075d262db32.png)| 

* Displays list of interesting **"Places to visit"** in Mysore.
* Reads and prepares [Place][] data from [place_arrays.xml][] for the `place_list_entries` mentioned in [arrays.xml][].
* Each Item View displays the following data for Places -
	* Name
	* Rating
	* Place Type in text and icon representation
	* Image of the Place
	* Place Timings
	* Entry Fee
	* Location address in text (expanded form) and as an action button (collapsed form)
	* Description of the Place (expanded form)
* Clicking on the Item View takes the user to the Website of the Place if present. When not present, a snackbar with message, _"No link available!"_ will be displayed.
* Clicking on the Location Icon button (in collapsed form) or on the Location text (in expanded form) will launch the Google Maps for the location.
* On Long press of Location text in expanded form, allows the user to copy/share the location address via any application compatible for sharing the text data.

### Bottom Navigation Menu : Parks - [ParkListFragment][] 

|Portrait & Landscape view - Collapsed Items|Portrait view - Expanded Items|
|---|---|
|![park_portrait](https://user-images.githubusercontent.com/26028981/51127878-9b15f780-184c-11e9-8ef0-c330923ff2e4.png)<br/>![park_landscape](https://user-images.githubusercontent.com/26028981/51127879-9bae8e00-184c-11e9-94fd-351a56b88680.png)|![park_item_expand_1](https://user-images.githubusercontent.com/26028981/51127881-9bae8e00-184c-11e9-91dd-1d77c061a68a.png)<br/>![park_item_expand_2](https://user-images.githubusercontent.com/26028981/51127877-9b15f780-184c-11e9-8925-5b1ed0074169.png)|

* Displays list of interesting **"Parks to visit"** in Mysore.
* Reads and prepares [Park][] data from [park_arrays.xml][] for the `park_list_entries` mentioned in [arrays.xml][].
* Each Item View displays the following data for Parks -
	* Name
	* Rating
	* Image of the Park
	* Park Timings
	* Entry Fee
	* Location address in text (expanded form) and as an action button (collapsed form)
	* Description of the Park (expanded form)
* Clicking on the Item View takes the user to the Website of the Park if present. When not present, a snackbar with message, _"No link available!"_ will be displayed.
* Clicking on the Location Icon button (in collapsed form) or on the Location text (in expanded form) will launch the Google Maps for the location.
* On Long press of Location text in expanded form, allows the user to copy/share the location address via any application compatible for sharing the text data.

### Bottom Navigation Menu : Hotels - [HotelListFragment][] 

|Portrait & Landscape view - Collapsed Items|Portrait view - Expanded Items|
|---|---|
|![hotel_portrait](https://user-images.githubusercontent.com/26028981/51127897-a49f5f80-184c-11e9-8876-e79b9c4a3101.png)<br/>![hotel_landscape](https://user-images.githubusercontent.com/26028981/51127898-a537f600-184c-11e9-9cf4-bb4310ae8416.png)|![hotel_item_expand](https://user-images.githubusercontent.com/26028981/51127900-a537f600-184c-11e9-867f-fb7f28832aa8.png)|

* Displays list of **"Hotels to stay"** in Mysore.
* Reads and prepares [Hotel][] data from [hotel_arrays.xml][] for the `hotel_list_entries` mentioned in [arrays.xml][].
* Each Item View displays the following data for Hotels -
	* Name
	* Traveller Rating
	* Hotel Star Rating using icon representation
	* Image of the Hotel
	* Rate per Night for a couple to stay
	* Location address in text (expanded form) and as an action button (collapsed form)
	* Contact Number in text (expanded form) and as an action button (collapsed form)
* Clicking on the Item View takes the user to the Website of the Hotel if present. When not present, a snackbar with message, _"No link available!"_ will be displayed.
* Clicking on the Location Icon button (in collapsed form) or on the Location text (in expanded form) will launch the Google Maps for the location.
* Clicking on the Phone Icon button (in collapsed form) or on the Contact Number text (in expanded form) will launch the Dialer for the Contact Number.
* If Contact Number is absent, Phone Icon button (in collapsed form) will be hidden away and the Contact Number text field (in expanded form) will display, _"No contact available!"_.
* On Long press of Location text in expanded form, allows the user to copy/share the location address via any application compatible for sharing the text data.
* On Long press of Contact Number text in expanded form, allows the user to copy/share the contact number via any application compatible for sharing the text data.

### Bottom Navigation Menu : Restaurants - [RestaurantListFragment][] 

|Portrait & Landscape view - Collapsed Items|Portrait view - Expanded Items|
|---|---|
|![restaurant_portrait](https://user-images.githubusercontent.com/26028981/51127912-ae28c780-184c-11e9-8b28-303544ef377d.png)<br/>![restaurant_landscape](https://user-images.githubusercontent.com/26028981/51127913-ae28c780-184c-11e9-9661-f392347a8516.png)|![restaurant_item_expand](https://user-images.githubusercontent.com/26028981/51127914-ae28c780-184c-11e9-840b-ded292b0d5df.png)|

* Displays list of **"Restaurants to dine"** in Mysore.
* Reads and prepares [Restaurant][] data from [restaurant_arrays.xml][] for the `restaurant_list_entries` mentioned in [arrays.xml][].
* Each Item View displays the following data for Restaurants -
	* Name
	* Cuisine Types
	* Foodie Rating
	* Image of the Restaurant
	* Restaurant Timings
	* Cost of dining, averaged for two persons
	* Location address in text (expanded form) and as an action button (collapsed form)
	* Contact Number in text (expanded form) and as an action button (collapsed form)
* Clicking on the Item View takes the user to the Website of the Restaurant if present. When not present, a snackbar with message, _"No link available!"_ will be displayed.
* Clicking on the Location Icon button (in collapsed form) or on the Location text (in expanded form) will launch the Google Maps for the location.
* Clicking on the Phone Icon button (in collapsed form) or on the Contact Number text (in expanded form) will launch the Dialer for the Contact Number.
* If Contact Number is absent, Phone Icon button (in collapsed form) will be hidden away and the Contact Number text field (in expanded form) will display, _"No contact available!"_.
* On Long press of Location text in expanded form, allows the user to copy/share the location address via any application compatible for sharing the text data.
* On Long press of Contact Number text in expanded form, allows the user to copy/share the contact number via any application compatible for sharing the text data.

### Bottom Navigation Menu : Shops - [ShopListFragment][] 

|Portrait & Landscape view - Collapsed Items|Portrait view - Expanded Items|
|---|---|
|![shop_portrait](https://user-images.githubusercontent.com/26028981/51127951-c26cc480-184c-11e9-96d2-8da754f185bb.png)<br/>![shop_landscape](https://user-images.githubusercontent.com/26028981/51127952-c26cc480-184c-11e9-9705-3b4db65293cb.png)|![shop_item_expand](https://user-images.githubusercontent.com/26028981/51127954-c3055b00-184c-11e9-9368-7bb575cbcef3.png)|

* Displays list of interesting **"Shops"** for shopping in Mysore.
* Reads and prepares [Shop][] data from [shop_arrays.xml][] for the `shop_list_entries` mentioned in [arrays.xml][].
* Each Item View displays the following data for Shops -
	* Name
	* Shop Type
	* Rating
	* Image of the Shop
	* Shop Timings
	* Location address in text (expanded form) and as an action button (collapsed form)
* Clicking on the Item View takes the user to the Website of the Shop if present. When not present, a snackbar with message, _"No link available!"_ will be displayed.
* Clicking on the Location Icon button (in collapsed form) or on the Location text (in expanded form) will launch the Google Maps for the location.
* On Long press of Location text in expanded form, allows the user to copy/share the location address via any application compatible for sharing the text data.

### Bottom Navigation Behavior - [BottomNavigationBehavior][]

**Images of Shop List Item that had no Website information. Snackbar message docks on top of BottomNavigationView when it is shown**

<img src="https://user-images.githubusercontent.com/26028981/51127965-ca2c6900-184c-11e9-9dfb-83e067e42324.png" width="40%" />   <img src="https://user-images.githubusercontent.com/26028981/51127966-ca2c6900-184c-11e9-9438-7034df166b94.png" width="40%" />

* This behavior hides away the `BottomNavigationView` when the user scrolls towards the bottom of the View, shown for the Navigation item selected.
* It checks for the `Snackbar` dependency and adjusts its layout parameters such that the Snackbar always appears above the `BottomNavigationView`. Without this, `Snackbar` always appears behind the `BottomNavigationView`.
* It mimics the Snap animation behavior of `AppBarLayout` for displaying/hiding the `BottomNavigationView` on scrolling. It hides the `BottomNavigationView` when more than or equal to half of it is  translated away, and shows the `BottomNavigationView` when less than half of it is translated away.

### About [Activity][AboutActivity]

<img src="https://user-images.githubusercontent.com/26028981/51127978-d0224a00-184c-11e9-8bd6-af5f4bd72ef9.png" width="40%" />   <img src="https://user-images.githubusercontent.com/26028981/51127979-d0bae080-184c-11e9-901d-5868bbfb3c27.png" width="40%" />

* Launches via the **"About"** Menu available in the `MainActivity`.
* This page describes in brief about the app, and has links to my bio and the course details hosted by Udacity. 

### Loading of Images

Loading of Images are carried out in a background thread through a Headless/Viewless Fragment [ImageDecoderFragment][]. Functioning of this fragment is as follows -
* It first checks whether the image to be loaded is present in the Bitmap Cache, implemented by [BitmapImageCache][].
* If present in the cache, it updates the image to the corresponding `ImageView` passed.
* If not present in cache, then the image pointed to by the Resource Id is decoded in a background thread using [ImageDecoder][] that extends an `AsyncTaskLoader`. Once successfully decoded, it updates the image to the corresponding `ImageView` passed, and also saves the same in the Bitmap Cache.
* While the Image is being decoded, the `ImageView` of the item will display the default image [layer_all_default_picture][]. If an error occurs while decoding or if the Resource Id is invalid, then the `ImageView` of the item will display the error image [layer_all_no_picture][].

The Identifier of the Loader for each Item View is maintained unique by the Resource Id of the image to be loaded. Hence no duplication of images is possible in the list.

_As per the Rubric, no third party library is used for loading images._ 

---

## App Architecture

### Implementation Architecture

* App follows **MVP + Repository** pattern with App Resources.
* Activities that do not need any access to Repository are excluded from the MVP architecture.
	* [MainActivity][] needs to manage only the `BottomNavigationView` and its fragments.
	* [AboutActivity][] is just a plain Activity and has no specific function.
* All `BottomNavigationView` Fragments follow the MVP pattern.
* Access to the Repository is governed by the [AppRepository][] which interfaces with another repository -
	1. **Local App Resources** - [ResourceRepository][]
		* To manage communication with the Resources of the App.

---

## Icon and Image credits

* App Icon, the vector illustration of Mysore Palace is a beautiful work of <a href="https://dribbble.com/shots/2208630-Mysore-Palace">Ranganath Krishnamani</a>. Do check out other designs by his team <a href="http://www.liquidink.design/about">Liquid Ink</a>.
* The Place Icons, Hotel Star Rating Icons and other icons, are from [Icons8](https://icons8.com). 
* Listing Images are mainly from Google.

---

## Review from the Reviewer (Udacity)

![review](https://user-images.githubusercontent.com/26028981/51263273-edd0ea00-19d9-11e9-837c-7ceb4aefb8c0.png)

<!-- Reference Style Links are to be placed after this -->
[MainActivity]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/ui/MainActivity.java
[arrays.xml]: /app/src/main/res/values/arrays.xml
[PlaceListFragment]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/ui/places/PlaceListFragment.java
[Place]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/data/local/models/Place.java
[place_arrays.xml]: /app/src/main/res/values/place_arrays.xml
[ParkListFragment]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/ui/parks/ParkListFragment.java
[Park]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/data/local/models/Park.java
[park_arrays.xml]: /app/src/main/res/values/park_arrays.xml
[HotelListFragment]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/ui/hotels/HotelListFragment.java
[Hotel]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/data/local/models/Hotel.java
[hotel_arrays.xml]: /app/src/main/res/values/hotel_arrays.xml
[RestaurantListFragment]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/ui/restaurants/RestaurantListFragment.java
[Restaurant]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/data/local/models/Restaurant.java
[restaurant_arrays.xml]: /app/src/main/res/values/restaurant_arrays.xml
[ShopListFragment]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/ui/shops/ShopListFragment.java
[Shop]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/data/local/models/Shop.java
[shop_arrays.xml]: /app/src/main/res/values/shop_arrays.xml
[BottomNavigationBehavior]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/extensions/BottomNavigationBehavior.java
[ImageDecoderFragment]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/workers/ImageDecoderFragment.java
[BitmapImageCache]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/cache/BitmapImageCache.java
[ImageDecoder]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/workers/ImageDecoder.java
[layer_all_default_picture]: /app/src/main/res/drawable/layer_all_default_picture.xml
[layer_all_no_picture]: /app/src/main/res/drawable/layer_all_no_picture.xml
[AboutActivity]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/ui/about/AboutActivity.java
[AppRepository]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/data/AppRepository.java
[ResourceRepository]: /app/src/main/java/com/example/kaushiknsanji/xploremysuru/data/local/ResourceRepository.java
