package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.IOrderPersistencePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseOrder;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    @Mock
    private IOrderPersistencePort iOrderPersistencePort;

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @InjectMocks
    private UseCaseOrder useCaseOrder;

    private User clientUser, ownerUser;
    private Restaurant restaurant;
    private Order order;
    private Rol clientRole, ownerRole;
    private Dish dish, dish2, dish3;
    private Category category;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
         clientRole = new Rol(1L, "CLIENT", "Client role");
         ownerRole = new Rol(2L, "OWNER", "Owner role");
         clientUser = new User(1L, "Client Name", "Client Nickname", TypeDocumentEnum.CC, "123456789",
                "+573001234567", LocalDate.of(1990, 1, 1),"pe@gmail.com","123",clientRole);
         ownerUser = new User(2L, "Client ", "owner", TypeDocumentEnum.CC, "1234532329",
                "+573001234567", LocalDate.of(1990, 1, 1),"p3232e@gmail.com","123",ownerRole);
         restaurant = new Restaurant(1L,"Restaurant Name", "Restaurant Address",ownerUser.getIdUser(),"+573142212051","www.uno.com","1234556");
         category = new Category(1L,"food", "Food Category");
         dish = new Dish(1L,"name",category,"cdsasa",7.0,restaurant,"ww.uno.com",StatusDish.ACT);
         dish2 = new Dish(2L,"name",category,"cdsasa",7.0,restaurant,"ww.uno.com",StatusDish.ACT);
         dish3 = new Dish(3L,"name",category,"cdsasa",7.0,restaurant,"ww.uno.com",StatusDish.ACT);

        order = new Order(1L,clientUser.getIdUser(),restaurant,
                LocalDate.now(),null, List.of(new OrderDetail(1L,null,dish,1),
                new OrderDetail(2L,null,dish2,2), new OrderDetail(3L,null,dish3,3)));
    }

    @Test
    void test_createOrder(){
        when(iOrderPersistencePort.findAllByIdClient(clientUser.getIdUser())).thenReturn(List.of());
        when(iRestaurantPersistencePort.findRestaurantById(restaurant.getIdRestaurant())).thenReturn(Optional.of(restaurant));
        when(iOrderPersistencePort.saveOrder(order)).thenReturn(order);

        Order result = useCaseOrder.saveOrder(order, clientUser.getIdUser());

        assertNotNull(result);
        assertEquals(StatusOrder.PENDING, result.getStatusOrder());
        verify(iOrderPersistencePort, times(1)).saveOrder(order);
    }

    @Test
    void test_create_order_with_pending_order_should_throw_exception() {
        order.setStatusOrder(StatusOrder.PENDING);
        when(iOrderPersistencePort.findAllByIdClient(clientUser.getIdUser())).thenReturn(List.of(order));

        DomainException exception = assertThrows(DomainException.class,
                () -> useCaseOrder.saveOrder(order, clientUser.getIdUser()));
        assertEquals(ConstantsErrorMessage.CLIENT_WITH_ORDERS, exception.getMessage());
        verify(iOrderPersistencePort, never()).saveOrder(any(Order.class));
    }
}
