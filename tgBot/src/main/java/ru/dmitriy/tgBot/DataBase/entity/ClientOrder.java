package ru.dmitriy.tgBot.DataBase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;

@Entity
public class ClientOrder {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Client client;
    @Column (nullable = false)
    private Integer status;
    @Column (nullable = false, length  = 17, precision = 2)
    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Double gettotal() {
        return total;
    }

    public void settotal(Double total) {
        this.total = total;
    }

    
}
