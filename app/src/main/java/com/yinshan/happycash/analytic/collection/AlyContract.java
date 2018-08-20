package com.yinshan.happycash.analytic.collection;

import org.json.JSONException;
import org.json.JSONObject;


public class AlyContract {


    public interface ReportPolicy {
        int REALTIME  = 0;      //实时发送
        int BATCH = 1;          //批量发送，打开App 5s之后
        int SEND_INTERVAL = 2;  //最小间隔发送
    }

    public interface ALY_KEY {
        String PROTOCALNAME = "protocolName";
        String PROTOCALVERSION = "protocolVersion";
        String VERSIONNAME = "versionName";
        String TOTALNUMBER = "totalNumber";
        String LATESTTIME = "latestTime";
        String EARLIESTTIME = "earliestTime";
        String DATA = "data";
    }

    public interface LOCATION_KEY {
        String LOCTYPE = "locType";
        String LATITUDE  = "latitude";
        String LONGITUDE = "longitude";
        String ALTITUDE = "altitude";
        String CREATETIME = "createTime";
    }

    public interface CONTACT_KEY {
        String NAME = "name";
        String NUMBER = "number";
        String EMAIL = "email";
        String UPDATETIME = "updateTime";
        String STATE = "state";
        String ACCOUNTTYPE = "accountType";
    }

    public interface EVENT_KEY {
        String EVENTID = "eventId";
        String EVENTTIME = "eventTime";
    }

    public interface CALLLOG_KEY {
        String NAME = "name";
        String NUMBER = "number";
        String TYPE = "type";
        String DATE = "date";
        String DURATION  = "duration";
    }

    public interface SMS_KEY {
        String TYPE = "type";
        String THREADID = "thread_id";
        String ADDRESS = "address";
        String DATE = "date";
        String DATE_SENT = "date_sent";
        String SUBJECT = "subject";
        String BODY = "content";
    }

    public static JSONObject getBaseObject() {
        JSONObject result = new JSONObject();
        try {
//            result.put(ALY_KEY.PROTOCALVERSION, ProtocolVersion.V_2_0);
//            result.put(ALY_KEY.VERSIONNAME, SystemUtil.getInstance().getVersionName());
            result.put(ALY_KEY.LATESTTIME, 0);
            result.put(ALY_KEY.EARLIESTTIME, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
