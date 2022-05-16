package com.nttdatabootcamp.springwithmongodb.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credit {

    @Id
    private String id;
    private String type;
    private String amountCredit;
    private double limitCredit;
    private String idProduct;

    private String idClient;

    public void setAmountCredit(String type){
        switch (type) {
            case "Personal":
                this.amountCredit = "1";
                break;
            case "Empresarial":
                this.amountCredit = null;
                break;
        }
    }
}
