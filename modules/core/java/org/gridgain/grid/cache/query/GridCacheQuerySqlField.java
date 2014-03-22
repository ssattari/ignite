/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.cache.query;

import java.lang.annotation.*;

/**
 * Annotates fields for SQL queries. All fields that will be involved in SQL clauses must have
 * this annotation. For more information about cache queries see {@link GridCacheQuery} documentation.
 * @see GridCacheQuery
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface GridCacheQuerySqlField {
    /**
     * Specifies whether cache should maintain an index for this field or not.
     * Just like with databases, field indexing may require additional overhead
     * during updates, but makes select operations faster.
     *
     * @return {@code True} if index must be created for this field in database.
     */
    boolean index() default false;

    /**
     * Specifies whether index should be in descending order or not. This property only
     * makes sense if {@link #index()} property is set to {@code true}.
     *
     * @return {@code True} if field index should be in descending order.
     */
    boolean descending() default false;

    /**
     * Array of index groups this field belongs to. Groups are used for compound indexes,
     * whenever index should be created on more than one field. All fields within the same
     * group will belong to the same index.
     * <p>
     * Most of the times user will not specify any group, which means that cache should
     * simply create a single field index.
     *
     * @return Array of group names.
     */
    String[] groups() default {};

    /**
     * Array of ordered index groups this field belongs to.
     *
     * @return Array of ordered group indexes.
     */
    Group[] orderedGroups() default {};

    /**
     * Property name. If not provided then field name will be used.
     *
     * @return Name of property.
     */
    String name() default "";

    /**
     * Describes group of index and position of field in this group.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD})
    @SuppressWarnings("PublicInnerClass")
    public static @interface Group {
        /**
         * Group index name where this field participate.
         *
         * @return Group index name
         */
        String name();

        /**
         * Fields in this group index will be sorted on this attribute.
         *
         * @return Order number.
         */
        int order();

        /**
         * Defines sorting order for this field in group.
         *
         * @return True if field will be in descending order.
         */
        boolean descending() default false;
    }
}
