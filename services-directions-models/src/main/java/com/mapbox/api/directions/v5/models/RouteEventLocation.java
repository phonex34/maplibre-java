package com.mapbox.api.directions.v5.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.mapbox.api.directions.v5.DirectionsAdapterFactory;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.geojson.Point;
import com.mapbox.api.directions.v5.models.RouteEventLocation;
/**
 * An object containing information about passing rest stops along the route.
 * Only available on the {@link DirectionsCriteria#PROFILE_DRIVING} profile.
 */
@AutoValue
public abstract class RouteEventLocation extends DirectionsJsonObject {

    /**
     * The type of rest stop, either `rest_area` (includes parking only) or `service_area`
     * (includes amenities such as gas or restaurants).
     * Note that adding new possible types is not considered a breaking change.
     */
    @Nullable
    public abstract String type();


    @NonNull
    public Point location() {
        return com.mapbox.geojson.Point.fromLngLat(rawLocation()[0], rawLocation()[1]);
    }

    /**
     * A {@link Point} representing this intersection location. Since the rawLocation isn't public,
     * it's okay to be mutable as long as nothing in this SDK changes values.
     *
     * @return GeoJson Point representing this intersection location
     * @since 3.0.0
     */
    @NonNull
    @SerializedName("coordinates")
    @SuppressWarnings( {"mutable", "WeakerAccess"})
    protected abstract double[] rawLocation();

    /**
     * Create a new instance of this class by passing in a formatted valid JSON String.
     *
     * @param json a formatted valid JSON string defining an Incident
     * @return a new instance of this class defined by the values passed in the method
     */
    public static RouteEventLocation fromJson(String json) {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapterFactory(DirectionsAdapterFactory.create());
        return gson.create().fromJson(json, RouteEventLocation.class);
    }

    public abstract RouteEventLocation.Builder toBuilder();

    /**
     * Gson type adapter for parsing Gson to this class.
     *
     * @param gson the built {@link Gson} object
     * @return the type adapter for this class
     * @since 3.0.0
     */
    public static TypeAdapter<RouteEventLocation> typeAdapter(Gson gson) {
        return new AutoValue_RouteEventLocation.GsonTypeAdapter(gson);
    }


    /**
     * This builder can be used to set the values describing the {@link StepManeuver}.
     *
     * @since 3.0.0
     */
    @AutoValue.Builder
    public abstract static class Builder {

        /**
         * The rawLocation as a double array. Once the {@link StepManeuver} object's created, this raw
         * location gets converted into a {@link Point} object and is public exposed as such. The double
         * array should have a length of two, index 0 being the longitude and index 1 being latitude.
         *
         * @param rawLocation a double array with a length of two, index 0 being the longitude and
         *                    index 1 being latitude.
         * @return this builder for chaining options together
         * @since 3.0.0
         */
        public abstract RouteEventLocation.Builder rawLocation(@NonNull double[] rawLocation);


        /**
         * A human-readable instruction of how to execute the returned maneuver. This String is built
         * using OSRM-Text-Instructions and can be further customized inside either the Mapbox
         * Navigation SDK for Android or using the OSRM-Text-Instructions.java project in Project-OSRM.
         *
         * @param type String with instruction
         * @return this builder for chaining options together
         * @see <a href='https://github.com/mapbox/mapbox-navigation-android'>Navigation SDK</a>
         * @see <a href='https://github.com/Project-OSRM/osrm-text-instructions.java'>OSRM-Text-Instructions.java</a>
         * @since 3.0.0
         */
        public abstract RouteEventLocation.Builder type(@Nullable String type);


        /**
         * Build a new {@link RouteEventLocation} object.
         *
         * @return a new {@link RouteEventLocation} using the provided values in this builder
         * @since 3.0.0
         */
        public abstract RouteEventLocation build();
    }
}
