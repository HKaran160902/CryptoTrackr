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
import com.hari.pdd.cryptotrackr.data.local.entity.AlertEntity;
import java.lang.Class;
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
public final class AlertDao_Impl implements AlertDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AlertEntity> __insertionAdapterOfAlertEntity;

  private final EntityDeletionOrUpdateAdapter<AlertEntity> __deletionAdapterOfAlertEntity;

  private final EntityDeletionOrUpdateAdapter<AlertEntity> __updateAdapterOfAlertEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAlertById;

  private final SharedSQLiteStatement __preparedStmtOfSetAlertEnabled;

  private final SharedSQLiteStatement __preparedStmtOfMarkAlertTriggered;

  private final SharedSQLiteStatement __preparedStmtOfClearAllAlerts;

  public AlertDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlertEntity = new EntityInsertionAdapter<AlertEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `alerts` (`id`,`coinId`,`coinName`,`coinSymbol`,`coinImage`,`targetPrice`,`isAbove`,`isEnabled`,`isTriggered`,`createdAt`,`triggeredAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AlertEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCoinId());
        statement.bindString(3, entity.getCoinName());
        statement.bindString(4, entity.getCoinSymbol());
        statement.bindString(5, entity.getCoinImage());
        statement.bindDouble(6, entity.getTargetPrice());
        final int _tmp = entity.isAbove() ? 1 : 0;
        statement.bindLong(7, _tmp);
        final int _tmp_1 = entity.isEnabled() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final int _tmp_2 = entity.isTriggered() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        statement.bindLong(10, entity.getCreatedAt());
        if (entity.getTriggeredAt() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getTriggeredAt());
        }
      }
    };
    this.__deletionAdapterOfAlertEntity = new EntityDeletionOrUpdateAdapter<AlertEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `alerts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AlertEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAlertEntity = new EntityDeletionOrUpdateAdapter<AlertEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `alerts` SET `id` = ?,`coinId` = ?,`coinName` = ?,`coinSymbol` = ?,`coinImage` = ?,`targetPrice` = ?,`isAbove` = ?,`isEnabled` = ?,`isTriggered` = ?,`createdAt` = ?,`triggeredAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AlertEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCoinId());
        statement.bindString(3, entity.getCoinName());
        statement.bindString(4, entity.getCoinSymbol());
        statement.bindString(5, entity.getCoinImage());
        statement.bindDouble(6, entity.getTargetPrice());
        final int _tmp = entity.isAbove() ? 1 : 0;
        statement.bindLong(7, _tmp);
        final int _tmp_1 = entity.isEnabled() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final int _tmp_2 = entity.isTriggered() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        statement.bindLong(10, entity.getCreatedAt());
        if (entity.getTriggeredAt() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getTriggeredAt());
        }
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAlertById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM alerts WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetAlertEnabled = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE alerts SET isEnabled = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAlertTriggered = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE alerts SET isTriggered = 1, triggeredAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearAllAlerts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM alerts";
        return _query;
      }
    };
  }

  @Override
  public Object insertAlert(final AlertEntity alert, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAlertEntity.insertAndReturnId(alert);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAlert(final AlertEntity alert, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAlertEntity.handle(alert);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAlert(final AlertEntity alert, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAlertEntity.handle(alert);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAlertById(final long alertId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAlertById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, alertId);
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
          __preparedStmtOfDeleteAlertById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setAlertEnabled(final long alertId, final boolean isEnabled,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetAlertEnabled.acquire();
        int _argIndex = 1;
        final int _tmp = isEnabled ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, alertId);
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
          __preparedStmtOfSetAlertEnabled.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markAlertTriggered(final long alertId, final long triggeredAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAlertTriggered.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, triggeredAt);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, alertId);
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
          __preparedStmtOfMarkAlertTriggered.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllAlerts(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllAlerts.acquire();
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
          __preparedStmtOfClearAllAlerts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AlertEntity>> getAllAlerts() {
    final String _sql = "SELECT * FROM alerts ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"alerts"}, new Callable<List<AlertEntity>>() {
      @Override
      @NonNull
      public List<AlertEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCoinId = CursorUtil.getColumnIndexOrThrow(_cursor, "coinId");
          final int _cursorIndexOfCoinName = CursorUtil.getColumnIndexOrThrow(_cursor, "coinName");
          final int _cursorIndexOfCoinSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "coinSymbol");
          final int _cursorIndexOfCoinImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coinImage");
          final int _cursorIndexOfTargetPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "targetPrice");
          final int _cursorIndexOfIsAbove = CursorUtil.getColumnIndexOrThrow(_cursor, "isAbove");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfIsTriggered = CursorUtil.getColumnIndexOrThrow(_cursor, "isTriggered");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfTriggeredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredAt");
          final List<AlertEntity> _result = new ArrayList<AlertEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AlertEntity _item;
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
            final double _tmpTargetPrice;
            _tmpTargetPrice = _cursor.getDouble(_cursorIndexOfTargetPrice);
            final boolean _tmpIsAbove;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAbove);
            _tmpIsAbove = _tmp != 0;
            final boolean _tmpIsEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_1 != 0;
            final boolean _tmpIsTriggered;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsTriggered);
            _tmpIsTriggered = _tmp_2 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpTriggeredAt;
            if (_cursor.isNull(_cursorIndexOfTriggeredAt)) {
              _tmpTriggeredAt = null;
            } else {
              _tmpTriggeredAt = _cursor.getLong(_cursorIndexOfTriggeredAt);
            }
            _item = new AlertEntity(_tmpId,_tmpCoinId,_tmpCoinName,_tmpCoinSymbol,_tmpCoinImage,_tmpTargetPrice,_tmpIsAbove,_tmpIsEnabled,_tmpIsTriggered,_tmpCreatedAt,_tmpTriggeredAt);
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
  public Flow<List<AlertEntity>> getActiveAlerts() {
    final String _sql = "SELECT * FROM alerts WHERE isEnabled = 1 AND isTriggered = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"alerts"}, new Callable<List<AlertEntity>>() {
      @Override
      @NonNull
      public List<AlertEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCoinId = CursorUtil.getColumnIndexOrThrow(_cursor, "coinId");
          final int _cursorIndexOfCoinName = CursorUtil.getColumnIndexOrThrow(_cursor, "coinName");
          final int _cursorIndexOfCoinSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "coinSymbol");
          final int _cursorIndexOfCoinImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coinImage");
          final int _cursorIndexOfTargetPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "targetPrice");
          final int _cursorIndexOfIsAbove = CursorUtil.getColumnIndexOrThrow(_cursor, "isAbove");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfIsTriggered = CursorUtil.getColumnIndexOrThrow(_cursor, "isTriggered");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfTriggeredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredAt");
          final List<AlertEntity> _result = new ArrayList<AlertEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AlertEntity _item;
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
            final double _tmpTargetPrice;
            _tmpTargetPrice = _cursor.getDouble(_cursorIndexOfTargetPrice);
            final boolean _tmpIsAbove;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAbove);
            _tmpIsAbove = _tmp != 0;
            final boolean _tmpIsEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_1 != 0;
            final boolean _tmpIsTriggered;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsTriggered);
            _tmpIsTriggered = _tmp_2 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpTriggeredAt;
            if (_cursor.isNull(_cursorIndexOfTriggeredAt)) {
              _tmpTriggeredAt = null;
            } else {
              _tmpTriggeredAt = _cursor.getLong(_cursorIndexOfTriggeredAt);
            }
            _item = new AlertEntity(_tmpId,_tmpCoinId,_tmpCoinName,_tmpCoinSymbol,_tmpCoinImage,_tmpTargetPrice,_tmpIsAbove,_tmpIsEnabled,_tmpIsTriggered,_tmpCreatedAt,_tmpTriggeredAt);
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
  public Object getActiveAlertsSync(final Continuation<? super List<AlertEntity>> $completion) {
    final String _sql = "SELECT * FROM alerts WHERE isEnabled = 1 AND isTriggered = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AlertEntity>>() {
      @Override
      @NonNull
      public List<AlertEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCoinId = CursorUtil.getColumnIndexOrThrow(_cursor, "coinId");
          final int _cursorIndexOfCoinName = CursorUtil.getColumnIndexOrThrow(_cursor, "coinName");
          final int _cursorIndexOfCoinSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "coinSymbol");
          final int _cursorIndexOfCoinImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coinImage");
          final int _cursorIndexOfTargetPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "targetPrice");
          final int _cursorIndexOfIsAbove = CursorUtil.getColumnIndexOrThrow(_cursor, "isAbove");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfIsTriggered = CursorUtil.getColumnIndexOrThrow(_cursor, "isTriggered");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfTriggeredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredAt");
          final List<AlertEntity> _result = new ArrayList<AlertEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AlertEntity _item;
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
            final double _tmpTargetPrice;
            _tmpTargetPrice = _cursor.getDouble(_cursorIndexOfTargetPrice);
            final boolean _tmpIsAbove;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAbove);
            _tmpIsAbove = _tmp != 0;
            final boolean _tmpIsEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_1 != 0;
            final boolean _tmpIsTriggered;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsTriggered);
            _tmpIsTriggered = _tmp_2 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpTriggeredAt;
            if (_cursor.isNull(_cursorIndexOfTriggeredAt)) {
              _tmpTriggeredAt = null;
            } else {
              _tmpTriggeredAt = _cursor.getLong(_cursorIndexOfTriggeredAt);
            }
            _item = new AlertEntity(_tmpId,_tmpCoinId,_tmpCoinName,_tmpCoinSymbol,_tmpCoinImage,_tmpTargetPrice,_tmpIsAbove,_tmpIsEnabled,_tmpIsTriggered,_tmpCreatedAt,_tmpTriggeredAt);
            _result.add(_item);
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
  public Object getAlertById(final long alertId,
      final Continuation<? super AlertEntity> $completion) {
    final String _sql = "SELECT * FROM alerts WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, alertId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AlertEntity>() {
      @Override
      @Nullable
      public AlertEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCoinId = CursorUtil.getColumnIndexOrThrow(_cursor, "coinId");
          final int _cursorIndexOfCoinName = CursorUtil.getColumnIndexOrThrow(_cursor, "coinName");
          final int _cursorIndexOfCoinSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "coinSymbol");
          final int _cursorIndexOfCoinImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coinImage");
          final int _cursorIndexOfTargetPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "targetPrice");
          final int _cursorIndexOfIsAbove = CursorUtil.getColumnIndexOrThrow(_cursor, "isAbove");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfIsTriggered = CursorUtil.getColumnIndexOrThrow(_cursor, "isTriggered");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfTriggeredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredAt");
          final AlertEntity _result;
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
            final double _tmpTargetPrice;
            _tmpTargetPrice = _cursor.getDouble(_cursorIndexOfTargetPrice);
            final boolean _tmpIsAbove;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAbove);
            _tmpIsAbove = _tmp != 0;
            final boolean _tmpIsEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_1 != 0;
            final boolean _tmpIsTriggered;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsTriggered);
            _tmpIsTriggered = _tmp_2 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpTriggeredAt;
            if (_cursor.isNull(_cursorIndexOfTriggeredAt)) {
              _tmpTriggeredAt = null;
            } else {
              _tmpTriggeredAt = _cursor.getLong(_cursorIndexOfTriggeredAt);
            }
            _result = new AlertEntity(_tmpId,_tmpCoinId,_tmpCoinName,_tmpCoinSymbol,_tmpCoinImage,_tmpTargetPrice,_tmpIsAbove,_tmpIsEnabled,_tmpIsTriggered,_tmpCreatedAt,_tmpTriggeredAt);
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
  public Flow<List<AlertEntity>> getAlertsForCoin(final String coinId) {
    final String _sql = "SELECT * FROM alerts WHERE coinId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, coinId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"alerts"}, new Callable<List<AlertEntity>>() {
      @Override
      @NonNull
      public List<AlertEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCoinId = CursorUtil.getColumnIndexOrThrow(_cursor, "coinId");
          final int _cursorIndexOfCoinName = CursorUtil.getColumnIndexOrThrow(_cursor, "coinName");
          final int _cursorIndexOfCoinSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "coinSymbol");
          final int _cursorIndexOfCoinImage = CursorUtil.getColumnIndexOrThrow(_cursor, "coinImage");
          final int _cursorIndexOfTargetPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "targetPrice");
          final int _cursorIndexOfIsAbove = CursorUtil.getColumnIndexOrThrow(_cursor, "isAbove");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfIsTriggered = CursorUtil.getColumnIndexOrThrow(_cursor, "isTriggered");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfTriggeredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredAt");
          final List<AlertEntity> _result = new ArrayList<AlertEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AlertEntity _item;
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
            final double _tmpTargetPrice;
            _tmpTargetPrice = _cursor.getDouble(_cursorIndexOfTargetPrice);
            final boolean _tmpIsAbove;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAbove);
            _tmpIsAbove = _tmp != 0;
            final boolean _tmpIsEnabled;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp_1 != 0;
            final boolean _tmpIsTriggered;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsTriggered);
            _tmpIsTriggered = _tmp_2 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpTriggeredAt;
            if (_cursor.isNull(_cursorIndexOfTriggeredAt)) {
              _tmpTriggeredAt = null;
            } else {
              _tmpTriggeredAt = _cursor.getLong(_cursorIndexOfTriggeredAt);
            }
            _item = new AlertEntity(_tmpId,_tmpCoinId,_tmpCoinName,_tmpCoinSymbol,_tmpCoinImage,_tmpTargetPrice,_tmpIsAbove,_tmpIsEnabled,_tmpIsTriggered,_tmpCreatedAt,_tmpTriggeredAt);
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
  public Object getActiveAlertsCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM alerts WHERE isEnabled = 1 AND isTriggered = 0";
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
