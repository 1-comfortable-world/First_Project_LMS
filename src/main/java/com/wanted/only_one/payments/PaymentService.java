package com.wanted.only_one.payments;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentService {

    private PaymentDAO paymentDAO;
    private Connection connection;

    public PaymentService(Connection connection) {
        this.paymentDAO = new PaymentDAO(connection);
        this.connection = connection;
    }

    public List<PaymentDTO> findMyPayment() {

        try {
            return paymentDAO.findPayment();
        } catch (SQLException e) {
            throw new RuntimeException("결제 내역 조회 중 Error 발생!!" + e);
        }
    }
}
