package com.yinshan.happycash.analytic.event;

/**
 * Created by huxin on 2018/3/29.
 */

public class MobEvent {
    public final static String  LIST="LIST_";
    public final static String   CLICK="CLICK_";
    public final static String  START="_START";
    public final static String  END = "_END";
    public final static String  PASTE="PASTE_";
    //LoginActivity
//    1.进入事件                             IN_LOGIN_ACTIVITY
//	2.输入类事件  输入电话                 INPUT_PHONE
//	3.输入类事件  输入验证码（4个事件）    INPUT_SMS
//	4.点击类事件  点击获取验证码           GET_SMS
//	5.点击类事件  获取语音验证码           GET_VIOCE_SMS
//	6.点击类事件  用户隐私协议             AGREEMENT_UESER
//	7.点击类事件  服务条款                 AGREEMENT_SERVER
//	8.点击类事件  登录                     LOGIN
//	9.退出事件 	                           BACK
//	10.输入类事件 图形验证码               PICTURE_VERIFICATION_CODE
//	11.点击类事件 刷新                     REFRASH
    public final static String IN_LOGIN_ACTIVITY = "IN_LOGIN_ACTIVITY";
    public final static String INPUT_PHONE = "INPUT_PHONE";
    public final static String INPUT_SMS = "INPUT_SMS";
    public final static String GET_SMS = "GET_SMS";
    public final static String GET_VOICE_SMS = "GET_VOICE_SMS";
    public final static String AGREEMENT_USER = "AGREEMENT_USER";
    public final static String AGREEMENT_SERVER = "AGREEMENT_SERVER";
    public final static String LOGIN_ACTIVITY_LOGIN = "LOGIN_ACTIVITY_LOGIN";
    public final static String LOGIN_ACTIVITY_BACK = "LOGIN_ACTIVITY_BACK";
    public final static String INPUT_PICTURE_VERIFICATION_CODE = "PICTURE_VERIFICATION_CODE";
    public final static String REFRESH = "REFRESH_PICTURE_VERIFICATION_CODE";

    //    MainActivity:(4个Tab)
    public final static String TAB_LOAN = "TAB_LOAN";
    public final static String TAB_INFORMATION = "TAB_INFORMATION";
    public final static String TAB_SET = "TAB_SET";
    public final static String TAB_ONLINE = "TAB_ONLINE";
    //1.Loan
//            <1>进入事件                IN_LOAN_FRAGMENT
//          <2>点击类事件  钱数(60万)  BORROW_MONEY_60
//          <3>点击类事件  钱数(120万) BORROW_MONEY_120
//          <4>点击类事件  期限(7天)	 PERIOD_7
//          <5>点击类事件  期限(14天)  PERIOD_14
//          <6>点击类事件  申请        APPLY
//            <7>点击类事件  信息        MESSAGE
    public final static String IN_LOAN_FRAGMENT = "IN_LOAN_FRAGMENT";
    public final static String BORROW_MONEY_60 = "BORROW_MONEY_60";
    public final static String BORROW_MONEY_120 = "BORROW_MONEY_120";
    public final static String BORROW_PERIOD_7 = "PERIOD_7";
    public final static String BORROW_PERIOD_14 = "PERIOD_14";
    public final static String LOAN_APPLY = "LOAN_APPLY";
    public final static String MESSAGE = "MESSAGE";

