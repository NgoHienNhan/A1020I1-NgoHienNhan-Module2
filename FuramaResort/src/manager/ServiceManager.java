package manager;

import common.ObjectFurama;
import common.WriteAndReadFileCSV;
import common.WriteAndReadFileServiceCSV;
import controllers.MainController;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class ServiceManager {
    public static void addNewServices() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            System.out.println("1. Add new villa");
            System.out.println("2. Add new house");
            System.out.println("3. Add new room");
            System.out.println("4. Back to menu");
            System.out.println("5. Exit");
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    ServiceManager.addNewVilla();
                    break;
                case 2:
                    ServiceManager.addNewHouse();
                    break;
                case 3:
                    ServiceManager.addNewRoom();
                    break;
                case 4:
                    MainController.displayMainMenu();
                    break;
                case 5:
                    check = true;
            }
        }
    }

     public static void inputInstanceServices(Services services) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter id:");
        services.setId(input.nextLine());
        System.out.println("Enter service name:");
        services.setServiceName(input.nextLine());
        System.out.println("Enter rental costs:");
        services.setRentalCosts(input.nextInt());
        System.out.println("Enter use acreage:");
        services.setAreaUsed(input.nextDouble());
        input.nextLine();
        System.out.println("Enter rental type:");
        services.setRentalType(input.nextLine());
        System.out.println("Enter max people:");
        services.setMaxPeople(input.nextInt());

        if (services instanceof Villa) {
            input.nextLine();
            System.out.println("Enter type of room:");
            ((Villa) services).setTypeOfRoom(input.nextLine());
            System.out.println("Enter other amenities:");
            ((Villa) services).setOtherAmenities(input.nextLine());
            System.out.println("Enter acreage pool:");
            ((Villa) services).setAcreagePool(input.nextDouble());
            System.out.println("Enter num of floor:");
            ((Villa) services).setNumOfFloor(input.nextInt());
        } else if (services instanceof House) {
            input.nextLine();
            System.out.println("Enter type of room:");
            ((House) services).setTypeOfRoom(input.nextLine());
            System.out.println("Enter other amenities:");
            ((House) services).setOtherAmenities(input.nextLine());
            System.out.println("Enter num of floor:");
            ((House) services).setNumOfFloor(input.nextInt());
        } else if (services instanceof Room) {
            input.nextLine();
            ServiceFree serviceFreeObj = new ServiceFree();
            System.out.println("Enter service free name (massage or karaoke or food or drink or car):");
            serviceFreeObj.setServiceFreeName(input.nextLine());
            System.out.println("Enter unit:");
            serviceFreeObj.setUnit(input.nextInt());
            System.out.println("Enter price:");
            serviceFreeObj.setPrice(input.nextInt());
            ((Room) services).setServiceFreeObj(serviceFreeObj);
        }
    }

    public static void showServices() {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            System.out.println("1. Show all Villa:");
            System.out.println("2. Show all house:");
            System.out.println("3. Show all room:");
            System.out.println("4. Show All Name Villa Not Duplicate:");
            System.out.println("5. Show All Name House Not Duplicate:");
            System.out.println("6. Show All Name Name Not Duplicate:");
            System.out.println("7. Back to menu:");
            System.out.println("8. Exit:");
            int choose = input.nextInt();
            switch (choose) {
                case 1:
                    ArrayList<Services> showListDataVilla = new ArrayList<Services>();
                    showListDataVilla = WriteAndReadFileCSV.readFileVilla();
                    for (Services services : showListDataVilla) {
                        ((Villa) services).showInfor();
                    }
                    break;
                case 2:
                    ArrayList<Services> showListDataHouse = new ArrayList<Services>();
                    showListDataHouse = WriteAndReadFileCSV.readFileHouse();
                    for (Services services : showListDataHouse) {
                        ((House) services).showInfor();
                    }
                    break;
                case 3:
                    ArrayList<Services> showListDataRoom = new ArrayList<Services>();
                    showListDataRoom = WriteAndReadFileCSV.readFileRoom();
                    for (Services services : showListDataRoom) {
                        ((Room) services).showInfor();
                    }
                    break;
                case 4:
                    ArrayList<Services> villaNotDup = WriteAndReadFileCSV.readFileVilla();
                    TreeSet<String> treeSetVilla = new TreeSet<String>();
                    for (Services services : villaNotDup) {
                        treeSetVilla.add(((Villa) services).getServiceName());
                    }
                    for (String str : treeSetVilla) {
                        System.out.println(str);
                    }
                    break;
                case 5:
                    ArrayList<Services> houseNotDup = WriteAndReadFileCSV.readFileHouse();
                    TreeSet<String> treeSetHouse = new TreeSet<String>();
                    for (Services services : houseNotDup) {
                        treeSetHouse.add(((House) services).getServiceName());
                    }
                    for (String str : treeSetHouse) {
                        System.out.println(str);
                    }
                    break;
                case 6:
                    ArrayList<Services> roomNotDup =WriteAndReadFileCSV.readFileRoom();
                    TreeSet<String> treeSetRoom = new TreeSet<String>();
                    for (Services services : roomNotDup) {
                        treeSetRoom.add(((Room) services).getServiceName());
                    }
                    for (String str : treeSetRoom) {
                        System.out.println(str);
                    }
                    break;
                case 7:
                    MainController.displayMainMenu();
                    break;
                case 8:
                    check = true;
                    break;
            }
        }
    }

    public static void addNewRoom() {
        ArrayList<Services> listServiceRoom = new ArrayList<Services>();
        Services roomObj = new Room();
        inputInstanceServices(roomObj);
        checkValue(roomObj);
        listServiceRoom.add(roomObj);
        WriteAndReadFileCSV.writeFile(listStringRoom(listServiceRoom), ObjectFurama.ROOM);
        System.out.println("successfully added");
    }

    public static List<String> listStringRoom(ArrayList<Services> roomArrayList) {
        List<String> stringList = new ArrayList<>();
        for (Services room : roomArrayList) {
            stringList.add(room.toString());
        }
        return stringList;
    }

    public static void addNewHouse() {
        ArrayList<Services> listServiceHouse = new ArrayList<Services>();
        Services houseObj = new House();
        inputInstanceServices(houseObj);
        checkValue(houseObj);
        listServiceHouse.add(houseObj);
        WriteAndReadFileCSV.writeFile(listStringHouse(listServiceHouse),ObjectFurama.HOUSE);
        System.out.println("successfully added");
    }

    public static List<String> listStringHouse(ArrayList<Services> houseArrayList) {
        List<String> stringList = new ArrayList<>();
        for (Services house : houseArrayList) {
            stringList.add(house.toString());
        }
        return stringList;
    }

    public static void addNewVilla() {
        ArrayList<Services> listServiceVilla = new ArrayList<Services>();
        Services villaObj = new Villa();
        inputInstanceServices(villaObj);
        checkValue(villaObj);
        listServiceVilla.add(villaObj);
        WriteAndReadFileCSV.writeFile(listStringVilla(listServiceVilla),ObjectFurama.VILLA);
        System.out.println("successfully added");
    }

    public static List<String> listStringVilla(ArrayList<Services> villaArrayList) {
        List<String> stringList = new ArrayList<>();
        for (Services villa : villaArrayList) {
            stringList.add(villa.toString());
        }
        return stringList;
    }
    static void checkValue(Services services) {
        Scanner input = new Scanner(System.in);
        boolean check = false;
        while (!check) {
            if (services instanceof Villa) {
                if (!Pattern.matches("^SVVL-[0-9]{4}$", ((Villa) services).getId())) {
                    System.out.println("Enter Id (SVVL-XXXX):");
                    services.setId(input.nextLine());
                } else {
                    check = true;
                }
            } else if (services instanceof House) {
                if (!Pattern.matches("^SVHO-[0-9]{4}$", ((House) services).getId())) {
                    System.out.println("Enter Id (SVHO-XXXX):");
                    services.setId(input.nextLine());
                } else {
                    check = true;
                }
            } else if (services instanceof Room) {
                if (!Pattern.matches("^SVRO-[0-9]{4}$", ((Room) services).getId())) {
                    System.out.println("Enter Id (SVRO-XXX):");
                    services.setId(input.nextLine());
                } else {
                    check = true;
                }
            }
        }
        check = false;
        while (!check) {
            if (!Pattern.matches("[A-Z][a-z]+", services.getServiceName())) {
                System.out.println("Enter service name - Service name must be first capital letters:");
                services.setServiceName(input.nextLine());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services.getAreaUsed() < 30) {
                System.out.println("Enter use acreage (>30):");
                services.setAreaUsed(input.nextDouble());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services instanceof Villa) {
                if (((Villa) services).getAcreagePool() < 30) {
                    System.out.println("Enter acreage pool (>30):");
                    ((Villa) services).setAcreagePool(input.nextDouble());
                } else {
                    check = true;
                }
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services.getRentalCosts() < 0) {
                System.out.println("Enter rental costs (>0):");
                services.setRentalCosts(input.nextInt());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services.getMaxPeople() < 0 && services.getMaxPeople() > 20) {
                System.out.println("Enter max people (>0 and <20):");
                services.setMaxPeople(input.nextInt());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services instanceof Room) {
                if (!Pattern.matches("(massage)|(karaoke)|(food)|(drink)|(car)",
                        ((Room) services).getServiceFreeObj().getServiceFreeName())) {
                    System.out.println("Enter service free name (massage or karaoke or food or drink or car):");
                    ((Room) services).getServiceFreeObj().setServiceFreeName(input.nextLine());
                } else {
                    check = true;
                }
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services instanceof Villa) {
                if (((Villa) services).getNumOfFloor() < 0) {
                    System.out.println("Enter num of floor (>0):");
                    ((Villa) services).setNumOfFloor(input.nextInt());
                } else {
                    check = true;
                }
            } else if (services instanceof House) {
                if (((House) services).getNumOfFloor() < 0) {
                    System.out.println("Enter num of floor (>0):");
                    ((House) services).setNumOfFloor(input.nextInt());
                } else {
                    check = true;
                }
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (!Pattern.matches("[A-Z][a-z]+", services.getRentalType())) {
                System.out.println("Enter rental type:");
                services.setRentalType(input.nextLine());
            } else {
                check = true;
            }
        }
        check = false;
        while (!check) {
            if (services instanceof Villa) {
                if (!Pattern.matches("[A-Z][a-z]+", ((Villa) services).getTypeOfRoom())) {
                    System.out.println("Enter type of room - Type of room must be first capital letters:");
                    ((Villa) services).setTypeOfRoom(input.nextLine());
                } else {
                    check = true;
                }
            } else if (services instanceof House) {
                if (!Pattern.matches("[A-Z][a-z]+", ((House) services).getTypeOfRoom())) {
                    System.out.println("Enter type of room - Type of room must be first capital letters:");
                    ((House) services).setTypeOfRoom(input.nextLine());
                } else {
                    check = true;
                }
            } else {
                check = true;
            }
        }

    }
}
