package com.hari.pdd.cryptotrackr.di;

import com.hari.pdd.cryptotrackr.data.local.database.CryptoDatabase;
import com.hari.pdd.cryptotrackr.data.local.database.dao.AlertDao;
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
public final class DatabaseModule_ProvideAlertDaoFactory implements Factory<AlertDao> {
  private final Provider<CryptoDatabase> databaseProvider;

  public DatabaseModule_ProvideAlertDaoFactory(Provider<CryptoDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AlertDao get() {
    return provideAlertDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideAlertDaoFactory create(
      Provider<CryptoDatabase> databaseProvider) {
    return new DatabaseModule_ProvideAlertDaoFactory(databaseProvider);
  }

  public static AlertDao provideAlertDao(CryptoDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAlertDao(database));
  }
}
