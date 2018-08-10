package com.yinshan.happycash.analytic.contacts;

import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by yyhuang on 2017/6/21  15:27.
 */

public class ContactContract {
    public final static int STATE_IDLE = 0;//空的
    public final static int STATE_ADD = 1;
    public final static int STATE_UPDATE = 2;
    public final static int STATE_DELETE = 3;

    public final static int FLAG_NEED_UPLOAD = 8;
    public final static int FLAG_ALREADY_UPLOAD = 9;

    public static final class DeletedContacts {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI,
                "deleted_contacts");

        public static final String CONTACT_ID = "contact_id";

        public static final String CONTACT_DELETED_TIMESTAMP = "contact_deleted_timestamp";
    }
}
