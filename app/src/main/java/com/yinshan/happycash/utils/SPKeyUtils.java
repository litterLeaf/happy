package com.yinshan.happycash.utils;


/**
 *     ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *            ┃┫┫　┃┫┫
 *            ┗┻┛　┗┻┛
 * 用来存储SharedPreference 的所有key
 *
 * @author admin
 * on 2018/1/11
 */
public interface SPKeyUtils {

    //--------------------------------------- int 类型的-------------------------------------

    //错误的警告
    int SNACKBAR_TYPE_ERROR = 0;
    //提示
    int SNACKBAR_TYPE_TIP = 1;
    //提醒
    int SNACKBAR_TYPE_WORN = 2;
    //跳转的提示
    int SNACKBAR_TYPE_INTEENT = 3;

    /**
     * dialog 的type
     */
    int DIALOG_GPS = 1;
    int DIALOG_SETTING = 2;
    int DIALOG_NULL = 3;

    //--------------------------------------- String 类型的-------------------------------------
    /**
     * 保存cookie
     */
    String COOKIE = "cookie";
    /**
     * 服务器相应时间
     */
    String TOKEN_KEY = "token_key";
    String DATE = "date";
    String APP_STATUES = "loan_app_status";
    String SERVER_STATUES = "loan_server_status";
    String LOANAPPBEAN = "loanAppBean";
    String UNLOAN_FRAG = "unLoanFrag";
    String LOANING_FRAG = "loaningFrag";
    String PROCESS_FRAG = "processFrag";
    String BUILDUP_FRAG = "buildUpFrag";
    String REJECT_FRAG = "rejectFrag";
    String REPAYMENT_FRAG = "repayment_fragment";
    String CERTIFICATION_TAB = "certificationFrag";
    String ME_TAB = "meFrag";


    String MOBILE = "mobile";
    String PHOTO_TYPE = "photo_type";
    String REQUEST_CODE = "request_code";
    String TEMP_INTENT = "tempintent";

    String IS_KTP_PHOTO_OK = "is_ktp_photo_ok";
    String IS_WORK_PHOTO_OK = "is_work_photo_ok";

    //环信账号信息
    String TENANTID="53718";//租户ID
    String IMSERVICE="kefuchannelimid_256170";//服务号
    String CHAT_CLIENT_APPKEY ="1481180402061178#kefuchannelapp53718";
    String PROJECT_ID  = "2610618";

    //环信的客服请求
    String HX_API_URL = "http://kefu.easemob.com/v1/Tenants/";

    String DialogType_CURRENT ="CURRENT";
    String DialogType_PAID_OFF ="PAID_OFF";
    String DialogType_HAIRCUT_ISSUE ="HAIRCUT_ISSUE";

    String SP_APPINFO_FILENAME = "appInformation";
    String IMEI = "imei";
    String FIRST_START = "first_start";
    String SHOW_GUIDE = "show_guide";
    String IS_LIVENESS = "is_liveness";
    String IS_Invitation_Enable = "is_invitation_enable";
    String REPAYMENT_LOAN_DETAIL = "repayment_Loan_detail";
    String  DEPOSITMETHODS_METHODS= "DepositMethodsBean";

    String CHANNEL_FASPAY= "FASPAY";
    String CHANNEL_XENDIT= "XENDIT";


}
