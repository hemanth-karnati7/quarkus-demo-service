package com.delta.rm.cassandra.dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.delta.rm.cassandra.entity.CfRetailOfferData;

import java.util.List;

@Dao
public interface CfRetailOfferDataDao {
	@Select
	CfRetailOfferData findById(String offerId);
}
