package net.dean.jraw.models;

import java.util.List;

/**
 * This class is used to imitate a Listing without parsing any JSON. This class essentially exposes Listing's
 * {@link Listing#Listing(Class, java.util.List, String, String, MoreChildren) protected constructor}, enabling data to
 * be fed directly to internal fields. Use of this class outside of the library is not recommended.
 *
 * @param <T> The type of elements that will be in this Listing
 */
public final class FauxListing<T extends RedditObject> extends Listing<T> {
    /** Instantiates a new FauxListing with a null More
     * @param children
     * @param before
     * @param after
     * */
    public FauxListing(List<T> children, String before, String after) {
        this(children, before, after, null);
    }

    /** Instantiates a new FauxListing */
    public FauxListing(List<T> children, String before, String after, MoreChildren more) {
        super(null, children, before, after, more);
    }
}
