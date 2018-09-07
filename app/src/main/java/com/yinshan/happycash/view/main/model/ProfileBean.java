package com.yinshan.happycash.view.main.model;

import java.io.Serializable;

/**
 * Created by huxin on 2018/9/6.
 */
public class ProfileBean implements Serializable{

    //是否强制更新
    private boolean forceUpgrade;
    private PrfileSettinBean setting;

    public boolean isForceUpgrade() {
        return forceUpgrade;
    }

    public void setForceUpgrade(boolean forceUpgrade) {
        this.forceUpgrade = forceUpgrade;
    }

    public PrfileSettinBean getSetting() {
        return setting;
    }

    public void setSetting(PrfileSettinBean setting) {
        this.setting = setting;
    }


    public class PrfileSettinBean {
        //语音验证码
        private boolean voice_verification_code;
        //活体检测还是视频签约
        private boolean liveness_detection;
        //OCR
        private boolean optical_char_recognition;

        //邀请权限
        private boolean invitation_enable;

        public boolean isInvitationEnable() {
            return invitation_enable;
        }

        public void setInvitationEnable(boolean invitation_enable) {
            this.invitation_enable = invitation_enable;
        }



        public boolean isVoice_verification_code() {
            return voice_verification_code;
        }

        public void setVoice_verification_code(boolean voice_verification_code) {
            this.voice_verification_code = voice_verification_code;
        }

        public boolean isLiveness_detection() {
            return liveness_detection;
        }

        public void setLiveness_detection(boolean liveness_detection) {
            this.liveness_detection = liveness_detection;
        }

        public boolean isOptical_char_recognition() {
            return optical_char_recognition;
        }

        public void setOptical_char_recognition(boolean optical_char_recognition) {
            this.optical_char_recognition = optical_char_recognition;
        }
    }
}
