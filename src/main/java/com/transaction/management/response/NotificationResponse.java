package com.transaction.management.response;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long user_id;
    private String status;
    private String message;
    private String email;
    private Double amount;
}
