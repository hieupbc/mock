package com.example.mockbus.repositories;

import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.RouteDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JourneyRepositoryTest {

    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    RouteRepository routeRepository;

    @Test
    public void findAll() {
        Page<JourneyDomain> all = journeyRepository.findAll(PageRequest.of(1, 10));
        Page<JourneyDomain> all1 = journeyRepository.findAll(PageRequest.of(1, 5));
        assertEquals(10, all.getContent().size());
        assertEquals(10,all1.getTotalPages());
        assertEquals(JourneyDomain.class, all.getContent().get(1).getClass());
    }

    @Test
    public void findJourneyDomainsByDepartDateAndAndRoute() {
        LocalDate localDate = LocalDate.now();
        RouteDomain routeDomain = routeRepository.getOne(1);

        List<JourneyDomain> journeyDomainsByDepartDateAndAndRoute = journeyRepository.findJourneyDomainsByDepartDateAndAndRoute(localDate, routeDomain);
        assertEquals(3, journeyDomainsByDepartDateAndAndRoute.size());
    }

    @Test
    public void findJourneyDomainsByDepartDateAndAndRoute1() {
    }
}