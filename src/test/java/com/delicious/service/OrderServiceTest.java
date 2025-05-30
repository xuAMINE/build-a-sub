package com.delicious.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.delicious.model.Order;
import com.delicious.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Arrays;

class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_setsTimestampAndSaves() {
        Order order = new Order();
        when(repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order saved = service.placeOrder(order);

        assertNotNull(saved.getOrderTime(), "orderTime should be set");
        verify(repository).save(saved);
    }

    @Test
    void getAllOrders_returnsList() {
        Order o1 = new Order();
        when(repository.findAll()).thenReturn(Arrays.asList(o1));

        var list = service.getAllOrders();

        assertEquals(1, list.size());
        assertSame(o1, list.get(0));
    }

    @Test
    void getOrderById_found() {
        Order o = new Order();
        when(repository.findById(1L)).thenReturn(Optional.of(o));

        var result = service.getOrderById(1L);

        assertSame(o, result);
    }

    @Test
    void getOrderById_notFound_throws() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.getOrderById(2L));
    }

    @Test
    void delete_callsRepository() {
        service.delete(3L);
        verify(repository).deleteById(3L);
    }
}
