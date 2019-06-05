package com.ubisys.uav.RetrofitConfig;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by liulinjie on 14/03/2017.
 * api返回值
 */

public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -5053125710481712211L;

    @SerializedName("code")
    private String code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("body")
    private T body;

    @SerializedName("weCharSign")
    private WeCharSign weCharSign;

    @SerializedName("aliPaySign")
    private String aliPaySign;

    @SerializedName("upacp")
    private String upacp;

    public boolean isSuccessful() {
        if (TextUtils.isEmpty(code)) {
            return false;
        }
        try {
            return Integer.parseInt(code) == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public WeCharSign getWeCharSign() {
        return weCharSign;
    }

    public void setWeCharSign(WeCharSign weCharSign) {
        this.weCharSign = weCharSign;
    }

    public String getAliPaySign() {
        return aliPaySign;
    }

    public void setAliPaySign(String aliPaySign) {
        this.aliPaySign = aliPaySign;
    }

    public String getUpacp() {
        return upacp;
    }

    public void setUpacp(String upacp) {
        this.upacp = upacp;
    }

    public static class WeCharSign {

        private String appId;
        private String nonceStr;
        @SerializedName("package")
        private String packageX;
        private String paySign;
        private String timeStamp;
        private String prepay_id;
        private String partnerId;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }
    }
}
