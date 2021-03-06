package com.mobitant.bestfood.item;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시글 정보를 저장하는 객체
 */
@org.parceler.Parcel
public class FoodInfoItem {
    public int seq;
    @SerializedName("member_seq") public int memberSeq;
    public String name;
    public String tel;
    public String description;
    public String post_nickname;
    private String sell_price;
    public String os;
    public int reply;
    public int commentCount;
    public int hits;
    public int like,dislike,blame,del,imageNumber;
    public int post_category;
    @SerializedName("post_member_icon_filename") public String postMemberIconFilename;
    @SerializedName("reg_date") public String regDate;
    @SerializedName("created_at") private String createdAt;
    @SerializedName("post_updated_datetime") public String updatedDate;
    @SerializedName("comments") private ArrayList<NotificationCommentItem> commentItems;
    @SerializedName("is_keep") public boolean isKeep;
    @SerializedName("image_filename") public String imageFilename;
    @SerializedName("total_image_filename") public ArrayList<ImageItem> totalImageFilename = new ArrayList<>();
    @SerializedName("_id") public String id;
    @SerializedName("post_tag")private ArrayList<String> postTag = new ArrayList<>();



    public void addPostTag(String addValue){
        this.postTag.add(addValue);
    }
    public ArrayList<String> getPostTag() {
        return postTag;
    }

    public void setPostTag(ArrayList<String> postTag) {
        this.postTag = postTag;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<NotificationCommentItem> getCommentItems() {
        return commentItems;
    }

    public void setCommentItems(ArrayList<NotificationCommentItem> commentItems) {
        this.commentItems = commentItems;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    @Override
    public String toString() {
        return "FoodInfoItem{" +
                "seq=" + seq +
                ", memberSeq=" + memberSeq +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", description='" + description + '\'' +
                ", post_nickname='" + post_nickname + '\'' +
                ", os='" + os + '\'' +
                ", reply=" + reply +
                ", commentCount=" + commentCount +
                ", hits=" + hits +
                ", like=" + like +
                ", dislike=" + dislike +
                ", blame=" + blame +
                ", del=" + del +
                ", imageNumber=" + imageNumber +
                ", post_category=" + post_category +
                ", postMemberIconFilename='" + postMemberIconFilename + '\'' +
                ", regDate='" + regDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", commentItems=" + commentItems +
                ", isKeep=" + isKeep +
                ", imageFilename='" + imageFilename + '\'' +
                ", totalImageFilename=" + totalImageFilename +
                ", id='" + id + '\'' +
                '}';
    }
}
