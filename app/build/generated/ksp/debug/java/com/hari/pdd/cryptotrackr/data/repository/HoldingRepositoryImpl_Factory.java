package com.hari.pdd.cryptotrackr.data.repository;

import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao;
import com.hari.pdd.cryptotrackr.data.local.database.dao.HoldingDao;
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
public final class HoldingRepositoryImpl_Factory implements Factory<HoldingRepositoryImpl> {
  private final Provider<HoldingDao> holdingDaoProvider;

  private final Provider<CoinDao> coinDaoProvider;

  private final Provider<CoinGeckoApi> apiProvider;

  public HoldingRepositoryImpl_Factory(Provider<HoldingDao> holdingDaoProvider,
      Provider<CoinDao> coinDaoProvider, Provider<CoinGeckoApi> apiProvider) {
    this.holdingDaoProvider = holdingDaoProvider;
    this.coinDaoProvider = coinDaoProvider;
    this.apiProvider = apiProvider;
  }

  @Override
  public HoldingRepositoryImpl get() {
    return newInstance(holdingDaoProvider.get(), coinDaoProvider.get(), apiProvider.get());
  }

  public static HoldingRepositoryImpl_Factory create(Provider<HoldingDao> holdingDaoProvider,
      Provider<CoinDao> coinDaoProvider, Provider<CoinGeckoApi> apiProvider) {
    return new HoldingRepositoryImpl_Factory(holdingDaoProvider, coinDaoProvider, apiProvider);
  }

  public static HoldingRepositoryImpl newInstance(HoldingDao holdingDao, CoinDao coinDao,
      CoinGeckoApi api) {
    return new HoldingRepositoryImpl(holdingDao, coinDao, api);
  }
}
