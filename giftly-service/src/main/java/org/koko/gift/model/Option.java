package org.koko.gift.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "option_items"
})
public class Option {

    @JsonProperty("type")
    private String type;
    @JsonProperty("option_items")
    private List<OptionItem> optionItems = new ArrayList<OptionItem>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public Option withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return The optionItems
     */
    @JsonProperty("option_items")
    public List<OptionItem> getOptionItems() {
        return optionItems;
    }

    /**
     * @param optionItems The option_items
     */
    @JsonProperty("option_items")
    public void setOptionItems(List<OptionItem> optionItems) {
        this.optionItems = optionItems;
    }

    public Option withOptionItems(List<OptionItem> optionItems) {
        this.optionItems = optionItems;
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

    public Option withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(optionItems).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Option) == false) {
            return false;
        }
        Option rhs = ((Option) other);
        return new EqualsBuilder().append(type, rhs.type).append(optionItems, rhs.optionItems).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
