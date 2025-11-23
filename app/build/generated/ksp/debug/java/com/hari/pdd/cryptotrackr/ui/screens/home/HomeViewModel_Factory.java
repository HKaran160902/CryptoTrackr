package com.hari.pdd.cryptotrackr.ui.screens.home;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<CoinRepository> coinRepositoryProvider;

  private final Provider<WatchlistRepository> watchlistRepositoryProvider;

  public HomeViewModel_Factory(Provider<CoinRepository> coinRepositoryProvider,
      Provider<WatchlistRepository> watchlistRepositoryProvider) {
    this.coinRepositoryProvider = coinRepositoryProvider;
    this.watchlistRepositoryProvider = watchlistRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(coinRepositoryProvider.get(), watchlistRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<CoinRepository> coinRepositoryProvider,
      Provider<WatchlistRepository> watchlistRepositoryProvider) {
    return new HomeViewModel_Factory(coinRepositoryProvider, watchlistRepositoryProvider);
  }

  public static HomeViewModel newInstance(CoinRepository coinRepository,
      WatchlistRepository watchlistRepository) {
    return new HomeViewModel(coinRepository, watchlistRepository);
  }
}
