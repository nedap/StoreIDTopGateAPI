package com.nedap.retail.api.v1.tester.events;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.gson.Gson;
import com.nedap.retail.api.v1.model.Event;

/**
 * Handles web server requests
 */
public class RawRequestHandler extends AbstractHandler {
    final BaseHandler baseHandler;

    public RawRequestHandler(final BaseHandler baseHandler) {
        this.baseHandler = baseHandler;
    }

    @Override
    public void handle(
            final String target, final Request baseRequest, final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        baseHandler.handle(target, baseRequest, request, response);
        final Event event = new Gson().fromJson(baseRequest.getReader(), Event.class);
        System.out.println(event.toString());
        System.out.println(event.getEpcList().size() + " EPCs in this event");
    }
}
