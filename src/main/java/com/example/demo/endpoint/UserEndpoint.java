package com.example.demo.endpoint;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRepository.UserData;
import com.example.soap.users.GetUserRequest;
import com.example.soap.users.GetUserResponse;
import com.example.soap.users.User;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/soap/users";

    private final UserRepository userRepository;

    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        UserData data = userRepository.findById(request.getId());

        User user = new User();
        user.setId(data.id());
        user.setName(data.name());
        user.setEmail(data.email());

        GetUserResponse response = new GetUserResponse();
        response.setUser(user);
        return response;
    }
}