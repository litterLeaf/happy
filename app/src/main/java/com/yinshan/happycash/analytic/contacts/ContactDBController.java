package com.yinshan.happycash.analytic.contacts;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.SparseArray;


import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.dao.Contact;
import com.yinshan.happycash.dao.ContactDao;
import com.yinshan.happycash.dao.DaoDbHelper;
import com.yinshan.happycash.dao.DaoMaster;
import com.yinshan.happycash.dao.DaoSession;
import com.yinshan.happycash.utils.DateUtil;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by yyhuang on 2017/6/20  11:34.
 */

public class ContactDBController {
    private Context mContext;
    private static ContactDBController mInstance;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private ContactDao mContactDao;

    public ContactDBController(Context context) {
        mContext = context;
//        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DaoDbHelper.DB_NAME, null);
//        mDaoMaster = new DaoMaster(openHelper.getWritableDatabase());
//        mDaoSession = mDaoMaster.newSession();
        DaoSession daoSession = DaoDbHelper.getInstance().getDaoSession();
        mContactDao = daoSession.getContactDao();
    }

    public static ContactDBController getInstance() {
        if (mInstance == null) {
            synchronized (ContactDBController.class) {
                if (mInstance == null) {
                    mInstance = new ContactDBController(AppApplication.instance);
                }
            }
        }
        return mInstance;
    }

    public void gainContacts() {
        Observable.create(new Observable.OnSubscribe<SparseArray<List<Contact>>>() {
            @Override
            public void call(Subscriber<? super SparseArray<List<Contact>>> subscriber) {
                List<Contact> contacts = getContacts();
                List<Contact> deletedContacts = getDeletedContacts();
                SparseArray<List<Contact>> group = new SparseArray<>();
                group.put(0, contacts);
                group.put(1, deletedContacts);
                subscriber.onNext(group);
                subscriber.onCompleted();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(new Observer<SparseArray<List<Contact>>>() {
            @Override
            public void onCompleted() {
                //send data
//                AppReport.sendContactsDelay();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(SparseArray<List<Contact>> group) {
                //正常的
                updateContacts(group.get(0));
                updateDeletedContacts(group.get(1));
            }
        });
    }

    public List<Contact> getDeletedContacts() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        List<Contact> contacts = new LinkedList<>();
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(ContactContract.DeletedContacts.CONTENT_URI,
                null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                final long contactId = cursor.getLong(cursor.getColumnIndex(ContactContract.DeletedContacts.CONTACT_ID));
                final long updateTime = cursor.getLong(cursor.getColumnIndex(ContactContract.DeletedContacts.CONTACT_DELETED_TIMESTAMP));
                Contact contact = new Contact();
                contact.setContactId(contactId);
                contact.setUpdateTime(DateUtil.now(updateTime));

                contacts.add(contact);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return contacts;
    }

    private List<Contact> getContacts() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        List<Contact> contacts = new LinkedList<>();
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);

                JSONArray numbers = new JSONArray();
                JSONArray emails = new JSONArray();
                JSONArray accountTypes = new JSONArray();

                String name = cursor.getString(nameFieldColumnIndex);

                final long contactId = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                final long updateTimeStamp = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP));

                Cursor accountCr = null;
                try {
                    accountCr = resolver.query(ContactsContract.RawContacts.CONTENT_URI,
                        new String[]{ContactsContract.RawContacts.ACCOUNT_TYPE},
                        ContactsContract.RawContacts.CONTACT_ID +"=?",
                        new String[]{String.valueOf(contactId)},
                        null);
                    while (accountCr.moveToNext()) {
                        String accountType = accountCr.getString(0);
                        accountTypes.put(accountType);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (accountCr != null) {
                        accountCr.close();
                    }
                }
                Cursor phoneCr = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                        null,
                        null);
                try {
                    while (phoneCr.moveToNext()) {
                        String phoneNumber = phoneCr.getString(phoneCr.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneNumber = phoneNumber.replace("-", "");
                        phoneNumber = phoneNumber.replace(" ", "");

                        numbers.put(phoneNumber);
                    }
                } finally {
                    if (phoneCr != null) {
                        phoneCr.close();
                    }
                }

                Cursor emailCr = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                        null,
                        null);
                try {
                    while (emailCr.moveToNext()) {
                        String email = emailCr.getString(emailCr.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        emails.put(email);
                    }
                } finally {
                    if (emailCr != null) {
                        emailCr.close();
                    }
                }

                Contact contact = new Contact();
                contact.setContactId(contactId);
                contact.setName(name);
                contact.setPhone(numbers.toString());
                contact.setEmail(emails.toString());
                contact.setUpdateTime(DateUtil.now(updateTimeStamp));
                contact.setAccountType(accountTypes.toString());

                contacts.add(contact);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return contacts;
    }

