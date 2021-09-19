package com.example.server.service;

import com.example.server.entity.Order;
import com.example.server.entity.Position;
import com.example.server.repository.PositionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class PositionServiceTest {

    @Autowired
    private PositionService service;

    @MockBean
    private PositionRepository repository;

    @Test
    void create() {
        Position position = new Position();
        Assertions.assertTrue(service.create(position));
    }

    @Test
    void getAll() {
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(new Position());
        Mockito.doReturn(positions).when(repository).findAll();
        Assertions.assertEquals(positions, service.getAll());
    }

    @Test
    void getOne() {
        Position position = new Position();
        Optional<Position> optionalDish = Optional.of(position);
        Mockito.doReturn(optionalDish).when(repository).findById(0L);
        Assertions.assertEquals(optionalDish, service.getOne(0L));
    }

    @Test
    void delete() {
        Position p = new Position();
        Optional<Position> position = Optional.of(p);
        Mockito.doReturn(position).when(repository).findById(0L);
        Assertions.assertTrue(service.delete(0L));
    }

    @Test
    void update() {
        Position position = new Position();
        position.setId(0L);
        Optional optional = Optional.of(position);
        Mockito.doReturn(optional).when(repository).findById(0L);
        Assertions.assertTrue(service.update(position));
    }
}