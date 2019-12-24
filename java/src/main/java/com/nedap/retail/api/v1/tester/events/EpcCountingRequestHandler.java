package com.nedap.retail.api.v1.tester.events;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.gson.Gson;
import com.nedap.retail.api.v1.model.Event;
import com.nedap.retail.api.v1.model.EventEpc;

public class EpcCountingRequestHandler extends AbstractHandler {

    private final Set<String> epcs;
    private final BaseHandler baseHandler;

    public EpcCountingRequestHandler(final BaseHandler baseHandler, final Set<String> epcs) {
        this.baseHandler = baseHandler;
        this.epcs = epcs;
    }

    @Override
    public void handle(
            final String target, final Request baseRequest, final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        baseHandler.handle(target, baseRequest, request, response);
        final Event event = new Gson().fromJson(baseRequest.getReader(), Event.class);
        event.getEpcList().stream().map(EventEpc::getEpc).forEach(epcs::add);
        System.out.print(epcs.size() + " unique EPCs read, ");
        System.out.println(event.getEpcList().size() + " EPCs in this event");
    }
}
