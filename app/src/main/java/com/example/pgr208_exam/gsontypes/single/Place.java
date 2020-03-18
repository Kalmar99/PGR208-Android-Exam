
package com.example.pgr208_exam.gsontypes.single;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Place {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("blogCount")
    @Expose
    private Long blogCount;
    @SerializedName("externalLink1")
    @Expose
    private String externalLink1;
    @SerializedName("externalLinkDescription1")
    @Expose
    private String externalLinkDescription1;
    @SerializedName("externalLink2")
    @Expose
    private String externalLink2;
    @SerializedName("externalLinkDescription2")
    @Expose
    private String externalLinkDescription2;
    @SerializedName("dieselPrice")
    @Expose
    private Double dieselPrice;
    @SerializedName("dieselPriceUpdatedMs")
    @Expose
    private Long dieselPriceUpdatedMs;
    @SerializedName("gasolinePrice")
    @Expose
    private Long gasolinePrice;
    @SerializedName("gasolinePriceUpdatedMs")
    @Expose
    private Long gasolinePriceUpdatedMs;
    @SerializedName("maxLiftWeightTonnes")
    @Expose
    private Long maxLiftWeightTonnes;
    @SerializedName("allowsExternalContractors")
    @Expose
    private Boolean allowsExternalContractors;
    @SerializedName("canWorkOnOwnBoat")
    @Expose
    private Boolean canWorkOnOwnBoat;
    @SerializedName("canStayOnOwnBoat")
    @Expose
    private Boolean canStayOnOwnBoat;
    @SerializedName("priceBandHighSeason")
    @Expose
    private String priceBandHighSeason;
    @SerializedName("priceBandLowSeason")
    @Expose
    private String priceBandLowSeason;
    @SerializedName("winterCommunity")
    @Expose
    private Boolean winterCommunity;
    @SerializedName("protectionFrom")
    @Expose
    private String protectionFrom;
    @SerializedName("addedMs")
    @Expose
    private Long addedMs;
    @SerializedName("addedBy")
    @Expose
    private String addedBy;
    @SerializedName("addedById")
    @Expose
    private Long addedById;
    @SerializedName("updatedMs")
    @Expose
    private Long updatedMs;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("updatedById")
    @Expose
    private Long updatedById;
    @SerializedName("col")
    @Expose
    private String col;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("mapboxIcon")
    @Expose
    private String mapboxIcon;
    @SerializedName("stars")
    @Expose
    private Long stars;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("images")
    @Expose
    private List<Object> images = null;
    @SerializedName("reviews")
    @Expose
    private List<Object> reviews = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Long getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Long blogCount) {
        this.blogCount = blogCount;
    }

    public String getExternalLink1() {
        return externalLink1;
    }

    public void setExternalLink1(String externalLink1) {
        this.externalLink1 = externalLink1;
    }

    public String getExternalLinkDescription1() {
        return externalLinkDescription1;
    }

    public void setExternalLinkDescription1(String externalLinkDescription1) {
        this.externalLinkDescription1 = externalLinkDescription1;
    }

    public String getExternalLink2() {
        return externalLink2;
    }

    public void setExternalLink2(String externalLink2) {
        this.externalLink2 = externalLink2;
    }

    public String getExternalLinkDescription2() {
        return externalLinkDescription2;
    }

    public void setExternalLinkDescription2(String externalLinkDescription2) {
        this.externalLinkDescription2 = externalLinkDescription2;
    }

    public Double getDieselPrice() {
        return dieselPrice;
    }

    public void setDieselPrice(Double dieselPrice) {
        this.dieselPrice = dieselPrice;
    }

    public Long getDieselPriceUpdatedMs() {
        return dieselPriceUpdatedMs;
    }

    public void setDieselPriceUpdatedMs(Long dieselPriceUpdatedMs) {
        this.dieselPriceUpdatedMs = dieselPriceUpdatedMs;
    }

    public Long getGasolinePrice() {
        return gasolinePrice;
    }

    public void setGasolinePrice(Long gasolinePrice) {
        this.gasolinePrice = gasolinePrice;
    }

    public Long getGasolinePriceUpdatedMs() {
        return gasolinePriceUpdatedMs;
    }

    public void setGasolinePriceUpdatedMs(Long gasolinePriceUpdatedMs) {
        this.gasolinePriceUpdatedMs = gasolinePriceUpdatedMs;
    }

    public Long getMaxLiftWeightTonnes() {
        return maxLiftWeightTonnes;
    }

    public void setMaxLiftWeightTonnes(Long maxLiftWeightTonnes) {
        this.maxLiftWeightTonnes = maxLiftWeightTonnes;
    }

    public Boolean getAllowsExternalContractors() {
        return allowsExternalContractors;
    }

    public void setAllowsExternalContractors(Boolean allowsExternalContractors) {
        this.allowsExternalContractors = allowsExternalContractors;
    }

    public Boolean getCanWorkOnOwnBoat() {
        return canWorkOnOwnBoat;
    }

    public void setCanWorkOnOwnBoat(Boolean canWorkOnOwnBoat) {
        this.canWorkOnOwnBoat = canWorkOnOwnBoat;
    }

    public Boolean getCanStayOnOwnBoat() {
        return canStayOnOwnBoat;
    }

    public void setCanStayOnOwnBoat(Boolean canStayOnOwnBoat) {
        this.canStayOnOwnBoat = canStayOnOwnBoat;
    }

    public String getPriceBandHighSeason() {
        return priceBandHighSeason;
    }

    public void setPriceBandHighSeason(String priceBandHighSeason) {
        this.priceBandHighSeason = priceBandHighSeason;
    }

    public String getPriceBandLowSeason() {
        return priceBandLowSeason;
    }

    public void setPriceBandLowSeason(String priceBandLowSeason) {
        this.priceBandLowSeason = priceBandLowSeason;
    }

    public Boolean getWinterCommunity() {
        return winterCommunity;
    }

    public void setWinterCommunity(Boolean winterCommunity) {
        this.winterCommunity = winterCommunity;
    }

    public String getProtectionFrom() {
        return protectionFrom;
    }

    public void setProtectionFrom(String protectionFrom) {
        this.protectionFrom = protectionFrom;
    }

    public Long getAddedMs() {
        return addedMs;
    }

    public void setAddedMs(Long addedMs) {
        this.addedMs = addedMs;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Long getAddedById() {
        return addedById;
    }

    public void setAddedById(Long addedById) {
        this.addedById = addedById;
    }

    public Long getUpdatedMs() {
        return updatedMs;
    }

    public void setUpdatedMs(Long updatedMs) {
        this.updatedMs = updatedMs;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMapboxIcon() {
        return mapboxIcon;
    }

    public void setMapboxIcon(String mapboxIcon) {
        this.mapboxIcon = mapboxIcon;
    }

    public Long getStars() {
        return stars;
    }

    public void setStars(Long stars) {
        this.stars = stars;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public void setReviews(List<Object> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("type", type).append("name", name).append("lat", lat).append("lon", lon).append("countryCode", countryCode).append("comments", comments).append("meta", meta).append("blogCount", blogCount).append("externalLink1", externalLink1).append("externalLinkDescription1", externalLinkDescription1).append("externalLink2", externalLink2).append("externalLinkDescription2", externalLinkDescription2).append("dieselPrice", dieselPrice).append("dieselPriceUpdatedMs", dieselPriceUpdatedMs).append("gasolinePrice", gasolinePrice).append("gasolinePriceUpdatedMs", gasolinePriceUpdatedMs).append("maxLiftWeightTonnes", maxLiftWeightTonnes).append("allowsExternalContractors", allowsExternalContractors).append("canWorkOnOwnBoat", canWorkOnOwnBoat).append("canStayOnOwnBoat", canStayOnOwnBoat).append("priceBandHighSeason", priceBandHighSeason).append("priceBandLowSeason", priceBandLowSeason).append("winterCommunity", winterCommunity).append("protectionFrom", protectionFrom).append("addedMs", addedMs).append("addedBy", addedBy).append("addedById", addedById).append("updatedMs", updatedMs).append("updatedBy", updatedBy).append("updatedById", updatedById).append("col", col).append("icon", icon).append("mapboxIcon", mapboxIcon).append("stars", stars).append("banner", banner).append("images", images).append("reviews", reviews).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(maxLiftWeightTonnes).append(col).append(gasolinePrice).append(icon).append(mapboxIcon).append(lon).append(type).append(winterCommunity).append(updatedMs).append(externalLinkDescription2).append(priceBandLowSeason).append(externalLinkDescription1).append(reviews).append(countryCode).append(dieselPrice).append(addedMs).append(externalLink1).append(externalLink2).append(allowsExternalContractors).append(id).append(canWorkOnOwnBoat).append(lat).append(blogCount).append(images).append(comments).append(updatedBy).append(canStayOnOwnBoat).append(addedBy).append(banner).append(protectionFrom).append(stars).append(updatedById).append(dieselPriceUpdatedMs).append(meta).append(name).append(priceBandHighSeason).append(gasolinePriceUpdatedMs).append(addedById).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Place) == false) {
            return false;
        }
        Place rhs = ((Place) other);
        return new EqualsBuilder().append(maxLiftWeightTonnes, rhs.maxLiftWeightTonnes).append(col, rhs.col).append(gasolinePrice, rhs.gasolinePrice).append(icon, rhs.icon).append(mapboxIcon, rhs.mapboxIcon).append(lon, rhs.lon).append(type, rhs.type).append(winterCommunity, rhs.winterCommunity).append(updatedMs, rhs.updatedMs).append(externalLinkDescription2, rhs.externalLinkDescription2).append(priceBandLowSeason, rhs.priceBandLowSeason).append(externalLinkDescription1, rhs.externalLinkDescription1).append(reviews, rhs.reviews).append(countryCode, rhs.countryCode).append(dieselPrice, rhs.dieselPrice).append(addedMs, rhs.addedMs).append(externalLink1, rhs.externalLink1).append(externalLink2, rhs.externalLink2).append(allowsExternalContractors, rhs.allowsExternalContractors).append(id, rhs.id).append(canWorkOnOwnBoat, rhs.canWorkOnOwnBoat).append(lat, rhs.lat).append(blogCount, rhs.blogCount).append(images, rhs.images).append(comments, rhs.comments).append(updatedBy, rhs.updatedBy).append(canStayOnOwnBoat, rhs.canStayOnOwnBoat).append(addedBy, rhs.addedBy).append(banner, rhs.banner).append(protectionFrom, rhs.protectionFrom).append(stars, rhs.stars).append(updatedById, rhs.updatedById).append(dieselPriceUpdatedMs, rhs.dieselPriceUpdatedMs).append(meta, rhs.meta).append(name, rhs.name).append(priceBandHighSeason, rhs.priceBandHighSeason).append(gasolinePriceUpdatedMs, rhs.gasolinePriceUpdatedMs).append(addedById, rhs.addedById).isEquals();
    }

}
