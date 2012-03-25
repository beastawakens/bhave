package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import annotations.NoJsonExport;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Screenshot extends JsonPersistingModel {
	
	public String name;
	public Long bhaviourId;
	@NoJsonExport
	public Blob source;
}
