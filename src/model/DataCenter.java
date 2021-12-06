package model;
import java.util.StringTokenizer;

public class DataCenter {
    public static final int MAX_COLUMS=8;
    public static final int MAX_ROWS=50;
    //atributes
    private double baseValueRoom;

    //relations
    private Room[][] rooms; 
    private Room[][] simulateRoom;

    //methods
    public DataCenter(double baseValueRoom) {
        this.baseValueRoom = baseValueRoom;
        rooms = new Room[MAX_COLUMS][MAX_ROWS];
        simulateRoom = new Room[MAX_COLUMS][MAX_ROWS];
    }    

    /**
     * simulates the shutdown of the rooms according to the entered command
     * @param letter
     * @param other
     */
    public void simulateShutdown(String letter, int other){
        String menu=letter.toUpperCase();
        other--;
        switch(menu){
            case "L":
            for (int i = 0; i < simulateRoom.length; i++) {
                for (int j = 0; j < simulateRoom[0].length; j++) {
                    if (i==0 || j==0){
                        simulateRoom[i][j]=null;
                    }
                }
            }
            break;
            case "H":
            for (int i = 0; i < simulateRoom.length; i++) {
                for (int j = 0; j < simulateRoom[0].length; j++) {
                    if (i%2==0){
                        simulateRoom[i][j]=null;
                    }
                }
                
            }
            break;
            case "O":
            for (int i = 0; i < simulateRoom.length; i++) {
                for (int j = 0; j < simulateRoom[0].length; j++) {
                    if (i==0 || i==simulateRoom.length-1 || j==0 || j==simulateRoom[0].length-1){
                        simulateRoom[i][j]=null;
                    }
                }
            }
            break;
            case "M":
            for (int i = 0; i < simulateRoom.length; i++) {
                simulateRoom[i][other]=null;
            }
            break;
            case "P":
            for (int i = 0; i < simulateRoom[0].length; i++) {
                simulateRoom[other][i]=null;
            }
            break;
            case "Z":
            for (int i = 0; i < simulateRoom.length; i++) {
                for (int j = 0; j < simulateRoom[0].length; j++) {
                    if (i==0 || i==simulateRoom.length-1){
                        simulateRoom[i][j]=null;
                    }
                    if((i+j)%(simulateRoom.length-1)==0){
                        simulateRoom[i][j]=null;
                    }
                }
            }
        }
    }

    /**
     * will show the occupied rooms of the simulation
     * @return
     */
    public String simulateOcupedRoom(){
        String ocupedRoom="";
        int count=1;
        for (int i = 0; i < simulateRoom.length; i++) {
            for (int j = 0; j < simulateRoom[0].length; j++) {
                if (simulateRoom[i][j]!=null){
                    if (count<10){
                        ocupedRoom=ocupedRoom+"[00"+count+"]";
                    }
                    else if(count<100){
                        ocupedRoom=ocupedRoom+"[0"+count+"]";
                    }
                    else {
                        ocupedRoom=ocupedRoom+"["+count+"]";
                    }
                }
                else {
                    ocupedRoom=ocupedRoom+"[___]";
                }
                count++;
            }
            ocupedRoom=ocupedRoom+"\n";
            
        }
        return ocupedRoom;
    }

    /**
     * will put all the rooms of the simulation occupied.
     */
    public void simulateStartRoom(){
        int count =1;
        for (int i = 0; i < simulateRoom.length; i++) {
            for (int j = 0; j < simulateRoom[0].length; j++) {
                simulateRoom[i][j]=new Room(count, "", "", 3, "");
            }
            count++;
        }
    }

    /**
     * to eliminate a room
     * @param numberRoom
     */
    public void deletedRooms(int numberRoom){
        String position=searchRoom(numberRoom);
        StringTokenizer room = new StringTokenizer(position);
        int colum = Integer.parseInt(room.nextToken());
        int rows = Integer.parseInt(room.nextToken());
        if (rooms[colum][rows]!=null){
            rooms[colum][rows].deletedServers();
            rooms[colum][rows]=null;
        }
    }

