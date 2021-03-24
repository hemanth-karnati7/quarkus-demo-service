package com.delta.rm.cassandra.mapper;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import com.delta.rm.cassandra.dao.CfRetailOfferDataDao;

@Mapper
public interface CfRetailOfferDataMapper {
	@DaoFactory
	CfRetailOfferDataDao cfRetailOfferDataDao();
}
