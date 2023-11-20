package DataServer;

import ProcessServer.Process.Tools.People;

import java.io.*;
import java.util.ArrayList;

public class DataServer {
    public ArrayList<People> peoList = null;
    public void openDataServer(){
        loadFile();
    }
    public String dataURL = "BS/src/DataServer/data.txt";
    public void loadFile(){
        peoList = new ArrayList<>();
        File file = new File(dataURL);  // 替换为你的文件路径

        try  {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] res = line.split(",");
                peoList.add(new People(res[0], res[1], res[2]));
            }
            br.close();
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
    public void add(String name, String address, String tel){
        peoList.add(new People(name, address, tel));
    }
    public boolean del(String name){
        int index = 0;
        boolean flag = false;
        for (People p: peoList) {

            if(p.name.equalsIgnoreCase(name)){
                flag = true;
                break;
            }
            index ++;
        }
        if(flag == true)
            peoList.remove(index);
        return flag;
    }
    public boolean change(String name, String newName, String newAddr, String newTel){
        boolean flag = false;
        for (People p : peoList) {
            if (p.name.equalsIgnoreCase(name)) {
                flag = true;
                p.name = newName;
                p.address = newAddr;
                p.tel = newTel;
                break;
            }
        }
        return flag;
    }
    public ArrayList<People> showAll(){
        return peoList;
    }

}
