package be.bstorm.akimts.rest.bxl.utils;

import java.time.LocalDateTime;

public class test {
    public static void main(String[] args) {
//        System.out.println(LocalDateTime.of(2022,8,8,0,0));//2022-08-08T00:00
//        System.out.println(LocalDateTime.now());
//
//        char a = 100;
//        System.out.println(a);

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime lastDayOfMonth  =  LocalDateTime.of(
                today.getYear(),
                today.getMonth(),
                today.getMonth().length(true),
                0,
                0
        );
        System.out.println(today);
        System.out.println(lastDayOfMonth);
    }
}
