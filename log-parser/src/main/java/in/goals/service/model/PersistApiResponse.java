package in.goals.service.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersistApiResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("errors")
	private List<String> errors;

	@JsonProperty("success")
	private Boolean success;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
