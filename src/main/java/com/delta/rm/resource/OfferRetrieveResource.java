package com.delta.rm.resource;

import com.delta.rm.dto.CfRetailOfferDataDto;
import com.delta.rm.service.OfferRetrieveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/offers")
public class OfferRetrieveResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(OfferRetrieveResource.class);

    @Inject
    OfferRetrieveService offerRetrieveService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CfRetailOfferDataDto get(@QueryParam("offerId") String offerId) {
        LOGGER.info("offerId >>>>>>>>>> " + offerId);
        LOGGER.debug("debug offerId >>>>>>>>>> " + offerId);
        return offerRetrieveService.get(offerId);
    }

}
