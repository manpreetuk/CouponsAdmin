package com.daakyou.pojo;

public class couponinsert {
   String couppon,title,offer,coupontype;

    public couponinsert()
    {

    }

    public couponinsert(String title,String offer,String coupontype,String couppon)
    {
        this.offer=offer;
        this.title=title;
        this.coupontype=coupontype;
        this.couppon=couppon;
    }

    public String getTitle() {
        return title;
    }

    public String getOffer() {
        return offer;
    }

    public String getCoupontype() {
        return coupontype;
    }

    public String getCouppon() {
        return couppon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoupontype(String coupontype) {
        this.coupontype = coupontype;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }
}
