fun main(args: Array<String>) {
    // Задача 1
    println(agotoText(60 * 60 * 2))

    // Задача 2
    println(countComission("Visa", 1000, 5000))
    println(countComission("Мир", 1000, 1000))
    println(countComission("Maestro", 70100, 5000))
    println(countComission("Mastercard", 1000, 5000))
    println(countComission(sumOfDeals = 14950, sum = 5000))
    println(countComission("Maestro", 596000, 5000))
    println(countComission("Visa", sumOfDeals = 496000, sum =  160000))
    println(countComission(sumOfDeals = 10000, sum =  50000))
}

// Задача 1
fun agotoText(seconds: Int) = when (seconds) {
    in 0..60 -> "только что"
    in 61..60 * 60 -> "был(а) " + seconds / 60 + minutesAgo(seconds) + " назад"
    in 60 * 60 + 1..24 * 60 * 60 -> "был(а) " + seconds / (60 * 60) + hoursAgo(seconds) + " назад"
    in 24 * 60 * 60 + 1..24 * 60 * 60 * 2 -> "был(а) вчера"
    in 24 * 60 * 60 * 2 + 1..24 * 60 * 60 * 3 -> "был(а) позавчера"
    else -> "был(а) давно"
}

fun minutesAgo(seconds: Int): String {
    return when (seconds / 60 % 100) {
        11, 12, 13, 14 -> " минут"
        else -> when (seconds / 60 % 10) {
            1 -> " минуту"
            2, 3, 4 -> " минуты"
            else -> " минут"
        }
    }
}

fun hoursAgo(seconds: Int): String {
    return when (seconds / (60 * 60) % 100) {
        11, 12, 13, 14 -> " часов"
        else -> when (seconds / (60 * 60) % 10) {
            1 -> " час"
            2, 3, 4 -> " часа"
            else -> " часов"
        }
    }
}

// Задача 2
fun countComission(type: String = "VKpay", sumOfDeals: Int = 0, sum: Int): String {
    // Лимиты для Mastercard и Maestro
    val limitMastMaesDaily = 150_000
    val limitMastMaesMonthly = 600_000

    // Лимиты для Visa и Мир
    val limitVisaMirDaily = 150_000
    val limitVisaMirMonthly = 600_000

    // Лимиты для VKpay
    val limitVKOneTransaction = 15_000
    val limitVKMonthly = 40_000
    val comission: Double = when (type) {
        "Mastercard", "Maestro" -> {
            if (sum > limitMastMaesDaily || sumOfDeals + sum > limitMastMaesMonthly) {
                -1.0
            } else {
                if (sumOfDeals + sum <= 75000) {
                    0.0
                } else {
                    sum * 0.6 / 100 + 20
                }
            }
        }

        "Visa", "Мир" -> {
            if (sum > limitVisaMirDaily || sumOfDeals + sum > limitVisaMirMonthly) {
                -1.0
            } else {
                if (sum * 0.75 / 100 > 35) {
                    sum * 0.75 / 100
                } else {
                    35.0
                }
            }
        }

        else -> if (sum > limitVKOneTransaction || sumOfDeals + sum > limitVKMonthly) {
            -1.0
        } else {
            0.0
        }
    }

    return if (comission < 0) {
        "превышен лимит"
    } else {
        comission.toString()
    }
}