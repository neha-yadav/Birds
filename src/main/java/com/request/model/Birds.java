package com.request.model;

import java.util.Arrays;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Birds {

	private String id;
	@NotNull()
	private String name;
	@NotNull
	private String family;
	@NotNull
	@Size(min=1)
	private String[] continents;
	@DateTimeFormat
	private String addedOn;
	private Boolean visible;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}
	/**
	 * @param family the family to set
	 */
	public void setFamily(String family) {
		this.family = family;
	}
	/**
	 * @return the continents
	 */
	public String[] getContinents() {
		return continents;
	}
	/**
	 * @param continents the continents to set
	 */
	public void setContinents(String[] continents) {
		this.continents = continents;
	}
	/**
	 * @return the addedOn
	 */
	public String getAddedOn() {
		return addedOn;
	}
	/**
	 * @param addedOn the addedOn to set
	 */
	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}
	/**
	 * @return the visible
	 */
	public Boolean getVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Birds [id=" + id + ", name=" + name + ", family=" + family + ", continents="
				+ Arrays.toString(continents) + ", addedOn=" + addedOn + ", visible=" + visible + "]";
	}
	
	
}
