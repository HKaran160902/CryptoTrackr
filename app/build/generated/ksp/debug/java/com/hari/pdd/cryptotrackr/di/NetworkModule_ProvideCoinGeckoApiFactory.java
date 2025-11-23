package com.hari.pdd.cryptotrackr.di;

import com.hari.pdd.cryptotrackr.data.remote.api.CoinGeckoApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideCoinGeckoApiFactory implements Factory<CoinGeckoApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideCoinGeckoApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public CoinGeckoApi get() {
    return provideCoinGeckoApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideCoinGeckoApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideCoinGeckoApiFactory(retrofitProvider);
  }

  public static CoinGeckoApi provideCoinGeckoApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideCoinGeckoApi(retrofit));
  }
}
