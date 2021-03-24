package com.delta.rm.dto;

public class CfRetailOfferDataDto {
    private String offerId;
    private String offerRequestId;
    private String offerResponseId;
    private String offerData;

    public CfRetailOfferDataDto() {}

    public CfRetailOfferDataDto(String offerId, Boolean isActive, Long offerLastUpdatedUtcTs, String offerRequestId,
                             String offerResponseId, String appId, String channelId, Long offerCreationUtcTs, String offerData,
                             String persistentCustomerId, String transactionId) {
        super();
        this.offerId = offerId;
        this.offerRequestId = offerRequestId;
        this.offerResponseId = offerResponseId;
        this.offerData = offerData;
    }

    public String getOfferId() {
        return offerId;
    }
    public void setOfferId(String offerId) {
        this.offerId = offerId;
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
    public String getOfferData() {
        return offerData;
    }
    public void setOfferData(String offerData) {
        this.offerData = offerData;
    }
}
