package com.delta.rm.cassandra.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;
import com.delta.rm.cassandra.dao.CfRetailOfferDataDao;
import com.delta.rm.cassandra.mapper.CfRetailOfferDataMapper;
import com.delta.rm.cassandra.mapper.CfRetailOfferDataMapperBuilder;

public class CfRetailOfferDataProducer {

	private final CfRetailOfferDataDao cfRetailOfferDataDao;

	@Inject
	public CfRetailOfferDataProducer(QuarkusCqlSession session) {
		// create a mapper
		CfRetailOfferDataMapper mapper = new CfRetailOfferDataMapperBuilder(session).build();
		// instantiate Daos
		cfRetailOfferDataDao = mapper.cfRetailOfferDataDao();
	}

	@Produces
	@ApplicationScoped
	CfRetailOfferDataDao produceCfRetailOfferDataDao() {
		return cfRetailOfferDataDao;
	}

}