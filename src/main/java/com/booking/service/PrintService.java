package com.booking.service;

import java.util.List;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (int i = 0; i < serviceList.size(); i++) {
            if(i == serviceList.size() - 1){
                result += serviceList.get(i).getServiceName() + "";
            }else{
                result += serviceList.get(i).getServiceName() + "";
            }
        }
        return result;
    }
    

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public static void showRecentReservation(List<Reservation> reservationList, String title){
        int num = 1;
        System.out.println("=========================== "+title+ " ==============================");
        String formatTable = "| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n";
        System.out.format(formatTable,"No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.format(formatTable,num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public static void showAllCustomer(List<Person> listCust, String title){
        int num = 1;
        Customer cust;
        System.out.println("======================= "+title+ " ======================");
        String formatTable= "| %-2s | %-8s | %-15s | %-10s | %-10s | %-10s |\n";
        System.out.format(formatTable, "No", "ID", "Nama Customer", "Alamat", "Membership", "Uang");
        System.out.println("=============================================================");
        for(Person list: listCust){
            if(list instanceof Customer){
                cust = (Customer)list;
                System.out.format(formatTable, num,
                list.getId(),
                list.getName(),
                list.getAddress(),
                ((Customer)list).getMember().getMembershipName(),
                formatCurency(cust.getWallet())); 
                num++;
            }
        }
        System.out.println("=============================================================");
    }
    public static void showAllService(List<Service>listService, String title){
        int num = 1;
        System.out.println("========================== "+title+ " ========================");
        String formatTable = "| %-2s | %-10s | %-20s | %-15s |\n";
        System.out.format(formatTable, "No", "ID", "Nama Service", "Harga");
        System.out.println("============================================================");
        for(Service list: listService){
            System.out.format(formatTable, num,
            list.getServiceId(),
            list.getServiceName(),
            "Rp."+(int)list.getPrice());
            num++;
        }
        System.out.println("============================================================");
    }

    public static void showAllEmployee(List<Person> listEmploye, String title){
        int num = 1;
        System.out.println("====================== "+title+ " =====================");
        String formatTable= "| %-2s | %-8s | %-15s | %-10s | %-10s |\n";
        System.out.format(formatTable, "No", "ID", "Nama Employe", "Alamat", "Pengalaman");
        System.out.println("=============================================================");
        for(Person list: listEmploye){
            if(list instanceof Employee){
                System.out.format(formatTable, num,
                list.getId(),
                list.getName(),
                list.getAddress(),
                ((Employee)list).getExperience());
                num++;
            }
        }
        System.out.println("=============================================================");

    }

    public void showHistoryReservation(){
        
    }
    public static String formatCurency(double uang){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("Rp#,##0", symbols); 

        return df.format(uang);
    }
}
