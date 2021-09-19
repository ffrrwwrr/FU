package com.example.server.service;

import com.example.server.entity.Position;
import com.example.server.entity.Unit;
import com.example.server.repository.UnitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UnitServiceTest {

    @Autowired
    private UnitService service;

    @MockBean
    private UnitRepository repository;

    @Test
    void create() {
        Unit unit = new Unit();
        Assertions.assertTrue(service.create(unit));
    }

    @Test
    void getAll() {
        ArrayList<Unit> positions = new ArrayList<>();
        positions.add(new Unit());
        Mockito.doReturn(positions).when(repository).findAll();
        Assertions.assertEquals(positions, service.getAll());
    }

    @Test
    void getOne() {
        Unit unit = new Unit();
        Optional<Unit> optionalDish = Optional.of(unit);
        Mockito.doReturn(optionalDish).when(repository).findById(0L);
        Assertions.assertEquals(optionalDish, service.getOne(0L));
    }

    @Test
    void delete() {
        Unit u = new Unit();
        Optional<Unit> unit = Optional.of(u);
        Mockito.doReturn(unit).when(repository).findById(0L);
        Assertions.assertTrue(service.delete(0L));
    }

    @Test
    void update() {
        Unit unit = new Unit();
        unit.setId(0L);
        Optional optional = Optional.of(unit);
        Mockito.doReturn(optional).when(repository).findById(0L);
        Assertions.assertTrue(service.update(unit));
    }
}