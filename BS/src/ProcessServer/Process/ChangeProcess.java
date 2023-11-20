package ProcessServer.Process;

import ProcessServer.ProcessServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class ChangeProcess extends ProcessServer implements HttpHandler {
    protected String oldNameInfo = "";
    protected String nameInfo = "";
    protected String addrInfo = "";
    protected String telInfo = "";

    public ChangeProcess() throws IOException {
        System.out.println("Change Process Created!");
    }
    private void change(){
        dataServer.change(oldNameInfo, nameInfo, addrInfo, telInfo);
        dataServer.writeFile();
        dataServer.loadFile();
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String queryString = exchange.getRequestURI().getQuery();
        Map<String , String > queryInfo = ProcessServer.dataExchangeMap(queryString);
        String oldName = "old_name";
        String name = "name";
        String address = "address";
        String tel = "phone";

        oldNameInfo = queryInfo.get(oldName);
        nameInfo = queryInfo.get(name);
        addrInfo = queryInfo.get(address);
        telInfo = queryInfo.get(tel);


        String reply = "Fxxk U";
        exchange.sendResponseHeaders(200,0);
        OutputStream os = exchange.getResponseBody();
        os.write(reply.getBytes());
        os.close();

        change();
    }
}
