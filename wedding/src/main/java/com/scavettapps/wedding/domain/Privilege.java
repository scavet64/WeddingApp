package com.scavettapps.wedding.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "privileges")
public class Privilege {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "privilege_id")
   private Long id;

   @Column(name = "privilege_name")
   private String name;
   public Privilege() {
	   
      super();
   }

   /**
    * @param name Privilege name
    */
   public Privilege(String name) {
      super();
      this.name = name;
   }

   /**
    * @return the id
    */
   public Long getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(Long id) {
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

}