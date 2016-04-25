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
        "image",
        "url"
})
public class Item {

    @JsonProperty("image")
    private String image;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Item() {
    }

    /**
     * @param image
     * @param url
     */
    public Item(String image, String url) {
        this.image = image;
        this.url = url;
    }

    /**
     * @return The image
     */
    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    public Item withImage(String image) {
        this.image = image;
        return this;
    }

    /**
     * @return The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Item withUrl(String url) {
        this.url = url;
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

    public Item withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(image).append(url).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return new EqualsBuilder().append(image, rhs.image).append(url, rhs.url).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
