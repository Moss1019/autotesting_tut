package org.autotesting.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.autotesting.model.Trade;
import org.autotesting.service.TradeService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import java.util.List;

@Path("api/trades")
public class TradeResource {
    private static final Logger log = Logger.getLogger(TradeResource.class);

    @Inject()
    TradeService service;

    @Path("")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Trade> getTrades(@QueryParam("username") String username) {
        var trades = service.getTrades(username);
        if(trades != null) {
            return trades;
        }
        log.error("Failed to get trades");
        throw new BadRequestException("Could not find trades for username");
    }

    @Path("")
    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    public Trade create(@RequestBody Trade trade) {
        if(service.openTrade(trade)) {
            return trade;
        }
        log.error("Failed to open trade");
        throw new BadRequestException("Could not open trade");
    }

    @Path("")
    @DELETE()
    @Produces(MediaType.APPLICATION_JSON)
    public Trade remove(@RequestBody Trade trade) {
        if(service.closeTrade(trade)) {
            return trade;
        }
        log.error("Failed to close trade");
        throw new BadRequestException("Could not close trade");
    }
}
