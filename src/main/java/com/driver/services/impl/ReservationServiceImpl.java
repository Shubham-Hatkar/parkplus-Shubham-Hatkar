package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception
    {
        User user = userRepository3.findById(userId).get();
        if(user == null) throw new Exception("Cannot make reservation");

        ParkingLot parkingLot = parkingLotRepository3.findById(parkingLotId).get();
        if(parkingLot == null)  throw new Exception("Cannot make reservation");

        List<Spot> spotList = parkingLot.getSpotList();
        boolean isSpotAvailable = false;
        for(Spot spot : spotList)
        {
            if(spot.getOccupied() == false)
            {
                isSpotAvailable = true;
                break;
            }
        }
        if(isSpotAvailable == false) throw new Exception("Cannot make reservation");

        SpotType spotType;
        if(numberOfWheels > 4) spotType = SpotType.OTHERS;
        else if(numberOfWheels > 2) spotType = SpotType.FOUR_WHEELER;
        else spotType = spotType = SpotType.TWO_WHEELER;

        isSpotAvailable = false;

        int minPrice = Integer.MAX_VALUE;
        Spot minPriceSpot = null;
        for(Spot spot : spotList)
        {
            if(spotType.equals(SpotType.OTHERS) && spot.getSpotType().equals(SpotType.OTHERS))
            {
                if(spot.getPricePerHour() * timeInHours < minPrice && spot.getOccupied() == false)
                {
                    minPrice = spot.getPricePerHour() * timeInHours;
                    isSpotAvailable = true;
                    minPriceSpot = spot;
                }
            }
            else if(spotType.equals(SpotType.FOUR_WHEELER) && spot.getSpotType().equals(SpotType.OTHERS)
                    || spot.getSpotType().equals(SpotType.FOUR_WHEELER))
            {
                if(spot.getPricePerHour() * timeInHours < minPrice && spot.getOccupied() == false)
                {
                    minPrice = spot.getPricePerHour() * timeInHours;
                    isSpotAvailable = true;
                    minPriceSpot = spot;
                }
            }
            else if(spotType.equals(SpotType.TWO_WHEELER) && spot.getSpotType().equals(SpotType.OTHERS)
            || spot.getSpotType().equals(SpotType.FOUR_WHEELER) || spot.getSpotType().equals(SpotType.TWO_WHEELER))
            {
                if(spot.getPricePerHour() * timeInHours < minPrice && spot.getOccupied() == false)
                {
                    minPrice = spot.getPricePerHour() * timeInHours;
                    isSpotAvailable = true;
                    minPriceSpot = spot;
                }
            }
        }

        if(isSpotAvailable == false) throw new Exception("Cannot make reservation");

        Reservation reservation = new Reservation();
        minPriceSpot.setOccupied(true);
        reservation.setSpot(minPriceSpot);
        reservation.setNumberOfHours(timeInHours);
        reservation.setUser(user);

        minPriceSpot.getReservationList().add(reservation);
        user.getReservationList().add(reservation);

        userRepository3.save(user);
        spotRepository3.save(minPriceSpot);

        return  reservation;
    }
}
