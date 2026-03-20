package com.wanted.only_one.payments;

import com.wanted.only_one.global.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class    PaymentDAO {

    private static Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public static boolean payingMoney(String email) throws SQLException {
        String query = QueryUtil.getQuery("payment.payMoney");
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);

            int result = pstmt.executeUpdate();
            return result > 0;
        }

    }

    public List<PaymentDTO> findPayment(String payEmail) throws SQLException {
        String query = QueryUtil.getQuery("payment.findPayment");
        List<PaymentDTO> payList = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, payEmail);

            ResultSet rset = pstmt.executeQuery();

            while(rset.next()) {

                    PaymentDTO payment = new PaymentDTO(
                        rset.getTimestamp("결제일"),
                        rset.getLong("결제금액")
                    );
                    payList.add(payment);
            }
            return payList;
        }


    }

    public boolean checkEmail(String value) throws SQLException {
        String query = QueryUtil.getQuery("payment_checkEmail");

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, value);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }
}
