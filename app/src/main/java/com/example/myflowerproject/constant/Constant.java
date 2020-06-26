package com.example.myflowerproject.constant;


import java.util.Arrays;

public class Constant {
    public static final Long ACTIVE = 1L;
    public static final Long UN_ACTIVE = 0L;

    public enum Sex {

        MALE(1, "Nam"),
        FEMALE(2, "Nữ");

        private int id;
        private String sex;

        Sex() {
        }

        Sex(int id, String sex) {
            this.id = id;
            this.sex = sex;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    public enum Role {

        CUSTOMER(1, "Khách hàng"),
        STAFF(2, "Nhân viên"),
        MANAGE(3, "Quản lý"),
        ADMIN(4, "Quản trị viên");

        private int id;
        private String role;

        Role() {
        }

        Role(int id, String role) {
            this.id = id;
            this.role = role;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }

    public enum OrderStatus {

        NEW(1, "Trạng thái giỏ hàng"),
        WAIT(2, "Chờ xác nhận đặt hàng"),
        CONFIRM(3, "Xác nhận đặt hàng thành công"),
        PACKING(4, "Đơn hàng đang được đóng gói"),
        SHIPPING(5, "Đơn hàng đang được giao"),
        RECEIVED(6, "Đơn hàng đã hoàn thành"),
        CANCEL(7, "Hủy đơn hàng"),
        FAILED(8, "Đơn hàng bị huỷ"),
        END(9, "END GAME !!!"),
        DONE(10, "Hoàn thành");

        private int id;
        private String status;

        OrderStatus() {
        }

        OrderStatus(int id, String status) {
            this.id = id;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public String getOrderStatus(int k) {
        String s = "";
        switch (k) {
            case 2:
                s = OrderStatus.WAIT.status;
                break;
            case 3:
                s = OrderStatus.CONFIRM.status;
                break;
            case 4:
                s = OrderStatus.PACKING.status;
                break;
            case 5:
                s = OrderStatus.SHIPPING.status;
                break;
            case 6:
                s = OrderStatus.RECEIVED.status;
                break;
            case 7:
                s = OrderStatus.CANCEL.status;
                break;
            case 8:
                s = OrderStatus.FAILED.status;
                break;
            case 9:
                s = OrderStatus.END.status;
                break;
            case 10:
                s = OrderStatus.DONE.status;
                break;
        }
        return s;
    }

}
