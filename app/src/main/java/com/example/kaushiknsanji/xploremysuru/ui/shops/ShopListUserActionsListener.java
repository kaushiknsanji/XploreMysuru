package com.example.kaushiknsanji.xploremysuru.ui.shops;

import com.example.kaushiknsanji.xploremysuru.data.local.models.Shop;

/**
 * Interface to be implemented by {@link ShopListFragment}
 * to receive callback events for User actions on RecyclerView list of Shops.
 *
 * @author Kaushik N Sanji
 */
public interface ShopListUserActionsListener {

    /**
     * Callback Method of {@link ShopListUserActionsListener} invoked when
     * the user clicks on the Item View itself. This should launch
     * the website link if any.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param shop         The {@link Shop} associated with the Item clicked
     */
    void onOpenLink(final int itemPosition, Shop shop);

    /**
     * Callback Method of {@link ShopListUserActionsListener} invoked when
     * the user clicks on the Map ImageButton or the Location Info TextView of the Item View.
     * This should launch any Map application passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked
     * @param shop         The {@link Shop} associated with the Item clicked
     */
    void onOpenLocation(final int itemPosition, Shop shop);

    /**
     * Callback Method of {@link ShopListUserActionsListener} invoked when
     * the user clicks and holds on to the Location Info TextView of the Item View. This should
     * launch a Share Intent passing in the location information.
     *
     * @param itemPosition The adapter position of the Item clicked and held
     * @param shop         The {@link Shop} associated with the Item clicked and held
     */
    void onLocationLongClicked(final int itemPosition, Shop shop);
}
