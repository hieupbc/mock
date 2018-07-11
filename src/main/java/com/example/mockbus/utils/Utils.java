package com.example.mockbus.utils;

import com.example.mockbus.entities.SeatPlan;
import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
@Component
public class Utils {

    @Autowired
    private  UserService userService;

    public  String getPositionFromSeatnumber(int seatnumber, SeatPlan seatPlan, char emptySeatSymbol) {
        String[] plan = seatPlan.getPlan();
        int colAmount = plan[0].length();
        int rowAmount = plan.length;
        int seatRow = 0;
        int seatCol = 0;
        StringBuilder planString = new StringBuilder();
        for (int i = 0; i < rowAmount; i++) {
            planString.append(plan[i]);
        }
        for (int i = 0; i < planString.length(); i++) {
            if (i % colAmount == 0) {
                seatRow += 1;
                seatCol = 1;
            } else {
                seatCol += 1;
            }
            if (planString.charAt(i) != emptySeatSymbol) {
                seatnumber -= 1;
                if (seatnumber == 0) {
                    break;
                }
            }
        }
        return seatRow + "_" + seatCol;
    }

    public  boolean hasPermission(long id, String rolename) {
        Optional<UserDomain> userDomain = userService.find(id);
        boolean hasRole = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority role : authorities) {
            if (role.getAuthority().equals(rolename)) {
                hasRole = true;
            }
        }
        if(!hasRole&&userDomain.get().getEmail()!=authentication.getName()){
            return false;
        }
        return true;
    }
}
