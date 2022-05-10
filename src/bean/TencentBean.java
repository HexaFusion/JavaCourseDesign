package bean;

import com.google.gson.annotations.SerializedName;

public class TencentBean {

    @SerializedName("Reply")
    private String Reply;
    @SerializedName("Confidence")
    private double Confidence;
    @SerializedName("RequestId")
    private String RequestId;

    public String getReply() {
        Reply = Reply.replaceAll("[小][龙][女]","李昊岩");
        Reply = Reply.replaceAll("[腾][讯]","");
        return Reply;
    }

    public void setReply(String Reply) {
        this.Reply = Reply;
    }

    public double getConfidence() {
        return Confidence;
    }

    public void setConfidence(double Confidence) {
        this.Confidence = Confidence;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String RequestId) {
        this.RequestId = RequestId;
    }
}
