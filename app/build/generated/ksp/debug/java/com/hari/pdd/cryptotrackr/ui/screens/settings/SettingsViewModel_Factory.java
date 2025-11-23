package com.hari.pdd.cryptotrackr.ui.screens.settings;

import com.hari.pdd.cryptotrackr.data.local.preferences.UserPreferences;
import com.hari.pdd.cryptotrackr.domain.repository.CoinRepository;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<UserPreferences> userPreferencesProvider;

  private final Provider<CoinRepository> coinRepositoryProvider;

  public SettingsViewModel_Factory(Provider<UserPreferences> userPreferencesProvider,
      Provider<CoinRepository> coinRepositoryProvider) {
    this.userPreferencesProvider = userPreferencesProvider;
    this.coinRepositoryProvider = coinRepositoryProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(userPreferencesProvider.get(), coinRepositoryProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<UserPreferences> userPreferencesProvider,
      Provider<CoinRepository> coinRepositoryProvider) {
    return new SettingsViewModel_Factory(userPreferencesProvider, coinRepositoryProvider);
  }

  public static SettingsViewModel newInstance(UserPreferences userPreferences,
      CoinRepository coinRepository) {
    return new SettingsViewModel(userPreferences, coinRepository);
  }
}
