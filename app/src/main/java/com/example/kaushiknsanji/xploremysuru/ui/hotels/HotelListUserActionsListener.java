package com.example.kaushiknsanji.xploremysuru.ui.hotels;

import com.example.kaushiknsanji.xploremysuru.data.local.models.Hotel;

/**
 * Interface to be implemented by {@link HotelListFragment}
 * to receive callback events for User actions on RecyclerView list of Hotels.
 *
 * @author Kaushik N Sanji
 */
public interface HotelListUserActionsListener {
    /**
     * Callback Method of {@link HotelListUserActionsListener} invoked when
     * the user clicks on the Item View itself. This should launch
     * the website link if any.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param hotel        The {@link Hotel} associated with the Item clicked
     */
    void onOpenLink(final int itemPosition, Hotel hotel);

    /**
     * Callback Method of {@link HotelListUserActionsListener} invoked when
     * the user clicks on the Map ImageButton or the Location Info TextView of the Item View.
     * This should launch any Map application passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param hotel        The {@link Hotel} associated with the Item clicked
     */
    void onOpenLocation(final int itemPosition, Hotel hotel);

    /**
     * Callback Method of {@link HotelListUserActionsListener} invoked when
     * the user clicks on the Call ImageButton or the Contact Info TextView of the Item View.
     * This should launch any Dialer application passing in the Contact Number.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param hotel        The {@link Hotel} associated with the Item clicked
     */
    void onOpenContact(final int itemPosition, Hotel hotel);

    /**
     * Callback Method of {@link HotelListUserActionsListener} invoked when
     * the user clicks and holds on to the Contact Info TextView of the Item View. This should
     * launch a Share Intent passing in the Contact Number.
     *
     * @param itemPosition The adapter position of the Item clicked and held
     * @param hotel        The {@link Hotel} associated with the Item clicked and held
     */
    void onContactLongClicked(final int itemPosition, Hotel hotel);

    /**
     * Callback Method of {@link HotelListUserActionsListener} invoked when
     * the user clicks and holds on to the Location Info TextView of the Item View. This should
     * launch a Share Intent passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked and held
     * @param hotel        The {@link Hotel} associated with the Item clicked and held
     */
    void onLocationLongClicked(final int itemPosition, Hotel hotel);
}
