package net.borkiss.weatherappkotlin

import org.junit.Test

import org.junit.Assert.*

class Test {

    @Test
    fun test() {
        val list = listOf(1, 2, 3, 4, 5, 6, 100)
        assertTrue(list.any { it % 2 == 0 })
        assertFalse(list.any { it > 100 })

        assertTrue(list.any { it == 100 })
        list.forEach { println(it) }

        list.forEachIndexed { index, value
            -> println("position $index contains a $value") }

        assertEquals(121, list.reduce { total, next -> total + next })

        assertEquals(25, list.fold(0) { total, next -> total + next })

    }
}