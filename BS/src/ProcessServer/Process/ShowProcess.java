package ProcessServer.Process;

import ProcessServer.Process.Tools.People;
import ProcessServer.ProcessServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class ShowProcess extends ProcessServer implements HttpHandler {
    public ShowProcess() throws IOException {
        System.out.println("Show Process Created!");
    }
    @Override
    public void handle(HttpExchange t) throws IOException {
        StringBuilder response = new StringBuilder();
        response.append("<html><body><h1>通讯录</h1>");
        response.append("<table border='1'><tr><th>姓名</th><th>地址</th><th>电话</th></tr>");
        for (People p : dataServer.showAll()) {
            response.append("<tr><td>").append(p.name).append("</td>" +
                    "<td>").append(p.address).append("</td>" +
                    "<td>").append(p.tel).append("</td></tr>");
        }
        response.append("</table></body></html>");
        byte[] bytes = response.toString().getBytes(StandardCharsets.UTF_8); // 设置字符编码为 UTF-8
        t.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8"); // 添加字符编码标头
        t.sendResponseHeaders(200, bytes.length);
        OutputStream os = t.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
