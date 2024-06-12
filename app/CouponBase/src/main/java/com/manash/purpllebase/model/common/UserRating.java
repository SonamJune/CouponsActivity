
package com.manash.purpllebase.model.common;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRating {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ratings")
    @Expose
    private String ratings;
    @SerializedName("avg_rating")
    @Expose
    private String avgRating;
    @SerializedName("avg_rating_text")
    @Expose
    private String avgRatingText;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The ratings
     */
    public String getRatings() {
        return ratings;
    }

    /**
     * 
     * @param ratings
     *     The ratings
     */
    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    /**
     * 
     * @return
     *     The avgRating
     */
    public String getAvgRating() {
        return avgRating;
    }

    /**
     * 
     * @param avgRating
     *     The avg_rating
     */
    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getAvgRatingText() {
        return avgRatingText;
    }

    public void setAvgRatingText(String avgRatingText) {
        this.avgRatingText = avgRatingText;
    }
}
