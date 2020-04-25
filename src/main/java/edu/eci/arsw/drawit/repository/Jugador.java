package edu.eci.arsw.drawit.repository;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jugador")
public class Jugador implements  JugadorRepository  {

    @Override
    public List<Jugador> findAll() {
        return null;
    }

    @Override
    public List<Jugador> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Jugador> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Jugador> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Jugador jugador) {

    }

    @Override
    public void deleteAll(Iterable<? extends Jugador> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Jugador> S save(S s) {
        return null;
    }

    @Override
    public <S extends Jugador> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Jugador> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Jugador> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Jugador> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Jugador getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Jugador> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Jugador> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Jugador> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Jugador> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Jugador> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Jugador> boolean exists(Example<S> example) {
        return false;
    }
}
