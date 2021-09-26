package jpql;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private int orderAmount;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    protected Order() {
    }

    public Order(Long id, int orderAmount, Address address, Member member, Product product) {
        this.id = id;
        this.orderAmount = orderAmount;
        this.address = address;
        this.member = member;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public Address getAddress() {
        return address;
    }

    public Member getMember() {
        return member;
    }

    public Product getProduct() {
        return product;
    }
}
