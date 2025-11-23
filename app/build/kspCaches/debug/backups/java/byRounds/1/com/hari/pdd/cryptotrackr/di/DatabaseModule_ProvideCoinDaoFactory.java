package com.hari.pdd.cryptotrackr.di;

import com.hari.pdd.cryptotrackr.data.local.database.CryptoDatabase;
import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao;
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
public final class DatabaseModule_ProvideCoinDaoFactory implements Factory<CoinDao> {
  private final Provider<CryptoDatabase> databaseProvider;

  public DatabaseModule_ProvideCoinDaoFactory(Provider<CryptoDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CoinDao get() {
    return provideCoinDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideCoinDaoFactory create(
      Provider<CryptoDatabase> databaseProvider) {
    return new DatabaseModule_ProvideCoinDaoFactory(databaseProvider);
  }

  public static CoinDao provideCoinDao(CryptoDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCoinDao(database));
  }
}
