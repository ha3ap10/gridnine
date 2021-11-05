package com.gridnine.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FiltersTest {

    List<Segment> segmentList;
    Filters filters;

    @BeforeEach
    public void start() {
        System.out.print("Start test ");
        segmentList = new ArrayList<>();
        filters = new Filters(new Flight(segmentList));
    }

    @AfterEach
    public void end() {
        segmentList = null;
        filters = null;
        System.out.print("End test");
    }

    @Test
    public void testDepartureUntilCurrentTime() {
        System.out.print("testDepartureUntilCurrentTime\n");

        segmentList.add(new Segment(LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(2)));

        Assertions.assertFalse(filters.filterDepartureUntilCurrentTime());
    }

    @Test
    public void testArrivalEarlierDeparture() {
        System.out.print("testArrivalEarlierDeparture\n");

        segmentList.add(new Segment(LocalDateTime.now().plusHours(5),
                LocalDateTime.now().plusHours(2)));

        Assertions.assertFalse(filters.filterArrivalEarlierDeparture());
    }

    @Test
    public void testTimeExceedsTwoHours() {
        System.out.print("testTimeExceedsTwoHours\n");

        segmentList.add(new Segment(LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(3)));
        segmentList.add(new Segment(LocalDateTime.now().plusHours(5).plusMinutes(1),
                LocalDateTime.now().plusHours(8)));

        Assertions.assertFalse(filters.filterTimeExceedsTwoHours());
    }
}
