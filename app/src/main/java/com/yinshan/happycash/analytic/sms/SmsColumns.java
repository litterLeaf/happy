package com.yinshan.happycash.analytic.sms;

import android.net.Uri;

/**
 * Created by yyhuang on 2017/6/27  11:34.
 */

public class SmsColumns {

    /**
     * The {@code content://} style URL for this table.
     */
    public static final Uri CONTENT_URI = Uri.parse("content://sms");

    /**
     * The default sort order for this table.
     */
    public static final String DEFAULT_SORT_ORDER = "date DESC";


    public final static String _ID = "_id";
    /**
     * The type of message.
     * <P>Type: INTEGER</P>
     */
    public static final String TYPE = "type";

    /**
     * The thread ID of the message.
     * <P>Type: INTEGER</P>
     */
    public static final String THREAD_ID = "thread_id";

    /**
     * The address of the other party.
     * <P>Type: TEXT</P>
     */
    public static final String ADDRESS = "address";

    /**
     * The date the message was received.
     * <P>Type: INTEGER (long)</P>
     */
    public static final String DATE = "date";

    /**
     * The date the message was sent.
     * <P>Type: INTEGER (long)</P>
     */
    public static final String DATE_SENT = "date_sent";

    /**
     * The subject of the message, if present.
     * <P>Type: TEXT</P>
     */
    public static final String SUBJECT = "subject";

    /**
     * The body of the message.
     * <P>Type: TEXT</P>
     */
    public static final String BODY = "body";

}
