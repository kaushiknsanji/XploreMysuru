package com.example.kaushiknsanji.xploremysuru.ui;

/**
 * Custom Presenter Interface that extends {@link BasePresenter} for use
 * with the Navigation Fragments of the {@link MainActivity}
 */
public interface NavigationPresenter extends BasePresenter {

    /**
     * Method invoked when the User clicks on the Refresh Menu button
     * shown by the {@link com.example.kaushiknsanji.xploremysuru.ui.MainActivity}
     */
    void onRefreshMenuClicked();
}
