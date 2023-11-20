package ProcessServer;

import DataServer.DataServer;
import ProcessServer.Process.*;
import ProcessServer.Process.Tools.People;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class ProcessServer {
    public static DataServer dataServer;
    public static void launchDataServer(){
        dataServer = new DataServer();
        dataServer.openDataServer();
    }
    public static void openProcessServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/add", new AddProcess());
        server.createContext("/change", new ChangeProcess());
        server.createContext("/del", new DelProcess());
        server.createContext("/show", new ShowProcess());
        server.start();
        System.out.println("server is running on port: 8080");
    }
    public static Map<String,String> dataExchangeMap(String formData ) {
        Map<String,String> result = new HashMap<>();
        if(formData== null || formData.trim().length() == 0) {
            return result;
        }
        final String[] items = formData.split("&");
        Arrays.stream(items).forEach(item ->{
            final String[] keyAndVal = item.split("=");
            if( keyAndVal.length == 2) {
                try{
                    final String key = URLDecoder.decode( keyAndVal[0],"utf8");
                    final String val = URLDecoder.decode( keyAndVal[1],"utf8");
                    result.put(key,val);
                }catch (UnsupportedEncodingException e) {}
            }
        });
        System.out.println(result);
        return result;
    }
}
