package model;

import java.util.ArrayList;

public class Room {
    //atributes
    private int numberIdentification;
    private String nit;
    private String nameCompany;
    private String name;

    //relations
    private CustomerType type;
    private ArrayList<Server> servers;

    //methods
    public Room(int numberIdentification, String nit, String name, int typeNumber, String nameProject) {
        this.setNumberIdentification(numberIdentification);
        this.nit = nit;
        this.nameCompany = name;
        this.setName(nameProject);
        setType(typeNumber);
        servers = new ArrayList<Server>();
    }

    /**
     * deleted servers
     */
    public void deletedServers(){
        for (int i = 0; i < sizeArrayList(); i++) {
            servers.remove(i);
        }
    }

    /**
     * add server
     * @param equalServers
     * @param cache
     * @param numberOfProcessors
     * @param ram
     * @param numberOfDiscs
     * @param diskCapacity
     * @param number
     */
    public void addServer(int equalServers ,double cache, int numberOfProcessors, double ram, int numberOfDiscs, double diskCapacity, int number){
        Server server = new Server(cache, numberOfProcessors, ram, numberOfDiscs, diskCapacity, number);
        for (int i = 0; i < equalServers; i++) {
            servers.add(server);
        }
    }

    /**
     * returns how many servers
     * @return
     */
    public int sizeArrayList(){
        int size=servers.size();
        return size;
    }
    
    /**
     * get number identification
     * @return
     */
    public int getNumberIdentification() {
        return numberIdentification;
    }

    /**
     * set number identification
     * @param numberIdentification
     */
    public void setNumberIdentification(int numberIdentification) {
        this.numberIdentification = numberIdentification;
    }

    /**
     * get nit
     * @return 
     */
    public String getNit() {
        return nit;
    }

    /**
     * set nit
     * @param nit
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * get name company
     * @return
     */
    public String getNameCompany() {
        return nameCompany;
    }

    /**
     * set name company
     * @param name
     */
    public void setNameCompany(String name) {
        this.nameCompany = name;
    }

    /**
     * get name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get type
     * @return
     */
    public CustomerType getType(){
        return type;
    }

    /**
     * set type
     * @param number
     */
    public void setType(int number){
        switch(number){
            case 1:
            type = CustomerType.RESEARCHPROJECT;
            break;
            case 2:
            type = CustomerType.COMPANY;
            break;
            case 3:
            type = CustomerType.EMPTY;
            break;
        }
    }
}
