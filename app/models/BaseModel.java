package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.BadRequestException;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;
import play.Logger;
import services.helpers.JsonHelper;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@SuppressWarnings("serial")
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseModel extends Model {
	@Id
	public UUID id;
	@CreatedTimestamp
	@JsonFormat
	private Date created;
	@UpdatedTimestamp
	@JsonFormat
	private Date updated;

	@JsonProperty("id")
	public UUID getId() {
		return id;
	}

	@JsonIgnore
	public void setId(UUID id) {
		this.id = id;
	}

	public void createFromJson(ObjectNode json) throws BadRequestException {
		try {
			JsonHelper.getMapper().readerForUpdating(this).readValue(json);
		} catch (IOException e) {
			Logger.debug(e.getMessage(), e);
			throw new BadRequestException(e.getMessage());
		}
	}

	@JsonIgnore
	public Date getCreated() {
		return created;
	}

	@JsonIgnore
	public void setCreated(Date created) {
		this.created = created;
	}

	@JsonIgnore
	public Date getUpdated() {
		return updated;
	}

	@JsonIgnore
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
