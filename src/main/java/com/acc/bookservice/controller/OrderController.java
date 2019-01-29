package com.acc.bookservice.controller;

import com.acc.bookservice.dto.BookSummaryDto;
import com.acc.bookservice.dto.OrderDto;
import com.acc.bookservice.exception.InvalidDataException;
import com.acc.bookservice.exception.TechnicalException;
import com.acc.bookservice.rest.client.PostAsyncClient;
import com.acc.bookservice.rest.dto.PostsDto;
import io.swagger.annotations.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * The class handles the requests for an order of books
 */
@Api(value = "Book Get/Search/Order Api", description = "Apis pertaining to the operations on Book search & order")
@RestController
public class OrderController {

    @Autowired
    private PostAsyncClient postAsyncClient;

    /**
     * This method takes an order of Books from a user
     * And returns the Posts
     */
    @ApiOperation(value = "Order Books", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created an order"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(path = "/order", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> orderBooks(
            @ApiParam(value = "Employee object store in database table", required = true) @Valid @RequestBody OrderDto orders) {
        String errors = "";
        try {
            errors = validateBookOrders(orders);
            if (!errors.isEmpty()) {
                throw new InvalidDataException("Invalid Request Payload");
            }
            CompletableFuture<List<PostsDto>> posts = postAsyncClient.getOrderDetails(orders);
            return new ResponseEntity<List<PostsDto>>(posts.get(), HttpStatus.CREATED);
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid Request Payload" + errors);
        } catch (Exception e) {
            throw new TechnicalException("Exception in posting the order");
        }
    }

    /**
     * Method to validate the payload for Book Order
     *
     * @param orderDto
     * @return
     */
    private String validateBookOrders(OrderDto orderDto) {

        StringBuffer errorList = new StringBuffer();
        if (Objects.isNull(orderDto) || Objects.isNull(orderDto.getBookOrderedDto())
                || Objects.isNull(orderDto.getBookOrderedDto().getBookDtos()) || orderDto.getBookOrderedDto().getBookDtos().isEmpty()) {
            errorList.append("Can not process an empty order. Kindly Add a book to the order to proceed.");
        } else {
            if (Objects.isNull(orderDto.getUserId()) || !NumberUtils.isParsable(orderDto.getUserId())) {
                errorList.append("Blank/Invalid userId.");
            }

            for (BookSummaryDto bookSummaryDTO : orderDto.getBookOrderedDto().getBookDtos()) {
                if (Objects.isNull(bookSummaryDTO.getIsbnNumber()) || !NumberUtils.isParsable(bookSummaryDTO.getIsbnNumber())) {
                    errorList.append("Blank/Invalid ISBN.");
                }
            }

        }
        return errorList.toString();

    }

}
