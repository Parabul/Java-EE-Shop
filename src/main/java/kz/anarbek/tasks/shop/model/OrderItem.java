package kz.anarbek.tasks.shop.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the order_item database table.
 * 
 */
@Entity
@Table(name = "order_item")
@NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ORDER_ITEM_ID_GENERATOR", sequenceName = "S_ORDER_ITEM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_ID_GENERATOR")
	private Long id;

	// bi-directional many-to-one association to OrderInfo
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "order_info_id")
	private OrderInfo orderInfo;

	// uni-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "pro_id")
	private Product product;

	public OrderItem() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderInfo getOrderInfo() {
		return this.orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}