package com.hari.pdd.cryptotrackr.di;

import com.hari.pdd.cryptotrackr.data.local.database.CryptoDatabase;
import com.hari.pdd.cryptotrackr.data.local.database.dao.WatchlistDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideWatchlistDaoFactory implements Factory<WatchlistDao> {
  private final Provider<CryptoDatabase> databaseProvider;

  public DatabaseModule_ProvideWatchlistDaoFactory(Provider<CryptoDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public WatchlistDao get() {
    return provideWatchlistDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideWatchlistDaoFactory create(
      Provider<CryptoDatabase> databaseProvider) {
    return new DatabaseModule_ProvideWatchlistDaoFactory(databaseProvider);
  }

  public static WatchlistDao provideWatchlistDao(CryptoDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideWatchlistDao(database));
  }
}
