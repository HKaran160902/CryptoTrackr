package com.hari.pdd.cryptotrackr.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class PriceAlertWorker_AssistedFactory_Impl implements PriceAlertWorker_AssistedFactory {
  private final PriceAlertWorker_Factory delegateFactory;

  PriceAlertWorker_AssistedFactory_Impl(PriceAlertWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public PriceAlertWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<PriceAlertWorker_AssistedFactory> create(
      PriceAlertWorker_Factory delegateFactory) {
    return InstanceFactory.create(new PriceAlertWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<PriceAlertWorker_AssistedFactory> createFactoryProvider(
      PriceAlertWorker_Factory delegateFactory) {
    return InstanceFactory.create(new PriceAlertWorker_AssistedFactory_Impl(delegateFactory));
  }
}
