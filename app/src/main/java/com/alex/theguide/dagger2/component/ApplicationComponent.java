package com.alex.theguide.dagger2.component;

import com.alex.theguide.dagger2.interfaces.ApplicationScope;
import com.alex.theguide.dagger2.module.LocalDBRepositoryModule;
import com.alex.theguide.model.LocalDBRepository;

import dagger.Component;

@ApplicationScope
@Component (modules = {LocalDBRepositoryModule.class})
public interface ApplicationComponent {

    LocalDBRepository getLocalDBRepository();


}
