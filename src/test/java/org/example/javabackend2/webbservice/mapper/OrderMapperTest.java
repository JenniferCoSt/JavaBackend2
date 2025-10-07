package org.example.javabackend2.webbservice.mapper;

import org.example.javabackend2.webbservice.dtos.OrderDto;
import org.example.javabackend2.webbservice.dtos.ProductDto;
import org.example.javabackend2.webbservice.dtos.UserDto;
import org.example.javabackend2.webbservice.mappers.OrderMapper;
import org.example.javabackend2.webbservice.models.*;
import org.example.javabackend2.webbservice.repos.ProductRepository;
import org.example.javabackend2.webbservice.repos.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderMapperTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    private OrderMapper orderMapper;

    @BeforeEach
    public void setUp() {
        orderMapper = new OrderMapper(productRepository, userRepository);
    }


    @Test
    void orderToOrderDto_ShouldMapAllFieldsCorrectly() {
        Category category = Category.builder()
                .type("Electronics").build();

        Rating rating = Rating.builder()
                .rate(4.5).build();

        Product product = Product.builder()
                .id(1L)
                .title("Test product")
                .description("Test description")
                .price(6.66)
                .image("Test.jpg")
                .category(category)
                .rating(rating)
                .build();

        User user = User.builder()
                .id(2L)
                .name("Test name")
                .build();

        Order order = Order.builder()
                .id(3L)
                .product(product)
                .user(user)
                .build();


        OrderDto result = orderMapper.orderToOrderDto(order);

        Assertions.assertEquals(3L, result.getId().longValue());
        Assertions.assertEquals(1L, result.getProduct().getId().longValue());
        Assertions.assertEquals("Test product", result.getProduct().getTitle());
        Assertions.assertEquals("Test description", result.getProduct().getDescription());
        Assertions.assertEquals(6.66, result.getProduct().getPrice(), 0.001);
        Assertions.assertEquals("Test.jpg", result.getProduct().getImage());
        Assertions.assertEquals("Electronics", result.getProduct().getCategoryName());
        Assertions.assertEquals(4.5, result.getProduct().getRating(), 0.001);
        Assertions.assertEquals(1L, result.getProduct().getId().longValue());
        Assertions.assertEquals(2L, result.getUser().getId().longValue());
        Assertions.assertEquals("Test name", result.getUser().getName());
    }

    @Test
    void orderDtoToOrder_ShouldMapAllFieldsCorrectly() {
        ProductDto productDto = new ProductDto(1L, "Test product", "Test description",
                6.66, "Test.jpg", "Electronics", 4.5);

        UserDto userDto = new UserDto(2L, "Test name");

        OrderDto orderDto = OrderDto.builder()
                .id(3L)
                .product(productDto)
                .user(userDto)
                .build();

        Product mockProduct = Product.builder().id(1L).title("Test product").build();
        User mockUser = User.builder().id(2L).name("Test name").build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(userRepository.findById(2L)).thenReturn(Optional.of(mockUser));

        Order result = orderMapper.orderDtoToOrder(orderDto);

        Assertions.assertEquals(3L, result.getId().longValue());
        Assertions.assertEquals(mockProduct, result.getProduct());
        Assertions.assertEquals(mockUser, result.getUser());
    }
}
