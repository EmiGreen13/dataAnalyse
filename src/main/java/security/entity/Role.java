package security.entity;

public class Role {
    private Integer id;
    private Integer userId;
    private String role;

    public Role(){}

    public Role(String role){
        this.setRole(role);
    }

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
}
