package com.incra.model

/**
 * An <i>Enumeration</i> is an ordered collection of values, each of which have a key [K], and a value [T].
 * The Enumeration values must extend MappedTo[K] for compatibility with Slick2.
 *
 * @author Jeff Risberg
 * @since 10/08/14
 */
trait Enumeration[K, T <: Enumerated[K]] extends Serializable {
  val list: List[T]

  private lazy val map: Map[K, T] = list.map((t: T) => t.value -> t).toMap

  def apply(value: K) = map(value)
}
