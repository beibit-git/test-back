package kz.test.controller;

import kz.test.dao.OrderDao;
import kz.test.dto.requestDto.OrderRequest;
import kz.test.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api/v1/order")
public class OrderController {
    private final OrderDao orderDao;
    @Autowired
    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @PostMapping("/create-order")
    public ResponseEntity<HttpStatus> create(@RequestBody OrderRequest orderRequest){
        orderDao.createOrder(orderRequest);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/get-orders")
    public ResponseEntity<List<Order>> getAll(){
        return new ResponseEntity<>(orderDao.index(), OK);
    }
}
