package com.ennea.enneaservices.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class InternalInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genkey;
    private String name;
    private String packing;

    private LocalDateTime creationDateTime;

    private LocalDateTime modificationDateTime;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<InternalInventoryMapping> mapping = new LinkedHashSet<>();

    public InternalInventory(String key) {
        this.genkey = key;
        this.creationDateTime = LocalDateTime.now();
        this.modificationDateTime = LocalDateTime.now();
    }

}
