package com.example.kaushiknsanji.xploremysuru.ui.places;

import com.example.kaushiknsanji.xploremysuru.data.local.models.Place;

/**
 * Interface to be implemented by {@link PlaceListFragment}
 * to receive callback events for User actions on RecyclerView list of Places.
 *
 * @author Kaushik N Sanji
 */
public interface PlaceListUserActionsListener {
    /**
     * Callback Method of {@link PlaceListUserActionsListener} invoked when
     * the user clicks on the Item View itself. This should launch
     * the website link if any.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param place        The {@link Place} associated with the Item clicked
     */
    void onOpenLink(final int itemPosition, Place place);

    /**
     * Callback Method of {@link PlaceListUserActionsListener} invoked when
     * the user clicks on the Map ImageButton or the Location Info TextView of the Item View.
     * This should launch any Map application passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param place        The {@link Place} associated with the Item clicked
     */
    void onOpenLocation(int itemPosition, Place place);

    /**
     * Callback Method of {@link PlaceListUserActionsListener} invoked when
     * the user clicks and holds on to the Location Info TextView of the Item View. This should
     * launch a Share Intent passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked and held
     * @param place        The {@link Place} associated with the Item clicked and held
     */
    void onLocationLongClicked(final int itemPosition, Place place);
}
