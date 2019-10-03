package com.nedap.retail.api.v1.tester;

import com.google.gson.Gson;
import com.nedap.retail.api.v1.model.Event;
import com.nedap.retail.api.v1.model.EventEpc;
import com.nedap.retail.api.v1.tester.EventsServer.MODE;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * Handles web server requests
 */
public class EventsServerRequestHandler extends AbstractHandler {
    private MODE mode = MODE.RAW;
    
    /**
     * How to handle a request: show raw event or send EPCs to EPC counter
     */
    public void setMode(MODE mode) {
        this.mode = mode;
    }
    
    /**
     * @param target The target of a request - either a URI or a name
     * @param baseRequest The original unwrapped request object
     * @param request The HttpServletRequest
     * @param response The HttpServletResponse
     * @throws IOException When the connection breaks
     */
    public void handle(
            final String target, final Request baseRequest, final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("ok");

        final Event event = new Gson().fromJson(baseRequest.getReader(), Event.class);
        
        switch(this.mode) {
            case RAW:
                System.out.println(event.toString());
                System.out.println(event.getEpcList().size() + " EPCs in this event");
                break;
            case EPCCOUNT:
                event.getEpcList().stream().map(EventEpc::getEpc).forEach(EpcCounter::addEpc);
                System.out.print(EpcCounter.count() + " unique EPCs read, ");
                System.out.println(event.getEpcList().size() + " EPCs in this event");
                break;
            case EPCLOG:
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
                break;
            default:
                System.out.println(event.toString());
        }
    }
}
