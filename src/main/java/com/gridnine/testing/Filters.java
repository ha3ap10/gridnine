package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Filters{

    private final List<Segment> segmentList;

    public Filters(Flight flight) {
        segmentList = flight.getSegments();
    }

    public boolean filterDepartureUntilCurrentTime() {
        return segmentList.stream()
                .anyMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()));
    }

    public boolean filterArrivalEarlierDeparture() {
        return segmentList.stream()
                .anyMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate()));
    }

    public boolean filterTimeExceedsTwoHours() {
        int minutes = 0;

        if (segmentList.size() > 1) {
            for (int i = 0; i < segmentList.size() - 1; i++) {
                minutes += Duration.between(segmentList.get(i).getArrivalDate(),
                        segmentList.get(i + 1).getDepartureDate()).toMinutes();
            }
        }
        return minutes <= 120;
    }

    @Override
    public String toString() {
        return segmentList.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
