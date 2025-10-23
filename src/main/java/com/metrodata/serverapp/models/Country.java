package com.metrodata.serverapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_country")
@AllArgsConstructor
@NoArgsConstructor
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer countryId; // db => country_id

  @Column(nullable = false, unique = true, length = 2)
  private String countryCode;

  @Column(nullable = false, unique = true)
  private String countryName;

  @ManyToOne
  // @JsonBackReference
  @JoinColumn(name = "region_id", nullable = false)
  private Region region;
}
