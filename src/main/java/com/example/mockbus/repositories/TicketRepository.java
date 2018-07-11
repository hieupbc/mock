package com.example.mockbus.repositories;

import com.example.mockbus.entities.JourneyDomain;
import com.example.mockbus.entities.TicketDomain;
import com.example.mockbus.entities.UserDomain;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketDomain, Integer> {

  List<TicketDomain> findByJourney(JourneyDomain journeyDomain);

  List<TicketDomain> findByUser(UserDomain userDomain);

}
