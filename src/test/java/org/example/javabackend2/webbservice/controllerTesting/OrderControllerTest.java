package org.example.javabackend2.webbservice.controllerTesting;

import org.example.javabackend2.webbservice.controllers.OrderController;
import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.dtos.UserDto;
import org.example.javabackend2.webbservice.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController controller;

    @Test
    void addOrder_success_setsSUCCESS_and_returns_purchaseConfirmation() {
        Model model = new ExtendedModelMap();
        long productId = 42L;

        //given
        ProductDto product = ProductDto.builder().id(productId).title("sportswear").build();
        UserDto user    = UserDto.builder().id(7L).name("Greta").build();
        OrderDto order   = OrderDto.builder().id(999L).product(product).user(user).build();

        when(orderService.createOrderFromProdId(productId)).thenReturn(order);

        //when
        String view = controller.addOrder(model, productId);

        //then
        assertThat(view).isEqualTo("purchaseConfirmation");
        assertThat(model.getAttribute("status")).isEqualTo("SUCCESS");
        assertThat(model.getAttribute("order")).isEqualTo(order);
        verify(orderService).createOrderFromProdId(productId);
    }


    @Test
    void listOrders_addsOrdersToModel_and_returns_ordersView() {
        Model model = new ExtendedModelMap();
        //given
        OrderDto o1 = OrderDto.builder().id(1L).build();
        OrderDto o2 = OrderDto.builder().id(2L).build();

        when(orderService.getAllOrders()).thenReturn(List.of(o1, o2));


        //when
        String view = controller.listProducts(model);

        //then
        assertThat(view).isEqualTo("orders");
        assertThat(model.getAttribute("orders")).isEqualTo(List.of(o1, o2));
        verify(orderService).getAllOrders();
    }

    @Test
    void deleteOrder_callsService_and_redirects() {

        //when
        String view = controller.deleteOrder(5L);

        //then
        assertThat(view).isEqualTo("redirect:/orders");
        verify(orderService).deleteById(5L);
    }
}