    //2.Information
//     	  <1>进入事件               IN_INFORMATION_FRAGMENT
//     	  <2>点击类事件  个人信息   PERSONAL_INFOR
//            <3>点击类事件  工作信息   WORK_INFOR
//            <4>点击类事件  联系人信息 CONTACT_INFOR
//            <5>点击类事件  照片事件   UPLOAD_PICTURE
//            <6>点击类事件  (在无借款状态下) 下一步  NEXT
//     	  <7>点击类事件  信息       MESSAGE
    public final static String IN_INFORMATION_FRAGMENT = "IN_INFORMATION_FRAGMENT";
    public final static String PERSONAL_INFOR = "PERSONAL_INFOR";
    public final static String WORK_INFOR = "WORK_INFOR";
    public final static String CONTACT_INFOR = "CONTACT_INFOR";
    public final static String SUBMIT = "INFORMATION_SUBMIT";
    public final static String UPLOAD_PICTURE = "UPLOAD_PICTURE";
    // 3.personal Center
//     	  <1>进入事件                IN_PERSONAL_CENTER_FRAGMENT
//     	  <2>点击类事件  登录        LOGIN
//            <3>点击类事件  我的借款    MYLOAN
//            <4>点击类事件	 安全设置    SETTING
//            <5>点击类事件  帮助中心    HELP
//            <6>点击类事件  关于软件    ABOUT_RUPIAH
//            <7>点击类事件  热线        HOTLINE
    public final static String IN_PERSONAL_CENTER_FRAGMENT = "IN_PERSONAL_CENTER_FRAGMENT";
    public final static String PERSONAL_CENTER_LOGIN = "PERSONAL_CENTER_LOGIN";
    public final static String PERSONAL_CENTER_MYLOAN = "PERSONAL_CENTER_MYLOAN";
    public final static String PERSONAL_CENTER_SETTING = "PERSONAL_CENTER_SETTING";
    public final static String PERSONAL_CENTER_HELP = "PERSONAL_CENTER_HELP";
    public final static String PERSONAL_CENTER_ABOUT_RUPIAH = "PERSONAL_CENTER_ABOUT_RUPIAH";
    public final static String PERSONAL_CENTER_HOTLINE = "PERSONAL_CENTER_HOTLINE";
    // 4.online (客服)
//
//     5.Loaning（借款信息补充界面）
//     	  <0>进入事件               IN_LOANING_FRAGMENT
//     	  <1>点击类事件  绑卡       BIND_BANK_CARD
//            <2>点击类事件  借款原因   BORROW_REANSON
//            <3>点击类事件  提交申请   APPLY_LOAN
    public final static String IN_LOANING_FRAGMENT = "IN_LOANING_FRAGMENT";
    public final static String BIND_BANK_CARD = "LOANING_BIND_BANK_CARD";
    public final static String BORROW_REANSON = "LOANING_BORROW_REANSON";
    public final static String APPLY_LOANING = "APPLY_LOANING";

    // 6.等待放款
//     	  <0>进入事件               IN_ISSUING_FRAGMENT
//     	  <1>点击类事件  取消申请   CANCEL_REASNON
//
    public final static String IN_ISSUING_FRAGMENT = "IN_ISSUING_FRAGMENT";
    public final static String CANCEL_REASON = "CANCEL_REASON";

    //     7.正常还款
//            <0>进入事件               IN_REPAYMENT_FRAGMENT
//     	  <1>点击类事件  还款       REPAYMENT
//            <2>点击类事件  续期       REPAYMENT_ROLLOVER
//
    public final static String IN_REPAYMENT_FRAGMENT = "IN_REPAYMENT_FRAGMENT";
    public final static String REPAYMENT = "REPAYMENT";
    public final static String REPAYMENT_ROLLOVER = "REPAYMENT_ROLLOVER";
    //     8.逾期界面
//            <0>进入事件     	        IN_EXREPAYMENT_FRAGMENT
//     	  <1>点击类事件  还款	  	EXREPAYMENT_REPAYMENT
//            <2>点击类事件  续期       EXREPAYMENT_ROLLOVER
//
    public final static String IN_EXREPAYMENT_FRAGMENT = "IN_EXREPAYMENT_FRAGMENT";
    public final static String EXREPAYMENT_REPAYMENT = "EXREPAYMENT_REPAYMENT";
    public final static String EXREPAYMENT_ROLLOVER = "EXREPAYMENT_ROLLOVER";

    //     9.续期界面
//            <0>进入事件	            		IN_ROLLOVER_FRAGMENT
//     	  <1>点击类事件  返回				BACK
//            <2>点击类事件  消息				MESSAGE
//            <3>点击类事件  续期手续费明细		ROLLOVER_FEE
//            <4>点击类事件  续期期限（7天）	ROLLOVER_PERIOD_7
//            <5>点击类事件  续期期限（14天）   ROLLOVER_PERIOD_14
//            <6>点击类事件  申请续期           ROLLOVER_APPLY
    public final static String IN_ROLLOVER_FRAGMENT = "IN_ROLLOVER_FRAGMENT";
    public final static String ROLLOVER_BACK = "ROLLOVER_BACK";
    public final static String ROLLOVER_FEE_TIP = "ROLLOVER_FEE_TIP";
    public final static String ROLLOVER_PERIOD_7 = "ROLLOVER_PERIOD_7";
    public final static String ROLLOVER_PERIOD_14 = "ROLLOVER_PERIOD_14";
    public final static String ROLLOVER_APPLY = "ROLLOVER_APPLY";

