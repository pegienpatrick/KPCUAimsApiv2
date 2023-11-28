package org.aims.kpcu.repositories;

import org.aims.kpcu.entities.Notification;
import org.aims.kpcu.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface NotificationsRepository extends JpaRepository<Notification,Long> {

    @Query("select n from Notification n where ownerId=?2 and userType=?1")
    List<Notification> findByUser(UserType userType,Long ownerId);

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.ownerId = ?2 AND n.userType = ?1 AND n.isRead = false")
    int findUnreadNotificationsCount(UserType userType, Long ownerId);

    @Query("update Notification n set n.isRead=true,readOn=?3 WHERE n.ownerId = ?2 AND n.userType = ?1 AND n.isRead = false")
    public void readAllNotifications(UserType userType, Long ownerId,Date whenread);



}
