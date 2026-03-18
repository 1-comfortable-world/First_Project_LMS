package com.wanted.only_one.payments;

import com.wanted.only_one.global.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PaymentDTO> findPayment() throws SQLException {
        String query = QueryUtil.getQuery("payment.findPayment");
        List<PaymentDTO> payList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                PaymentDTO payment = new PaymentDTO(
                        rset.getLong("member_id"),
                        rset.getTimestamp("payed_at"),
                        rset.getLong("price")
                );
                payList.add(payment);
            }
        }
        return payList;

    }
}
