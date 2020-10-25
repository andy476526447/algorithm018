package com.example.javalib.arg;

import java.util.List;

//leetcode-21合并两个有序链表
public class Test_21 {


    public static void main(String[] args) {
        Test_21 test_21 = new Test_21();
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(2);
        ListNode l1_3 = new ListNode(4);
        l1_1.next = l1_2;
        l1_2.next = l1_3;

        ListNode l2_1 = new ListNode(1);
        ListNode l2_2 = new ListNode(3);
        ListNode l2_3 = new ListNode(4);
        l2_1.next = l2_2;
        l2_2.next = l2_3;

        test_21.mergeTwoLists(l1_1, l2_1);
    }

    //暴力法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode curNode = new ListNode();
        ListNode newLink = curNode;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curNode.next = l1;
                l1 = l1.next;
            } else {
                curNode.next = l2;
                l2 = l2.next;
            }
            curNode = curNode.next;
        }

        curNode.next = l1 == null ? l2 : l1;

        /*if (l1 != null) {
            curNode.next = l1;
        }

        if (l2 != null) {
            curNode.next = l2;
        }*/

        /*newLink = newLink.next;
        while (newLink != null) {
            System.out.println(newLink.val);
            newLink = newLink.next;
        }*/

        return newLink.next;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
