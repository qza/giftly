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
        "_id",
        "name",
        "size",
        "meterial",
        "tags",
        "manufacturer",
        "images",
        "price",
        "options"
})
public class Gift {

    @JsonProperty("_id")
    private Double Id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("size")
    private String size;
    @JsonProperty("meterial")
    private String meterial;
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("manufacturer")
    private Manufacturer manufacturer;
    @JsonProperty("images")
    private List<Image> images = new ArrayList<Image>();
    @JsonProperty("price")
    private Price price;
    @JsonProperty("options")
    private List<Option> options = new ArrayList<Option>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Gift() {
    }

    /**
     * @param tags
     * @param price
     * @param manufacturer
     * @param meterial
     * @param name
     * @param images
     * @param Id
     * @param options
     * @param size
     */
    public Gift(Double Id, String name, String size, String meterial, List<String> tags, Manufacturer manufacturer, List<Image> images, Price price, List<Option> options) {
        this.Id = Id;
        this.name = name;
        this.size = size;
        this.meterial = meterial;
        this.tags = tags;
        this.manufacturer = manufacturer;
        this.images = images;
        this.price = price;
        this.options = options;
    }

    /**
     * @return The Id
     */
    @JsonProperty("_id")
    public Double getId() {
        return Id;
    }

    /**
     * @param Id The _id
     */
    @JsonProperty("_id")
    public void setId(Double Id) {
        this.Id = Id;
    }

    public Gift withId(Double Id) {
        this.Id = Id;
        return this;
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

    public Gift withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The size
     */
    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    /**
     * @param size The size
     */
    @JsonProperty("size")
    public void setSize(String size) {
        this.size = size;
    }

    public Gift withSize(String size) {
        this.size = size;
        return this;
    }

    /**
     * @return The meterial
     */
    @JsonProperty("meterial")
    public String getMeterial() {
        return meterial;
    }

    /**
     * @param meterial The meterial
     */
    @JsonProperty("meterial")
    public void setMeterial(String meterial) {
        this.meterial = meterial;
    }

    public Gift withMeterial(String meterial) {
        this.meterial = meterial;
        return this;
    }

    /**
     * @return The tags
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags The tags
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Gift withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * @return The manufacturer
     */
    @JsonProperty("manufacturer")
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer The manufacturer
     */
    @JsonProperty("manufacturer")
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Gift withManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    /**
     * @return The images
     */
    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    /**
     * @param images The images
     */
    @JsonProperty("images")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Gift withImages(List<Image> images) {
        this.images = images;
        return this;
    }

    /**
     * @return The price
     */
    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    @JsonProperty("price")
    public void setPrice(Price price) {
        this.price = price;
    }

    public Gift withPrice(Price price) {
        this.price = price;
        return this;
    }

    /**
     * @return The options
     */
    @JsonProperty("options")
    public List<Option> getOptions() {
        return options;
    }

    /**
     * @param options The options
     */
    @JsonProperty("options")
    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Gift withOptions(List<Option> options) {
        this.options = options;
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

    public Gift withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(Id).append(name).append(size).append(meterial).append(tags).append(manufacturer).append(images).append(price).append(options).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Gift) == false) {
            return false;
        }
        Gift rhs = ((Gift) other);
        return new EqualsBuilder().append(Id, rhs.Id).append(name, rhs.name).append(size, rhs.size).append(meterial, rhs.meterial).append(tags, rhs.tags).append(manufacturer, rhs.manufacturer).append(images, rhs.images).append(price, rhs.price).append(options, rhs.options).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
