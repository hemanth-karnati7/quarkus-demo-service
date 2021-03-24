package com.delta.rm.cassandra.entity;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

@Entity
public class CfRetailOfferData {
	@PartitionKey private String offerId;
	@ClusteringColumn(0) private Boolean isActive;
	@ClusteringColumn(1) private Long offerLastUpdatedUtcTs;
	@ClusteringColumn(2) private String offerRequestId;
	@ClusteringColumn(3) private String offerResponseId;
	private String appId;
	private String channelId;
	private Long offerCreationUtcTs;
	private String offerData;
	private String persistentCustomerId;
	private String transactionId;
	
	public CfRetailOfferData() {}
	
	public CfRetailOfferData(String offerId, Boolean isActive, Long offerLastUpdatedUtcTs, String offerRequestId,
			String offerResponseId, String appId, String channelId, Long offerCreationUtcTs, String offerData,
			String persistentCustomerId, String transactionId) {
		super();
		this.offerId = offerId;
		this.isActive = isActive;
		this.offerLastUpdatedUtcTs = offerLastUpdatedUtcTs;
		this.offerRequestId = offerRequestId;
		this.offerResponseId = offerResponseId;
		this.appId = appId;
		this.channelId = channelId;
		this.offerCreationUtcTs = offerCreationUtcTs;
		this.offerData = offerData;
		this.persistentCustomerId = persistentCustomerId;
		this.transactionId = transactionId;
	}
	
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Long getOfferLastUpdatedUtcTs() {
		return offerLastUpdatedUtcTs;
	}
	public void setOfferLastUpdatedUtcTs(Long offerLastUpdatedUtcTs) {
		this.offerLastUpdatedUtcTs = offerLastUpdatedUtcTs;
	}
	public String getOfferRequestId() {
		return offerRequestId;
	}
	public void setOfferRequestId(String offerRequestId) {
		this.offerRequestId = offerRequestId;
	}
	public String getOfferResponseId() {
		return offerResponseId;
	}
	public void setOfferResponseId(String offerResponseId) {
		this.offerResponseId = offerResponseId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public Long getOfferCreationUtcTs() {
		return offerCreationUtcTs;
	}
	public void setOfferCreationUtcTs(Long offerCreationUtcTs) {
		this.offerCreationUtcTs = offerCreationUtcTs;
	}
	public String getOfferData() {
		return offerData;
	}
	public void setOfferData(String offerData) {
		this.offerData = offerData;
	}
	public String getPersistentCustomerId() {
		return persistentCustomerId;
	}
	public void setPersistentCustomerId(String persistentCustomerId) {
		this.persistentCustomerId = persistentCustomerId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
