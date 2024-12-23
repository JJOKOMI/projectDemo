package com.example.demo.repository;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
//    @Query("""
//SELECT m
//FROM Message m
//WHERE m.senderId = :id
//    OR m.receiverId = :id
//ORDER BY m.createdAt DESC
//""")
    List<Message> findMessageById(Long id);

    List<Message> findAllBySenderId(User senderId); // 발신자 ID로 메시지 조회
    List<Message> findAllByReceiverId(User receiverId);
//    List<Message> findById(User user);

//    List<Object[]> findAllMessages(); - 쿼리쓸때
}
