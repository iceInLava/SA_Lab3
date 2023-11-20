import ProcessServer.ProcessServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ProcessServer.openProcessServer();
        ProcessServer.launchDataServer();
    }
}
