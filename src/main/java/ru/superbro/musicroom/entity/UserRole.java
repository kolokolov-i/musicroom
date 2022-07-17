package ru.superbro.musicroom.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue
    private int id;
    @JoinColumn(name = "app_user", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;
    @JoinColumn(name = "app_role", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AppRole role;

    public UserRole() {
    }

    public UserRole(int id, AppUser user, AppRole role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        this.role = role;
    }

}
