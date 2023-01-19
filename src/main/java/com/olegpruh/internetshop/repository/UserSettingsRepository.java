package com.olegpruh.internetshop.repository;

import com.olegpruh.internetshop.model.User;
import com.olegpruh.internetshop.model.UserSettings;
import com.olegpruh.internetshop.service.UserSettingsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE UserSettings u SET u.listLayout = ?1 WHERE u.id = ?2")
    void update(String listLayout, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE UserSettings u SET u.user = ?1 WHERE u.id = ?2")
    void updateUser(User user, Long id);

    UserSettings findByUserEmailOrSession(String user_email, String session);

    UserSettings findBySession(String session);

    UserSettings findByUserEmail(String user_email);

    @Transactional
    void deleteById(Long id);
}
