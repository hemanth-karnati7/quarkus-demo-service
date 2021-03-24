package com.delta.rm.service;

import com.delta.rm.cassandra.dao.CfRetailOfferDataDao;
import com.delta.rm.cassandra.entity.CfRetailOfferData;
import com.delta.rm.dto.CfRetailOfferDataDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class OfferRetrieveService {
    private final CfRetailOfferDataDao cfRetailOfferDataDao;

    @Inject
    public OfferRetrieveService(CfRetailOfferDataDao cfRetailOfferDataDao) {
        this.cfRetailOfferDataDao = cfRetailOfferDataDao;
    }

    public CfRetailOfferDataDto get(String offerId) {
        return convertToDto(cfRetailOfferDataDao.findById(offerId));
    }

    private CfRetailOfferDataDto convertToDto(CfRetailOfferData cfRetailOfferData) {
        CfRetailOfferDataDto cfRetailOfferDataDto = new CfRetailOfferDataDto();
        cfRetailOfferDataDto.setOfferRequestId(cfRetailOfferData.getOfferRequestId());
        cfRetailOfferDataDto.setOfferResponseId(cfRetailOfferData.getOfferResponseId());
        cfRetailOfferDataDto.setOfferData(cfRetailOfferData.getOfferData());
        return cfRetailOfferDataDto;
    }
}
