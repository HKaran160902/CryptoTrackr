package com.hari.pdd.cryptotrackr.ui.screens.watchlist;

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
public final class WatchlistViewModel_Factory implements Factory<WatchlistViewModel> {
  private final Provider<WatchlistRepository> watchlistRepositoryProvider;

  public WatchlistViewModel_Factory(Provider<WatchlistRepository> watchlistRepositoryProvider) {
    this.watchlistRepositoryProvider = watchlistRepositoryProvider;
  }

  @Override
  public WatchlistViewModel get() {
    return newInstance(watchlistRepositoryProvider.get());
  }

  public static WatchlistViewModel_Factory create(
      Provider<WatchlistRepository> watchlistRepositoryProvider) {
    return new WatchlistViewModel_Factory(watchlistRepositoryProvider);
  }

  public static WatchlistViewModel newInstance(WatchlistRepository watchlistRepository) {
    return new WatchlistViewModel(watchlistRepository);
  }
}
