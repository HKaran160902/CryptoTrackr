package com.hari.pdd.cryptotrackr.data.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.hari.pdd.cryptotrackr.data.local.database.dao.AlertDao;
import com.hari.pdd.cryptotrackr.data.local.database.dao.AlertDao_Impl;
import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao;
import com.hari.pdd.cryptotrackr.data.local.database.dao.CoinDao_Impl;
import com.hari.pdd.cryptotrackr.data.local.database.dao.HoldingDao;
import com.hari.pdd.cryptotrackr.data.local.database.dao.HoldingDao_Impl;
import com.hari.pdd.cryptotrackr.data.local.database.dao.WatchlistDao;
import com.hari.pdd.cryptotrackr.data.local.database.dao.WatchlistDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CryptoDatabase_Impl extends CryptoDatabase {
  private volatile CoinDao _coinDao;

  private volatile WatchlistDao _watchlistDao;

  private volatile HoldingDao _holdingDao;

  private volatile AlertDao _alertDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `coins` (`id` TEXT NOT NULL, `symbol` TEXT NOT NULL, `name` TEXT NOT NULL, `image` TEXT NOT NULL, `currentPrice` REAL, `marketCap` INTEGER, `marketCapRank` INTEGER, `totalVolume` INTEGER, `high24h` REAL, `low24h` REAL, `priceChange24h` REAL, `priceChangePercentage24h` REAL, `priceChangePercentage1h` REAL, `priceChangePercentage7d` REAL, `circulatingSupply` REAL, `totalSupply` REAL, `maxSupply` REAL, `ath` REAL, `athChangePercentage` REAL, `athDate` TEXT, `atl` REAL, `atlChangePercentage` REAL, `atlDate` TEXT, `lastUpdated` TEXT, `sparklineData` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `watchlist` (`coinId` TEXT NOT NULL, `addedAt` INTEGER NOT NULL, PRIMARY KEY(`coinId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `holdings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `coinId` TEXT NOT NULL, `coinName` TEXT NOT NULL, `coinSymbol` TEXT NOT NULL, `coinImage` TEXT NOT NULL, `quantity` REAL NOT NULL, `purchasePrice` REAL NOT NULL, `purchaseDate` INTEGER NOT NULL, `notes` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `alerts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `coinId` TEXT NOT NULL, `coinName` TEXT NOT NULL, `coinSymbol` TEXT NOT NULL, `coinImage` TEXT NOT NULL, `targetPrice` REAL NOT NULL, `isAbove` INTEGER NOT NULL, `isEnabled` INTEGER NOT NULL, `isTriggered` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `triggeredAt` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1a455e5a47b12629e5e611d31e2e8930')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `coins`");
        db.execSQL("DROP TABLE IF EXISTS `watchlist`");
        db.execSQL("DROP TABLE IF EXISTS `holdings`");
        db.execSQL("DROP TABLE IF EXISTS `alerts`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCoins = new HashMap<String, TableInfo.Column>(26);
        _columnsCoins.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("symbol", new TableInfo.Column("symbol", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("image", new TableInfo.Column("image", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("currentPrice", new TableInfo.Column("currentPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("marketCap", new TableInfo.Column("marketCap", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("marketCapRank", new TableInfo.Column("marketCapRank", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("totalVolume", new TableInfo.Column("totalVolume", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("high24h", new TableInfo.Column("high24h", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("low24h", new TableInfo.Column("low24h", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("priceChange24h", new TableInfo.Column("priceChange24h", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("priceChangePercentage24h", new TableInfo.Column("priceChangePercentage24h", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("priceChangePercentage1h", new TableInfo.Column("priceChangePercentage1h", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("priceChangePercentage7d", new TableInfo.Column("priceChangePercentage7d", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("circulatingSupply", new TableInfo.Column("circulatingSupply", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("totalSupply", new TableInfo.Column("totalSupply", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("maxSupply", new TableInfo.Column("maxSupply", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("ath", new TableInfo.Column("ath", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("athChangePercentage", new TableInfo.Column("athChangePercentage", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("athDate", new TableInfo.Column("athDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("atl", new TableInfo.Column("atl", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("atlChangePercentage", new TableInfo.Column("atlChangePercentage", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("atlDate", new TableInfo.Column("atlDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("lastUpdated", new TableInfo.Column("lastUpdated", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("sparklineData", new TableInfo.Column("sparklineData", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoins.put("cachedAt", new TableInfo.Column("cachedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCoins = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCoins = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCoins = new TableInfo("coins", _columnsCoins, _foreignKeysCoins, _indicesCoins);
        final TableInfo _existingCoins = TableInfo.read(db, "coins");
        if (!_infoCoins.equals(_existingCoins)) {
          return new RoomOpenHelper.ValidationResult(false, "coins(com.hari.pdd.cryptotrackr.data.local.entity.CoinEntity).\n"
                  + " Expected:\n" + _infoCoins + "\n"
                  + " Found:\n" + _existingCoins);
        }
        final HashMap<String, TableInfo.Column> _columnsWatchlist = new HashMap<String, TableInfo.Column>(2);
        _columnsWatchlist.put("coinId", new TableInfo.Column("coinId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchlist.put("addedAt", new TableInfo.Column("addedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWatchlist = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWatchlist = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWatchlist = new TableInfo("watchlist", _columnsWatchlist, _foreignKeysWatchlist, _indicesWatchlist);
        final TableInfo _existingWatchlist = TableInfo.read(db, "watchlist");
        if (!_infoWatchlist.equals(_existingWatchlist)) {
          return new RoomOpenHelper.ValidationResult(false, "watchlist(com.hari.pdd.cryptotrackr.data.local.entity.WatchlistEntity).\n"
                  + " Expected:\n" + _infoWatchlist + "\n"
                  + " Found:\n" + _existingWatchlist);
        }
        final HashMap<String, TableInfo.Column> _columnsHoldings = new HashMap<String, TableInfo.Column>(9);
        _columnsHoldings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHoldings.put("coinId", new TableInfo.Column("coinId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHoldings.put("coinName", new TableInfo.Column("coinName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHoldings.put("coinSymbol", new TableInfo.Column("coinSymbol", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHoldings.put("coinImage", new TableInfo.Column("coinImage", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHoldings.put("quantity", new TableInfo.Column("quantity", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHoldings.put("purchasePrice", new TableInfo.Column("purchasePrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHoldings.put("purchaseDate", new TableInfo.Column("purchaseDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHoldings.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHoldings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHoldings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHoldings = new TableInfo("holdings", _columnsHoldings, _foreignKeysHoldings, _indicesHoldings);
        final TableInfo _existingHoldings = TableInfo.read(db, "holdings");
        if (!_infoHoldings.equals(_existingHoldings)) {
          return new RoomOpenHelper.ValidationResult(false, "holdings(com.hari.pdd.cryptotrackr.data.local.entity.HoldingEntity).\n"
                  + " Expected:\n" + _infoHoldings + "\n"
                  + " Found:\n" + _existingHoldings);
        }
        final HashMap<String, TableInfo.Column> _columnsAlerts = new HashMap<String, TableInfo.Column>(11);
        _columnsAlerts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("coinId", new TableInfo.Column("coinId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("coinName", new TableInfo.Column("coinName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("coinSymbol", new TableInfo.Column("coinSymbol", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("coinImage", new TableInfo.Column("coinImage", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("targetPrice", new TableInfo.Column("targetPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("isAbove", new TableInfo.Column("isAbove", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("isTriggered", new TableInfo.Column("isTriggered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlerts.put("triggeredAt", new TableInfo.Column("triggeredAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlerts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAlerts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAlerts = new TableInfo("alerts", _columnsAlerts, _foreignKeysAlerts, _indicesAlerts);
        final TableInfo _existingAlerts = TableInfo.read(db, "alerts");
        if (!_infoAlerts.equals(_existingAlerts)) {
          return new RoomOpenHelper.ValidationResult(false, "alerts(com.hari.pdd.cryptotrackr.data.local.entity.AlertEntity).\n"
                  + " Expected:\n" + _infoAlerts + "\n"
                  + " Found:\n" + _existingAlerts);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1a455e5a47b12629e5e611d31e2e8930", "5af4fcc4c4103cf139cd8976a94fc48c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "coins","watchlist","holdings","alerts");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `coins`");
      _db.execSQL("DELETE FROM `watchlist`");
      _db.execSQL("DELETE FROM `holdings`");
      _db.execSQL("DELETE FROM `alerts`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CoinDao.class, CoinDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WatchlistDao.class, WatchlistDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HoldingDao.class, HoldingDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AlertDao.class, AlertDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CoinDao coinDao() {
    if (_coinDao != null) {
      return _coinDao;
    } else {
      synchronized(this) {
        if(_coinDao == null) {
          _coinDao = new CoinDao_Impl(this);
        }
        return _coinDao;
      }
    }
  }

  @Override
  public WatchlistDao watchlistDao() {
    if (_watchlistDao != null) {
      return _watchlistDao;
    } else {
      synchronized(this) {
        if(_watchlistDao == null) {
          _watchlistDao = new WatchlistDao_Impl(this);
        }
        return _watchlistDao;
      }
    }
  }

  @Override
  public HoldingDao holdingDao() {
    if (_holdingDao != null) {
      return _holdingDao;
    } else {
      synchronized(this) {
        if(_holdingDao == null) {
          _holdingDao = new HoldingDao_Impl(this);
        }
        return _holdingDao;
      }
    }
  }

  @Override
  public AlertDao alertDao() {
    if (_alertDao != null) {
      return _alertDao;
    } else {
      synchronized(this) {
        if(_alertDao == null) {
          _alertDao = new AlertDao_Impl(this);
        }
        return _alertDao;
      }
    }
  }
}
