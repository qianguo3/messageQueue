package com.badu.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserOrder implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        if (o1.getAge()<o2.getAge()){
            return 1;
        }
        else if (o1.getAge()>o2.getAge()){
            return -1;
        }

        return 0;
    }


    public static void main(String[] args) {
        List<User> list = new ArrayList<>();

        User u1 = new User(1);

        User u2 = new User(2);
        User u3 = new User(3);

        list.add(u3);
        list.add(u1);
        list.add(u2);


        list.sort(new UserOrder());
        for (User user : list) {
            System.out.println(user);
        }
    }
}
