package com.slabstech.apitestcontainer.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import com.slabstech.apitestcontainer.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    List<User> findAll();

    Stream<User> findAllByName(String userNameAdam);
    @Query("SELECT u FROM User u WHERE u.status = 1")
    Collection<User> findAllActiveUsers();
    @Query(value = "SELECT * FROM Users u WHERE u.status = 1", nativeQuery = true)
    Collection<User> findAllActiveUsersNative();
    @Query("SELECT u FROM User u WHERE u.status = ?1")
    User findUserByStatus(Integer activeStatus);
    @Query(value = "SELECT * FROM Users u WHERE u.status = ?1", nativeQuery = true)
    User findUserByStatusNative(Integer activeStatus);

    @Query("SELECT u FROM User u WHERE u.status = ?1 and u.name = ?2")
    User findUserByStatusAndName(Integer activeStatus, String userNameAdam);

    @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
    User findUserByStatusAndNameNamedParams(@Param("status") Integer status, @Param("name") String name);

    @Query(value = "SELECT * FROM Users u WHERE u.status = :status AND u.name = :name", nativeQuery = true)
    User findUserByStatusAndNameNamedParamsNative(@Param("status") Integer status, @Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
    User findUserByUserStatusAndUserName(@Param("status") Integer userStatus, @Param("name") String userName);
    @Query("SELECT u FROM User u WHERE u.name like ?1%")
    User findUserByNameLike(String ad);

    @Query("SELECT u FROM User u WHERE u.name like :name%")
    User findUserByNameLikeNamedParam(@Param("name") String name);

    @Query(value = "SELECT * FROM users u WHERE u.name LIKE ?1%", nativeQuery = true)
    User findUserByNameLikeNative(String name);


    @Query(value = "SELECT u FROM User u ORDER BY id")
    Page<User> findAllUsersWithPagination(Pageable pageable);

    @Query(value = "SELECT * FROM Users ORDER BY id", countQuery = "SELECT count(*) FROM Users", nativeQuery = true)
    Page<User> findAllUsersWithPaginationNative(Pageable pageable);
    @Modifying
    @Query("update User u set u.status = :status where u.name = :name")
    int updateUserSetStatusForName(@Param("status") Integer status, @Param("name") String name);

    @Modifying
    @Query(value = "UPDATE Users u SET u.status = ? WHERE u.name = ?", nativeQuery = true)
    int updateUserSetStatusForNameNative(Integer status, String name);

    @Query(value = "SELECT u FROM User u WHERE u.name IN :names")
    List<User> findUserByNameList(@Param("names") Collection<String> names);

    @Query(value = "INSERT INTO Users (name, age, email, status, active) VALUES (:name, :age, :email, :status, :active)", nativeQuery = true)
    @Modifying
    void insertUser(@Param("name") String name, @Param("age") Integer age, @Param("email") String email, @Param("status") Integer status, @Param("active") boolean active);

    @Query("select u from User u where u.email like '%@gmail.com'")
    List<User> findUsersWithGmailAddress();

    void deleteAllByCreationDateAfter(LocalDate of);

    @Query(value = "SELECT u FROM User u")
    List<User> findAllUsers(Sort sort);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u set u.active = false where u.lastLoginDate < :date")
    void deactivateUsersNotLoggedInSince(@Param("date") LocalDate date);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete User u where u.active = false")
    int deleteDeactivatedUsers();
    @Query("delete User u where u.active = false")
    int deleteDeactivatedUsersWithNoModifyingAnnotation();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "alter table USERS add column deleted int not null default 0", nativeQuery = true)
    void addDeletedColumn();

    @Modifying
    @Query(value = "UPDATE Users u SET status = ? WHERE u.name = ?", nativeQuery = true)
    int updateUserSetStatusForNameNativePostgres(Integer inactiveStatus, String sample);

    List<User> findAll(Sort by);

}

