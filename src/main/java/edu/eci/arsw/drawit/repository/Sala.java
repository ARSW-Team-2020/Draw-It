package edu.eci.arsw.drawit.repository;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;


@Service
@Qualifier("sala")
public class Sala implements SalaRepository  {

    @Override
    public List<Sala> findAll() {
        return null;
    }

    @Override
    public List<Sala> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Sala> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Sala> findAllById(Iterable<Integer> iterable) {
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
    public void delete(Sala sala) {

    }

    @Override
    public void deleteAll(Iterable<? extends Sala> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Sala> S save(S s) {
        return null;
    }

    @Override
    public <S extends Sala> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Sala> findById(Integer integer) {
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
    public <S extends Sala> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Sala> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Sala getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Sala> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Sala> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Sala> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Sala> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Sala> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Sala> boolean exists(Example<S> example) {
        return false;
    }
}
