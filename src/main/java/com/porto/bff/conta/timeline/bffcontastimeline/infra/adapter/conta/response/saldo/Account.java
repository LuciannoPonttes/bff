package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.response.saldo;

import java.util.List;

public class Account {

        private Id id;
        private ProductId productId;
        private List<Limit> limits;

    public List<Limit> getLimits() {
        return limits;
    }

    public void setLimits(List<Limit> limits) {
        this.limits = limits;
    }

    public Id getId() {
            return id;
        }

        public void setId(Id id) {
            this.id = id;
        }

        public ProductId getProductId() {
            return productId;
        }

        public void setProductId(ProductId productId) {
            this.productId = productId;
        }




}