    /**
     * 更新status 的  走的正常的数据上报
     * @param contacts
     */
    private void updateContacts(List<Contact> contacts) {
        List<Contact> addContacts = new ArrayList<>();
        List<Contact> updateContacts = new ArrayList<>();
        if(contacts==null){
            Log.e("ContactDBController","contactList is null");
            return;
        }
        for (Contact contact : contacts) {
            QueryBuilder<Contact> qb = mContactDao.queryBuilder();
            qb.where(ContactDao.Properties.ContactId.eq(contact.getContactId()));
            Contact c = qb.unique();
            if (c == null) {
                contact.setState(ContactContract.STATE_ADD);
                contact.setFlag(ContactContract.FLAG_NEED_UPLOAD);
                addContacts.add(contact);
            } else {
                if (!contact.getUpdateTime().equals(c.getUpdateTime())
                        || !contact.getAccountType().equals(c.getAccountType())) {
                    contact.setId(c.getId());
                    contact.setState(ContactContract.STATE_UPDATE);
                    updateContacts.add(contact);
                }
            }
        }

        if (addContacts.size() > 0) {
            mContactDao.insertInTx(addContacts);
        }

        if (updateContacts.size() > 0) {
            mContactDao.updateInTx(updateContacts);
        }
    }
    private void updateDeletedContacts(List<Contact> contacts) {
        List<Contact> updateContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            QueryBuilder<Contact> qb = mContactDao.queryBuilder();
            qb.where(ContactDao.Properties.ContactId.eq(contact.getContactId()));
            Contact c = qb.unique();
            if (c != null && !c.getUpdateTime().equals(contact.getUpdateTime())) {
                c.setUpdateTime(contact.getUpdateTime());
                c.setState(ContactContract.STATE_DELETE);
                updateContacts.add(c);
            }
        }
        if (updateContacts.size() > 0) {
            mContactDao.updateInTx(updateContacts);
        }
    }
    public List<Contact> getUploadContacts() {
        QueryBuilder<Contact> qb = mContactDao.queryBuilder();
        qb.where(ContactDao.Properties.State.notEq(ContactContract.STATE_IDLE));
        qb.limit(500);
        return qb.list();
    }

    public void updateStateByKey(List<Long> keys) {
        List<Contact> contacts;
        QueryBuilder<Contact> qb = mContactDao.queryBuilder();
        contacts = qb.where(ContactDao.Properties.Id.in(keys)).list();
        for (Contact contact : contacts) {
            contact.setState(ContactContract.STATE_IDLE);
        }
        mContactDao.updateInTx(contacts);
    }

    /**
     * 整体上报
     * @return
     */

    public List<Contact> getUploadFlagContacts() {
        QueryBuilder<Contact> qb = mContactDao.queryBuilder();
        qb.where(ContactDao.Properties.Flag.eq(ContactContract.FLAG_NEED_UPLOAD));
        return qb.list();
    }
    public void updateContactFlagByKey() {
//        List<Contact> contacts;
//        QueryBuilder<Contact> qb = mContactDao.queryBuilder();
//            for (int i =0;i<keys.size();i++){
//                contacts = qb.where(ContactDao.Properties.Id.in(keys.get(i))).list();
//                Contact contact= contacts.get(i);
//                contact.setFlag(ContactContract.FLAG_ALREADY_UPLOAD);
//                mContactDao.updateInTx(contact);
//        }
        mContactDao.getDatabase().execSQL("UPDATE " +ContactDao.TABLENAME+" SET "+"FLAG = 9");
    }


//    public List<Contact> getContract() {
//        QueryBuilder<Contact> qb = mContactDao.queryBuilder();
//        qb.orderDesc(ContactDao.Properties.Id);
//        return qb.list();
//    }

}
