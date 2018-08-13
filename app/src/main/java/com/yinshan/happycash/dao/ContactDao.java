package com.yinshan.happycash.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CONTACT".
*/
public class ContactDao extends AbstractDao<Contact, Long> {

    public static final String TABLENAME = "CONTACT";

    /**
     * Properties of entity Contact.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ContactId = new Property(1, Long.class, "contactId", false, "CONTACT_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Phone = new Property(3, String.class, "phone", false, "PHONE");
        public final static Property Email = new Property(4, String.class, "email", false, "EMAIL");
        public final static Property UpdateTime = new Property(5, String.class, "updateTime", false, "UPDATE_TIME");
        public final static Property State = new Property(6, Integer.class, "state", false, "STATE");
        public final static Property AccountType = new Property(7, String.class, "accountType", false, "ACCOUNT_TYPE");
        public final static Property Flag = new Property(8, Integer.class, "flag", false, "FLAG");
    }


    public ContactDao(DaoConfig config) {
        super(config);
    }
    
    public ContactDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CONTACT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CONTACT_ID\" INTEGER," + // 1: contactId
                "\"NAME\" TEXT," + // 2: name
                "\"PHONE\" TEXT," + // 3: phone
                "\"EMAIL\" TEXT," + // 4: email
                "\"UPDATE_TIME\" TEXT," + // 5: updateTime
                "\"STATE\" INTEGER," + // 6: state
                "\"ACCOUNT_TYPE\" TEXT," + // 7: accountType
                "\"FLAG\" INTEGER);"); // 8: flag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CONTACT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Contact entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long contactId = entity.getContactId();
        if (contactId != null) {
            stmt.bindLong(2, contactId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(6, updateTime);
        }
 
        Integer state = entity.getState();
        if (state != null) {
            stmt.bindLong(7, state);
        }
 
        String accountType = entity.getAccountType();
        if (accountType != null) {
            stmt.bindString(8, accountType);
        }
 
        Integer flag = entity.getFlag();
        if (flag != null) {
            stmt.bindLong(9, flag);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Contact entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long contactId = entity.getContactId();
        if (contactId != null) {
            stmt.bindLong(2, contactId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(5, email);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(6, updateTime);
        }
 
        Integer state = entity.getState();
        if (state != null) {
            stmt.bindLong(7, state);
        }
 
        String accountType = entity.getAccountType();
        if (accountType != null) {
            stmt.bindString(8, accountType);
        }
 
        Integer flag = entity.getFlag();
        if (flag != null) {
            stmt.bindLong(9, flag);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Contact readEntity(Cursor cursor, int offset) {
        Contact entity = new Contact( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // contactId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // phone
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // email
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // updateTime
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // state
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // accountType
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8) // flag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Contact entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContactId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhone(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEmail(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUpdateTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setState(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setAccountType(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFlag(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Contact entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Contact entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Contact entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}