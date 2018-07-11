package com.example.mockbus.configs;

import com.example.mockbus.DTO.TicketDto;
import com.example.mockbus.entities.RoleDomain;
import com.example.mockbus.entities.TicketDomain;
import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.repositories.RoleRepository;
import com.example.mockbus.repositories.UserRepository;
import com.example.mockbus.services.RouteService;
import com.example.mockbus.services.StationService;
import com.example.mockbus.services.TicketService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataBootstrap implements
    ApplicationListener<ContextRefreshedEvent> {

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private RouteService routeService;
  private StationService stationService;
  private TicketService ticketService;
  private Converter<TicketDomain, TicketDto> ticketDomainToTIcketDto;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public DataBootstrap(UserRepository userRepository,
      RoleRepository roleRepository, RouteService routeService,
      StationService stationService, TicketService ticketService,
      Converter<TicketDomain, TicketDto> ticketDomainToTIcketDto) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.routeService = routeService;
    this.stationService = stationService;
    this.ticketService = ticketService;
    this.ticketDomainToTIcketDto = ticketDomainToTIcketDto;
  }

  /**
   * Handle an application event.
   *
   * @param event the event to respond to
   */
  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {

    RoleDomain adminRole = new RoleDomain("ROLE_ADMIN");
    roleRepository.save(adminRole);
    RoleDomain userRole = new RoleDomain();
    userRole.setName("ROLE_USER");
    roleRepository.save(userRole);
    RoleDomain employeeRole = new RoleDomain();
    employeeRole.setName("ROLE_EMPLOYEE");
    roleRepository.save(employeeRole);

    UserDomain userDomain = new UserDomain();
    userDomain.setEmail("hieupbc@gmail.com");
    userDomain.setName("Hieu Hoang");
    userDomain.setPassword(passwordEncoder.encode("123"));
    Set<RoleDomain> roles = userDomain.getRoles();
    roles.add(adminRole);
    roles.add(employeeRole);
    roles.add(userRole);
    userDomain.setRoles(roles);
    userRepository.save(userDomain);

    userDomain = new UserDomain();
    userDomain.setEmail("member@gmail.com");
    userDomain.setName("Member");
    userDomain.setPassword(passwordEncoder.encode("123"));
    roles = userDomain.getRoles();
    roles.add(employeeRole);
    userDomain.setRoles(roles);
    userRepository.save(userDomain);

    userDomain = new UserDomain();
    userDomain.setEmail("admin@gmail.com");
    userDomain.setName("Admin");
    userDomain.setPassword(passwordEncoder.encode("123"));
    roles = userDomain.getRoles();
    roles.add(employeeRole);
    userDomain.setRoles(roles);
    userRepository.save(userDomain);

    userDomain = new UserDomain();
    userDomain.setEmail("customer@gmail.com");
    userDomain.setName("Customer");
    userDomain.setPassword(passwordEncoder.encode("123"));
    roles = userDomain.getRoles();
    roles.add(userRole);
    userDomain.setRoles(roles);
    userRepository.save(userDomain);

    userDomain = new UserDomain();
    userDomain.setEmail("deactived@gmail.com");
    userDomain.setName("Deactived");
    userDomain.setPassword(passwordEncoder.encode("123"));
    userDomain.setEnabled(false);
    roles = userDomain.getRoles();
    roles.add(userRole);
    userDomain.setRoles(roles);
    userRepository.save(userDomain);

  }
}
