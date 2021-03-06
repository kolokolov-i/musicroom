package ru.superbro.musicroom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.superbro.musicroom.entity.AppUser;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Integer> {

    AppUser getByName(@Param("name") String name);
}