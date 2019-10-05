package com.qfedu.entry;

import java.util.Date;

public class Community {
    private int id;
    private String communityName;
    private Date createTime;
    private String communityDesc;
    private String videoImgUrl;
    private String playNum;
    private String userId;

    public Community() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCommunityDesc() {
        return communityDesc;
    }

    public void setCommunityDesc(String communityDesc) {
        this.communityDesc = communityDesc;
    }

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    public String getPlayNum() {
        return playNum;
    }

    public void setPlayNum(String playNum) {
        this.playNum = playNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", communityName='" + communityName + '\'' +
                ", createTime=" + createTime +
                ", communityDesc='" + communityDesc + '\'' +
                ", videoImgUrl='" + videoImgUrl + '\'' +
                ", playNum='" + playNum + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
