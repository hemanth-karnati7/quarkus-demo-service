package com.delta.rm.resource;

import com.delta.rm.dto.CfRetailOfferDataDto;
import com.delta.rm.service.OfferRetrieveService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/offers")
public class OfferRetrieveResource {
    @Inject
    OfferRetrieveService offerRetrieveService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CfRetailOfferDataDto get(@QueryParam("offerId") String offerId) {
        System.out.println("offerId >>>>>>>>>> " + offerId);
        return offerRetrieveService.get(offerId);
    }

}
