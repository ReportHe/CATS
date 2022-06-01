package entity;

import java.util.Date;

/**
 * Description：信息类
 * Date:2022/5/21 16:57
 **/
public class Msg {
    private String senderName;
    private String senderId;
    private String content;
    private String time;

    public Msg(String senderName, String senderId, String content, String time) {
        this.senderName = senderName;
        this.senderId = senderId;
        this.content = content;
        this.time = time;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //重写toString方法

    @Override
    public String toString() {
        return "<== " + time + " ==>" + "(" + "合法签名" + ")" + "\n" +
                "[" + senderName + "]" +
                "(" + senderId + ")" + "说：" +
                content;
    }

    public static void main(String[] args) {
        System.out.println(new Msg("张三","309501908","大家好！", "2022-5-21-12:20"));
    }
}
