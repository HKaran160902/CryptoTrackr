package com.hari.pdd.cryptotrackr.data.local.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.hari.pdd.cryptotrackr.data.local.entity.CoinEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CoinDao_Impl implements CoinDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CoinEntity> __insertionAdapterOfCoinEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCoins;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldCoins;

  public CoinDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCoinEntity = new EntityInsertionAdapter<CoinEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `coins` (`id`,`symbol`,`name`,`image`,`currentPrice`,`marketCap`,`marketCapRank`,`totalVolume`,`high24h`,`low24h`,`priceChange24h`,`priceChangePercentage24h`,`priceChangePercentage1h`,`priceChangePercentage7d`,`circulatingSupply`,`totalSupply`,`maxSupply`,`ath`,`athChangePercentage`,`athDate`,`atl`,`atlChangePercentage`,`atlDate`,`lastUpdated`,`sparklineData`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CoinEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getSymbol());
        statement.bindString(3, entity.getName());
        statement.bindString(4, entity.getImage());
        if (entity.getCurrentPrice() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getCurrentPrice());
        }
        if (entity.getMarketCap() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getMarketCap());
        }
        if (entity.getMarketCapRank() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getMarketCapRank());
        }
        if (entity.getTotalVolume() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getTotalVolume());
        }
        if (entity.getHigh24h() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getHigh24h());
        }
        if (entity.getLow24h() == null) {
          statement.bindNull(10);
        } else {
          statement.bindDouble(10, entity.getLow24h());
        }
        if (entity.getPriceChange24h() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getPriceChange24h());
        }
        if (entity.getPriceChangePercentage24h() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getPriceChangePercentage24h());
        }
        if (entity.getPriceChangePercentage1h() == null) {
          statement.bindNull(13);
        } else {
          statement.bindDouble(13, entity.getPriceChangePercentage1h());
        }
        if (entity.getPriceChangePercentage7d() == null) {
          statement.bindNull(14);
        } else {
          statement.bindDouble(14, entity.getPriceChangePercentage7d());
        }
        if (entity.getCirculatingSupply() == null) {
          statement.bindNull(15);
        } else {
          statement.bindDouble(15, entity.getCirculatingSupply());
        }
        if (entity.getTotalSupply() == null) {
          statement.bindNull(16);
        } else {
          statement.bindDouble(16, entity.getTotalSupply());
        }
        if (entity.getMaxSupply() == null) {
          statement.bindNull(17);
        } else {
          statement.bindDouble(17, entity.getMaxSupply());
        }
        if (entity.getAth() == null) {
          statement.bindNull(18);
        } else {
          statement.bindDouble(18, entity.getAth());
        }
        if (entity.getAthChangePercentage() == null) {
          statement.bindNull(19);
        } else {
          statement.bindDouble(19, entity.getAthChangePercentage());
        }
        if (entity.getAthDate() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getAthDate());
        }
        if (entity.getAtl() == null) {
          statement.bindNull(21);
        } else {
          statement.bindDouble(21, entity.getAtl());
        }
        if (entity.getAtlChangePercentage() == null) {
          statement.bindNull(22);
        } else {
          statement.bindDouble(22, entity.getAtlChangePercentage());
        }
        if (entity.getAtlDate() == null) {
          statement.bindNull(23);
        } else {
          statement.bindString(23, entity.getAtlDate());
        }
        if (entity.getLastUpdated() == null) {
          statement.bindNull(24);
        } else {
          statement.bindString(24, entity.getLastUpdated());
        }
        if (entity.getSparklineData() == null) {
          statement.bindNull(25);
        } else {
          statement.bindString(25, entity.getSparklineData());
        }
        statement.bindLong(26, entity.getCachedAt());
      }
    };
    this.__preparedStmtOfDeleteAllCoins = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM coins";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldCoins = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM coins WHERE cachedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertCoins(final List<CoinEntity> coins,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCoinEntity.insert(coins);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCoin(final CoinEntity coin, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCoinEntity.insert(coin);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllCoins(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCoins.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllCoins.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldCoins(final long timestamp, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldCoins.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOldCoins.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CoinEntity>> getAllCoins() {
    final String _sql = "SELECT * FROM coins ORDER BY marketCapRank ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"coins"}, new Callable<List<CoinEntity>>() {
      @Override
      @NonNull
      public List<CoinEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final int _cursorIndexOfCurrentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentPrice");
          final int _cursorIndexOfMarketCap = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCap");
          final int _cursorIndexOfMarketCapRank = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCapRank");
          final int _cursorIndexOfTotalVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "totalVolume");
          final int _cursorIndexOfHigh24h = CursorUtil.getColumnIndexOrThrow(_cursor, "high24h");
          final int _cursorIndexOfLow24h = CursorUtil.getColumnIndexOrThrow(_cursor, "low24h");
          final int _cursorIndexOfPriceChange24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChange24h");
          final int _cursorIndexOfPriceChangePercentage24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage24h");
          final int _cursorIndexOfPriceChangePercentage1h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage1h");
          final int _cursorIndexOfPriceChangePercentage7d = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage7d");
          final int _cursorIndexOfCirculatingSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "circulatingSupply");
          final int _cursorIndexOfTotalSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSupply");
          final int _cursorIndexOfMaxSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "maxSupply");
          final int _cursorIndexOfAth = CursorUtil.getColumnIndexOrThrow(_cursor, "ath");
          final int _cursorIndexOfAthChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "athChangePercentage");
          final int _cursorIndexOfAthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "athDate");
          final int _cursorIndexOfAtl = CursorUtil.getColumnIndexOrThrow(_cursor, "atl");
          final int _cursorIndexOfAtlChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "atlChangePercentage");
          final int _cursorIndexOfAtlDate = CursorUtil.getColumnIndexOrThrow(_cursor, "atlDate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfSparklineData = CursorUtil.getColumnIndexOrThrow(_cursor, "sparklineData");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<CoinEntity> _result = new ArrayList<CoinEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CoinEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpImage;
            _tmpImage = _cursor.getString(_cursorIndexOfImage);
            final Double _tmpCurrentPrice;
            if (_cursor.isNull(_cursorIndexOfCurrentPrice)) {
              _tmpCurrentPrice = null;
            } else {
              _tmpCurrentPrice = _cursor.getDouble(_cursorIndexOfCurrentPrice);
            }
            final Long _tmpMarketCap;
            if (_cursor.isNull(_cursorIndexOfMarketCap)) {
              _tmpMarketCap = null;
            } else {
              _tmpMarketCap = _cursor.getLong(_cursorIndexOfMarketCap);
            }
            final Integer _tmpMarketCapRank;
            if (_cursor.isNull(_cursorIndexOfMarketCapRank)) {
              _tmpMarketCapRank = null;
            } else {
              _tmpMarketCapRank = _cursor.getInt(_cursorIndexOfMarketCapRank);
            }
            final Long _tmpTotalVolume;
            if (_cursor.isNull(_cursorIndexOfTotalVolume)) {
              _tmpTotalVolume = null;
            } else {
              _tmpTotalVolume = _cursor.getLong(_cursorIndexOfTotalVolume);
            }
            final Double _tmpHigh24h;
            if (_cursor.isNull(_cursorIndexOfHigh24h)) {
              _tmpHigh24h = null;
            } else {
              _tmpHigh24h = _cursor.getDouble(_cursorIndexOfHigh24h);
            }
            final Double _tmpLow24h;
            if (_cursor.isNull(_cursorIndexOfLow24h)) {
              _tmpLow24h = null;
            } else {
              _tmpLow24h = _cursor.getDouble(_cursorIndexOfLow24h);
            }
            final Double _tmpPriceChange24h;
            if (_cursor.isNull(_cursorIndexOfPriceChange24h)) {
              _tmpPriceChange24h = null;
            } else {
              _tmpPriceChange24h = _cursor.getDouble(_cursorIndexOfPriceChange24h);
            }
            final Double _tmpPriceChangePercentage24h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage24h)) {
              _tmpPriceChangePercentage24h = null;
            } else {
              _tmpPriceChangePercentage24h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage24h);
            }
            final Double _tmpPriceChangePercentage1h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage1h)) {
              _tmpPriceChangePercentage1h = null;
            } else {
              _tmpPriceChangePercentage1h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage1h);
            }
            final Double _tmpPriceChangePercentage7d;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage7d)) {
              _tmpPriceChangePercentage7d = null;
            } else {
              _tmpPriceChangePercentage7d = _cursor.getDouble(_cursorIndexOfPriceChangePercentage7d);
            }
            final Double _tmpCirculatingSupply;
            if (_cursor.isNull(_cursorIndexOfCirculatingSupply)) {
              _tmpCirculatingSupply = null;
            } else {
              _tmpCirculatingSupply = _cursor.getDouble(_cursorIndexOfCirculatingSupply);
            }
            final Double _tmpTotalSupply;
            if (_cursor.isNull(_cursorIndexOfTotalSupply)) {
              _tmpTotalSupply = null;
            } else {
              _tmpTotalSupply = _cursor.getDouble(_cursorIndexOfTotalSupply);
            }
            final Double _tmpMaxSupply;
            if (_cursor.isNull(_cursorIndexOfMaxSupply)) {
              _tmpMaxSupply = null;
            } else {
              _tmpMaxSupply = _cursor.getDouble(_cursorIndexOfMaxSupply);
            }
            final Double _tmpAth;
            if (_cursor.isNull(_cursorIndexOfAth)) {
              _tmpAth = null;
            } else {
              _tmpAth = _cursor.getDouble(_cursorIndexOfAth);
            }
            final Double _tmpAthChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAthChangePercentage)) {
              _tmpAthChangePercentage = null;
            } else {
              _tmpAthChangePercentage = _cursor.getDouble(_cursorIndexOfAthChangePercentage);
            }
            final String _tmpAthDate;
            if (_cursor.isNull(_cursorIndexOfAthDate)) {
              _tmpAthDate = null;
            } else {
              _tmpAthDate = _cursor.getString(_cursorIndexOfAthDate);
            }
            final Double _tmpAtl;
            if (_cursor.isNull(_cursorIndexOfAtl)) {
              _tmpAtl = null;
            } else {
              _tmpAtl = _cursor.getDouble(_cursorIndexOfAtl);
            }
            final Double _tmpAtlChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAtlChangePercentage)) {
              _tmpAtlChangePercentage = null;
            } else {
              _tmpAtlChangePercentage = _cursor.getDouble(_cursorIndexOfAtlChangePercentage);
            }
            final String _tmpAtlDate;
            if (_cursor.isNull(_cursorIndexOfAtlDate)) {
              _tmpAtlDate = null;
            } else {
              _tmpAtlDate = _cursor.getString(_cursorIndexOfAtlDate);
            }
            final String _tmpLastUpdated;
            if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
              _tmpLastUpdated = null;
            } else {
              _tmpLastUpdated = _cursor.getString(_cursorIndexOfLastUpdated);
            }
            final String _tmpSparklineData;
            if (_cursor.isNull(_cursorIndexOfSparklineData)) {
              _tmpSparklineData = null;
            } else {
              _tmpSparklineData = _cursor.getString(_cursorIndexOfSparklineData);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new CoinEntity(_tmpId,_tmpSymbol,_tmpName,_tmpImage,_tmpCurrentPrice,_tmpMarketCap,_tmpMarketCapRank,_tmpTotalVolume,_tmpHigh24h,_tmpLow24h,_tmpPriceChange24h,_tmpPriceChangePercentage24h,_tmpPriceChangePercentage1h,_tmpPriceChangePercentage7d,_tmpCirculatingSupply,_tmpTotalSupply,_tmpMaxSupply,_tmpAth,_tmpAthChangePercentage,_tmpAthDate,_tmpAtl,_tmpAtlChangePercentage,_tmpAtlDate,_tmpLastUpdated,_tmpSparklineData,_tmpCachedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCoinById(final String coinId,
      final Continuation<? super CoinEntity> $completion) {
    final String _sql = "SELECT * FROM coins WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, coinId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CoinEntity>() {
      @Override
      @Nullable
      public CoinEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final int _cursorIndexOfCurrentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentPrice");
          final int _cursorIndexOfMarketCap = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCap");
          final int _cursorIndexOfMarketCapRank = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCapRank");
          final int _cursorIndexOfTotalVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "totalVolume");
          final int _cursorIndexOfHigh24h = CursorUtil.getColumnIndexOrThrow(_cursor, "high24h");
          final int _cursorIndexOfLow24h = CursorUtil.getColumnIndexOrThrow(_cursor, "low24h");
          final int _cursorIndexOfPriceChange24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChange24h");
          final int _cursorIndexOfPriceChangePercentage24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage24h");
          final int _cursorIndexOfPriceChangePercentage1h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage1h");
          final int _cursorIndexOfPriceChangePercentage7d = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage7d");
          final int _cursorIndexOfCirculatingSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "circulatingSupply");
          final int _cursorIndexOfTotalSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSupply");
          final int _cursorIndexOfMaxSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "maxSupply");
          final int _cursorIndexOfAth = CursorUtil.getColumnIndexOrThrow(_cursor, "ath");
          final int _cursorIndexOfAthChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "athChangePercentage");
          final int _cursorIndexOfAthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "athDate");
          final int _cursorIndexOfAtl = CursorUtil.getColumnIndexOrThrow(_cursor, "atl");
          final int _cursorIndexOfAtlChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "atlChangePercentage");
          final int _cursorIndexOfAtlDate = CursorUtil.getColumnIndexOrThrow(_cursor, "atlDate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfSparklineData = CursorUtil.getColumnIndexOrThrow(_cursor, "sparklineData");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final CoinEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpImage;
            _tmpImage = _cursor.getString(_cursorIndexOfImage);
            final Double _tmpCurrentPrice;
            if (_cursor.isNull(_cursorIndexOfCurrentPrice)) {
              _tmpCurrentPrice = null;
            } else {
              _tmpCurrentPrice = _cursor.getDouble(_cursorIndexOfCurrentPrice);
            }
            final Long _tmpMarketCap;
            if (_cursor.isNull(_cursorIndexOfMarketCap)) {
              _tmpMarketCap = null;
            } else {
              _tmpMarketCap = _cursor.getLong(_cursorIndexOfMarketCap);
            }
            final Integer _tmpMarketCapRank;
            if (_cursor.isNull(_cursorIndexOfMarketCapRank)) {
              _tmpMarketCapRank = null;
            } else {
              _tmpMarketCapRank = _cursor.getInt(_cursorIndexOfMarketCapRank);
            }
            final Long _tmpTotalVolume;
            if (_cursor.isNull(_cursorIndexOfTotalVolume)) {
              _tmpTotalVolume = null;
            } else {
              _tmpTotalVolume = _cursor.getLong(_cursorIndexOfTotalVolume);
            }
            final Double _tmpHigh24h;
            if (_cursor.isNull(_cursorIndexOfHigh24h)) {
              _tmpHigh24h = null;
            } else {
              _tmpHigh24h = _cursor.getDouble(_cursorIndexOfHigh24h);
            }
            final Double _tmpLow24h;
            if (_cursor.isNull(_cursorIndexOfLow24h)) {
              _tmpLow24h = null;
            } else {
              _tmpLow24h = _cursor.getDouble(_cursorIndexOfLow24h);
            }
            final Double _tmpPriceChange24h;
            if (_cursor.isNull(_cursorIndexOfPriceChange24h)) {
              _tmpPriceChange24h = null;
            } else {
              _tmpPriceChange24h = _cursor.getDouble(_cursorIndexOfPriceChange24h);
            }
            final Double _tmpPriceChangePercentage24h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage24h)) {
              _tmpPriceChangePercentage24h = null;
            } else {
              _tmpPriceChangePercentage24h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage24h);
            }
            final Double _tmpPriceChangePercentage1h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage1h)) {
              _tmpPriceChangePercentage1h = null;
            } else {
              _tmpPriceChangePercentage1h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage1h);
            }
            final Double _tmpPriceChangePercentage7d;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage7d)) {
              _tmpPriceChangePercentage7d = null;
            } else {
              _tmpPriceChangePercentage7d = _cursor.getDouble(_cursorIndexOfPriceChangePercentage7d);
            }
            final Double _tmpCirculatingSupply;
            if (_cursor.isNull(_cursorIndexOfCirculatingSupply)) {
              _tmpCirculatingSupply = null;
            } else {
              _tmpCirculatingSupply = _cursor.getDouble(_cursorIndexOfCirculatingSupply);
            }
            final Double _tmpTotalSupply;
            if (_cursor.isNull(_cursorIndexOfTotalSupply)) {
              _tmpTotalSupply = null;
            } else {
              _tmpTotalSupply = _cursor.getDouble(_cursorIndexOfTotalSupply);
            }
            final Double _tmpMaxSupply;
            if (_cursor.isNull(_cursorIndexOfMaxSupply)) {
              _tmpMaxSupply = null;
            } else {
              _tmpMaxSupply = _cursor.getDouble(_cursorIndexOfMaxSupply);
            }
            final Double _tmpAth;
            if (_cursor.isNull(_cursorIndexOfAth)) {
              _tmpAth = null;
            } else {
              _tmpAth = _cursor.getDouble(_cursorIndexOfAth);
            }
            final Double _tmpAthChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAthChangePercentage)) {
              _tmpAthChangePercentage = null;
            } else {
              _tmpAthChangePercentage = _cursor.getDouble(_cursorIndexOfAthChangePercentage);
            }
            final String _tmpAthDate;
            if (_cursor.isNull(_cursorIndexOfAthDate)) {
              _tmpAthDate = null;
            } else {
              _tmpAthDate = _cursor.getString(_cursorIndexOfAthDate);
            }
            final Double _tmpAtl;
            if (_cursor.isNull(_cursorIndexOfAtl)) {
              _tmpAtl = null;
            } else {
              _tmpAtl = _cursor.getDouble(_cursorIndexOfAtl);
            }
            final Double _tmpAtlChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAtlChangePercentage)) {
              _tmpAtlChangePercentage = null;
            } else {
              _tmpAtlChangePercentage = _cursor.getDouble(_cursorIndexOfAtlChangePercentage);
            }
            final String _tmpAtlDate;
            if (_cursor.isNull(_cursorIndexOfAtlDate)) {
              _tmpAtlDate = null;
            } else {
              _tmpAtlDate = _cursor.getString(_cursorIndexOfAtlDate);
            }
            final String _tmpLastUpdated;
            if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
              _tmpLastUpdated = null;
            } else {
              _tmpLastUpdated = _cursor.getString(_cursorIndexOfLastUpdated);
            }
            final String _tmpSparklineData;
            if (_cursor.isNull(_cursorIndexOfSparklineData)) {
              _tmpSparklineData = null;
            } else {
              _tmpSparklineData = _cursor.getString(_cursorIndexOfSparklineData);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _result = new CoinEntity(_tmpId,_tmpSymbol,_tmpName,_tmpImage,_tmpCurrentPrice,_tmpMarketCap,_tmpMarketCapRank,_tmpTotalVolume,_tmpHigh24h,_tmpLow24h,_tmpPriceChange24h,_tmpPriceChangePercentage24h,_tmpPriceChangePercentage1h,_tmpPriceChangePercentage7d,_tmpCirculatingSupply,_tmpTotalSupply,_tmpMaxSupply,_tmpAth,_tmpAthChangePercentage,_tmpAthDate,_tmpAtl,_tmpAtlChangePercentage,_tmpAtlDate,_tmpLastUpdated,_tmpSparklineData,_tmpCachedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<CoinEntity> getCoinByIdFlow(final String coinId) {
    final String _sql = "SELECT * FROM coins WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, coinId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"coins"}, new Callable<CoinEntity>() {
      @Override
      @Nullable
      public CoinEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final int _cursorIndexOfCurrentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentPrice");
          final int _cursorIndexOfMarketCap = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCap");
          final int _cursorIndexOfMarketCapRank = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCapRank");
          final int _cursorIndexOfTotalVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "totalVolume");
          final int _cursorIndexOfHigh24h = CursorUtil.getColumnIndexOrThrow(_cursor, "high24h");
          final int _cursorIndexOfLow24h = CursorUtil.getColumnIndexOrThrow(_cursor, "low24h");
          final int _cursorIndexOfPriceChange24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChange24h");
          final int _cursorIndexOfPriceChangePercentage24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage24h");
          final int _cursorIndexOfPriceChangePercentage1h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage1h");
          final int _cursorIndexOfPriceChangePercentage7d = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage7d");
          final int _cursorIndexOfCirculatingSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "circulatingSupply");
          final int _cursorIndexOfTotalSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSupply");
          final int _cursorIndexOfMaxSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "maxSupply");
          final int _cursorIndexOfAth = CursorUtil.getColumnIndexOrThrow(_cursor, "ath");
          final int _cursorIndexOfAthChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "athChangePercentage");
          final int _cursorIndexOfAthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "athDate");
          final int _cursorIndexOfAtl = CursorUtil.getColumnIndexOrThrow(_cursor, "atl");
          final int _cursorIndexOfAtlChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "atlChangePercentage");
          final int _cursorIndexOfAtlDate = CursorUtil.getColumnIndexOrThrow(_cursor, "atlDate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfSparklineData = CursorUtil.getColumnIndexOrThrow(_cursor, "sparklineData");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final CoinEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpImage;
            _tmpImage = _cursor.getString(_cursorIndexOfImage);
            final Double _tmpCurrentPrice;
            if (_cursor.isNull(_cursorIndexOfCurrentPrice)) {
              _tmpCurrentPrice = null;
            } else {
              _tmpCurrentPrice = _cursor.getDouble(_cursorIndexOfCurrentPrice);
            }
            final Long _tmpMarketCap;
            if (_cursor.isNull(_cursorIndexOfMarketCap)) {
              _tmpMarketCap = null;
            } else {
              _tmpMarketCap = _cursor.getLong(_cursorIndexOfMarketCap);
            }
            final Integer _tmpMarketCapRank;
            if (_cursor.isNull(_cursorIndexOfMarketCapRank)) {
              _tmpMarketCapRank = null;
            } else {
              _tmpMarketCapRank = _cursor.getInt(_cursorIndexOfMarketCapRank);
            }
            final Long _tmpTotalVolume;
            if (_cursor.isNull(_cursorIndexOfTotalVolume)) {
              _tmpTotalVolume = null;
            } else {
              _tmpTotalVolume = _cursor.getLong(_cursorIndexOfTotalVolume);
            }
            final Double _tmpHigh24h;
            if (_cursor.isNull(_cursorIndexOfHigh24h)) {
              _tmpHigh24h = null;
            } else {
              _tmpHigh24h = _cursor.getDouble(_cursorIndexOfHigh24h);
            }
            final Double _tmpLow24h;
            if (_cursor.isNull(_cursorIndexOfLow24h)) {
              _tmpLow24h = null;
            } else {
              _tmpLow24h = _cursor.getDouble(_cursorIndexOfLow24h);
            }
            final Double _tmpPriceChange24h;
            if (_cursor.isNull(_cursorIndexOfPriceChange24h)) {
              _tmpPriceChange24h = null;
            } else {
              _tmpPriceChange24h = _cursor.getDouble(_cursorIndexOfPriceChange24h);
            }
            final Double _tmpPriceChangePercentage24h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage24h)) {
              _tmpPriceChangePercentage24h = null;
            } else {
              _tmpPriceChangePercentage24h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage24h);
            }
            final Double _tmpPriceChangePercentage1h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage1h)) {
              _tmpPriceChangePercentage1h = null;
            } else {
              _tmpPriceChangePercentage1h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage1h);
            }
            final Double _tmpPriceChangePercentage7d;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage7d)) {
              _tmpPriceChangePercentage7d = null;
            } else {
              _tmpPriceChangePercentage7d = _cursor.getDouble(_cursorIndexOfPriceChangePercentage7d);
            }
            final Double _tmpCirculatingSupply;
            if (_cursor.isNull(_cursorIndexOfCirculatingSupply)) {
              _tmpCirculatingSupply = null;
            } else {
              _tmpCirculatingSupply = _cursor.getDouble(_cursorIndexOfCirculatingSupply);
            }
            final Double _tmpTotalSupply;
            if (_cursor.isNull(_cursorIndexOfTotalSupply)) {
              _tmpTotalSupply = null;
            } else {
              _tmpTotalSupply = _cursor.getDouble(_cursorIndexOfTotalSupply);
            }
            final Double _tmpMaxSupply;
            if (_cursor.isNull(_cursorIndexOfMaxSupply)) {
              _tmpMaxSupply = null;
            } else {
              _tmpMaxSupply = _cursor.getDouble(_cursorIndexOfMaxSupply);
            }
            final Double _tmpAth;
            if (_cursor.isNull(_cursorIndexOfAth)) {
              _tmpAth = null;
            } else {
              _tmpAth = _cursor.getDouble(_cursorIndexOfAth);
            }
            final Double _tmpAthChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAthChangePercentage)) {
              _tmpAthChangePercentage = null;
            } else {
              _tmpAthChangePercentage = _cursor.getDouble(_cursorIndexOfAthChangePercentage);
            }
            final String _tmpAthDate;
            if (_cursor.isNull(_cursorIndexOfAthDate)) {
              _tmpAthDate = null;
            } else {
              _tmpAthDate = _cursor.getString(_cursorIndexOfAthDate);
            }
            final Double _tmpAtl;
            if (_cursor.isNull(_cursorIndexOfAtl)) {
              _tmpAtl = null;
            } else {
              _tmpAtl = _cursor.getDouble(_cursorIndexOfAtl);
            }
            final Double _tmpAtlChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAtlChangePercentage)) {
              _tmpAtlChangePercentage = null;
            } else {
              _tmpAtlChangePercentage = _cursor.getDouble(_cursorIndexOfAtlChangePercentage);
            }
            final String _tmpAtlDate;
            if (_cursor.isNull(_cursorIndexOfAtlDate)) {
              _tmpAtlDate = null;
            } else {
              _tmpAtlDate = _cursor.getString(_cursorIndexOfAtlDate);
            }
            final String _tmpLastUpdated;
            if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
              _tmpLastUpdated = null;
            } else {
              _tmpLastUpdated = _cursor.getString(_cursorIndexOfLastUpdated);
            }
            final String _tmpSparklineData;
            if (_cursor.isNull(_cursorIndexOfSparklineData)) {
              _tmpSparklineData = null;
            } else {
              _tmpSparklineData = _cursor.getString(_cursorIndexOfSparklineData);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _result = new CoinEntity(_tmpId,_tmpSymbol,_tmpName,_tmpImage,_tmpCurrentPrice,_tmpMarketCap,_tmpMarketCapRank,_tmpTotalVolume,_tmpHigh24h,_tmpLow24h,_tmpPriceChange24h,_tmpPriceChangePercentage24h,_tmpPriceChangePercentage1h,_tmpPriceChangePercentage7d,_tmpCirculatingSupply,_tmpTotalSupply,_tmpMaxSupply,_tmpAth,_tmpAthChangePercentage,_tmpAthDate,_tmpAtl,_tmpAtlChangePercentage,_tmpAtlDate,_tmpLastUpdated,_tmpSparklineData,_tmpCachedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<CoinEntity>> getCoinsByIds(final List<String> coinIds) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM coins WHERE id IN (");
    final int _inputSize = coinIds.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : coinIds) {
      _statement.bindString(_argIndex, _item);
      _argIndex++;
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"coins"}, new Callable<List<CoinEntity>>() {
      @Override
      @NonNull
      public List<CoinEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final int _cursorIndexOfCurrentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentPrice");
          final int _cursorIndexOfMarketCap = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCap");
          final int _cursorIndexOfMarketCapRank = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCapRank");
          final int _cursorIndexOfTotalVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "totalVolume");
          final int _cursorIndexOfHigh24h = CursorUtil.getColumnIndexOrThrow(_cursor, "high24h");
          final int _cursorIndexOfLow24h = CursorUtil.getColumnIndexOrThrow(_cursor, "low24h");
          final int _cursorIndexOfPriceChange24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChange24h");
          final int _cursorIndexOfPriceChangePercentage24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage24h");
          final int _cursorIndexOfPriceChangePercentage1h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage1h");
          final int _cursorIndexOfPriceChangePercentage7d = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage7d");
          final int _cursorIndexOfCirculatingSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "circulatingSupply");
          final int _cursorIndexOfTotalSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSupply");
          final int _cursorIndexOfMaxSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "maxSupply");
          final int _cursorIndexOfAth = CursorUtil.getColumnIndexOrThrow(_cursor, "ath");
          final int _cursorIndexOfAthChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "athChangePercentage");
          final int _cursorIndexOfAthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "athDate");
          final int _cursorIndexOfAtl = CursorUtil.getColumnIndexOrThrow(_cursor, "atl");
          final int _cursorIndexOfAtlChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "atlChangePercentage");
          final int _cursorIndexOfAtlDate = CursorUtil.getColumnIndexOrThrow(_cursor, "atlDate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfSparklineData = CursorUtil.getColumnIndexOrThrow(_cursor, "sparklineData");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<CoinEntity> _result = new ArrayList<CoinEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CoinEntity _item_1;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpImage;
            _tmpImage = _cursor.getString(_cursorIndexOfImage);
            final Double _tmpCurrentPrice;
            if (_cursor.isNull(_cursorIndexOfCurrentPrice)) {
              _tmpCurrentPrice = null;
            } else {
              _tmpCurrentPrice = _cursor.getDouble(_cursorIndexOfCurrentPrice);
            }
            final Long _tmpMarketCap;
            if (_cursor.isNull(_cursorIndexOfMarketCap)) {
              _tmpMarketCap = null;
            } else {
              _tmpMarketCap = _cursor.getLong(_cursorIndexOfMarketCap);
            }
            final Integer _tmpMarketCapRank;
            if (_cursor.isNull(_cursorIndexOfMarketCapRank)) {
              _tmpMarketCapRank = null;
            } else {
              _tmpMarketCapRank = _cursor.getInt(_cursorIndexOfMarketCapRank);
            }
            final Long _tmpTotalVolume;
            if (_cursor.isNull(_cursorIndexOfTotalVolume)) {
              _tmpTotalVolume = null;
            } else {
              _tmpTotalVolume = _cursor.getLong(_cursorIndexOfTotalVolume);
            }
            final Double _tmpHigh24h;
            if (_cursor.isNull(_cursorIndexOfHigh24h)) {
              _tmpHigh24h = null;
            } else {
              _tmpHigh24h = _cursor.getDouble(_cursorIndexOfHigh24h);
            }
            final Double _tmpLow24h;
            if (_cursor.isNull(_cursorIndexOfLow24h)) {
              _tmpLow24h = null;
            } else {
              _tmpLow24h = _cursor.getDouble(_cursorIndexOfLow24h);
            }
            final Double _tmpPriceChange24h;
            if (_cursor.isNull(_cursorIndexOfPriceChange24h)) {
              _tmpPriceChange24h = null;
            } else {
              _tmpPriceChange24h = _cursor.getDouble(_cursorIndexOfPriceChange24h);
            }
            final Double _tmpPriceChangePercentage24h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage24h)) {
              _tmpPriceChangePercentage24h = null;
            } else {
              _tmpPriceChangePercentage24h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage24h);
            }
            final Double _tmpPriceChangePercentage1h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage1h)) {
              _tmpPriceChangePercentage1h = null;
            } else {
              _tmpPriceChangePercentage1h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage1h);
            }
            final Double _tmpPriceChangePercentage7d;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage7d)) {
              _tmpPriceChangePercentage7d = null;
            } else {
              _tmpPriceChangePercentage7d = _cursor.getDouble(_cursorIndexOfPriceChangePercentage7d);
            }
            final Double _tmpCirculatingSupply;
            if (_cursor.isNull(_cursorIndexOfCirculatingSupply)) {
              _tmpCirculatingSupply = null;
            } else {
              _tmpCirculatingSupply = _cursor.getDouble(_cursorIndexOfCirculatingSupply);
            }
            final Double _tmpTotalSupply;
            if (_cursor.isNull(_cursorIndexOfTotalSupply)) {
              _tmpTotalSupply = null;
            } else {
              _tmpTotalSupply = _cursor.getDouble(_cursorIndexOfTotalSupply);
            }
            final Double _tmpMaxSupply;
            if (_cursor.isNull(_cursorIndexOfMaxSupply)) {
              _tmpMaxSupply = null;
            } else {
              _tmpMaxSupply = _cursor.getDouble(_cursorIndexOfMaxSupply);
            }
            final Double _tmpAth;
            if (_cursor.isNull(_cursorIndexOfAth)) {
              _tmpAth = null;
            } else {
              _tmpAth = _cursor.getDouble(_cursorIndexOfAth);
            }
            final Double _tmpAthChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAthChangePercentage)) {
              _tmpAthChangePercentage = null;
            } else {
              _tmpAthChangePercentage = _cursor.getDouble(_cursorIndexOfAthChangePercentage);
            }
            final String _tmpAthDate;
            if (_cursor.isNull(_cursorIndexOfAthDate)) {
              _tmpAthDate = null;
            } else {
              _tmpAthDate = _cursor.getString(_cursorIndexOfAthDate);
            }
            final Double _tmpAtl;
            if (_cursor.isNull(_cursorIndexOfAtl)) {
              _tmpAtl = null;
            } else {
              _tmpAtl = _cursor.getDouble(_cursorIndexOfAtl);
            }
            final Double _tmpAtlChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAtlChangePercentage)) {
              _tmpAtlChangePercentage = null;
            } else {
              _tmpAtlChangePercentage = _cursor.getDouble(_cursorIndexOfAtlChangePercentage);
            }
            final String _tmpAtlDate;
            if (_cursor.isNull(_cursorIndexOfAtlDate)) {
              _tmpAtlDate = null;
            } else {
              _tmpAtlDate = _cursor.getString(_cursorIndexOfAtlDate);
            }
            final String _tmpLastUpdated;
            if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
              _tmpLastUpdated = null;
            } else {
              _tmpLastUpdated = _cursor.getString(_cursorIndexOfLastUpdated);
            }
            final String _tmpSparklineData;
            if (_cursor.isNull(_cursorIndexOfSparklineData)) {
              _tmpSparklineData = null;
            } else {
              _tmpSparklineData = _cursor.getString(_cursorIndexOfSparklineData);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item_1 = new CoinEntity(_tmpId,_tmpSymbol,_tmpName,_tmpImage,_tmpCurrentPrice,_tmpMarketCap,_tmpMarketCapRank,_tmpTotalVolume,_tmpHigh24h,_tmpLow24h,_tmpPriceChange24h,_tmpPriceChangePercentage24h,_tmpPriceChangePercentage1h,_tmpPriceChangePercentage7d,_tmpCirculatingSupply,_tmpTotalSupply,_tmpMaxSupply,_tmpAth,_tmpAthChangePercentage,_tmpAthDate,_tmpAtl,_tmpAtlChangePercentage,_tmpAtlDate,_tmpLastUpdated,_tmpSparklineData,_tmpCachedAt);
            _result.add(_item_1);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<CoinEntity>> searchCoins(final String query) {
    final String _sql = "SELECT * FROM coins WHERE name LIKE '%' || ? || '%' OR symbol LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"coins"}, new Callable<List<CoinEntity>>() {
      @Override
      @NonNull
      public List<CoinEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final int _cursorIndexOfCurrentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentPrice");
          final int _cursorIndexOfMarketCap = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCap");
          final int _cursorIndexOfMarketCapRank = CursorUtil.getColumnIndexOrThrow(_cursor, "marketCapRank");
          final int _cursorIndexOfTotalVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "totalVolume");
          final int _cursorIndexOfHigh24h = CursorUtil.getColumnIndexOrThrow(_cursor, "high24h");
          final int _cursorIndexOfLow24h = CursorUtil.getColumnIndexOrThrow(_cursor, "low24h");
          final int _cursorIndexOfPriceChange24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChange24h");
          final int _cursorIndexOfPriceChangePercentage24h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage24h");
          final int _cursorIndexOfPriceChangePercentage1h = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage1h");
          final int _cursorIndexOfPriceChangePercentage7d = CursorUtil.getColumnIndexOrThrow(_cursor, "priceChangePercentage7d");
          final int _cursorIndexOfCirculatingSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "circulatingSupply");
          final int _cursorIndexOfTotalSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSupply");
          final int _cursorIndexOfMaxSupply = CursorUtil.getColumnIndexOrThrow(_cursor, "maxSupply");
          final int _cursorIndexOfAth = CursorUtil.getColumnIndexOrThrow(_cursor, "ath");
          final int _cursorIndexOfAthChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "athChangePercentage");
          final int _cursorIndexOfAthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "athDate");
          final int _cursorIndexOfAtl = CursorUtil.getColumnIndexOrThrow(_cursor, "atl");
          final int _cursorIndexOfAtlChangePercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "atlChangePercentage");
          final int _cursorIndexOfAtlDate = CursorUtil.getColumnIndexOrThrow(_cursor, "atlDate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfSparklineData = CursorUtil.getColumnIndexOrThrow(_cursor, "sparklineData");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<CoinEntity> _result = new ArrayList<CoinEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CoinEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpSymbol;
            _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpImage;
            _tmpImage = _cursor.getString(_cursorIndexOfImage);
            final Double _tmpCurrentPrice;
            if (_cursor.isNull(_cursorIndexOfCurrentPrice)) {
              _tmpCurrentPrice = null;
            } else {
              _tmpCurrentPrice = _cursor.getDouble(_cursorIndexOfCurrentPrice);
            }
            final Long _tmpMarketCap;
            if (_cursor.isNull(_cursorIndexOfMarketCap)) {
              _tmpMarketCap = null;
            } else {
              _tmpMarketCap = _cursor.getLong(_cursorIndexOfMarketCap);
            }
            final Integer _tmpMarketCapRank;
            if (_cursor.isNull(_cursorIndexOfMarketCapRank)) {
              _tmpMarketCapRank = null;
            } else {
              _tmpMarketCapRank = _cursor.getInt(_cursorIndexOfMarketCapRank);
            }
            final Long _tmpTotalVolume;
            if (_cursor.isNull(_cursorIndexOfTotalVolume)) {
              _tmpTotalVolume = null;
            } else {
              _tmpTotalVolume = _cursor.getLong(_cursorIndexOfTotalVolume);
            }
            final Double _tmpHigh24h;
            if (_cursor.isNull(_cursorIndexOfHigh24h)) {
              _tmpHigh24h = null;
            } else {
              _tmpHigh24h = _cursor.getDouble(_cursorIndexOfHigh24h);
            }
            final Double _tmpLow24h;
            if (_cursor.isNull(_cursorIndexOfLow24h)) {
              _tmpLow24h = null;
            } else {
              _tmpLow24h = _cursor.getDouble(_cursorIndexOfLow24h);
            }
            final Double _tmpPriceChange24h;
            if (_cursor.isNull(_cursorIndexOfPriceChange24h)) {
              _tmpPriceChange24h = null;
            } else {
              _tmpPriceChange24h = _cursor.getDouble(_cursorIndexOfPriceChange24h);
            }
            final Double _tmpPriceChangePercentage24h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage24h)) {
              _tmpPriceChangePercentage24h = null;
            } else {
              _tmpPriceChangePercentage24h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage24h);
            }
            final Double _tmpPriceChangePercentage1h;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage1h)) {
              _tmpPriceChangePercentage1h = null;
            } else {
              _tmpPriceChangePercentage1h = _cursor.getDouble(_cursorIndexOfPriceChangePercentage1h);
            }
            final Double _tmpPriceChangePercentage7d;
            if (_cursor.isNull(_cursorIndexOfPriceChangePercentage7d)) {
              _tmpPriceChangePercentage7d = null;
            } else {
              _tmpPriceChangePercentage7d = _cursor.getDouble(_cursorIndexOfPriceChangePercentage7d);
            }
            final Double _tmpCirculatingSupply;
            if (_cursor.isNull(_cursorIndexOfCirculatingSupply)) {
              _tmpCirculatingSupply = null;
            } else {
              _tmpCirculatingSupply = _cursor.getDouble(_cursorIndexOfCirculatingSupply);
            }
            final Double _tmpTotalSupply;
            if (_cursor.isNull(_cursorIndexOfTotalSupply)) {
              _tmpTotalSupply = null;
            } else {
              _tmpTotalSupply = _cursor.getDouble(_cursorIndexOfTotalSupply);
            }
            final Double _tmpMaxSupply;
            if (_cursor.isNull(_cursorIndexOfMaxSupply)) {
              _tmpMaxSupply = null;
            } else {
              _tmpMaxSupply = _cursor.getDouble(_cursorIndexOfMaxSupply);
            }
            final Double _tmpAth;
            if (_cursor.isNull(_cursorIndexOfAth)) {
              _tmpAth = null;
            } else {
              _tmpAth = _cursor.getDouble(_cursorIndexOfAth);
            }
            final Double _tmpAthChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAthChangePercentage)) {
              _tmpAthChangePercentage = null;
            } else {
              _tmpAthChangePercentage = _cursor.getDouble(_cursorIndexOfAthChangePercentage);
            }
            final String _tmpAthDate;
            if (_cursor.isNull(_cursorIndexOfAthDate)) {
              _tmpAthDate = null;
            } else {
              _tmpAthDate = _cursor.getString(_cursorIndexOfAthDate);
            }
            final Double _tmpAtl;
            if (_cursor.isNull(_cursorIndexOfAtl)) {
              _tmpAtl = null;
            } else {
              _tmpAtl = _cursor.getDouble(_cursorIndexOfAtl);
            }
            final Double _tmpAtlChangePercentage;
            if (_cursor.isNull(_cursorIndexOfAtlChangePercentage)) {
              _tmpAtlChangePercentage = null;
            } else {
              _tmpAtlChangePercentage = _cursor.getDouble(_cursorIndexOfAtlChangePercentage);
            }
            final String _tmpAtlDate;
            if (_cursor.isNull(_cursorIndexOfAtlDate)) {
              _tmpAtlDate = null;
            } else {
              _tmpAtlDate = _cursor.getString(_cursorIndexOfAtlDate);
            }
            final String _tmpLastUpdated;
            if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
              _tmpLastUpdated = null;
            } else {
              _tmpLastUpdated = _cursor.getString(_cursorIndexOfLastUpdated);
            }
            final String _tmpSparklineData;
            if (_cursor.isNull(_cursorIndexOfSparklineData)) {
              _tmpSparklineData = null;
            } else {
              _tmpSparklineData = _cursor.getString(_cursorIndexOfSparklineData);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new CoinEntity(_tmpId,_tmpSymbol,_tmpName,_tmpImage,_tmpCurrentPrice,_tmpMarketCap,_tmpMarketCapRank,_tmpTotalVolume,_tmpHigh24h,_tmpLow24h,_tmpPriceChange24h,_tmpPriceChangePercentage24h,_tmpPriceChangePercentage1h,_tmpPriceChangePercentage7d,_tmpCirculatingSupply,_tmpTotalSupply,_tmpMaxSupply,_tmpAth,_tmpAthChangePercentage,_tmpAthDate,_tmpAtl,_tmpAtlChangePercentage,_tmpAtlDate,_tmpLastUpdated,_tmpSparklineData,_tmpCachedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCoinsCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM coins";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLastCacheTime(final Continuation<? super Long> $completion) {
    final String _sql = "SELECT MAX(cachedAt) FROM coins";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            final Long _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
