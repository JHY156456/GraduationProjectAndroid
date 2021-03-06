package com.mobitant.bestfood.item;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderCheckItem implements Serializable {
    @SerializedName("info_first_image_file_name")
    private String infoFirstImageFilename;
    @SerializedName("seller_nickname")
    private String postNickName; // 구매화면 전환해서 정보를 보여주기위함
    @SerializedName("post_member_icon_file_name")
    private String postMemberIconFilename;
    @SerializedName("info_title") private String infoTitle;
    private String infoContent;
    private String postRegisterDate;
    private String postPhoneNumber;
    private String postRealName;
    @SerializedName("card_number")
    private String cardNumber;
    private int postSeq;
    private int postMemberSeq;
    private int buyerMemberSeq;
    @SerializedName("buyer_nickname")
    private String buyerMemberNickName;
    @SerializedName("card_holder")
    private String CardHorder;
    private String created_at;
    private String updated_at;
    @SerializedName("post_price")
    private String postPrice;

    public String getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(String postPrice) {
        this.postPrice = postPrice;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPostMemberSeq() {
        return postMemberSeq;
    }

    public void setPostMemberSeq(int postMemberSeq) {
        this.postMemberSeq = postMemberSeq;
    }

    public int getBuyerMemberSeq() {
        return buyerMemberSeq;
    }

    public void setBuyerMemberSeq(int buyerMemberSeq) {
        this.buyerMemberSeq = buyerMemberSeq;
    }

    public String getBuyerMemberNickName() {
        return buyerMemberNickName;
    }

    public void setBuyerMemberNickName(String buyerMemberNickName) {
        this.buyerMemberNickName = buyerMemberNickName;
    }

    public String getCardHorder() {
        return CardHorder;
    }

    public void setCardHorder(String cardHorder) {
        CardHorder = cardHorder;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getPostSeq() {
        return postSeq;
    }

    public void setPostSeq(int postSeq) {
        this.postSeq = postSeq;
    }

    @Override
    public String toString() {
        return "OrderCheckItem{" +
                "infoFirstImageFilename='" + infoFirstImageFilename + '\'' +
                ", postNickName='" + postNickName + '\'' +
                ", postMemberIconFilename='" + postMemberIconFilename + '\'' +
                ", infoTitle='" + infoTitle + '\'' +
                ", infoContent='" + infoContent + '\'' +
                ", postRegisterDate='" + postRegisterDate + '\'' +
                ", postPhoneNumber='" + postPhoneNumber + '\'' +
                ", postRealName='" + postRealName + '\'' +
                '}';
    }

    public String getPostRealName() {
        return postRealName;
    }

    public void setPostRealName(String postRealName) {
        this.postRealName = postRealName;
    }

    public String getPostPhoneNumber() {
        return postPhoneNumber;
    }

    public void setPostPhoneNumber(String postPhoneNumber) {
        this.postPhoneNumber = postPhoneNumber;
    }

    public String getPostRegisterDate() {
        return postRegisterDate;
    }

    public void setPostRegisterDate(String postRegisterDate) {
        this.postRegisterDate = postRegisterDate;
    }

    public String getInfoFirstImageFilename() {
        return infoFirstImageFilename;
    }

    public void setInfoFirstImageFilename(String infoFirstImageFilename) {
        this.infoFirstImageFilename = infoFirstImageFilename;
    }

    public String getPostNickName() {
        return postNickName;
    }

    public void setPostNickName(String postNickName) {
        this.postNickName = postNickName;
    }

    public String getPostMemberIconFilename() {
        return postMemberIconFilename;
    }

    public void setPostMemberIconFilename(String postMemberIconFilename) {
        this.postMemberIconFilename = postMemberIconFilename;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }
}
