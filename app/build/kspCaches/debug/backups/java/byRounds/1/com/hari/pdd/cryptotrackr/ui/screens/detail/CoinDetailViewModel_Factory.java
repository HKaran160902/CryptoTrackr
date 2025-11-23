package com.hari.pdd.cryptotrackr.ui.screens.detail;

import androidx.lifecycle.SavedStateHandle;
import com.hari.pdd.cryptotrackr.domain.repository.AlertRepository;
import com.hari.pdd.cryptotrackr.domain.repository.CoinRepository;
import com.hari.pdd.cryptotrackr.domain.repository.WatchlistRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class CoinDetailViewModel_Factory implements Factory<CoinDetailViewModel> {
  private final Provider<CoinRepository> coinRepositoryProvider;

  private final Provider<WatchlistRepository> watchlistRepositoryProvider;

  private final Provider<AlertRepository> alertRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public CoinDetailViewModel_Factory(Provider<CoinRepository> coinRepositoryProvider,
      Provider<WatchlistRepository> watchlistRepositoryProvider,
      Provider<AlertRepository> alertRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.coinRepositoryProvider = coinRepositoryProvider;
    this.watchlistRepositoryProvider = watchlistRepositoryProvider;
    this.alertRepositoryProvider = alertRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public CoinDetailViewModel get() {
    return newInstance(coinRepositoryProvider.get(), watchlistRepositoryProvider.get(), alertRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static CoinDetailViewModel_Factory create(Provider<CoinRepository> coinRepositoryProvider,
      Provider<WatchlistRepository> watchlistRepositoryProvider,
      Provider<AlertRepository> alertRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new CoinDetailViewModel_Factory(coinRepositoryProvider, watchlistRepositoryProvider, alertRepositoryProvider, savedStateHandleProvider);
  }

  public static CoinDetailViewModel newInstance(CoinRepository coinRepository,
      WatchlistRepository watchlistRepository, AlertRepository alertRepository,
      SavedStateHandle savedStateHandle) {
    return new CoinDetailViewModel(coinRepository, watchlistRepository, alertRepository, savedStateHandle);
  }
}
