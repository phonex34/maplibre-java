package com.mapbox.api.directions.v5.models;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mapbox.api.directions.v5.DirectionsAdapterFactory;

/**
 * A route between only two {@link DirectionsWaypoint}.
 *
 * @since 1.0.0
 */
@AutoValue
public abstract class RouteEvent extends DirectionsJsonObject {
    public static RouteEvent.Builder builder() {
        return new AutoValue_RouteEvent.Builder();
    }
    /**
     * The String id
     *
     * @return a double number with unit meters
     * @since 1.0.0
     */
    @Nullable
    public abstract String _id();

    /**
     * The estimated travel time from one waypoint to another.
     *
     * @return a double number with unit secondsc
     * @since 1.0.0
     */
    @Nullable
    public abstract String address();



    @Nullable
    public abstract Integer type();



    @Nullable
    @SerializedName("location")
    public abstract RouteEventLocation eventLocation();


    /**
     * Create a new instance of this class by passing in a formatted valid JSON String.
     *
     * @param json a formatted valid JSON string defining a RouteEvent
     * @return a new instance of this class defined by the values passed inside this static factory
     *   method
     * @since 3.4.0
     */
    public static RouteEvent fromJson(String json) {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return gson.create().fromJson(json, RouteEvent.class);
    }

    public abstract RouteEvent.Builder toBuilder();

    /**
     * Gson type adapter for parsing Gson to this class.
     *
     * @param gson the built {@link Gson} object
     * @return the type adapter for this class
     * @since 3.0.0
     */
    public static TypeAdapter<RouteEvent> typeAdapter(Gson gson) {
        return new AutoValue_RouteEvent.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {

        /**
         * The distance traveled from one waypoint to another.
         *
         * @param _id a double number with unit meters
         * @return this builder for chaining options together
         * @since 3.0.0
         */
        public abstract RouteEvent.Builder _id(@Nullable String _id);

        /**
         * The estimated travel time from one waypoint to another.
         *
         * @param address a double number with unit seconds
         * @return this builder for chaining options together
         * @since 1.0.0
         */
        public abstract RouteEvent.Builder address(@Nullable String address);


        public abstract RouteEvent.Builder type(@Nullable Integer type);


        public abstract RouteEvent.Builder eventLocation(@Nullable RouteEventLocation eventLocation);

        public abstract RouteEvent build();
    }
}
