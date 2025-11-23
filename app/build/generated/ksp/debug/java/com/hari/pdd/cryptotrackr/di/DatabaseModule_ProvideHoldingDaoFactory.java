package com.hari.pdd.cryptotrackr.di;

import com.hari.pdd.cryptotrackr.data.local.database.CryptoDatabase;
import com.hari.pdd.cryptotrackr.data.local.database.dao.HoldingDao;
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
public final class DatabaseModule_ProvideHoldingDaoFactory implements Factory<HoldingDao> {
  private final Provider<CryptoDatabase> databaseProvider;

  public DatabaseModule_ProvideHoldingDaoFactory(Provider<CryptoDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public HoldingDao get() {
    return provideHoldingDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideHoldingDaoFactory create(
      Provider<CryptoDatabase> databaseProvider) {
    return new DatabaseModule_ProvideHoldingDaoFactory(databaseProvider);
  }

  public static HoldingDao provideHoldingDao(CryptoDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideHoldingDao(database));
  }
}
