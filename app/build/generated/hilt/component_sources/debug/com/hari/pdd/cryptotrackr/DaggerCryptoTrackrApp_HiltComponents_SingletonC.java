package com.hari.pdd.cryptotrackr;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.hari.pdd.cryptotrackr.data.local.database.CryptoDatabase;
import com.hari.pdd.cryptotrackr.data.local.database.dao.AlertDao;
import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao;
import com.hari.pdd.cryptotrackr.data.local.database.dao.WatchlistDao;
import com.hari.pdd.cryptotrackr.data.local.preferences.UserPreferences;
import com.hari.pdd.cryptotrackr.data.remote.api.CoinGeckoApi;
import com.hari.pdd.cryptotrackr.data.repository.AlertRepositoryImpl;
import com.hari.pdd.cryptotrackr.data.repository.CoinRepositoryImpl;
import com.hari.pdd.cryptotrackr.data.repository.WatchlistRepositoryImpl;
import com.hari.pdd.cryptotrackr.di.DatabaseModule_ProvideAlertDaoFactory;
import com.hari.pdd.cryptotrackr.di.DatabaseModule_ProvideCoinDaoFactory;
import com.hari.pdd.cryptotrackr.di.DatabaseModule_ProvideDatabaseFactory;
import com.hari.pdd.cryptotrackr.di.DatabaseModule_ProvideWatchlistDaoFactory;
import com.hari.pdd.cryptotrackr.di.NetworkModule_ProvideCoinGeckoApiFactory;
import com.hari.pdd.cryptotrackr.di.NetworkModule_ProvideLoggingInterceptorFactory;
import com.hari.pdd.cryptotrackr.di.NetworkModule_ProvideOkHttpClientFactory;
import com.hari.pdd.cryptotrackr.di.NetworkModule_ProvideRetrofitFactory;
import com.hari.pdd.cryptotrackr.ui.screens.alerts.AlertsViewModel;
import com.hari.pdd.cryptotrackr.ui.screens.alerts.AlertsViewModel_HiltModules;
import com.hari.pdd.cryptotrackr.ui.screens.detail.CoinDetailViewModel;
import com.hari.pdd.cryptotrackr.ui.screens.detail.CoinDetailViewModel_HiltModules;
import com.hari.pdd.cryptotrackr.ui.screens.home.HomeViewModel;
import com.hari.pdd.cryptotrackr.ui.screens.home.HomeViewModel_HiltModules;
import com.hari.pdd.cryptotrackr.ui.screens.settings.SettingsViewModel;
import com.hari.pdd.cryptotrackr.ui.screens.settings.SettingsViewModel_HiltModules;
import com.hari.pdd.cryptotrackr.ui.screens.watchlist.WatchlistViewModel;
import com.hari.pdd.cryptotrackr.ui.screens.watchlist.WatchlistViewModel_HiltModules;
import com.hari.pdd.cryptotrackr.worker.PriceAlertWorker;
import com.hari.pdd.cryptotrackr.worker.PriceAlertWorker_AssistedFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DaggerCryptoTrackrApp_HiltComponents_SingletonC {
  private DaggerCryptoTrackrApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public CryptoTrackrApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements CryptoTrackrApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public CryptoTrackrApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements CryptoTrackrApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public CryptoTrackrApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements CryptoTrackrApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public CryptoTrackrApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements CryptoTrackrApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CryptoTrackrApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements CryptoTrackrApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CryptoTrackrApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements CryptoTrackrApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public CryptoTrackrApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements CryptoTrackrApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public CryptoTrackrApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends CryptoTrackrApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends CryptoTrackrApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends CryptoTrackrApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends CryptoTrackrApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(5).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_alerts_AlertsViewModel, AlertsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_detail_CoinDetailViewModel, CoinDetailViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_home_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_settings_SettingsViewModel, SettingsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_watchlist_WatchlistViewModel, WatchlistViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectUserPreferences(instance, singletonCImpl.userPreferencesProvider.get());
      return instance;
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_hari_pdd_cryptotrackr_ui_screens_detail_CoinDetailViewModel = "com.hari.pdd.cryptotrackr.ui.screens.detail.CoinDetailViewModel";

      static String com_hari_pdd_cryptotrackr_ui_screens_home_HomeViewModel = "com.hari.pdd.cryptotrackr.ui.screens.home.HomeViewModel";

      static String com_hari_pdd_cryptotrackr_ui_screens_watchlist_WatchlistViewModel = "com.hari.pdd.cryptotrackr.ui.screens.watchlist.WatchlistViewModel";

      static String com_hari_pdd_cryptotrackr_ui_screens_alerts_AlertsViewModel = "com.hari.pdd.cryptotrackr.ui.screens.alerts.AlertsViewModel";

      static String com_hari_pdd_cryptotrackr_ui_screens_settings_SettingsViewModel = "com.hari.pdd.cryptotrackr.ui.screens.settings.SettingsViewModel";

      @KeepFieldType
      CoinDetailViewModel com_hari_pdd_cryptotrackr_ui_screens_detail_CoinDetailViewModel2;

      @KeepFieldType
      HomeViewModel com_hari_pdd_cryptotrackr_ui_screens_home_HomeViewModel2;

      @KeepFieldType
      WatchlistViewModel com_hari_pdd_cryptotrackr_ui_screens_watchlist_WatchlistViewModel2;

      @KeepFieldType
      AlertsViewModel com_hari_pdd_cryptotrackr_ui_screens_alerts_AlertsViewModel2;

      @KeepFieldType
      SettingsViewModel com_hari_pdd_cryptotrackr_ui_screens_settings_SettingsViewModel2;
    }
  }

  private static final class ViewModelCImpl extends CryptoTrackrApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AlertsViewModel> alertsViewModelProvider;

    private Provider<CoinDetailViewModel> coinDetailViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private Provider<WatchlistViewModel> watchlistViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.alertsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.coinDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.watchlistViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(5).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_alerts_AlertsViewModel, ((Provider) alertsViewModelProvider)).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_detail_CoinDetailViewModel, ((Provider) coinDetailViewModelProvider)).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_home_HomeViewModel, ((Provider) homeViewModelProvider)).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_settings_SettingsViewModel, ((Provider) settingsViewModelProvider)).put(LazyClassKeyProvider.com_hari_pdd_cryptotrackr_ui_screens_watchlist_WatchlistViewModel, ((Provider) watchlistViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_hari_pdd_cryptotrackr_ui_screens_home_HomeViewModel = "com.hari.pdd.cryptotrackr.ui.screens.home.HomeViewModel";

      static String com_hari_pdd_cryptotrackr_ui_screens_watchlist_WatchlistViewModel = "com.hari.pdd.cryptotrackr.ui.screens.watchlist.WatchlistViewModel";

      static String com_hari_pdd_cryptotrackr_ui_screens_detail_CoinDetailViewModel = "com.hari.pdd.cryptotrackr.ui.screens.detail.CoinDetailViewModel";

      static String com_hari_pdd_cryptotrackr_ui_screens_alerts_AlertsViewModel = "com.hari.pdd.cryptotrackr.ui.screens.alerts.AlertsViewModel";

      static String com_hari_pdd_cryptotrackr_ui_screens_settings_SettingsViewModel = "com.hari.pdd.cryptotrackr.ui.screens.settings.SettingsViewModel";

      @KeepFieldType
      HomeViewModel com_hari_pdd_cryptotrackr_ui_screens_home_HomeViewModel2;

      @KeepFieldType
      WatchlistViewModel com_hari_pdd_cryptotrackr_ui_screens_watchlist_WatchlistViewModel2;

      @KeepFieldType
      CoinDetailViewModel com_hari_pdd_cryptotrackr_ui_screens_detail_CoinDetailViewModel2;

      @KeepFieldType
      AlertsViewModel com_hari_pdd_cryptotrackr_ui_screens_alerts_AlertsViewModel2;

      @KeepFieldType
      SettingsViewModel com_hari_pdd_cryptotrackr_ui_screens_settings_SettingsViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.hari.pdd.cryptotrackr.ui.screens.alerts.AlertsViewModel 
          return (T) new AlertsViewModel(singletonCImpl.alertRepositoryImplProvider.get());

          case 1: // com.hari.pdd.cryptotrackr.ui.screens.detail.CoinDetailViewModel 
          return (T) new CoinDetailViewModel(singletonCImpl.coinRepositoryImplProvider.get(), singletonCImpl.watchlistRepositoryImplProvider.get(), singletonCImpl.alertRepositoryImplProvider.get(), viewModelCImpl.savedStateHandle);

          case 2: // com.hari.pdd.cryptotrackr.ui.screens.home.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.coinRepositoryImplProvider.get(), singletonCImpl.watchlistRepositoryImplProvider.get());

          case 3: // com.hari.pdd.cryptotrackr.ui.screens.settings.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.userPreferencesProvider.get(), singletonCImpl.coinRepositoryImplProvider.get());

          case 4: // com.hari.pdd.cryptotrackr.ui.screens.watchlist.WatchlistViewModel 
          return (T) new WatchlistViewModel(singletonCImpl.watchlistRepositoryImplProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends CryptoTrackrApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends CryptoTrackrApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends CryptoTrackrApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<CryptoDatabase> provideDatabaseProvider;

    private Provider<AlertDao> provideAlertDaoProvider;

    private Provider<AlertRepositoryImpl> alertRepositoryImplProvider;

    private Provider<HttpLoggingInterceptor> provideLoggingInterceptorProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<CoinGeckoApi> provideCoinGeckoApiProvider;

    private Provider<PriceAlertWorker_AssistedFactory> priceAlertWorker_AssistedFactoryProvider;

    private Provider<UserPreferences> userPreferencesProvider;

    private Provider<CoinDao> provideCoinDaoProvider;

    private Provider<CoinRepositoryImpl> coinRepositoryImplProvider;

    private Provider<WatchlistDao> provideWatchlistDaoProvider;

    private Provider<WatchlistRepositoryImpl> watchlistRepositoryImplProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return Collections.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>singletonMap("com.hari.pdd.cryptotrackr.worker.PriceAlertWorker", ((Provider) priceAlertWorker_AssistedFactoryProvider));
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<CryptoDatabase>(singletonCImpl, 3));
      this.provideAlertDaoProvider = DoubleCheck.provider(new SwitchingProvider<AlertDao>(singletonCImpl, 2));
      this.alertRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<AlertRepositoryImpl>(singletonCImpl, 1));
      this.provideLoggingInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<HttpLoggingInterceptor>(singletonCImpl, 7));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 6));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 5));
      this.provideCoinGeckoApiProvider = DoubleCheck.provider(new SwitchingProvider<CoinGeckoApi>(singletonCImpl, 4));
      this.priceAlertWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<PriceAlertWorker_AssistedFactory>(singletonCImpl, 0));
      this.userPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<UserPreferences>(singletonCImpl, 8));
      this.provideCoinDaoProvider = DoubleCheck.provider(new SwitchingProvider<CoinDao>(singletonCImpl, 10));
      this.coinRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<CoinRepositoryImpl>(singletonCImpl, 9));
      this.provideWatchlistDaoProvider = DoubleCheck.provider(new SwitchingProvider<WatchlistDao>(singletonCImpl, 12));
      this.watchlistRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<WatchlistRepositoryImpl>(singletonCImpl, 11));
    }

    @Override
    public void injectCryptoTrackrApp(CryptoTrackrApp cryptoTrackrApp) {
      injectCryptoTrackrApp2(cryptoTrackrApp);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private CryptoTrackrApp injectCryptoTrackrApp2(CryptoTrackrApp instance) {
      CryptoTrackrApp_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.hari.pdd.cryptotrackr.worker.PriceAlertWorker_AssistedFactory 
          return (T) new PriceAlertWorker_AssistedFactory() {
            @Override
            public PriceAlertWorker create(Context context, WorkerParameters workerParams) {
              return new PriceAlertWorker(context, workerParams, singletonCImpl.alertRepositoryImplProvider.get(), singletonCImpl.provideCoinGeckoApiProvider.get());
            }
          };

          case 1: // com.hari.pdd.cryptotrackr.data.repository.AlertRepositoryImpl 
          return (T) new AlertRepositoryImpl(singletonCImpl.provideAlertDaoProvider.get());

          case 2: // com.hari.pdd.cryptotrackr.data.local.database.dao.AlertDao 
          return (T) DatabaseModule_ProvideAlertDaoFactory.provideAlertDao(singletonCImpl.provideDatabaseProvider.get());

          case 3: // com.hari.pdd.cryptotrackr.data.local.database.CryptoDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.hari.pdd.cryptotrackr.data.remote.api.CoinGeckoApi 
          return (T) NetworkModule_ProvideCoinGeckoApiFactory.provideCoinGeckoApi(singletonCImpl.provideRetrofitProvider.get());

          case 5: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 6: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.provideLoggingInterceptorProvider.get());

          case 7: // okhttp3.logging.HttpLoggingInterceptor 
          return (T) NetworkModule_ProvideLoggingInterceptorFactory.provideLoggingInterceptor();

          case 8: // com.hari.pdd.cryptotrackr.data.local.preferences.UserPreferences 
          return (T) new UserPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 9: // com.hari.pdd.cryptotrackr.data.repository.CoinRepositoryImpl 
          return (T) new CoinRepositoryImpl(singletonCImpl.provideCoinGeckoApiProvider.get(), singletonCImpl.provideCoinDaoProvider.get(), singletonCImpl.userPreferencesProvider.get());

          case 10: // com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao 
          return (T) DatabaseModule_ProvideCoinDaoFactory.provideCoinDao(singletonCImpl.provideDatabaseProvider.get());

          case 11: // com.hari.pdd.cryptotrackr.data.repository.WatchlistRepositoryImpl 
          return (T) new WatchlistRepositoryImpl(singletonCImpl.provideWatchlistDaoProvider.get(), singletonCImpl.provideCoinDaoProvider.get(), singletonCImpl.provideCoinGeckoApiProvider.get());

          case 12: // com.hari.pdd.cryptotrackr.data.local.database.dao.WatchlistDao 
          return (T) DatabaseModule_ProvideWatchlistDaoFactory.provideWatchlistDao(singletonCImpl.provideDatabaseProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
