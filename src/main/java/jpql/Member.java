package jpql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    protected Member() {
    }


    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public Team getTeam() {
        return team;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