    /**
     * will search for companies that match the search to eliminate each room.
     * @param nameToEliminate
     */
    public void deletedCompanyRoom(String nameToEliminate){
        int count=1;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j]!=null){
                    if (rooms[i][j].getNameCompany().equalsIgnoreCase(nameToEliminate)){
                        deletedRooms(count);
                    }
                }
                count++;
            }
        }
    }

    /**
     * add room
     * @param numberIdentification
     * @param nit
     * @param name
     * @param typeNumber
     * @param nameProject
     */
    public void addRoom(int numberIdentification, String nit, String name, int typeNumber, String nameProject){
        String position=searchRoom(numberIdentification);
        StringTokenizer room = new StringTokenizer(position);
        int colum = Integer.parseInt(room.nextToken());
        int rows = Integer.parseInt(room.nextToken());
        rooms[colum][rows]=new Room(numberIdentification, nit, name, typeNumber, nameProject);
    }

    /**
     * add server
     * @param numberRoom
     * @param equalServers
     * @param cache
     * @param numberOfProcessors
     * @param ram
     * @param numberOfDiscs
     * @param diskCapacity
     * @param number
     */
    public void addServer(int numberRoom, int equalServers, double cache, int numberOfProcessors, double ram, int numberOfDiscs, double diskCapacity, int number){
        String position=searchRoom(numberRoom);
        StringTokenizer room = new StringTokenizer(position);
        int colum = Integer.parseInt(room.nextToken());
        int rows = Integer.parseInt(room.nextToken());
        equalServers++;
        rooms[colum][rows].addServer(equalServers, cache, numberOfProcessors, ram, numberOfDiscs, diskCapacity, number);
    }

    /**
     * calculates the price of a room
     * @param numberRoom
     * @return
     */
    public String priceRoom(int numberRoom){
        String position=searchRoom(numberRoom);
        StringTokenizer room = new StringTokenizer(position);
        int colum = Integer.parseInt(room.nextToken());
        int rows = Integer.parseInt(room.nextToken());
        String price="";
        double discount=0;
        double surcharge=0;
        double total=0;
        if (colum==0 || colum==rooms.length-1 || rows==0 || rows==rooms[0].length-1){
            discount=baseValueRoom*0.1;
        }
        if (colum==rooms.length-2){
            discount=discount+(baseValueRoom*0.15);
        }
        if (colum>0 && colum<rooms.length-3){
            surcharge=baseValueRoom*0.25;
        }
        if (rooms[colum][rows].sizeArrayList()<4){
            surcharge=surcharge+(baseValueRoom*0.15);
        }
        total = baseValueRoom-discount+surcharge;
        price="the total cost is: "+total;

        return price;
    }

    /**
     * will show the occupied rooms
     */
    public String ocupedRoom(){
        String ocupedRoom="";
        int count=1;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j]!=null){
                    if (count<10){
                        ocupedRoom=ocupedRoom+"[00"+count+"]";
                    }
                    else if(count<100){
                        ocupedRoom=ocupedRoom+"[0"+count+"]";
                    }
                    else {
                        ocupedRoom=ocupedRoom+"["+count+"]";
                    }
                }
                else {
                    ocupedRoom=ocupedRoom+"[___]";
                }
                count++;
            }
            ocupedRoom=ocupedRoom+"\n";
            
        }
        return ocupedRoom;
    }

    /**
     * will tell if a room is occupied or not
     * @param number
     * @return
     */
    public boolean isOcuped (int number){
        boolean isOcuped = true;
        StringTokenizer room = new StringTokenizer(searchRoom(number));
        int colum = Integer.parseInt(room.nextToken());
        int rows = Integer.parseInt(room.nextToken());
        if (rooms[colum][rows]==null){
            isOcuped=false;
        }
        return isOcuped;
    }

    /**
     * will look up the number of a room to return the position of the two coordinates (columns and rows)
     * @param numberRoom
     * @return
     */
    public String searchRoom(int numberRoom){
        String position = "";
        int count=1;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (count==numberRoom){
                    position=i+" "+j;
                }
                count++;
            }
        }
        return position;
    }

    /**
     * will show the free rooms
     * @return
     */
    public String availableRoom(){
        String availableRoom="";
        int count=1;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j]==null){
                    if (count<10){
                        availableRoom=availableRoom+"[00"+count+"]";
                    }
                    else if(count<100){
                        availableRoom=availableRoom+"[0"+count+"]";
                    }
                    else {
                        availableRoom=availableRoom+"["+count+"]";
                    }
                }
                else {
                    availableRoom=availableRoom+"[___]";
                }
                count++;
            }
            availableRoom=availableRoom+"\n";
            
        }
        return availableRoom;
    }

    /**
     * returns the base value
     * @return
     */
    public double getBaseValueRoom() {
        return baseValueRoom;
    }

    /**
     * changes the base value
     * @param baseValueRoom
     */
    public void setBaseValueRoom(double baseValueRoom) {
        this.baseValueRoom = baseValueRoom;
    }


    
}
