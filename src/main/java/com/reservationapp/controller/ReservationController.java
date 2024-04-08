package com.reservationapp.controller;


import com.reservationapp.entity.*;

import com.reservationapp.repository.*;

import com.reservationapp.util.EmailService;
import com.reservationapp.util.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private PdfGeneratorService pdfGeneratorService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subrouteRepository;
    @PostMapping
    public ResponseEntity<String> bookTicket(
            @RequestParam long busId,
            @RequestParam long routeId,
            @RequestBody Passenger passenger
    ) throws MessagingException, IOException {
        boolean busIspresent = false;
        boolean routeIspresent = false;
        boolean subRouteIspresent = false;

        Optional<Bus> ById = busRepository.findById(busId);

        if(ById.isPresent()){
            busIspresent = true;
            Bus bus = ById.get();
        }

        Optional<Route> byId = routeRepository.findById(routeId);

        if(byId.isPresent()){
            routeIspresent = true;
            Route route = byId.get();
        }

        Optional<SubRoute> bySubRouteId = subrouteRepository.findById(routeId);

        if(bySubRouteId.isPresent()){
            subRouteIspresent = true;
            SubRoute subRoute = bySubRouteId.get();
        }

        if(busIspresent && routeIspresent || busIspresent && subRouteIspresent){

            Passenger p = new Passenger();
            p.setFirstName(passenger.getFirstName());
            p.setLastName(passenger.getLastName());
            p.setEmail(passenger.getEmail());
            p.setMobile(passenger.getMobile());
            p.setRouteId(routeId);
            p.setBusId(busId);

            sendPdfByEmail(p);
            passengerRepository.save(p);
        }

        return new ResponseEntity<>("Done...", HttpStatus.OK);
    }

    public void sendPdfByEmail(Passenger p) {
        try {
            byte[] pdfBytes = pdfGeneratorService.generatePdfFromPassenger(p);
            emailService.sendEmailWithAttachment(p.getEmail(), "Passenger Details", pdfBytes, "passenger_details.pdf");
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly
        }
    }
    @PostMapping("/passenger")
    public void sendEmailWithExcelAttachment(@RequestParam String to,
                                             @RequestParam String subject) throws MessagingException, IOException {
        emailService.sendEmailWithExcelAttachment(to, subject);

    }
}
