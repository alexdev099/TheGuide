package com.alex.theguide.dagger2.module;

import android.content.Context;

import com.alex.theguide.dagger2.interfaces.ApplicationContext;
import com.alex.theguide.dagger2.interfaces.ApplicationScope;
import com.alex.theguide.model.LocalDBRepository;
import com.alex.theguide.model.MyDBHelper;

import dagger.Module;
import dagger.Provides;

@Module (includes = ContextModule.class)
public class LocalDBRepositoryModule {

    @Provides
    @ApplicationScope
    public LocalDBRepository localDBRepository(MyDBHelper myDBHelper){
        return new LocalDBRepository(myDBHelper);
    }

    @Provides
    @ApplicationScope
    public MyDBHelper myDBHelper(@ApplicationContext Context context){
        return new MyDBHelper(context);
    }
}
