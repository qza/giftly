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
        "name",
        "country",
        "city"
})
public class Manufacturer {

    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("city")
    private String city;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Manufacturer() {
    }

    /**
     * @param name
     * @param city
     * @param country
     */
    public Manufacturer(String name, String country, String city) {
        this.name = name;
        this.country = country;
        this.city = city;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The country
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    public Manufacturer withCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     * @return The city
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    public Manufacturer withCity(String city) {
        this.city = city;
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

    public Manufacturer withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(country).append(city).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Manufacturer) == false) {
            return false;
        }
        Manufacturer rhs = ((Manufacturer) other);
        return new EqualsBuilder().append(name, rhs.name).append(country, rhs.country).append(city, rhs.city).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
