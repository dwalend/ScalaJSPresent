package net.walend.present

import net.walend.present.Shortcuts._

/**
  * Tables for Kapow!
  */
case class Table(headRow:Array[Item],contents:Array[Array[Item]]) extends Item

object Table {
/*
  def apply(headRow: Array[String], contents: Array[Array[String]],stringToItem:(String => Item) = l2): Table = {
    Table(headRow.map(stringToItem),contents.map(_.map(stringToItem)))
  }
  */
}
