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
     * @throws IOException
     * @throws ServletException 
     */
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("ok");

        Reader reader = baseRequest.getReader();
        Gson gson = new Gson();
        Event event = gson.fromJson(reader, Event.class);
        
        switch(this.mode) {
            case RAW:
                System.out.println(event.toString());
                System.out.println(event.epcList.size() + " EPCs in this event");
                break;
            case EPCCOUNT:
                for (EventEpc epc : event.epcList) {
                    EpcCounter.addEpc(epc.epc);
                }
                System.out.print(EpcCounter.count() + " unique EPCs read, ");
                System.out.println(event.epcList.size() + " EPCs in this event");
                break;
            case EPCLOG:
                System.out.println(event.toString());
                System.out.println(event.epcList.size() + " EPCs in this event");
                StringBuilder prefix = new StringBuilder();
                prefix.append("\"");
                prefix.append(event.id);
                prefix.append("\";\"");
                prefix.append(event.type);
                prefix.append("\";\"");
                prefix.append(event.occurTime);
                prefix.append("\";\"");
                if (event.direction != null) {
                    prefix.append(event.direction);
                }
                prefix.append("\";\"");
                for (EventEpc epc : event.epcList) {
                    StringBuilder line = new StringBuilder(prefix);
                    line.append(epc.epc);
                    line.append("\";\"");
                    line.append(epc.time);
                    line.append("\";\"");
                    if (epc.eas_status != null) {
                        line.append(epc.eas_status);
                    }
                    line.append("\"");
                    LogFile.write(line.toString());
                }
                break;
            default:
                System.out.println(event.toString());
        }
    }
}
