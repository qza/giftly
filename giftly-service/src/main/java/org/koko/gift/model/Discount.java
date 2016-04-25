package org.koko.gift.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type",
        "amount",
        "condition"
})
public class Discount {

    @JsonProperty("type")
    private String type;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("condition")
    private Condition condition;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Discount() {
    }

    /**
     * @param amount
     * @param condition
     * @param type
     */
    public Discount(String type, String amount, Condition condition) {
        this.type = type;
        this.amount = amount;
        this.condition = condition;
    }

    /**
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Discount withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return The amount
     */
    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    @JsonProperty("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Discount withAmount(String amount) {
        this.amount = amount;
        return this;
    }

    /**
     * @return The condition
     */
    @JsonProperty("condition")
    public Condition getCondition() {
        return condition;
    }

    /**
     * @param condition The condition
     */
    @JsonProperty("condition")
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Discount withCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Discount withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(amount).append(condition).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Discount) == false) {
            return false;
        }
        Discount rhs = ((Discount) other);
        return new EqualsBuilder().append(type, rhs.type).append(amount, rhs.amount).append(condition, rhs.condition).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
