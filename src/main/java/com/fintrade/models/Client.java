package com.fintrade.models;

import com.fintrade.core.models.Address;
import com.fintrade.core.models.Contact;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    private String clientId;

    private String clientName;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @Embedded
    private Address address;

    @Embedded
    private Contact contact;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users;
}
