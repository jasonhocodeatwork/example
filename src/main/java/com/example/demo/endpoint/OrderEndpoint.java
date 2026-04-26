package com.example.demo.endpoint;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRepository.UserData;
import com.example.soap.orders.GetOrderRequest;
import com.example.soap.orders.GetOrderResponse;
import com.example.soap.orders.Order;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class OrderEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/soap/orders";

    private final UserRepository userRepository;

    public OrderEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request) {
        UserData data = userRepository.findById(request.getId());

        Order order = new Order();
        order.setId(data.id());
        order.setOrder(data.name());

        GetOrderResponse response = new GetOrderResponse();
        response.setOrder(order);
        return response;
    }
}