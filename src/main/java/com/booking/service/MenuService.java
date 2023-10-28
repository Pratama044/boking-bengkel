package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Show History Reservation","Back to main menu"};
    
        int optionMainMenu;
        int optionSubMenu;

		boolean backToMainMenu = false;
        boolean backToSubMenu = false;
        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            optionMainMenu = input.nextInt();
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        optionSubMenu = input.nextInt();
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                // panggil fitur tampilkan recent reservation
                                PrintService.showRecentReservation(reservationList, "Tampilan Recent Reservation");
                                break;
                            case 2:
                                // panggil fitur tampilkan semua customer
                                PrintService.showAllCustomer(personList, "Tampilan Customers");
                                break;
                            case 3:
                                // panggil fitur tampilkan semua employee
                                PrintService.showAllEmployee(personList, "Tampilan Employee");
                                break;
                            case 4:
                                // panggil fitur tampilkan history reservation + total keuntungan
                                ReservationService.showHistoryReservation(reservationList, "Menu Histori Reservation");
                                break;
                            default:
                                break;
                        }
                    } while (optionSubMenu != 0 && optionSubMenu <= 4);
                    break;
                case 2:
                    // panggil fitur menambahkan reservation
                    reservationList.add(ReservationService.createReservation(reservationList, personList, serviceList, "Tampilan Add Recervation"));
                    
                    break;
                case 3:
                    // panggil fitur mengubah workstage menjadi finish/cancel
                    ReservationService.editReservationWorkstage(reservationList, serviceList,"Finish/Cansel Reservation");
                    break;
                case 0:
                    System.out.println("Terimakasih,dan sampai jumpa >_<");
                    break;
            }
        } while (optionMainMenu >= 1 && optionMainMenu <= 3); 
		
	}

}
