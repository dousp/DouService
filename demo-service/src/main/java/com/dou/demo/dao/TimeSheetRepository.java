package com.dou.demo.dao;

import com.dou.demo.entity.TimeSheet;
import org.springframework.data.repository.CrudRepository;

public interface TimeSheetRepository extends CrudRepository<TimeSheet,Long> {
}
