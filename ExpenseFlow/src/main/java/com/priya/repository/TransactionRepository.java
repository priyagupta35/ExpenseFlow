package com.priya.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.priya.model.Transaction;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByUserId(int userId);

    List<Transaction> findByUserIdAndType(int userId, String type);

    List<Transaction> findByUserIdAndDateBetween(
        int userId, LocalDate start, LocalDate end);

    List<Transaction> findByUserIdAndCategoryId(
        int userId, int categoryId);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
           "WHERE t.user.id = :userId AND t.type = :type")
    java.math.BigDecimal sumByUserIdAndType(
        @Param("userId") int userId,
        @Param("type") String type);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
           "WHERE t.user.id = :userId AND t.type = :type " +
           "AND t.date BETWEEN :start AND :end")
    java.math.BigDecimal sumByUserIdAndTypeAndDateBetween(
        @Param("userId") int userId,
        @Param("type") String type,
        @Param("start") LocalDate start,
        @Param("end") LocalDate end);

    @Query("SELECT t.category.name, COALESCE(SUM(t.amount), 0) " +
           "FROM Transaction t WHERE t.user.id = :userId " +
           "GROUP BY t.category.name")
    List<Object[]> sumByCategoryForUser(@Param("userId") int userId);
}