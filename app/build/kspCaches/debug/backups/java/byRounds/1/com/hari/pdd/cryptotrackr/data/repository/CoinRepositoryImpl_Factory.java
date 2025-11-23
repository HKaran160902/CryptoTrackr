package com.hari.pdd.cryptotrackr.data.repository;

import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao;
import com.hari.pdd.cryptotrackr.data.local.preferences.UserPreferences;
import com.hari.pdd.cryptotrackr.data.remote.api.CoinGeckoApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class CoinRepositoryImpl_Factory implements Factory<CoinRepositoryImpl> {
  private final Provider<CoinGeckoApi> apiProvider;

  private final Provider<CoinDao> coinDaoProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public CoinRepositoryImpl_Factory(Provider<CoinGeckoApi> apiProvider,
      Provider<CoinDao> coinDaoProvider, Provider<UserPreferences> userPreferencesProvider) {
    this.apiProvider = apiProvider;
    this.coinDaoProvider = coinDaoProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public CoinRepositoryImpl get() {
    return newInstance(apiProvider.get(), coinDaoProvider.get(), userPreferencesProvider.get());
  }

  public static CoinRepositoryImpl_Factory create(Provider<CoinGeckoApi> apiProvider,
      Provider<CoinDao> coinDaoProvider, Provider<UserPreferences> userPreferencesProvider) {
    return new CoinRepositoryImpl_Factory(apiProvider, coinDaoProvider, userPreferencesProvider);
  }

  public static CoinRepositoryImpl newInstance(CoinGeckoApi api, CoinDao coinDao,
      UserPreferences userPreferences) {
    return new CoinRepositoryImpl(api, coinDao, userPreferences);
  }
}
