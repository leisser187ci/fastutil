/* Generic definitions */




/* Assertions (useful to generate conditional code) */
/* Current type and class (and size, if applicable) */
/* Value methods */
/* Interfaces (keys) */
/* Interfaces (values) */
/* Abstract implementations (keys) */
/* Abstract implementations (values) */
/* Static containers (keys) */
/* Static containers (values) */
/* Implementations */
/* Synchronized wrappers */
/* Unmodifiable wrappers */
/* Other wrappers */
/* Methods (keys) */
/* Methods (values) */
/* Methods (keys/values) */
/* Methods that have special names depending on keys (but the special names depend on values) */
/* Equality */
/* Object/Reference-only definitions (keys) */
/* Primitive-type-only definitions (keys) */
/* Object/Reference-only definitions (values) */
/* Primitive-type-only definitions (values) */
/*		 
 * Copyright (C) 2002-2013 Sebastiano Vigna 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package it.unimi.dsi.fastutil.longs;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectSortedSets;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.NoSuchElementException;
/** A class providing static methods and objects that do useful things with type-specific sorted maps.
 *
 * @see java.util.Collections
 */
public class Long2IntSortedMaps {
 private Long2IntSortedMaps() {}
 /** Returns a comparator for entries based on a given comparator on keys.
	 *
	 * @param comparator a comparator on keys.
	 * @return the associated comparator on entries.
	 */
 public static Comparator<? super Map.Entry<Long, ?>> entryComparator( final LongComparator comparator ) {
  return new Comparator<Map.Entry<Long, ?>>() {
   public int compare( Map.Entry<Long, ?> x, Map.Entry<Long, ?> y ) {
    return comparator.compare( x.getKey(), y.getKey() );
   }
  };
 }
 /** An immutable class representing an empty type-specific sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */
 public static class EmptySortedMap extends Long2IntMaps.EmptyMap implements Long2IntSortedMap , java.io.Serializable, Cloneable {
  private static final long serialVersionUID = -7046029254386353129L;
  protected EmptySortedMap() {}
  public LongComparator comparator() { return null; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Long2IntMap.Entry > long2IntEntrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Map.Entry<Long, Integer>> entrySet() { return ObjectSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public LongSortedSet keySet() { return LongSortedSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public Long2IntSortedMap subMap( final long from, final long to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Long2IntSortedMap headMap( final long to ) { return EMPTY_MAP; }
  @SuppressWarnings("unchecked")
  public Long2IntSortedMap tailMap( final long from ) { return EMPTY_MAP; }
  public long firstLongKey() { throw new NoSuchElementException(); }
  public long lastLongKey() { throw new NoSuchElementException(); }
  public Long2IntSortedMap headMap( Long oto ) { return headMap( ((oto).longValue()) ); }
  public Long2IntSortedMap tailMap( Long ofrom ) { return tailMap( ((ofrom).longValue()) ); }
  public Long2IntSortedMap subMap( Long ofrom, Long oto ) { return subMap( ((ofrom).longValue()), ((oto).longValue()) ); }
  public Long firstKey() { return (Long.valueOf(firstLongKey())); }
  public Long lastKey() { return (Long.valueOf(lastLongKey())); }
 }

 /** An empty type-specific sorted map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptySortedMap EMPTY_MAP = new EmptySortedMap();


 /** An immutable class representing a type-specific singleton sorted map. 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific sorted map.
	 */

 public static class Singleton extends Long2IntMaps.Singleton implements Long2IntSortedMap , java.io.Serializable, Cloneable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final LongComparator comparator;

  protected Singleton( final long key, final int value, LongComparator comparator ) {
   super( key, value );
   this.comparator = comparator;
  }

  protected Singleton( final long key, final int value ) {
   this( key, value, null );
  }

  @SuppressWarnings("unchecked")
  final int compare( final long k1, final long k2 ) {
   return comparator == null ? ( (k1) < (k2) ? -1 : ( (k1) == (k2) ? 0 : 1 ) ) : comparator.compare( k1, k2 );
  }

  public LongComparator comparator() { return comparator; }

  @SuppressWarnings("unchecked")
  public ObjectSortedSet<Long2IntMap.Entry > long2IntEntrySet() { if ( entries == null ) entries = ObjectSortedSets.singleton( (Long2IntMap.Entry )new SingletonEntry(), (Comparator<? super Long2IntMap.Entry >)entryComparator( comparator ) ); return (ObjectSortedSet<Long2IntMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Long, Integer>> entrySet() { return (ObjectSortedSet)long2IntEntrySet(); }

  public LongSortedSet keySet() { if ( keys == null ) keys = LongSortedSets.singleton( key, comparator ); return (LongSortedSet )keys; }

  @SuppressWarnings("unchecked")
  public Long2IntSortedMap subMap( final long from, final long to ) { if ( compare( from, key ) <= 0 && compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Long2IntSortedMap headMap( final long to ) { if ( compare( key, to ) < 0 ) return this; return EMPTY_MAP; }

  @SuppressWarnings("unchecked")
  public Long2IntSortedMap tailMap( final long from ) { if ( compare( from, key ) <= 0 ) return this; return EMPTY_MAP; }

  public long firstLongKey() { return key; }
  public long lastLongKey() { return key; }


  public Long2IntSortedMap headMap( Long oto ) { return headMap( ((oto).longValue()) ); }
  public Long2IntSortedMap tailMap( Long ofrom ) { return tailMap( ((ofrom).longValue()) ); }
  public Long2IntSortedMap subMap( Long ofrom, Long oto ) { return subMap( ((ofrom).longValue()), ((oto).longValue()) ); }

  public Long firstKey() { return (Long.valueOf(firstLongKey())); }
  public Long lastKey() { return (Long.valueOf(lastLongKey())); }

 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Long2IntSortedMap singleton( final Long key, Integer value ) {
  return new Singleton ( ((key).longValue()), ((value).intValue()) );
 }

 /** RETURNS a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Long2IntSortedMap singleton( final Long key, Integer value, LongComparator comparator ) {
  return new Singleton ( ((key).longValue()), ((value).intValue()), comparator );
 }



 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Long2IntSortedMap singleton( final long key, final int value ) {
  return new Singleton ( key, value );
 }

 /** Returns a type-specific immutable sorted map containing only the specified pair. The returned sorted map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned sorted map.
	 * @param value the only value of the returned sorted map.
	 * @param comparator the comparator to use in the returned sorted map.
	 * @return a type-specific immutable sorted map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Long2IntSortedMap singleton( final long key, final int value, LongComparator comparator ) {
  return new Singleton ( key, value, comparator );
 }




  /** A synchronized wrapper class for sorted maps. */

 public static class SynchronizedSortedMap extends Long2IntMaps.SynchronizedMap implements Long2IntSortedMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Long2IntSortedMap sortedMap;

  protected SynchronizedSortedMap( final Long2IntSortedMap m, final Object sync ) {
   super( m, sync );
   sortedMap = m;
  }

  protected SynchronizedSortedMap( final Long2IntSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public LongComparator comparator() { synchronized( sync ) { return sortedMap.comparator(); } }

  public ObjectSortedSet<Long2IntMap.Entry > long2IntEntrySet() { if ( entries == null ) entries = ObjectSortedSets.synchronize( sortedMap.long2IntEntrySet(), sync ); return (ObjectSortedSet<Long2IntMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Long, Integer>> entrySet() { return (ObjectSortedSet)long2IntEntrySet(); }
  public LongSortedSet keySet() { if ( keys == null ) keys = LongSortedSets.synchronize( sortedMap.keySet(), sync ); return (LongSortedSet )keys; }

  public Long2IntSortedMap subMap( final long from, final long to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Long2IntSortedMap headMap( final long to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Long2IntSortedMap tailMap( final long from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }

  public long firstLongKey() { synchronized( sync ) { return sortedMap.firstLongKey(); } }
  public long lastLongKey() { synchronized( sync ) { return sortedMap.lastLongKey(); } }


  public Long firstKey() { synchronized( sync ) { return sortedMap.firstKey(); } }
  public Long lastKey() { synchronized( sync ) { return sortedMap.lastKey(); } }

  public Long2IntSortedMap subMap( final Long from, final Long to ) { return new SynchronizedSortedMap ( sortedMap.subMap( from, to ), sync ); }
  public Long2IntSortedMap headMap( final Long to ) { return new SynchronizedSortedMap ( sortedMap.headMap( to ), sync ); }
  public Long2IntSortedMap tailMap( final Long from ) { return new SynchronizedSortedMap ( sortedMap.tailMap( from ), sync ); }



 }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */
 public static Long2IntSortedMap synchronize( final Long2IntSortedMap m ) { return new SynchronizedSortedMap ( m ); }

 /** Returns a synchronized type-specific sorted map backed by the given type-specific sorted map, using an assigned object to synchronize.
	 *
	 * @param m the sorted map to be wrapped in a synchronized sorted map.
	 * @param sync an object that will be used to synchronize the access to the sorted sorted map.
	 * @return a synchronized view of the specified sorted map.
	 * @see java.util.Collections#synchronizedSortedMap(SortedMap)
	 */

 public static Long2IntSortedMap synchronize( final Long2IntSortedMap m, final Object sync ) { return new SynchronizedSortedMap ( m, sync ); }




 /** An unmodifiable wrapper class for sorted maps. */

 public static class UnmodifiableSortedMap extends Long2IntMaps.UnmodifiableMap implements Long2IntSortedMap , java.io.Serializable {

  private static final long serialVersionUID = -7046029254386353129L;

  protected final Long2IntSortedMap sortedMap;

  protected UnmodifiableSortedMap( final Long2IntSortedMap m ) {
   super( m );
   sortedMap = m;
  }

  public LongComparator comparator() { return sortedMap.comparator(); }

  public ObjectSortedSet<Long2IntMap.Entry > long2IntEntrySet() { if ( entries == null ) entries = ObjectSortedSets.unmodifiable( sortedMap.long2IntEntrySet() ); return (ObjectSortedSet<Long2IntMap.Entry >)entries; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSortedSet<Map.Entry<Long, Integer>> entrySet() { return (ObjectSortedSet)long2IntEntrySet(); }
  public LongSortedSet keySet() { if ( keys == null ) keys = LongSortedSets.unmodifiable( sortedMap.keySet() ); return (LongSortedSet )keys; }

  public Long2IntSortedMap subMap( final long from, final long to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Long2IntSortedMap headMap( final long to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Long2IntSortedMap tailMap( final long from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }

  public long firstLongKey() { return sortedMap.firstLongKey(); }
  public long lastLongKey() { return sortedMap.lastLongKey(); }


  public Long firstKey() { return sortedMap.firstKey(); }
  public Long lastKey() { return sortedMap.lastKey(); }

  public Long2IntSortedMap subMap( final Long from, final Long to ) { return new UnmodifiableSortedMap ( sortedMap.subMap( from, to ) ); }
  public Long2IntSortedMap headMap( final Long to ) { return new UnmodifiableSortedMap ( sortedMap.headMap( to ) ); }
  public Long2IntSortedMap tailMap( final Long from ) { return new UnmodifiableSortedMap ( sortedMap.tailMap( from ) ); }



 }

 /** Returns an unmodifiable type-specific sorted map backed by the given type-specific sorted map.
	 *
	 * @param m the sorted map to be wrapped in an unmodifiable sorted map.
	 * @return an unmodifiable view of the specified sorted map.
	 * @see java.util.Collections#unmodifiableSortedMap(SortedMap)
	 */
 public static Long2IntSortedMap unmodifiable( final Long2IntSortedMap m ) { return new UnmodifiableSortedMap ( m ); }
}
