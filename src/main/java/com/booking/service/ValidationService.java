package com.booking.service;

import java.util.List;
import java.util.Scanner;

import com.booking.models.Reservation;

public class ValidationService {
    public static Scanner input = new Scanner(System.in);
    // Buatlah function sesuai dengan kebutuhan
    public static void validateInput(){

    }

    public static void validateCustomerId(){

    }

    public static void validateWorkStage(List<Reservation>listRes, String id){
        boolean isTrue = false;
        String newId = "";
        do{
            System.out.println("Selesaikan Reservasi");
            String status = input.nextLine();
            for(Reservation list : listRes){
                if(id.equalsIgnoreCase(list.getReservationId())){
                    list.setWorkstage(status);
                    newId = list.getReservationId();
                    isTrue = true;
                }
            }
            if(isTrue == true){
                if(status.equalsIgnoreCase("Finish")){
                   System.out.println("Reservasi dengan Id "+newId + " sudah Finish");
                }else if(status.equalsIgnoreCase("Cancel")){
                   System.out.println("Reservasi dengan Id "+ newId+ " sudah Cancel");
                }
            }else{
                System.out.println("Masukan Reservasi dengan benar!!");
            }
            
        }while(!isTrue != false);
    }
}
