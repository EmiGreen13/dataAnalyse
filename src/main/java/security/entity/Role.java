package security.entity;

import entities.Product;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "Role")
@Table(name = "tblRole")
public class Role {
    @Id
    @Column(name = "RoleId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "UserId", nullable = false)
    private Integer userId;
    @Column(name = "Role", length = 50, nullable = false)
    private String role;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="UserId", insertable = false, updatable = false)
    private User user;

    public Role(){}

    public Role(Integer userId, String role){
        this.setUserId(userId);
        this.setRole(role);
    }

    public Role(Integer id, Integer userId, String role){
        this.setId(id);
        this.setUserId(userId);
        this.setRole(role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
