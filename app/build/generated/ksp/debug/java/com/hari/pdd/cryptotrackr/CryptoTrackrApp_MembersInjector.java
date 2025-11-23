package com.hari.pdd.cryptotrackr;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class CryptoTrackrApp_MembersInjector implements MembersInjector<CryptoTrackrApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public CryptoTrackrApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<CryptoTrackrApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new CryptoTrackrApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(CryptoTrackrApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.hari.pdd.cryptotrackr.CryptoTrackrApp.workerFactory")
  public static void injectWorkerFactory(CryptoTrackrApp instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
