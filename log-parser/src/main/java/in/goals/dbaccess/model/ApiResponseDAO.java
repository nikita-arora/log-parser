package in.goals.dbaccess.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Api_Response", uniqueConstraints = { @UniqueConstraint(columnNames = { "HTTP_METHOD", "API_URL" }) })
@NamedQuery(name = "Api_Response.findAll", query = "SELECT a FROM ApiResponseDAO a")
public class ApiResponseDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum HttpMethod {
		GET, POST, PUT, DELETE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	@JsonProperty("id")
	private Integer id;

	@Column(name = "http_method", nullable = false)
	@JsonProperty("http_method")
	@Enumerated(EnumType.STRING)
	private HttpMethod httpMethod;

	@Column(name = "api_url", nullable = false)
	@JsonProperty("api_url")
	private String apiUrl;

	@Column(nullable = false)
	private Integer count;

	@Column(nullable = false)
	@JsonProperty("total_response_time")
	private Double totalResponseTime;

	@Column(nullable = false)
	@JsonProperty("avg_response_time")
	private Double avgResponseTime;

	@JsonProperty("created_at")
	private Date createdAt;

	@JsonProperty("updated_at")
	private Date updatedAt;

	@PrePersist
	void onCreate() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	@PreUpdate
	void onPersist() {
		this.setUpdatedAt(new Date());
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getTotalResponseTime() {
		return totalResponseTime;
	}

	public void setTotalResponseTime(Double totalResponseTime) {
		this.totalResponseTime = totalResponseTime;
	}

	public Double getAvgResponseTime() {
		return avgResponseTime;
	}

	public void setAvgResponseTime(Double avgResponseTime) {
		this.avgResponseTime = avgResponseTime;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
