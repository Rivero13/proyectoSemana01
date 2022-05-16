package com.nttdatabootcamp.springwithmongodb.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "bankAccounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {
  @Id
  private String id;
  private String type;
  private double maintenanceFee;
  private int maxMovement;
  private Date date;
  private double amount;
  private String idProduct;
  private String idClient;

  public void maintenanceFee(String type){

    switch (type){
      case "saving":
        this.maxMovement = 5;
        break;
      case "Current account":
        this.maintenanceFee = 2.50;
        break;
      case "Fixed term":
        this.maxMovement = 1;
        break;
    }
  }

}
