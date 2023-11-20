package Server.ProcessServer;

import Server.DataServer.DataServer;
import tools.People;

import java.util.ArrayList;

public class ProcessServer {
    public ProcessServer(){
        dataServer = new DataServer();
    }
    public DataServer dataServer;
    public void add(String name, String address, String tel){
        dataServer.peoList.add(new People(name, address, tel));
    }
    public boolean del(String name){
        int index = 0;
        boolean flag = false;
        for (People p: dataServer.peoList) {

            if(p.name.equalsIgnoreCase(name)){
                flag = true;
                break;
            }
            index ++;
        }
        if(flag == true)
            dataServer.peoList.remove(index);
        return flag;
    }
    public People search(String name) {
        People aim = null;
        boolean flag = false;
        for (People p : dataServer.peoList) {
            if (p.name.equalsIgnoreCase(name)) {
                flag = true;
                aim = new People(p.name, p.address, p.tel);
                break;
            }
        }
        return aim;
    }
    public boolean change(String name, String newName, String newAddr, String newTel){
        boolean flag = false;
        for (People p : dataServer.peoList) {
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
        return dataServer.peoList;
    }

    public void saveInfo() {
        dataServer.writeFile();
    }

    public void LoadInfo() {
        dataServer.loadFile();
    }
}
