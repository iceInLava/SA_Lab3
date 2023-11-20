package Server.DataServer;

import tools.People;

import java.io.*;
import java.util.ArrayList;

public class DataServer {
    public DataServer(){
        loadFile();
    }
    public ArrayList<People> peoList = null;
    public String dataURL = "CS3/src/Server/DataServer/data.txt";
    public void loadFile(){
        peoList = new ArrayList<>();
        File file = new File(dataURL);  // 替换为你的文件路径
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] res = line.split(",");
                peoList.add(new People(res[0], res[1], res[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataURL));
            for (People p: peoList) {
                String content = "";
                content += (p.name+","+p.address+","+p.tel);
                writer.write(content);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
