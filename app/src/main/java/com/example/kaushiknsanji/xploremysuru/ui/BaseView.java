package com.example.kaushiknsanji.xploremysuru.ui;

/**
 * Base View Interface as in Model-View-Presenter.
 *
 * @author Kaushik N Sanji
 */
public interface BaseView<T extends BasePresenter> {

    /**
     * Method that registers the Presenter {@code presenter} with the View implementing {@link com.example.kaushiknsanji.xploremysuru.ui.BaseView}
     *
     * @param presenter Presenter instance implementing the {@link com.example.kaushiknsanji.xploremysuru.ui.BasePresenter}
     */
    void setPresenter(T presenter);
}
