package in.goals.dbaccess.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Payload")
@NamedQuery(name = "Payload.findAll", query = "SELECT p FROM PayloadDAO p")
public class PayloadDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, unique = true)
	@JsonProperty("waybill")
	private String waybill;

	@Column(nullable = false)
	@JsonProperty("client")
	private String client;

	@JsonProperty("name")
	private String name;

	@JsonProperty("order_date")
	private String orderDate;

	@JsonProperty("payment_mode")
	private String paymentMode;

	@JsonProperty("total_amount")
	private Integer totalAmount;

	@JsonProperty("cod_amount")
	private Integer codAmount;

	@JsonProperty("add")
	private String address;

	@JsonProperty("city")
	private String city;

	@JsonProperty("state")
	private String state;

	@JsonProperty("country")
	private String country;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("products_desc")
	private String productsDesc;

	@JsonProperty("shipment_width")
	private String shipmentWidth;

	@JsonProperty("shipment_height")
	private String shipmentHeight;

	@JsonProperty("weight")
	private String weight;

	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("pickup_start_time")
	private String pickupStartTime;

	@JsonProperty("pickup_end_time")
	private String pickupEndTime;

	public String getWaybill() {
		return waybill;
	}

	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getCodAmount() {
		return codAmount;
	}

	public void setCodAmount(Integer codAmount) {
		this.codAmount = codAmount;
	}

	public String getAdd() {
		return address;
	}

	public void setAdd(String add) {
		this.address = add;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getProductsDesc() {
		return productsDesc;
	}

	public void setProductsDesc(String productsDesc) {
		this.productsDesc = productsDesc;
	}

	public String getShipmentWidth() {
		return shipmentWidth;
	}

	public void setShipmentWidth(String shipmentWidth) {
		this.shipmentWidth = shipmentWidth;
	}

	public String getShipmentHeight() {
		return shipmentHeight;
	}

	public void setShipmentHeight(String shipmentHeight) {
		this.shipmentHeight = shipmentHeight;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPickupStartTime() {
		return pickupStartTime;
	}

	public void setPickupStartTime(String pickupStartTime) {
		this.pickupStartTime = pickupStartTime;
	}

	public String getPickupEndTime() {
		return pickupEndTime;
	}

	public void setPickupEndTime(String pickupEndTime) {
		this.pickupEndTime = pickupEndTime;
	}
}
