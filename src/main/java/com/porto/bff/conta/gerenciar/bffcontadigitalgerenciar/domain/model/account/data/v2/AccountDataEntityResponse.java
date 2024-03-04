package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.account.data.v2;


/**
 * {
 * "data": {
 * "id": "ad9edd43-698d-49d0-8951-0fa92d9fe63f",
 * "bankAccount": {
 * "company": "004",
 * "bank": "341",
 * "branch": "1500",
 * "number": "0018139",
 * "checkDigit": "9",
 * "type": "PAYMENT"
 * }
 * }
 * }
 */
public record AccountDataEntityResponse(
        String id,
        BankAccount bankAccount,
        String status,
        String type,
        String createdAt,
        String updatedAt
) {

}
