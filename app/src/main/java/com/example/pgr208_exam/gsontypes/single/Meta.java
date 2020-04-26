
package com.example.pgr208_exam.gsontypes.single;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;

public class Meta {

    @SerializedName("facilities")
    @Expose
    private String facilities;

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("facilities", facilities).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(facilities).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Meta) == false) {
            return false;
        }
        Meta rhs = ((Meta) other);
        return new EqualsBuilder().append(facilities, rhs.facilities).isEquals();
    }

}
