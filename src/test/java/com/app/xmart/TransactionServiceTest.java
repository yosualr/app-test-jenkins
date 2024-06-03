package com.app.xmart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.app.xmart.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.xmart.model.Transactions;
import com.app.xmart.repositories.TransactionsRepositories;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {TransactionServiceTest.class})
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionsRepositories transactionsRepositories;

    @InjectMocks
    private TransactionService transactionService;

    private Transactions transaction;

    @BeforeEach
    void setup() {
        transaction = new Transactions();
        transaction.setTransactionId(1);
        transaction.setCustomerId(123);
        transaction.setRfid(456);
        transaction.setProductPrice("100.00");
        transaction.setQuantity(2);
        transaction.setTransactionDatetime(LocalDateTime.now());
    }

    @Test
    void testFindAllTransactions() {
        when(transactionsRepositories.findAll()).thenReturn(Arrays.asList(transaction));

        List<Transactions> transactions = transactionService.findAllTransactions();

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.get(0));
    }
}