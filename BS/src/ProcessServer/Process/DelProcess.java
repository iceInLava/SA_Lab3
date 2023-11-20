package ProcessServer.Process;

import DataServer.DataServer;
import ProcessServer.ProcessServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class DelProcess extends ProcessServer implements HttpHandler {
    protected String nameInfo = "";

    public DelProcess() throws IOException {
        System.out.println("Del Process Created!");
    }
    private void delete(){
        dataServer.del(nameInfo);
        dataServer.writeFile();
        dataServer.loadFile();
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String queryString = exchange.getRequestURI().getQuery();
        Map<String , String > queryInfo = ProcessServer.dataExchangeMap(queryString);
        String name = "name";
        nameInfo = queryInfo.get(name);

        String reply = "Deleted";
        exchange.sendResponseHeaders(200,0);
        OutputStream os = exchange.getResponseBody();
        os.write(reply.getBytes());
        os.close();

        delete();
    }
}
