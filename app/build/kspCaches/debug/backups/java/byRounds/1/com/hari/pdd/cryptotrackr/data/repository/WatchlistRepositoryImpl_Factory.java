package com.hari.pdd.cryptotrackr.data.repository;

import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao;
import com.hari.pdd.cryptotrackr.data.local.database.dao.WatchlistDao;
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
public final class WatchlistRepositoryImpl_Factory implements Factory<WatchlistRepositoryImpl> {
  private final Provider<WatchlistDao> watchlistDaoProvider;

  private final Provider<CoinDao> coinDaoProvider;

  private final Provider<CoinGeckoApi> apiProvider;

  public WatchlistRepositoryImpl_Factory(Provider<WatchlistDao> watchlistDaoProvider,
      Provider<CoinDao> coinDaoProvider, Provider<CoinGeckoApi> apiProvider) {
    this.watchlistDaoProvider = watchlistDaoProvider;
    this.coinDaoProvider = coinDaoProvider;
    this.apiProvider = apiProvider;
  }

  @Override
  public WatchlistRepositoryImpl get() {
    return newInstance(watchlistDaoProvider.get(), coinDaoProvider.get(), apiProvider.get());
  }

  public static WatchlistRepositoryImpl_Factory create(Provider<WatchlistDao> watchlistDaoProvider,
      Provider<CoinDao> coinDaoProvider, Provider<CoinGeckoApi> apiProvider) {
    return new WatchlistRepositoryImpl_Factory(watchlistDaoProvider, coinDaoProvider, apiProvider);
  }

  public static WatchlistRepositoryImpl newInstance(WatchlistDao watchlistDao, CoinDao coinDao,
      CoinGeckoApi api) {
    return new WatchlistRepositoryImpl(watchlistDao, coinDao, api);
  }
}
