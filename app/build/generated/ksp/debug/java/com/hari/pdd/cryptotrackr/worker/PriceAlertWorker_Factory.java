package com.hari.pdd.cryptotrackr.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.hari.pdd.cryptotrackr.data.remote.api.CoinGeckoApi;
import com.hari.pdd.cryptotrackr.domain.repository.AlertRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class PriceAlertWorker_Factory {
  private final Provider<AlertRepository> alertRepositoryProvider;

  private final Provider<CoinGeckoApi> coinGeckoApiProvider;

  public PriceAlertWorker_Factory(Provider<AlertRepository> alertRepositoryProvider,
      Provider<CoinGeckoApi> coinGeckoApiProvider) {
    this.alertRepositoryProvider = alertRepositoryProvider;
    this.coinGeckoApiProvider = coinGeckoApiProvider;
  }

  public PriceAlertWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, alertRepositoryProvider.get(), coinGeckoApiProvider.get());
  }

  public static PriceAlertWorker_Factory create(Provider<AlertRepository> alertRepositoryProvider,
      Provider<CoinGeckoApi> coinGeckoApiProvider) {
    return new PriceAlertWorker_Factory(alertRepositoryProvider, coinGeckoApiProvider);
  }

  public static PriceAlertWorker newInstance(Context context, WorkerParameters workerParams,
      AlertRepository alertRepository, CoinGeckoApi coinGeckoApi) {
    return new PriceAlertWorker(context, workerParams, alertRepository, coinGeckoApi);
  }
}
