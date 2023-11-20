package ProcessServer.Process;

import ProcessServer.Process.Tools.People;
import ProcessServer.ProcessServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

public class AddProcess extends ProcessServer implements HttpHandler {
    protected String nameInfo = "";
    protected String addrInfo = "";
    protected String telInfo = "";

    public AddProcess() throws IOException {
        System.out.println("Add Process Created!");
    }
    private void add(){
        dataServer.add(nameInfo, addrInfo, telInfo);
        dataServer.writeFile();
        dataServer.loadFile();
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String queryString = exchange.getRequestURI().getQuery();
        Map<String , String > queryInfo = ProcessServer.dataExchangeMap(queryString);
        String name = "name";
        String address = "address";
        String tel = "phone";
        nameInfo = queryInfo.get(name);
        addrInfo = queryInfo.get(address);
        telInfo = queryInfo.get(tel);

        String reply = "Added";
        exchange.sendResponseHeaders(200,0);
        OutputStream os = exchange.getResponseBody();
        os.write(reply.getBytes());
        os.close();

        add();
    }
}
