package com.hari.pdd.cryptotrackr.data.local.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.hari.pdd.cryptotrackr.data.local.entity.HoldingEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
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
public final class HoldingDao_Impl implements HoldingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HoldingEntity> __insertionAdapterOfHoldingEntity;

  private final EntityDeletionOrUpdateAdapter<HoldingEntity> __deletionAdapterOfHoldingEntity;

  private final EntityDeletionOrUpdateAdapter<HoldingEntity> __updateAdapterOfHoldingEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteHoldingById;

  private final SharedSQLiteStatement __preparedStmtOfClearAllHoldings;

  public HoldingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHoldingEntity = new EntityInsertionAdapter<HoldingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `holdings` (`id`,`coinId`,`coinName`,`coinSymbol`,`coinImage`,`quantity`,`purchasePrice`,`purchaseDate`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HoldingEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCoinId());
        statement.bindString(3, entity.getCoinName());
        statement.bindString(4, entity.getCoinSymbol());
        statement.bindString(5, entity.getCoinImage());
        statement.bindDouble(6, entity.getQuantity());
        statement.bindDouble(7, entity.getPurchasePrice());
        statement.bindLong(8, entity.getPurchaseDate());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
      }
    };
    this.__deletionAdapterOfHoldingEntity = new EntityDeletionOrUpdateAdapter<HoldingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `holdings` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HoldingEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfHoldingEntity = new EntityDeletionOrUpdateAdapter<HoldingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `holdings` SET `id` = ?,`coinId` = ?,`coinName` = ?,`coinSymbol` = ?,`coinImage` = ?,`quantity` = ?,`purchasePrice` = ?,`purchaseDate` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HoldingEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCoinId());
        statement.bindString(3, entity.getCoinName());
        statement.bindString(4, entity.getCoinSymbol());
        statement.bindString(5, entity.getCoinImage());
        statement.bindDouble(6, entity.getQuantity());
        statement.bindDouble(7, entity.getPurchasePrice());
        statement.bindLong(8, entity.getPurchaseDate());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteHoldingById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM holdings WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearAllHoldings = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM holdings";
        return _query;
      }
    };
  }

  @Override
  public Object insertHolding(final HoldingEntity holding,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfHoldingEntity.insertAndReturnId(holding);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteHolding(final HoldingEntity holding,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHoldingEntity.handle(holding);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateHolding(final HoldingEntity holding,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfHoldingEntity.handle(holding);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteHoldingById(final long holdingId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteHoldingById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, holdingId);
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
          __preparedStmtOfDeleteHoldingById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllHoldings(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllHoldings.acquire();
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
          __preparedStmtOfClearAllHoldings.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HoldingEntity>> getAllHoldings() {
    final String _sql = "SELECT * FROM holdings ORDER BY purchaseDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"holdings"}, new Callable<List<HoldingEntity>>() {
      @Override
      @NonNull
      public List<HoldingEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCoinId = CursorUtil.getColumnIndexOrThrow(_cursor, "coinId");
          final int _cursorIndexOfCoinName = CursorUtil.getColumnIndexOrThrow(_cursor, "coinName");
          final int _cursorIndexOfCoinSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "coinSymbol");
          final int _cursorIndexOfCoinImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coinImage");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfPurchasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "purchasePrice");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<HoldingEntity> _result = new ArrayList<HoldingEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HoldingEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCoinId;
            _tmpCoinId = _cursor.getString(_cursorIndexOfCoinId);
            final String _tmpCoinName;
            _tmpCoinName = _cursor.getString(_cursorIndexOfCoinName);
            final String _tmpCoinSymbol;
            _tmpCoinSymbol = _cursor.getString(_cursorIndexOfCoinSymbol);
            final String _tmpCoinImage;
            _tmpCoinImage = _cursor.getString(_cursorIndexOfCoinImage);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpPurchasePrice;
            _tmpPurchasePrice = _cursor.getDouble(_cursorIndexOfPurchasePrice);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new HoldingEntity(_tmpId,_tmpCoinId,_tmpCoinName,_tmpCoinSymbol,_tmpCoinImage,_tmpQuantity,_tmpPurchasePrice,_tmpPurchaseDate,_tmpNotes);
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
  public Object getHoldingById(final long holdingId,
      final Continuation<? super HoldingEntity> $completion) {
    final String _sql = "SELECT * FROM holdings WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, holdingId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<HoldingEntity>() {
      @Override
      @Nullable
      public HoldingEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCoinId = CursorUtil.getColumnIndexOrThrow(_cursor, "coinId");
          final int _cursorIndexOfCoinName = CursorUtil.getColumnIndexOrThrow(_cursor, "coinName");
          final int _cursorIndexOfCoinSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "coinSymbol");
          final int _cursorIndexOfCoinImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coinImage");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfPurchasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "purchasePrice");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final HoldingEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCoinId;
            _tmpCoinId = _cursor.getString(_cursorIndexOfCoinId);
            final String _tmpCoinName;
            _tmpCoinName = _cursor.getString(_cursorIndexOfCoinName);
            final String _tmpCoinSymbol;
            _tmpCoinSymbol = _cursor.getString(_cursorIndexOfCoinSymbol);
            final String _tmpCoinImage;
            _tmpCoinImage = _cursor.getString(_cursorIndexOfCoinImage);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpPurchasePrice;
            _tmpPurchasePrice = _cursor.getDouble(_cursorIndexOfPurchasePrice);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new HoldingEntity(_tmpId,_tmpCoinId,_tmpCoinName,_tmpCoinSymbol,_tmpCoinImage,_tmpQuantity,_tmpPurchasePrice,_tmpPurchaseDate,_tmpNotes);
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
  public Flow<List<HoldingEntity>> getHoldingsByCoinId(final String coinId) {
    final String _sql = "SELECT * FROM holdings WHERE coinId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, coinId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"holdings"}, new Callable<List<HoldingEntity>>() {
      @Override
      @NonNull
      public List<HoldingEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCoinId = CursorUtil.getColumnIndexOrThrow(_cursor, "coinId");
          final int _cursorIndexOfCoinName = CursorUtil.getColumnIndexOrThrow(_cursor, "coinName");
          final int _cursorIndexOfCoinSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "coinSymbol");
          final int _cursorIndexOfCoinImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coinImage");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfPurchasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "purchasePrice");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<HoldingEntity> _result = new ArrayList<HoldingEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HoldingEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCoinId;
            _tmpCoinId = _cursor.getString(_cursorIndexOfCoinId);
            final String _tmpCoinName;
            _tmpCoinName = _cursor.getString(_cursorIndexOfCoinName);
            final String _tmpCoinSymbol;
            _tmpCoinSymbol = _cursor.getString(_cursorIndexOfCoinSymbol);
            final String _tmpCoinImage;
            _tmpCoinImage = _cursor.getString(_cursorIndexOfCoinImage);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpPurchasePrice;
            _tmpPurchasePrice = _cursor.getDouble(_cursorIndexOfPurchasePrice);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new HoldingEntity(_tmpId,_tmpCoinId,_tmpCoinName,_tmpCoinSymbol,_tmpCoinImage,_tmpQuantity,_tmpPurchasePrice,_tmpPurchaseDate,_tmpNotes);
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
  public Flow<List<String>> getAllHeldCoinIds() {
    final String _sql = "SELECT DISTINCT coinId FROM holdings";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"holdings"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
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
  public Object getHoldingsCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM holdings";
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
  public Flow<Double> getTotalInvested() {
    final String _sql = "SELECT SUM(quantity * purchasePrice) FROM holdings";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"holdings"}, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
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
  public Object getTotalQuantityForCoin(final String coinId,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(quantity) FROM holdings WHERE coinId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, coinId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
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