    /**
     * 补建界面（补充个人信息）：Fragment
     * 1.进入事件
     * 2.提交补建信息
     * 3.补建联系信息
     * 4.补建相册信息
     * 5.补建银行信息
     * 6.补建工作信息
     */
    public final static String IN_BUILD_UP_FRAGMENT = "IN_BUILD_UP_FRAGMENT";
    public final static String SUBMIT_BUILD_UP = "SUBMIT_BUILD_UP";
    public final static String BUILD_UP_CONTACT_INFO = "BUILD_UP_CONTACT_INFO";
    public final static String BUILD_UP_KPT_INFO = "BUILD_UP_KPT_INFO";
    public final static String BUILD_UP_BANK_INFO = "BUILD_UP_BANK_INFO";
    public final static String BUILD_UP_WORK_INFO = "BUILD_UP_WORK_INFO";

    //    我的借款界面：（显示所有的借款记录）    Activity
//            <1>进入事件                      IN_MY_LOAN_ACTIVITY
//          <2>点击类事件  返回              CLICK_BACK
//            <3>退出                          BACK
    public final static String IN_MY_LOAN_ACTIVITY = "IN_MY_LOAN_ACTIVITY";
    public final static String MY_LOAN_CLICK_BACK = "MY_LOAN_CLICK_BACK";
    public final static String MY_LOAN_BACK = "MY_LOAN_BACK";

    //    安全设置界面：  Activity
//            <1>进入事件                    IN_MY_SET_ACTIVITY
//            <2>点击类事件  返回            CLICK_BACK
//            <3>退出                               BACK
    //        <4>登出                             LOGOUT
    public final static String IN_MY_SET_ACTIVITY = "IN_MY_SET_ACTIVITY";
    public final static String MY_SET_LOGOUT= "MY_SET_LOGOUT";
    public final static String MY_SET_CLICK_BACK = "MY_SET_CLICK_BACK";
    public final static String MY_SET_BACK = "MY_SET_BACK";
    public final static String MY_SET_GESTURE = "MY_SET_GESTURE";

    //    帮助中心界面：  Activity
//            <1>进入事件                    IN_HELP_ACTIVITY
//          <2>点击类事件  返回            CLICK_BACK
//            <3>退出                        BACK
    public final static String IN_HELP_ACTIVITY = "IN_HELP_ACTIVITY";
    public final static String HELP_CLICK_BACK = "HELP_CLICK_BACK";
    public final static String HELP_BACK = "HELP_BACK";
    public final static String HELP_LOAN = "HELP_LOAN";     //帮助中心点击借款
    public final static String HELP_REPAY = "HELP_REPAY";     //帮助中心点击还款
    public final static String HELP_ITEM = "HELP_ITEM";     //帮助中心点击item
    //    关于软件界面：  Activity
//            <1>进入事件                    IN_ABOUT_ACTIVITY
//          <2>点击类事件  返回            CLICK_BACK
//            <3>退出                        BACK
    public final static String IN_ABOUT_ACTIVITY = "IN_ABOUT_ACTIVITY";
    public final static String ABOUT_CLICK_BACK = "ABOUT_CLICK_BACK";
    public final static String ABOUT_BACK = "ABOUT_BACK";

    //    还款界面：Activity
//            <1>进入事件                    IN_BANK_PAYMENT_ACTIVITY
//          <2>点击类事件  返回            CLICK_BACK
//            <3>点击类事件  信息            MESSAGE
//            <4>退出                        BACK
    public final static String IN_BANK_PAYMENT_ACTIVITY = "IN_BANK_PAYMENT_ACTIVITY";
    public final static String BANK_PAYMENT_CLICK_BACK = "BANK_PAYMENT_CLICK_BACK";
    public final static String BANK_PAYMENT_BACK = "BANK_PAYMENT_BACK";

