package com.example.kaushiknsanji.xploremysuru.ui.parks;

import com.example.kaushiknsanji.xploremysuru.data.local.models.Park;

/**
 * Interface to be implemented by {@link ParkListFragment}
 * to receive callback events for User actions on RecyclerView list of Parks.
 *
 * @author Kaushik N Sanji
 */
public interface ParkListUserActionsListener {
    /**
     * Callback Method of {@link ParkListUserActionsListener} invoked when
     * the user clicks on the Item View itself. This should launch
     * the website link if any.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param park         The {@link Park} associated with the Item clicked
     */
    void onOpenLink(final int itemPosition, Park park);

    /**
     * Callback Method of {@link ParkListUserActionsListener} invoked when
     * the user clicks on the Map ImageButton or the Location Info TextView of the Item View.
     * This should launch any Map application passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param park         The {@link Park} associated with the Item clicked
     */
    void onOpenLocation(final int itemPosition, Park park);

    /**
     * Callback Method of {@link ParkListUserActionsListener} invoked when
     * the user clicks and holds on to the Location Info TextView of the Item View. This should
     * launch a Share Intent passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked and held
     * @param park         The {@link Park} associated with the Item clicked and held
     */
    void onLocationLongClicked(final int itemPosition, Park park);
}
