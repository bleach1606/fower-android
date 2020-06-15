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
        SHIPPING(5, "Đang hàng đang được giao"),
        COMPLETED(6, "Đang hàng đã hoàn thành"),
        RECEIVED(7, "Đã nhận được hàng"),
        FAILED(8, "Đơn hàng bị huỷ");

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

        public String getSex() {
            return status;
        }

        public void setSex(String status) {
            this.status = status;
        }

    }

}