    //    uploadPictureActivity
//            <1>进入事件                    IN_UPLOAD_PICTURE_ACTIVITY
//          <2>点击类事件  返回            CLICK_BACK
//            <3>点击类事件  上传KTP         UPLOAD_KTP
//            <4>点击类事件  上传工作照      UPLOAD_WORK
//            <5>点击类事件  提交            UPLOAD
//            <6>退出                        BACK
    public final static String IN_UPLOAD_PICTURE_ACTIVITY = "IN_UPLOAD_PICTURE_ACTIVITY";
    public final static String UPLOAD_PICTURE_ACTIVITY_CLICK_BACK = "UPLOAD_PICTURE_CLICK_BACK";
    public final static String UPLOAD_KTP = "UPLOAD_KTP";
    public final static String UPLOAD_WORK = "UPLOAD_WORK";
    public final static String UPLOAD_KTP_WORK = "UPLOAD_KTP_WORK";
    public final static String UPLOAD_PICTURE_ACTIVITY_BACK = "UPLOAD_PICTURE_BACK";

    //    联系人信息Activity
//            <1>进入事件                    IN_CONTACT_ACTIVITY
//          <2>点击类事件  返回            CLICK_BACK
//            <3>点击类事件  选择关系1       SELECT_CONTACT1
//            <4>点击类事件  选择联系人1（跳到系统的联系人界面选择） CONTACT1
//            <3>点击类事件  选择关系2       SELECT_CONTACT2
//            <4>点击类事件  选择联系人2（跳到系统的联系人界面选择） CONTACT2
//            <5>点击类事件  提交            CONFIRM
//            <6>退出                        BACK
    public final static String IN_CONTACT_ACTIVITY = "IN_CONTACT_ACTIVITY";
    public final static String CONTACT_CLICK_BACK = "CONTACT_CLICK_BACK";
    public final static String SELECT_CONTACT1 = "SELECT_CONTACT1";
    public final static String CONTACT1 = "CONTACT1_TYPE";
    public final static String SELECT_CONTACT2 = "SELECT_CONTACT2";
    public final static String CONTACT2 = "CONTACT2_TYPE";
    public final static String CONFIRM = "CONFIRM_CONTACT";
    public final static String CONTACT_BACK = "CONTACT_BACK";

