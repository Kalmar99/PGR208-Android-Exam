
package com.example.pgr208_exam.gsontypes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FeatureCollection {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("features")
    @Expose
    private List<Feature> features = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("type", type).append("features", features).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(type).append(features).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FeatureCollection) == false) {
            return false;
        }
        FeatureCollection rhs = ((FeatureCollection) other);
        return new EqualsBuilder().append(type, rhs.type).append(features, rhs.features).isEquals();
    }

}
