package org.example.javabackend2.webbservice.serviceTesting;

import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.dtos.UserDto;
import org.example.javabackend2.webbservice.mappers.OrderMapper;
import org.example.javabackend2.webbservice.models.Order;
import org.example.javabackend2.webbservice.repos.OrderRepository;
import org.example.javabackend2.webbservice.services.ProductService;
import org.example.javabackend2.webbservice.services.UserService;
import org.example.javabackend2.webbservice.services.impl.OrderServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock private OrderMapper orderMapper;
    @Mock private OrderRepository orderRepository;
    @Mock private ProductService productService;
    @Mock private UserService userService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Authentication authentication;
    private SecurityContext securityContext;

    @BeforeEach
    void setUpSecurity() {
        authentication = mock(Authentication.class);
        securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
    }

    @AfterEach
    void tearDownSecurity() {
        SecurityContextHolder.clearContext();
    }


    @Test
    void createOrder_mapsAndSavesAndReturnsDto() {
        ProductDto prod = ProductDto.builder()
                .id(10L).title("T").description("D").price(99.0)
                .image("img").categoryName("cat").rating(4.5)
                .build();
        UserDto user = UserDto.builder().id(5L).name("Pippi").build();
        OrderDto input = OrderDto.builder().id(null).product(prod).user(user).build();

        Order mappedEntity = new Order();
        Order savedEntity = new Order();
        OrderDto mappedBack = OrderDto.builder().id(123L).product(prod).user(user).build();

        when(orderMapper.orderDtoToOrder(any(OrderDto.class))).thenReturn(mappedEntity);
        when(orderRepository.save(any(Order.class))).thenReturn(savedEntity);
        when(orderMapper.orderToOrderDto(any(Order.class))).thenReturn(mappedBack);

        // when
        OrderDto result = orderService.createOrder(input);

        // then
        assertThat(result.getId()).isEqualTo(123L);
        assertThat(result.getProduct()).isEqualTo(prod);
        assertThat(result.getUser()).isEqualTo(user);
        verify(orderMapper).orderDtoToOrder(input);
        verify(orderRepository).save(mappedEntity);
        verify(orderMapper).orderToOrderDto(savedEntity);
        verifyNoMoreInteractions(orderMapper, orderRepository);
    }


    @Test
    void createOrderFromProdId_happyPath_productAndUserFound_createsAndReturns() {
        // given
        long prodId = 42L;
        ProductDto prod = ProductDto.builder().id(prodId).title("X").build();
        when(productService.getProductById(prodId)).thenReturn(prod);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("user@example.com");
        UserDto user = UserDto.builder().id(7L).name("Greta").build();
        when(userService.findUserDtoByEmail("user@example.com")).thenReturn(user);

        //OrderDto preSave = OrderDto.builder().product(prod).user(user).build();

        Order mappedEntity = new Order();
        Order savedEntity = new Order();
        OrderDto savedDto = OrderDto.builder().id(999L).product(prod).user(user).build();

        when(orderMapper.orderDtoToOrder(argThat(dto ->
                dto.getProduct() == prod && dto.getUser() == user))).thenReturn(mappedEntity);
        when(orderRepository.save(mappedEntity)).thenReturn(savedEntity);
        when(orderMapper.orderToOrderDto(savedEntity)).thenReturn(savedDto);

        // when
        OrderDto result = orderService.createOrderFromProdId(prodId);

        // then
        assertThat(result.getId()).isEqualTo(999L);
        assertThat(result.getProduct()).isEqualTo(prod);
        assertThat(result.getUser()).isEqualTo(user);

        verify(productService).getProductById(prodId);
        verify(authentication).getName();
        verify(userService).findUserDtoByEmail("user@example.com");
        verify(orderMapper).orderDtoToOrder(argThat(dto ->
                dto.getProduct() == prod && dto.getUser() == user));
        verify(orderRepository).save(mappedEntity);
        verify(orderMapper).orderToOrderDto(savedEntity);
    }

    @Test
    void createOrderFromProdId_returnsDtoWithOnlyNulls_whenProductNotFound() {
        // given
        long prodId = 100L;
        when(productService.getProductById(prodId)).thenReturn(null);

        // when
        OrderDto result = orderService.createOrderFromProdId(prodId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getProduct()).isNull();
        assertThat(result.getUser()).isNull();

        verify(productService).getProductById(prodId);
        verifyNoInteractions(userService, orderMapper, orderRepository);
    }

    @Test
    void createOrderFromProdId_returnsDtoWithProductOnly_whenUserNotFound() {
        // given
        long prodId = 200L;
        ProductDto prod = ProductDto.builder().id(prodId).title("Y").build();
        when(productService.getProductById(prodId)).thenReturn(prod);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("ghost@example.com");
        when(userService.findUserDtoByEmail("ghost@example.com")).thenReturn(null);

        // when
        OrderDto result = orderService.createOrderFromProdId(prodId);

        // then
        assertThat(result.getProduct()).isEqualTo(prod);
        assertThat(result.getUser()).isNull();

        verify(productService).getProductById(prodId);
        verify(authentication).getName();
        verify(userService).findUserDtoByEmail("ghost@example.com");
        verifyNoInteractions(orderMapper, orderRepository);
    }

    @Test
    void deleteById_delegatesToRepository() {
        // when
        orderService.deleteById(55L);

        // then
        verify(orderRepository).deleteById(55L);
        verifyNoMoreInteractions(orderRepository);
    }


    @Test
    void getAllOrders_mapsAllEntitiesToDtos() {
        // given
        Order e1 = new Order();
        Order e2 = new Order();
        when(orderRepository.findAll()).thenReturn(List.of(e1, e2));

        OrderDto d1 = OrderDto.builder().id(1L).build();
        OrderDto d2 = OrderDto.builder().id(2L).build();

        when(orderMapper.orderToOrderDto(same(e1))).thenReturn(d1);
        when(orderMapper.orderToOrderDto(same(e2))).thenReturn(d2);

        // when
        List<OrderDto> result = orderService.getAllOrders();

        // then
        assertThat(result).containsExactly(d1, d2);
        verify(orderRepository).findAll();
        verify(orderMapper).orderToOrderDto(same(e1));
        verify(orderMapper).orderToOrderDto(same(e2));
    }
}

