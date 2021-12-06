package ui;
import model.*;
import java.util.Scanner;

public class Main{
    public static Scanner lec;
    public static DataCenter dataCenter;
    public static void main(String[] args) {
        lec = new Scanner(System.in);
        System.out.println("enter the base value of each room");
        double baseValueRoom=lec.nextDouble();
        dataCenter= new DataCenter(baseValueRoom);
        boolean exit = false;
        int menu = 0;
        while(exit==false){
            System.out.println("\n -----------------MAIN-MENU------------------"+"\n");
            System.out.println("what do you want to do");
            System.out.println("1: available rooms");
            System.out.println("2: rent a room");
            System.out.println("3: cancel rent");
            System.out.println("4: view occupied rooms");
            System.out.println("5: Simulate the turn on of all mini-chambers");
            System.out.println("6: simulate room shutdown with commands");
            System.out.println("7: exit");
            menu = lec.nextInt();
            lec.nextLine();
            switch(menu){
                case 1:
                availableRoom();
                break;
                case 2:
                addRoom();
                break;
                case 3:
                deletedRooms();
                break;
                case 4:
                ocupedRoom();
                break;
                case 5:
                simulateStartRoom();
                break;
                case 6:
                simulateShutdown();
                break;
                case 7:
                exit=true;
                break;
            }

        }
    }

    /**
     * simulates the shutdown of the rooms according to the entered command
     */
    public static void simulateShutdown(){
        System.out.println("enter the command ");
        String comand=lec.nextLine();
        if (comand.equalsIgnoreCase("m")){
            System.out.println("enter the number of the column you want to turn off");
            int column = lec.nextInt();
            lec.nextLine();
            dataCenter.simulateShutdown(comand, column);
        }
        else if(comand.equalsIgnoreCase("p")){
            System.out.println("enter the number of the corridor you want to shut down");
            int corridor=lec.nextInt();
            lec.nextLine();
            dataCenter.simulateShutdown(comand, corridor);
        }
        else {
            dataCenter.simulateShutdown(comand, 0);
        }
        System.out.println("\n -----------------------OCUPED-ROOMS------------------------------"+"\n");
        System.out.println(dataCenter.simulateOcupedRoom());
    }

    /**
     * creates the simulation and if already created, turns all rooms on
     */
    public static void simulateStartRoom(){
        dataCenter.simulateStartRoom();
        System.out.println(dataCenter.simulateOcupedRoom());
    }

    /**
     * you are asked to provide information on whether a specific room or all rooms of a company will be eliminated.
     */
    public static void deletedRooms(){
        System.out.println("what do you want to do:");
        System.out.println("1: you want to eliminate a specific room");
        System.out.println("2: you want to eliminate all rooms of a company");
        int menu = lec.nextInt();
        lec.nextLine();
        switch(menu){
            case 1:
            deletedEsoecificRoom();
            break;
            case 2:
            deletedCompanyRoom();
            break;
        }
    }

    /**
     * enter the name of the company to be deleted 
     */
    public static void deletedCompanyRoom(){
        System.out.println("enter the name of the company you wish to delete");
        String nameCompany = lec.nextLine();
        dataCenter.deletedCompanyRoom(nameCompany);
    }

    /**
     * the room that the person selects will be eliminated.
     */
    public static void deletedEsoecificRoom(){
        ocupedRoom();
        System.out.println("\n ---------------------------ENTER-ROOM-TO-ELIMINATE------------------------------------------"+"\n");
        int roomEliminate=lec.nextInt();
        lec.nextLine();
        dataCenter.deletedRooms(roomEliminate);
        System.out.println("everything in room number was eliminated: ");
    }

    /**
     * occupied rooms will be displayed
     */
    public static void ocupedRoom(){
        System.out.println("press enter");
        System.out.println("after pressing enter you will be able to see the rooms that are occupied as they will be shown with a number inside, if they have ___ they are free.");
        lec.nextLine();
        System.out.println("\n ---------------------------------------------ROOMS-TURN-ON--------------------------------------------"+"\n");
        System.out.println(dataCenter.ocupedRoom());
    }

    /**
     * the free rooms will be shown
     */
    public static void availableRoom(){
        System.out.println("press enter");
        System.out.println("after pressing enter you will be able to see the rooms that are free since they will be shown with a number inside, if they have ___ they are occupied.");
        lec.nextLine();
        System.out.println("\n ---------------------------------------------ROOMS-EMPTY---------------------------------------------"+"\n");
        System.out.println(dataCenter.availableRoom());
    }

    /**
     * add room
     */
    public static void addRoom (){
        availableRoom();
        System.out.println("\n ------ENTER-THE-NUMBER-OF-THE-ROOM-YOU-WISH-TO-USE----------");
        int room = lec.nextInt();
        lec.nextLine();
        if (dataCenter.searchRoom(room)!=""){
            if (dataCenter.isOcuped(room)==false){
                System.out.println("will be saved:");
                System.out.println("1: research project");
                System.out.println("2: will be rented to a company");
                int type=lec.nextInt();
                lec.nextLine();
                switch(type){
                    case 1:
                    addProject(room);
                    break;
                    case 2:
                    addCompany(room);
                    break;
                }
                if (type==1 || type==2){
                    boolean continuee=true;
                    while(continuee){
                        addServer(room);
                        System.out.println("you want to add a different server");
                        System.out.println("1: yes");
                        System.out.println("other number: no");
                        int x=lec.nextInt();
                        if (x!=1){
                            continuee=false;
                        }
                    }
                    System.out.println(dataCenter.priceRoom(room));
                }
            }
            else{
                System.out.println("the room is already occupied");
            }
        }
        else {
            System.out.println("the number entered is not found");
        }
    }

    /**
     * add server
     * @param room
     */
    public static void addServer(int room){
        System.out.println("\n -----------------NEW-SERVERS--------------------"+"\n");
        System.out.println("Amount of cache memory (in GB)");
        double cache = lec.nextDouble();
        System.out.println("Number of processors");
        int numberOfProcessors=lec.nextInt();
        System.out.println("Amount of RAM memory (in GB)");
        double ram=lec.nextDouble();
        System.out.println("Number of discs");
        int numberOfDiscs=lec.nextInt();
        System.out.println("Disk capacity (in terabytes)");
        double diskCapacity=lec.nextDouble();
        System.out.println("processor brand");
        System.out.println("1: AMD");
        System.out.println("2: INTEL");
        int number = lec.nextInt();
        System.out.println("how many of the same servers you wish to add");
        int equalServers=lec.nextInt();
        lec.nextLine();
        dataCenter.addServer(room, equalServers, cache, numberOfProcessors, ram, numberOfDiscs, diskCapacity, number);
    }

    /**
     * add project
     * @param room
     */
    public static void addProject(int room){
        System.out.println("enter the name of the project ");
        String nameProject = lec.nextLine();
        dataCenter.addRoom(room, "", "ICESI", 1, nameProject);
    }

    /**
     * add company
     * @param room
     */
    public static void addCompany(int room){
        System.out.println("enter company name");
        String name=lec.nextLine();
        System.out.println("enter company nit");
        String nit=lec.nextLine();
        dataCenter.addRoom(room, nit, name, 2, "");
    }
}