    //    个人信息Activity
//            <1>进入事件                   IN_PERSONAL_INFORMATION_ACTIVITY
//          <2>点击类事件  返回           CLICK_BACK
//            <3>输入类事件  姓名           INPUT_NAME
//            <4>输入类事件  ktp            INPUT_KTP
//            <5>输入类事件  母亲婚前名字   INPUT_MATHER_NAME_BEFORE_MARRYED
//          <6>点击类事件  性别           GENDER
//            <7>点击类事件  学历           EDUCATION
//            <8>点击类事件  是否已婚       ISMARRYED
//            <9>点击类事件  孩子个数       CHILDS_NUMBER
//            <10>点击类事件  住址省份       ADDRESS_PROVINCE
//            <11>点击类事件  家庭地址所在省份   FAMILY_PROVINCE
//            <12>点击类事件  家庭地址所在城市  RAMILY_CITY
//            <13>点击类事件  家庭地址所在区    FAMILY_AREA
//            <14>点击类事件  家庭地址所在村    FAMILY_STREET
//            <15>输入类事件  详细家庭地址      INPUT_FRMILY_ADDRESS
//            <16>点击类事件  居住时间          STAY_TIME
//            <17>点击类事件  提交              INFOR_CONFIRM
//            <18>退出                          BACK
    public final static String IN_PERSONAL_INFORMATION_ACTIVITY = "IN_PERSONAL_INFORMATION_ACTIVITY";
    public final static String PERSONAL_INFORMATION_CLICK_BACK = "PERSONAL_INFORMATION_CLICK_BACK";
    public final static String INPUT_NAME = "INPUT_NAME";
    public final static String INPUT_KTP = "INPUT_KTP";
    public final static String INPUT_MATHER_NAME_BEFORE_MARRYED = "INPUT_MATHER_NAME_BEFORE_MARRYED";
    public final static String GENDER = "GENDER";
    public final static String EDUCATION = "EDUCATION";
    public final static String ISMARRYED = "ISMARRYED";
    public final static String CHILDS_NUMBER = "CHILDS_NUMBER";
    public final static String FAMILY_PROVINCE = "FAMILY_PROVINCE";
    public final static String FAMILY_CITY = "FAMILY_CITY";
    public final static String FAMILY_STREET = "FAMILY_STREET";
    public final static String FAMILY_AREA = "FAMILY_AREA";
    public final static String INPUT_FAMILY_ADDRESS = "INPUT_FAMILY_ADDRESS";
    public final static String STAY_TIME = "STAY_TIME";
    public final static String INFOR_CONFIRM = "PERSONAL_INFORMATION_CONFIRM";
    public final static String PERSONAL_INFORMATION_BACK = "PERSONAL_INFORMATION_BACK";
    //    工作信息Activity
//            <1>进入事件                       IN_WORK_INFORMATION_ACTIVITY
//          <2>点击类事件  返回               CLICK_BACK
//            <4>点击类事件  职业               JOB
//            <5>点击类事件  月收入             INCOME
//            <6>输入类事件  公司名字           COMPANY_NAME
//            <7>点击类事件  公司所在省份       COMPANY_PROVINCE
//            <8>点击类事件  公司所在城市       COMPANY_CITY
//            <9>点击类事件  公司所在所在区     COMPANY_AREA
//            <10>点击类事件  公司所在所在村    COMPANY_STREET
//            <11>输入类事件  公司详细地址      COMPANY_ADDRESS
//            <12>点击类事件  区号              DESCRIPTION
//            <13>输入类事件  公司电话号码      COMPANY_TELPHONE
//            <14>点击类事件  提交              WORK_CONFIRM
//            <15>退出                          BACK
    public final static String IN_WORK_INFORMATION_ACTIVITY = "IN_WORK_INFORMATION_ACTIVITY";
    public final static String WORK_INFORMATION_CLICK_BACK = "WORK_INFORMATION_CLICK_BACK";
    public final static String JOB = "JOB";
    public final static String INCOME = "PROFESSIONAL_INCOME";
    public final static String INPUT_COMPANY_NAME = "INPUT_COMPANY_NAME";
    public final static String COMPANY_PROVINCE = "COMPANY_PROVINCE";
    public final static String COMPANY_CITY = "COMPANY_CITY";
    public final static String COMPANY_AREA = "COMPANY_AREA";
    public final static String COMPANY_STREET = "COMPANY_STREET";
    public final static String INPUT_COMPANY_ADDRESS = "INPUT_COMPANY_ADDRESS";
    public final static String DESCRIPTION = "DESCRIPTION";
    public final static String INPUT_COMPANY_TELPHONE = "INPUT_COMPANY_TELEPHONE";
    public final static String WORK_CONFIRM = "WORK_INFORMATION_CONFIRM";
    public final static String WORK_INFORMATION_BACK = "WORK_INFORMATION_BACK";
    //bindBankCardActivity
//      <1>进入事件                    IN_BIND_BANK_CARD_ACTIVITY
//          <2>点击类事件  返回            CLICK_BACK
//            <3>点击类事件  选择银行       SELECT_BANK
//            <4>输入类事件  银行账户名称（跳到系统的联系人界面选择） YOUR_BANK_ACCOUNT_NAME
//            <5>输入类事件  银行卡卡号       BANK_CARD_NUMBER
//            <6>点击类事件  提交            CONFIRM
//            <7>退出                        BACK
    public final static String IN_BIND_BANK_CARD_ACTIVITY = "IN_BIND_BANK_CARD_ACTIVITY";
    public final static String BIND_BANK_CARD_CLICK_BACK = "BIND_BANK_CARD_CLICK_BACK";
    public final static String SELECT_BANK = "SELECT_BANK";
    public final static String INPUT_YOUR_BANK_ACCOUNT_NAME = "INPUT_YOUR_BANK_ACCOUNT_NAME";
    public final static String INPUT_BANK_CARD_NUMBER = "INPUT_BANK_CARD_NUMBER";
    public final static String BIND_BANK_CARD_CONFIRM = "BIND_BANK_CARD_CONFIRM";
    public final static String BIND_BANK_CARD_BACK = "BIND_BANK_CARD_BACK";
    //permission
    public final static String PERMISSION_REJECT_LOCATION = "PERMISSION_REJECT_LOCATION";   //用户拒绝地理位置权限
    public final static String PERMISSION_REJECT_PHONE = "PERMISSION_REJECT_PHONE";     //用户拒绝电话权限
    public final static String PERMISSION_REJECT_CONTACT = "PERMISSION_REJECT_CONTACT";     //用户拒绝联系人权限
    public final static String PERMISSION_REJECT_SMS = "PERMISSION_REJECT_SMS";     //用户拒绝短信权限
    public final static String PERMISSION_REJECT_READ_WRITE = "PERMISSION_REJECT_READ_WRITE";     //用户拒绝读写权限


