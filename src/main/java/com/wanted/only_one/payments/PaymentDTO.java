package com.wanted.only_one.payments;

import com.wanted.only_one.course.dto.CourseDTO;

import java.sql.Timestamp;

public class PaymentDTO {
    private Long pay_id;
    private Long member_id;
    private Timestamp payed_at;
    private Long price;

    public PaymentDTO(Long pay_id, Long member_id, Timestamp payed_at, Long price) {

        this.pay_id = pay_id;
        this.member_id = member_id;
        this.payed_at = payed_at;
        this.price = price;
    }

    public PaymentDTO() {}





}
