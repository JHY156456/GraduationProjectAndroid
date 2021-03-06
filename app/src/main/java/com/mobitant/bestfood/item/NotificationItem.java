package com.mobitant.bestfood.item;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NotificationItem {
    public int seq;
    private String title;
    private String contents;

    private String created_at;
    private String updated_at;
    @SerializedName("comments") private ArrayList<NotificationCommentItem> commentItems;
    public String name;
    public String tel;
    public String description;
    public String post_nickname;
    public String postMemberIconFilename;
    @SerializedName("_id") public String id;
    @SerializedName("image_filename") public String imageFilename;
    @SerializedName("total_image_filename") public ArrayList<ImageItem> totalImageFilename = new ArrayList<>();
    @SerializedName("writer") private NotiWriterItem notiWriter;


    public NotiWriterItem getNotiWriter() {
        return notiWriter;
    }

    public void setNotiWriter(NotiWriterItem notiWriter) {
        this.notiWriter = notiWriter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }



    public String getCreate_at() {
        return created_at;
    }

    public void setCreate_at(String create_at) {
        this.created_at = create_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public ArrayList<NotificationCommentItem> getCommentItems() {
        return commentItems;
    }

    public void setCommentItems(ArrayList<NotificationCommentItem> commentItems) {
        this.commentItems = commentItems;
    }


}
