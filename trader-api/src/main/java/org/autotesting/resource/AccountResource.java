package org.autotesting.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.autotesting.model.Account;
import org.autotesting.service.AccountService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

@Path("api/accounts")
public class AccountResource {
    private static final Logger log = Logger.getLogger(AccountResource.class);

    @Inject
    AccountService service;

    @Path("")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@QueryParam("username") String username) {
        var account = service.getForUsername(username);
        if (account != null) {
            return account;
        }
        log.error("Failed to find account");
        throw new BadRequestException("Could not find account");
    }

    @Path("")
    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    public Account createAccount(@RequestBody Account account) {
        if (service.create(account)) {
            return account;
        }
        log.error("Failed to create account");
        throw new BadRequestException("Could not create account");
    }

    @Path("open-trades")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getTotalOpenTrades(@QueryParam("username") String username) {
        var numberTrades = service.getTotalOpenTrades(username);
        if (numberTrades > -1) {
            var result = new HashMap<String, Integer>();
            result.put("total", numberTrades);
            return result;
        }
        log.error("Failed to remove account");
        throw new BadRequestException("Could not find open trades for user");
    }
}
