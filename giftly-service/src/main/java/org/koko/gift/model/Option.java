package org.koko.gift.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        "optionItems"
})
public class Option {

    @JsonProperty("type")
    private String type;

    @JsonProperty("optionItems")
    private List<OptionItem> optionItems = new ArrayList<OptionItem>();

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Option() {
    }

    /**
     * @param type
     * @param optionItems
     */
    public Option(String type, List<OptionItem> optionItems) {
        this.type = type;
        this.optionItems = optionItems;
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

    public Option withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return The optionItems
     */
    @JsonProperty("optionItems")
    public List<OptionItem> getOptionItems() {
        return optionItems;
    }

    /**
     * @param optionItems The optionItems
     */
    @JsonProperty("optionItems")
    public void setOptionItems(List<OptionItem> optionItems) {
        this.optionItems = optionItems;
    }

    public Option withOptions(List<OptionItem> options) {
        this.optionItems = options;
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
