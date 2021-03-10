package com.mphasis.travel.model;

import java.util.Arrays;
import java.util.Date;

public class Travel {
	private int travelId;
	private String placeName;
	private Date dateOfJourney;
	private int pricePackage;
	private byte[] placeImage;
	 public Travel(){}
	public Travel(int travelId, String placeName, Date dateOfJourney, int pricePackage, byte[] placeImage) {
		super();
		this.travelId = travelId;
		this.placeName = placeName;
		this.dateOfJourney = dateOfJourney;
		this.pricePackage = pricePackage;
		this.placeImage = placeImage;
	}
	public int getTravelId() {
		return travelId;
	}
	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public Date getDateOfJourney() {
		return dateOfJourney;
	}
	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}
	public int getPricePackage() {
		return pricePackage;
	}
	public void setPricePackage(int pricePackage) {
		this.pricePackage = pricePackage;
	}
	public byte[] getPlaceImage() {
		return placeImage;
	}
	public void setPlaceImage(byte[] placeImage) {
		this.placeImage = placeImage;
	}
	@Override
	public String toString() {
		return "Travel [travelId=" + travelId + ", placeName=" + placeName + ", dateOfJourney=" + dateOfJourney
				+ ", pricePackage=" + pricePackage + ", placeImage=" + Arrays.toString(placeImage) + "]";
	}
	 
	

}
