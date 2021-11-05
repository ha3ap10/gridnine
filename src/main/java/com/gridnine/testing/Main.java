package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flightList = FlightBuilder.createFlights();

        System.out.println("\nСписок перелётов:");

        flightList
                .forEach(System.out::println);

        System.out.println("\nИсключены перелёты, в которых вылеты до текущего момента времени:");

        flightList.stream()
                .map(Filters::new)
                .filter(Filters::filterDepartureUntilCurrentTime)
                .forEach(System.out::println);

        System.out.println("\nИсключены перелёты, в которых имеются сегменты с датой прилёта раньше даты вылета:");

        flightList.stream()
                .map(Filters::new)
                .filter(Filters::filterArrivalEarlierDeparture)
                .forEach(System.out::println);

        System.out.println("\nИсключены перелёты, в которых общее время, проведённое на земле, превышает два часа:");

        flightList.stream()
                .map(Filters::new)
                .filter(Filters::filterTimeExceedsTwoHours)
                .forEach(System.out::println);
    }
}
