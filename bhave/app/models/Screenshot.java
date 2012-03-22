package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import annotations.NoJsonExport;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Screenshot extends BhaveModel {
	
	public String name;
	public Long testId;
	@NoJsonExport
	public Blob source;
}
