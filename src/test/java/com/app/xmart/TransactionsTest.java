package com.app.xmart;

import static org.junit.jupiter.api.Assertions.*;

import com.app.xmart.model.Transactions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class TransactionsTest {

    @Test
    void testNoArgsConstructor() {
        Transactions transactions = new Transactions();
        assertNull(transactions.getTransactionId());
        assertNull(transactions.getCustomerId());
        assertNull(transactions.getRfid());
        assertNull(transactions.getProductPrice());
        assertNull(transactions.getQuantity());
        assertNull(transactions.getTransactionDatetime());
    }

    @Test
    void testAllArgsConstructor() {
        Integer transactionId = 1;
        Integer customerId = 101;
        Integer rfid = 12345;
        String productPrice = "100";
        Integer quantity = 2;
        LocalDateTime transactionDatetime = LocalDateTime.now();

        Transactions transactions = new Transactions(transactionId, customerId, rfid, productPrice, quantity, transactionDatetime);

        assertEquals(transactionId, transactions.getTransactionId());
        assertEquals(customerId, transactions.getCustomerId());
        assertEquals(rfid, transactions.getRfid());
        assertEquals(productPrice, transactions.getProductPrice());
        assertEquals(quantity, transactions.getQuantity());
        assertEquals(transactionDatetime, transactions.getTransactionDatetime());
    }

    @Test
    void testGetterSetterTransactionId() {
        Transactions transactions = new Transactions();
        Integer transactionId = 1;
        transactions.setTransactionId(transactionId);
        assertEquals(transactionId, transactions.getTransactionId());
    }

    @Test
    void testGetterSetterCustomerId() {
        Transactions transactions = new Transactions();
        Integer customerId = 101;
        transactions.setCustomerId(customerId);
        assertEquals(customerId, transactions.getCustomerId());
    }

    @Test
    void testGetterSetterRfid() {
        Transactions transactions = new Transactions();
        Integer rfid = 12345;
        transactions.setRfid(rfid);
        assertEquals(rfid, transactions.getRfid());
    }

    @Test
    void testGetterSetterProductPrice() {
        Transactions transactions = new Transactions();
        String productPrice = "100";
        transactions.setProductPrice(productPrice);
        assertEquals(productPrice, transactions.getProductPrice());
    }

    @Test
    void testGetterSetterQuantity() {
        Transactions transactions = new Transactions();
        Integer quantity = 2;
        transactions.setQuantity(quantity);
        assertEquals(quantity, transactions.getQuantity());
    }

    @Test
    void testGetterSetterTransactionDatetime() {
        Transactions transactions = new Transactions();
        LocalDateTime transactionDatetime = LocalDateTime.now();
        transactions.setTransactionDatetime(transactionDatetime);
        assertEquals(transactionDatetime, transactions.getTransactionDatetime());
    }

    @Test
    void testToString() {
        Transactions transactions = new Transactions(1, 101, 12345, "100", 2, LocalDateTime.of(2023, 1, 1, 12, 0, 0));
        String expectedToString = "Transactions(transactionId=1, customerId=101, rfid=12345, productPrice=100, quantity=2, transactionDatetime=2023-01-01T12:00)";
        assertEquals(expectedToString, transactions.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Transactions transactions1 = new Transactions(1, 101, 12345, "100", 2, LocalDateTime.of(2023, 1, 1, 12, 0, 0));
        Transactions transactions2 = new Transactions(1, 101, 12345, "100", 2, LocalDateTime.of(2023, 1, 1, 12, 0, 0));
        Transactions transactions3 = new Transactions(2, 102, 54321, "200", 3, LocalDateTime.of(2023, 1, 2, 13, 0, 0));

        assertEquals(transactions1, transactions2);
        assertEquals(transactions1.hashCode(), transactions2.hashCode());

        assertNotEquals(transactions1, transactions3);
        assertNotEquals(transactions1.hashCode(), transactions3.hashCode());
    }
}