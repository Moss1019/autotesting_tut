package org.autotesting.resource;

import io.smallrye.common.constraint.NotNull;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.autotesting.model.Candle;
import org.autotesting.service.CandleService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import java.util.List;

@Path("api/candles")
public class CandleResource {
    private static final Logger log = Logger.getLogger(CandleResource.class);

    @Inject
    CandleService service;

    @Path("")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Candle> getCandles(@QueryParam("currency") @NotNull() String currency,
                                   @QueryParam("limit") @NotNull() int limit,
                                   @QueryParam("offset") @NotNull() int offset) {
        var candles = service.getCandles(currency, limit, offset);
        if(candles != null) {
            return candles;
        }
        log.error("Failed to retrieve candles");
        throw new BadRequestException("Could not load candles");
    }

    @Path("")
    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    public Candle create(@RequestBody() Candle candle) {
        if(service.create(candle)) {
            return candle;
        }
        log.error("Failed to create candle");
        throw new BadRequestException("Could not create candle");
    }
}
