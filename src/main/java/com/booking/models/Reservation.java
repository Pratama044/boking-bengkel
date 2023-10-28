package com.booking.models;

import java.util.List;

import com.booking.repositories.ServiceRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice(services, customer);
        this.workstage = workstage;
    };



    public double calculateReservationPrice(List<Service> listService, Customer customer){
        double result = 0;
        String [] member = {"Silver", "Gold"};
        double [] diskon = {0.05, 0.10};
        double getDiskon = 0;
        double diskonService = 0;
        for(Service list : listService){
            if(customer.getMember().getMembershipName().equalsIgnoreCase("None")){
                result = result + list.getPrice();
            }else{
                for(int i = 0; i < diskon.length; i++){
                    if(customer.getMember().getMembershipName().equalsIgnoreCase(member[i])){
                        getDiskon = diskon[i];
                        result += list.getPrice();
                        diskonService = result * getDiskon;
                        result = result - diskonService;

                    }
                }
            }
        }
        
        return result;
    }
}
