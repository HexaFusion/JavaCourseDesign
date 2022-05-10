package bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bean implements Serializable {

    @SerializedName("reqType")
    private String reqType;
    @SerializedName("perception")
    private Perception perception;
    @SerializedName("userInfo")
    private UserInfo userInfo;

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public Perception getPerception() {
        return perception;
    }

    public void setPerception(Perception perception) {
        this.perception = perception;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static class Perception implements Serializable {
        @SerializedName("inputText")
        private InputText inputText;

        public InputText getInputText() {
            return inputText;
        }

        public void setInputText(InputText inputText) {
            this.inputText = inputText;
        }

        public static class InputText implements Serializable {
            @SerializedName("text")
            private String text;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }

    public static class UserInfo implements Serializable {
        @SerializedName("apiKey")
        private String apiKey;
        @SerializedName("userId")
        private String userId;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}