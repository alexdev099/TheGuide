package com.alex.theguide.dagger2.module;

import android.content.Context;

import com.alex.theguide.dagger2.interfaces.ApplicationContext;
import com.alex.theguide.dagger2.interfaces.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationContext
    @ApplicationScope
    @Provides
    public Context context(){
        return context.getApplicationContext();
    }
}
