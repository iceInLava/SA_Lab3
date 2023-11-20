package Client;

import Server.Server;
import tools.People;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    Scanner sc = new Scanner(System.in);
    Server server;
    public Client(){
        server = new Server();
    }
    public void UI(){
        System.out.println("个人通讯系统");

        int menuNum = 0;
        boolean flag = true;
        while(flag == true){
            System.out.println("请选择业务操作");
            System.out.println("1. 查看所有");
            System.out.println("2. 按姓名查找");
            System.out.println("3. 按姓名删除");
            System.out.println("4. 按姓名修改");
            System.out.println("5. 添加联系人");
            menuNum = Integer.parseInt(sc.nextLine());
            switch (menuNum){
                case 1:
                    showAllPeople();
                    break;
                case 2:
                    searchPeople();
                    break;
                case 3:
                    delPeople();
                    save();
                    load();
                    break;
                case 4:
                    changePeople();
                    save();
                    load();
                    break;
                case 5:
                    addPeople();
                    save();
                    load();
                    break;
                default:
                    save();
                    flag = false;
                    break;
            }
        }
    }
    public void showAllPeople(){
        ArrayList<People> list = server.showAll();
        for (People p :
                list) {
            System.out.println(p);
        }
    }
    public void addPeople(){
//        Scanner sc = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name= sc.nextLine();
        System.out.println("请输入地址：");
        String addr = sc.nextLine();
        System.out.println("请输入电话：");
        String tel = sc.nextLine();
//        sc.close();
        server.add(name, addr, tel);
    }
    public void delPeople(){
//        Scanner sc_del = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = sc.nextLine();
//        sc_del.close();
        if(server.del(name))
            System.out.println("done");
        else
            System.out.println("false");
    }
    public void changePeople(){
//        Scanner sc = new Scanner(System.in);

        System.out.println("请输入姓名：");
        String name = sc.nextLine();
        System.out.println("请输入新的姓名：");
        String newName= sc.nextLine();
        System.out.println("请输入新的地址：");
        String newAddr = sc.nextLine();
        System.out.println("请输入新的电话：");
        String newTel = sc.nextLine();
//        sc.close();

        if(server.change(name, newName, newAddr, newTel))
            System.out.println("成功");
        else
            System.out.println("查无此人");


    }
    public void searchPeople(){
        System.out.println("请输入姓名：");
//        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();
//        sc.close();
        People people = server.search(name);
        if(people != null)
            System.out.println(people);
        else
            System.out.println("false");
    }
    public void save(){
        server.writeFile();
    }
    public void load(){
        server.loadFile();
    }
}
