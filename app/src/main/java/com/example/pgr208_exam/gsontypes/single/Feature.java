
package com.example.pgr208_exam.gsontypes.single;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Feature {

    @SerializedName("snapshots")
    @Expose
    private List<Object> snapshots = null;
    @SerializedName("place")
    @Expose
    private Place place;

    public List<Object> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<Object> snapshots) {
        this.snapshots = snapshots;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("snapshots", snapshots).append("place", place).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(snapshots).append(place).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Feature) == false) {
            return false;
        }
        Feature rhs = ((Feature) other);
        return new EqualsBuilder().append(snapshots, rhs.snapshots).append(place, rhs.place).isEquals();
    }

}
