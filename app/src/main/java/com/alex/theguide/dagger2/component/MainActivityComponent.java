package com.alex.theguide.dagger2.component;

import com.alex.theguide.dagger2.interfaces.MainActivityScope;
import com.alex.theguide.dagger2.module.MainActivityModule;
import com.alex.theguide.presenter.IMainViewPresenter;
import com.alex.theguide.view.MyDialogFragment;
import com.alex.theguide.view.MyRecyclerViewAdapter;
import com.alex.theguide.view.RecyclerItemTouchHelper;

import dagger.Component;

@Component(modules = {MainActivityModule.class}, dependencies = ApplicationComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    MyDialogFragment getMyDialogFragment();

    MyRecyclerViewAdapter getMyRecyclerViewAdapter();

    IMainViewPresenter getMainViewPresenter();

    RecyclerItemTouchHelper getRecyclerItemTouchHelper();
}
