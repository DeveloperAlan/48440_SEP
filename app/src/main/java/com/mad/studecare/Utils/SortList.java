package com.mad.studecare.Utils;

import com.mad.studecare.Models.TimeSlots.TimeSlots;

import java.util.Comparator;

public class SortList implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        TimeSlots slot1 = (TimeSlots) o;
        TimeSlots slot2 = (TimeSlots) t1;
        int result;
        if (slot1.getDate().equals(slot2.getDate())) {
//            if(slot1.getTime().split(":")[0].equals(slot2.getTime().split(":")[0])){
//                result = -(slot1.getTime().split(":")[1].compareTo(slot2.getTime().split(":")[1]));
//            }else {
//                result = -(slot1.getTime().split(":")[0].compareTo(slot2.getTime().split(":")[0]));
//            }
            result = -(slot1.getTime().compareTo(slot2.getTime()));
        } else {
            if (slot1.getDate().split("/")[2].equals(slot2.getDate().split("/")[2])) {
                if (slot1.getDate().split("/")[1].equals(slot2.getDate().split("/")[1])) {
                    result = slot1.getDate().split("/")[0].compareTo(slot2.getDate().split("/")[0]);
                } else {
                    result = slot1.getDate().split("/")[1].compareTo(slot2.getDate().split("/")[1]);
                }
            } else {
                result = slot1.getDate().split("/")[2].compareTo(slot2.getDate().split("/")[2]);
            }
        }
        return result;
    }
}
