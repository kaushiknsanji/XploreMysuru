package com.example.kaushiknsanji.xploremysuru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kaushiknsanji.xploremysuru.R;
import com.example.kaushiknsanji.xploremysuru.cache.BitmapImageCache;
import com.example.kaushiknsanji.xploremysuru.data.AppRepository;
import com.example.kaushiknsanji.xploremysuru.ui.about.AboutActivity;
import com.example.kaushiknsanji.xploremysuru.ui.hotels.HotelListFragment;
import com.example.kaushiknsanji.xploremysuru.ui.hotels.HotelListPresenter;
import com.example.kaushiknsanji.xploremysuru.ui.parks.ParkListFragment;
import com.example.kaushiknsanji.xploremysuru.ui.parks.ParkListPresenter;
import com.example.kaushiknsanji.xploremysuru.ui.places.PlaceListFragment;
import com.example.kaushiknsanji.xploremysuru.ui.places.PlaceListPresenter;
import com.example.kaushiknsanji.xploremysuru.ui.restaurants.RestaurantListFragment;
import com.example.kaushiknsanji.xploremysuru.ui.restaurants.RestaurantListPresenter;
import com.example.kaushiknsanji.xploremysuru.ui.shops.ShopListFragment;
import com.example.kaushiknsanji.xploremysuru.ui.shops.ShopListPresenter;
import com.example.kaushiknsanji.xploremysuru.utils.InjectorUtility;

/**
 * The Main Activity of the App that inflates the layout 'R.layout.activity_main'
 * containing the BottomNavigationView that displays a list of Places, Parks, Hotels, Restaurants
 * and Shops, loaded by the Navigation Fragments.
 *
 * @author Kaushik N Sanji
 */
