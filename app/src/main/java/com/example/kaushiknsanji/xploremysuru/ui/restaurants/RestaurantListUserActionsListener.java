package com.example.kaushiknsanji.xploremysuru.ui.restaurants;

import com.example.kaushiknsanji.xploremysuru.data.local.models.Restaurant;

/**
 * Interface to be implemented by {@link RestaurantListFragment}
 * to receive callback events for User actions on RecyclerView list of Restaurants.
 *
 * @author Kaushik N Sanji
 */
public interface RestaurantListUserActionsListener {
    /**
     * Callback Method of {@link RestaurantListUserActionsListener} invoked when
     * the user clicks on the Item View itself. This should launch
     * the website link if any.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param restaurant   The {@link Restaurant} associated with the Item clicked
     */
    void onOpenLink(final int itemPosition, Restaurant restaurant);

    /**
     * Callback Method of {@link RestaurantListUserActionsListener} invoked when
     * the user clicks on the Map ImageButton or the Location Info TextView of the Item View.
     * This should launch any Map application passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param restaurant   The {@link Restaurant} associated with the Item clicked
     */
    void onOpenLocation(final int itemPosition, Restaurant restaurant);

    /**
     * Callback Method of {@link RestaurantListUserActionsListener} invoked when
     * the user clicks on the Call ImageButton or the Contact Info TextView of the Item View.
     * This should launch any Dialer application passing in the Contact Number.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param restaurant   The {@link Restaurant} associated with the Item clicked
     */
    void onOpenContact(final int itemPosition, Restaurant restaurant);

    /**
     * Callback Method of {@link RestaurantListUserActionsListener} invoked when
     * the user clicks and holds on to the Contact Info TextView of the Item View. This should
     * launch a Share Intent passing in the Contact Number.
     *
     * @param itemPosition The adapter position of the Item clicked and held
     * @param restaurant   The {@link Restaurant} associated with the Item clicked and held
     */
    void onContactLongClicked(final int itemPosition, Restaurant restaurant);

    /**
     * Callback Method of {@link RestaurantListUserActionsListener} invoked when
     * the user clicks and holds on to the Location Info TextView of the Item View. This should
     * launch a Share Intent passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked and held
     * @param restaurant   The {@link Restaurant} associated with the Item clicked and held
     */
    void onLocationLongClicked(final int itemPosition, Restaurant restaurant);
}
