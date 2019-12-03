package com.nedap.retail.api.v1.tester.events;

import com.google.gson.Gson;
import com.nedap.retail.api.v1.model.Event;
import com.nedap.retail.api.v1.tester.LogFile;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class EpcLoggingRequestHandler extends AbstractHandler {

    private final BaseHandler baseHandler;

    public EpcLoggingRequestHandler(final BaseHandler baseHandler) {
        this.baseHandler = baseHandler;
    }

    @Override
    public void handle(
            final String target, final Request baseRequest, final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException, ServletException {
        baseHandler.handle(target, baseRequest, request, response);
        final Event event = new Gson().fromJson(baseRequest.getReader(), Event.class);
        System.out.println(event.toString());
        System.out.println(event.getEpcList().size() + " EPCs in this event");
        final String prefix = String.format(
                "\"%s\";\"%s\";\"%d\";\"%s\";", event.getId(), event.getType(), event.getOccurTime(),
                Optional.ofNullable(event.getDirection()).orElse("")
        );
        event.getEpcList().forEach(epc ->
                LogFile.write(String.format(
                        "%s%s\";\"%d\";\"%s\"", prefix, epc.getEpc(), epc.getTime(),
                        Optional.ofNullable(epc.getEasStatus()).orElse("")
                ))
        );
    }
}
