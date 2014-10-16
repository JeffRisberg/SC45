package com.incra.model

/**
 * Works in combination with Enumeration.  In the case of Slick2 mappings, we used MappedTo[K] instead.
 *
 * @author Jeff Risberg
 * @since 10/08/14
 **/
trait Enumerated[T] {
  def value: T
}