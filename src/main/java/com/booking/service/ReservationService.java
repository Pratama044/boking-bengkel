package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;


public class ReservationService {
    public static Scanner input = new Scanner(System.in);
    public static Reservation createReservation(List<Reservation> listRev, List<Person> listPerson, List<Service> listSer,String title){
        String idReservation = "";
        boolean isLopoping = true;
        do{
            System.out.println("Diharapkan Id diawali dengan Rsv-");
            System.out.println();
            System.out.print("Masukan ID Reservation : ");
            idReservation = input.nextLine();
            for (Reservation list : listRev) {
                if(idReservation.equalsIgnoreCase(list.getReservationId())){
                    isLopoping = false;
                }
            }
            if(isLopoping == false){
                System.out.println("Id sudah ada, Masukan Id lain");
            }
        }while(!isLopoping != false);
        
        Customer customer = getCustomerByCustomerId(listPerson);
        Employee employee = getEmployeByEmployeeId(listPerson);
        List<Service> services = getServiceByServiceId(listSer, idReservation);
        // System.out.print("Masukan Id Reservation : ");
        // String IdReservation = input.nextLine();

        for(Reservation list : listRev){
            Reservation reservation1 = new Reservation(idReservation, customer, employee, services, 0, "in process");
            return reservation1;

        }
        return null;
        
    }

    public static Customer getCustomerByCustomerId(List<Person> listPerson){
        boolean isCustomerId = false;
        String idCustomer ;
        Customer name;
        PrintService.showAllCustomer(listPerson, "Customer");
        do{
            System.out.print("Masukan Customer Id : ");
            String costumerId = input.nextLine();
            for(Person list : listPerson){
                if(list instanceof Customer){
                    if(costumerId.equalsIgnoreCase(((Customer)list).getId())){
                        isCustomerId = true;
                    }
                }
                
            }
            if(isCustomerId == true){
                idCustomer = costumerId;
            }else{
                System.out.println("Customer yang anda cari tidak di temukan");
            }

        }while(!isCustomerId != false);
        return null;
    }

    public static Employee getEmployeByEmployeeId(List<Person> listPerson){
        boolean isCustomerId = false;
        String idCustomer;
        PrintService.showAllEmployee(listPerson, "Employee");
        do{
            System.out.print("Masukan Employee Id : ");
            String costumerId = input.nextLine();
            for(Person list : listPerson){
                if(list instanceof Employee){
                    if(costumerId.equalsIgnoreCase(((Employee)list).getId())){
                        isCustomerId = true;
                    }
                }
                
            }
            if(isCustomerId == true){
                idCustomer = costumerId;
            }else{
                System.out.println("Employee Id yang anda cari tidak di temukan");
            }

        }while(!isCustomerId != false);
        return null;
    }
    public static List<Service> getServiceByServiceId(List<Service> listService, String costumerId){
        int num = 1;
        boolean isCustomerId = false;
        String idCustomer;
        String comfirm = " ";
        boolean isConfirm = false;
        Service service;
        System.out.println();
        PrintService.showAllService(listService, "Service");
        double price = 0;
        do{
            System.out.print("Masukan Service Id : ");
            costumerId = input.nextLine();
            for(Service list : listService){
                if(costumerId.equalsIgnoreCase(list.getServiceId())){
                    isCustomerId = true;
                    price += list.getPrice();
                }
                
            }
            if(isCustomerId == true){
                    System.out.println("Ingin Pilih Service Yang lain (Y/T)");
                    comfirm = input.nextLine();
                    if(comfirm.equalsIgnoreCase("Y")){
                        getServiceByServiceId(listService, costumerId);
                    }else if(comfirm.equalsIgnoreCase("T")){
                        isConfirm = false;
                        System.out.println("Boking berhasil\n");
                        System.out.println("Total biaya boking Rp. "+(int)price);
                    }
            }else{
                System.out.println("Service yang anda cari tidak di temukan");
            }


        }while(!isCustomerId != false && !isConfirm != false);
        return null;
        
    }
    public static String printServicesId(List<Service>listService, String result){
        result = "";
        for(Service list : listService){
            result += list.getServiceId();
        }
        return result;
    }
    public static String printServicesNama(List<Service>listService){
        String result = "";
        for(Service list : listService){
            result += list.getServiceName();
        }
        return result;
    }

    public static void editReservationWorkstage(List<Reservation> listReservation,List<Service> listService,String title){
        int num= 1;
        boolean isTrue = false;
        System.out.println("================ "+title+ " ===============");
        String formatTable = "| %-2s | %-5s | %-10s | %-10s |\n";
        System.out.format(formatTable, "No", "ID", "Nama Service", "Harga");
        for(Reservation list: listReservation){
            if(list.getWorkstage().equalsIgnoreCase("in process")){
                System.out.format(formatTable, num,
                printServicesId(list.getServices(),
                printServicesNama(list.getServices())),
                list.getReservationPrice());
            }

            
        }
        String idReservation;
        do{
            System.out.print("Silahkan Masukan Reservation ID : ");
            idReservation = input.nextLine();
            for(Reservation list : listReservation){
                if(idReservation.equalsIgnoreCase(list.getReservationId())){
                    isTrue = true;
                }
            }
            if (isTrue == true) {
                System.out.println("");
            }else{
                System.out.println("Id Reservation tidak ditemukan");
            }

        }while(!isTrue != false);

        ValidationService.validateWorkStage(listReservation, idReservation);

    }

    public static void showHistoryReservation(List<Reservation> listReservation, String title){
        int num = 1;
        double keuntungan = 0;
        System.out.println("=========================== "+title+ " ==============================");
        System.out.println("+========================================================================================+");
        String formatTable = "| %-2s | %-10s | %-10s | %-20s | %-15s | %-10s |\n";
        System.out.format(formatTable, "No", "ID", "Nama Customer", "Service", "Total Biaya", "Workstage");
        for (Reservation list : listReservation) {
            System.out.format(formatTable, num,
            list.getReservationId(),
            list.getCustomer().getName(),
            printServicesNama(list.getServices()),
            "Rp. "+(int)list.getReservationPrice(),
            list.getWorkstage());
            num++;
            if(list.getWorkstage().equalsIgnoreCase("Finish")){
                keuntungan += list.getReservationPrice();
            }
            
        }
        System.out.println("Total Keuntungan                                          |Rp. "+(int)keuntungan+"                |");
        System.out.println("+========================================================================================+");
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
