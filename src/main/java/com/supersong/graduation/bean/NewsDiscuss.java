package com.supersong.graduation.bean;

import java.util.Date;

public class NewsDiscuss {
    private String id;
    private String newsId;
    private String commentTo;
    private String toComment;
    private Date createTime;
    private String content;

    /***********扩展变量***********/
    private String toCommentName;
    private String commentToName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(String commentTo) {
        this.commentTo = commentTo;
    }

    public String getCommentToName() {
        return commentToName;
    }

    public void setCommentToName(String commentToName) {
        this.commentToName = commentToName;
    }

    public String getToComment() {
        return toComment;
    }

    public void setToComment(String toComment) {
        this.toComment = toComment;
    }

    public String getToCommentName() {
        return toCommentName;
    }

    public void setToCommentName(String toCommentName) {
        this.toCommentName = toCommentName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
