package org.example

import kotlin.random.Random
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

fun main() {
    println("Добро пожаловать в приложение 'Гороскоп'!")
    println("Введите ваше имя: ")

    val name = readlnOrNull()?.trim() ?: "Гость"

    println("Выберите ваш знак зодиака или введите дату рождения (в формате ДД.ММ.ГГГГ):")
    val zodiacSign = readlnOrNull()?.trim()

    println("На какую дату вы хотите получить гороскоп? " +
            "(сегодня, завтра, следующая неделя, конкретная дата в формате ДД.ММ.ГГГГ):")
    val dateInput = readlnOrNull()?.trim()

    val date = when (dateInput?.lowercase(Locale.getDefault())) {
        "сегодня" -> LocalDate.now()
        "завтра" -> LocalDate.now().plusDays(1)
        "следующая неделя" -> LocalDate.now().plusWeeks(1)
        else -> parseSpecificDate(dateInput)
    }

    if (date != null && zodiacSign != null) {
        val horoscope = generateHoroscope()
        println("$name, здравствуйте! Ваш знак зодиака: $zodiacSign. " +
                "Персональный гороскоп на ${date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}: $horoscope")
    } else {
        println("Ошибка: Проверьте введенные данные.")
    }
}

fun parseSpecificDate(dateInput: String?): LocalDate? {
    return try {
        if (dateInput != null) {
            LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        } else {
            null
        }
    } catch (e: DateTimeParseException) {
        println("Неверный формат даты. Пожалуйста, используйте формат ДД.ММ.ГГГГ.")
        null
    }
}

fun generateHoroscope(): String {
    val horoscopes = listOf(
        "Звёзды вам будут помогать, обращайте чаще внимание на окружающих, помогая им Вы помогаете себе!",
        "Сегодня подходящий день для новых начинаний и свершений.",
        "Сосредоточьтесь на своих целях и не бойтесь просить помощи.",
        "Ваша интуиция подскажет верное решение — доверьтесь ей.",
        "Отдохните и позаботьтесь о себе — это важно для вашего внутреннего баланса.",
        "Возможны приятные встречи с людьми из прошлого.",
        "Не упустите шанс проявить свои таланты — они будут замечены!",
        "Будьте внимательны к мелочам — именно они могут изменить вашу жизнь.",
        "Сегодня вы можете получить интересное предложение — не упустите его!",
        "Ваши усилия будут вознаграждены — оставайтесь на правильном пути."
    )

    return horoscopes[Random.nextInt(horoscopes.size)]
}