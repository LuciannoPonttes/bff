package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.pix;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ListChavePixResponse {
    private String key;
    private String type;
    private String status;
    private OwnerResponse owner;
    private String createdAt;
    private String updatedAt;
    private String registeredAt;
    private String ownershipDate;
    private String eventId;
    private boolean impoundment;
    private DonorParticipantResponse donorParticipant;
    private String endToEndId;
}
