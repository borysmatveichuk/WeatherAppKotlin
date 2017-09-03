package net.borkiss.weatherappkotlin.domain.commands

interface Command<out T> {
    fun execute(): T
}