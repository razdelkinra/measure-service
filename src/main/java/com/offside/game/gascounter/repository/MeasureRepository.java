package com.offside.game.gascounter.repository;

import com.offside.game.gascounter.entity.Measure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MeasureRepository<T extends Measure> extends CrudRepository<T, Long> {
    List<T> findByUserId(String userId);
}
