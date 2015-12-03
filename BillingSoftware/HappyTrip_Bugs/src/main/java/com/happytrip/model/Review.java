package com.happytrip.model;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the reviews database table.
 * 
 */
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	private long reviewId;

	private Date createdDate;

	private byte isActive;

	private byte isReportAbused;

	private String reviewText;

	//bi-directional many-to-many association to Hotel
	private Hotel hotelReviewed;

	//bi-directional many-to-one association to User
	private User reviewer;

    public Review() {
    }

	public long getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public Date getCreatedDate() {
		return (Date)this.createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = (Date)createdDate.clone();
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public byte getIsReportAbused() {
		return this.isReportAbused;
	}

	public void setIsReportAbused(byte isReportAbused) {
		this.isReportAbused = isReportAbused;
	}

	public String getReviewText() {
		return this.reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Hotel getHotelReviewed() {
		return this.hotelReviewed;
	}

	public void setHotelsReviewed(Hotel hotelReviewed) {
		this.hotelReviewed = hotelReviewed;
	}
	
	public User getReviewer() {
		return this.reviewer;
	}

	public void setReviewer(User user) {
		this.reviewer = user;
	}
	
	
}