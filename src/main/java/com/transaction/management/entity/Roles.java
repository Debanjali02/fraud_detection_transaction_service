package com.transaction.management.entity;



import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "roles",schema = "fraud_detection_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Roles {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
}
