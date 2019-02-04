package com.alex.theguide.dagger2.module;

import com.alex.theguide.dagger2.interfaces.MainActivityScope;
import com.alex.theguide.model.LocalDBRepository;
import com.alex.theguide.presenter.IMainViewPresenter;
import com.alex.theguide.presenter.MainViewPresenter;
import com.alex.theguide.view.MyDialogFragment;
import com.alex.theguide.view.MyRecyclerViewAdapter;
import com.alex.theguide.view.RecyclerItemTouchHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private int dragsDirs, swipeDirs;

    public MainActivityModule(int dragsDirs, int swipeDirs){
        this.dragsDirs = dragsDirs;
        this.swipeDirs = swipeDirs;
    }

    @Provides
    @MainActivityScope
    public MyRecyclerViewAdapter recyclerViewAdapter(){
        return new MyRecyclerViewAdapter();
    }

    @Provides
    @MainActivityScope
    public MyDialogFragment dialogFragment(){
        return new MyDialogFragment();
    }

    @Provides
    @MainActivityScope
    public IMainViewPresenter presenter(LocalDBRepository localDBRepository){
        return new MainViewPresenter(localDBRepository);
    }

    @Provides
    @MainActivityScope
    public RecyclerItemTouchHelper itemTouchHelper(){
        return new RecyclerItemTouchHelper(dragsDirs, swipeDirs);
    }
}