    public final static String EVENT_CLICK_LOAN_TAB = "event_click_loan_tab";   //点击借款tab
    public final static String EVENT_CLICK_CERTIFICATION_TAB = "event_click_certification_tab";     //点击资料tab
    public final static String EVENT_CLICK_MY_TAB = "event_click_my_tab";       //点击我的tab
    public final static String EVENT_CLICK_IM_TAB = "event_click_im_tab";       //点击客服tab

    public final static String EVENT_CLICK_ACCOUNT_A = "event_click_account_a";     //点击第一个贷款额
    public final static String EVENT_CLICK_ACCOUNT_B = "event_click_account_b";     //点击第二个贷款额
    public final static String EVENT_CLICK_DAYS_A = "event_click_days_a";     //点击第一个贷款天数
    public final static String EVENT_CLICK_DAYS_B = "event_click_days_b";     //点击第二个贷款天数
    public final static String EVENT_CLICK_LOAN_BUTTON = "event_click_loan_button";     //点击马上借款按钮

    //main activity
    public final static String EVENT_CLICK_MAIN_MESSAGE_LIST = "event_click_main_message_list";     //点击消息列表按钮
    //login
    public final static String EVENT_CLICK_LOGIN_OBTAIN_CODE = "event_click_login_obtain_code";       //登录页面点击获取验证码
    public final static String EVENT_CLICK_LOGIN_REFRESH = "event_click_login_refreh";       //登录页面点击刷新按钮
    public final static String EVENT_CLICK_LOGIN_LOGIN = "event_click_login_login";       //登录页面点击登录按钮
    public final static String EVENT_CLICK_LOGIN_VIOCE_MESSAGE ="event_click_login_vioce_message";
    //my
    public final static String EVENT_CLICK_MY_LOAN = "event_click_my_loan";     //我的页面，点击我的借款
    public final static String EVENT_CLICK_MY_INVITED = "event_click_my_invited";       //我的页面，点击邀请好友
    public final static String EVENT_CLICK_MY_ABOUT = "event_click_my_about";       //我的页面，点击关于supiash plus
    public final static String EVENT_CLICK_MY_HELP = "event_click_my_help";       //我的页面，点击帮助
    public final static String EVENT_CLICK_MY_HOTLINE = "event_click_my_hotline";       //我的页面，点击热线
    public final static String EVENT_CLICK_MY_SETTING = "event_click_my_setting";       //我的页面，点击设置
    public final static String EVENT_CLICK_MY_LOGIN = "event_click_my_login";       //我的页面，点击登录图标
    //loan
    public final static String EVENT_CLICK_LOAN_CANCEL = "event_click_loan_cancel";     //借款页面点击取消按钮
    public final static String EVENT_CLICK_LOAN_VIDEO = "event_click_loan_video";       //借款页面点击录制视频
    //repay expiry
    public final static String EVENT_CLICK_REPAY_EXPIRY_IWANT = "event_click_repay_iwant";     //预期还款页面点击我要还款
    //repay
    public final static String EVENT_CLICK_REPAY_DOWN = "event_click_repay_down";       //还款页面点击向下图标
    public final static String EVENT_CLICK_REPAY_IWANT = "event_click_repay_iwant";     //还款页面点击我要还款
    //certification
    public final static String EVENT_CLICK_CERTIFICATION_SUBMIT = "event_click_certification_submit";       //点击认证提交按钮
    public final static String EVENT_CLICK_PARENTS_NAME = "event_click_parents_name";       //输入父母姓名
    public final static String EVENT_CLICK_FRIEND_NAME = "event_click_friend_name";     //输入朋友姓名
    public final static String EVENT_CLICK_CONTACT_SUBMIT = "event_click_contact_submit";       //点击联系人信息提交按钮
    public final static String EVENT_CLICK_CONTACT_BACK = "event_click_contact_back";       //输入联系人页面，点击返回按钮
    //about
    public final static String EVENT_CLICK_ABOUT_BACK = "event_click_about_back";       //关于页面点击返回按钮
    //hotline
    public final static String EVENT_CLICK_HOTLINE_BACK = "event_click_hotline_back";       //热线界面点击返回按钮
    public final static String EVENT_CLICK_HOTLINE_CANCEL = "event_click_hotline_cancel";       //取消拨打热线电话
    public final static String EVENT_CLICK_HOTLINE_ENSURE = "event_click_hotline_cancel";       //确定拨打热线电话
    //help center
    public final static String EVENT_CLICK_HELP_LOAN = "event_click_help_loan";     //帮助中心点击借款
    public final static String EVENT_CLICK_HELP_REPAY = "event_click_help_repay";     //帮助中心点击还款
    public final static String EVENT_CLICK_HELP_ITEM = "event_click_help_item";     //帮助中心点击item
    public final static String EVENT_CLICK_HELP_BACK = "event_click_help_back";     //帮助中心点击返回
    //invited
    public final static String EVENT_CLICK_INVITED_BACK = "event_click_invited_back";       //我的邀请页面点击返回
    //my loan
    public final static String EVENT_CLICK_MYLOAN_BACK = "event_click_myloan_back";     //我的贷款页面点击返回
    //setting
    public final static String EVENT_CLICK_SETTING_BACK = "event_click_setting_back";       //设置页面点击返回
    public final static String EVENT_CLICK_SETTING_LOGOUT = "event_click_setting_logout";       //设置页面点击登出
    public final static String EVENT_CLICK_SETTING_GESTURE = "event_click_setting_gesture";   //设置手势密码页面点击
    //personal
    public final static String EVENT_CLICK_PERSONAL_SUBMIT = "event_click_personal_submit";     //个人信息页面点击提交按钮
    public final static String EVENT_CLICK_PERSONAL_BACK = "event_click_personal_back";     //个人信息页面点击返回
    //video
    public final static String EVENT_CLICK_VIDEO_RERECORD = "event_click_video_rerecord";       //视频播放页面点击重录按钮
    public final static String EVENT_CLICK_VIDEO_PLAY = "event_click_video_play";       //视频播放页面点击播放按钮
    public final static String EVENT_CLICK_VIDEO_UPLOAD = "event_click_video_upload";       //视频播放页面点击上传按钮
    public final static String EVENT_CLICK_VIDEO_CANCEL = "event_click_video_cancel";       //录制视频页面点击取消按钮
    public final static String EVENT_CLICK_VIDEO_BACK = "event_click_video_back";       //录制视频页面点击返回
    public final static String EVENT_CLICK_VIDEO_START = "event_click_video_start";     //点击开始录制视频
    public final static String EVENT_CLICK_VIDEO_PREVIEW = "event_click_video_preview";     //点击视频预览
    public final static String EVENT_CLICK_VIDEO_RERECORD_V = "event_click_video_rerecord_v";       //视频录制页面点击重录按钮
    //photo
    public final static String EVENT_CLICK_PHOTO_KTP = "event_click_photo_ktp";     //点击拍摄KTP证件照
    public final static String EVENT_CLICK_PHOTO_WORKCARD = "event_click_photo_word_card";      //点击拍摄工作证
    public final static String EVENT_CLICK_PHOTO_SUBMIT = "event_click_photo_submit";       //点击上传照片
    //job
    public final static String EVENT_CLICK_JOB_SUBMIT = "event_click_job_submit";       //个人专业信息页面点击提交按钮
    public final static String EVENT_CLICK_JOB_BACK = "event_click_job_back";       //个人专业信息页面点击返回
    //permission
    public final static String EVENT_PERMISSION_REJECT_LOCATION = "event_permission_reject_location";   //用户拒绝地理位置权限
    public final static String EVENT_PERMISSION_REJECT_PHONE = "event_permission_reject_phone";     //用户拒绝电话权限
    public final static String EVENT_PERMISSION_REJECT_CONTACT = "event_permission_reject_contact";     //用户拒绝联系人权限
    public final static String EVENT_PERMISSION_REJECT_SMS = "event_permission_reject_sms";     //用户拒绝短信权限

    //others
    public final static String EVENT_KTP_FOCUS = "event_ktp_focus";     //开始编辑KTP
    public final static String EVENT_KTP_UNFOCUS = "event_ktp_unfocus";     //结束编辑KTP
    public final static  String EVENT_PERSONALINFO_START_TIME ="event_personalinfo_start_time";//开始输入的时间
    public final static  String EVENT_PERSONALINFO_END_TIME ="event_personalinfo_end_time";//开始输入的时间

    public final static  String EVENT_PROFESSIONINFO_START_TIME ="event_professioninfo_start_time";//开始输入的时间
    public final static  String EVENT_PROFESSIONINFO_END_TIME ="event_professioninfo_end_time";//开始输入的时间


}