public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    //Bundle Constant for persisting the last viewed BottomNavigationView fragment menu item resource id
    private static final String BUNDLE_LAST_VIEWED_NAV_ITEM_INT_KEY = "Main.LastViewedNavMenuItemResId";

    //The Common Presenter Interface implemented by the BottomNavigationView Fragments
    private NavigationPresenter mNavigationPresenter;
    //For the BottomNavigationView
    private BottomNavigationView mBottomNavigationView;

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inflating the Activity's UI
        setContentView(R.layout.activity_main);

        //Initialize Toolbar
        setupToolbar();

        //Initialize BottomNavigationView
        setupBottomNavigationView();

        if (savedInstanceState == null) {
            //On Initial Launch, loading the first BottomNavigationView Menu Item - "Places"
            mBottomNavigationView.setSelectedItemId(R.id.navigation_places);
        } else {
            //On Subsequent Launch, loading the last viewed BottomNavigationView Menu Item
            mBottomNavigationView.setSelectedItemId(savedInstanceState.getInt(BUNDLE_LAST_VIEWED_NAV_ITEM_INT_KEY));
        }
    }

    /**
     * Called to retrieve per-instance state from an activity before being killed
     * so that the state can be restored in {@link #onCreate} or
     * {@link #onRestoreInstanceState} (the {@link Bundle} populated by this method
     * will be passed to both).
     *
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Saving the last viewed BottomNavigationView Menu Item Resource Id
        outState.putInt(BUNDLE_LAST_VIEWED_NAV_ITEM_INT_KEY, mBottomNavigationView.getSelectedItemId());
    }

    /**
     * Method that initializes the Toolbar
     */
    private void setupToolbar() {
        //Finding the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        //Setting it as the Action Bar
        setSupportActionBar(toolbar);
    }

    /**
     * Method that initializes the BottomNavigationView and its item selection listener
     */
    private void setupBottomNavigationView() {
        //Finding the BottomNavigationView
        mBottomNavigationView = findViewById(R.id.navigation_main);
        //Registering the Item Selection Listener
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflating the Menu options from 'R.menu.menu_main'
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //Returning true for the Menu to be displayed
        return true;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen.
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handling based on the Menu item selected
        switch (item.getItemId()) {
            case R.id.action_refresh:
                //On Click of Refresh Menu

                if (mNavigationPresenter != null) {
                    //When Presenter is initialized, invoke the Refresh method
                    mNavigationPresenter.onRefreshMenuClicked();
                }
                return true;
            case R.id.action_about:
                //On Click of About

                //Launch the About Activity
                launchAboutActivity();
                return true;
            default:
                //On other cases, do the default menu handling
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Method that launches the {@link AboutActivity}. Invoked when "About" Menu is clicked.
     */
    private void launchAboutActivity() {
        //Creating an Intent to launch AboutActivity
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        //Starting the Activity
        startActivity(aboutIntent);
    }

    /**
     * Called when an item in the bottom navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item and false if the item should not
     * be selected. Consider setting non-selectable items as disabled preemptively to
     * make them appear non-interactive.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Retrieving the App Repository Instance
        AppRepository appRepository = InjectorUtility.provideAppRepository(getApplicationContext());

        //Taking action based on the Id of the Menu item clicked
        switch (item.getItemId()) {
            case R.id.navigation_places:
                //For Places

                //Load the PlaceListFragment and its Presenter
                mNavigationPresenter = new PlaceListPresenter(appRepository,
                        (PlaceListFragment) switchNavigationFragment(PlaceListFragment.newInstance()));
                return true;

            case R.id.navigation_parks:
                //For Parks

                //Load the ParkListFragment and its Presenter
                mNavigationPresenter = new ParkListPresenter(appRepository,
                        (ParkListFragment) switchNavigationFragment(ParkListFragment.newInstance()));
                return true;

            case R.id.navigation_hotels:
                //For Hotels

                //Load the HotelListFragment and its Presenter
                mNavigationPresenter = new HotelListPresenter(appRepository,
                        (HotelListFragment) switchNavigationFragment(HotelListFragment.newInstance()));
                return true;

            case R.id.navigation_restaurants:
                //For Restaurants

                //Load the RestaurantListFragment and its Presenter
                mNavigationPresenter = new RestaurantListPresenter(appRepository,
                        (RestaurantListFragment) switchNavigationFragment(RestaurantListFragment.newInstance()));
                return true;

            case R.id.navigation_shops:
                //For Shops

                //Load the ShopListFragment and its Presenter
                mNavigationPresenter = new ShopListPresenter(appRepository,
                        (ShopListFragment) switchNavigationFragment(ShopListFragment.newInstance()));
                return true;
        }
        //On all else, returning False
        return false;
    }

    /**
     * Method that replaces the Fragment at the FrameLayout 'R.id.content_main' with the given {@code fragment}
     *
     * @param fragment The Fragment to be loaded and shown.
     * @return Instance of the loaded Fragment.
     */
    private Fragment switchNavigationFragment(Fragment fragment) {
        //Retrieving the Fragment Manager
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //Retrieving the current Fragment at the FrameLayout 'R.id.content_main'
        Fragment currentFragment = supportFragmentManager.findFragmentById(R.id.content_main);
        //Checking if any Fragment exists at the FrameLayout 'R.id.content_main'
        if (currentFragment == null || !fragment.getClass().isInstance(currentFragment)) {
            //When there is NO Fragment or the Fragment is different from the fragment to be loaded

            //Replace the Current Fragment with the given Fragment at the FrameLayout 'R.id.content_main'
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();
        } else {
            //When there is a Fragment already and is same as the given Fragment,
            //then return the Fragment found
            return currentFragment;
        }
        //Return the New Fragment loaded
        return fragment;
    }

    /**
     * Called as part of the activity lifecycle when an activity is going into
     * the background, but has not (yet) been killed.  The counterpart to
     * {@link #onResume}.
     */
    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()) {
            //Clearing the Bitmap Cache when the activity is finishing
            BitmapImageCache.clearCache();
        }
    }

}
