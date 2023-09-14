fun main(args: Array<String>) {
    // Задача 1
    println(agotoText(60 * 60 * 2))

    // Задача 2
    println(countComission("Visa", 1000, 5000))
    println(countComission("Мир", 1000, 1000))
    println(countComission("Maestro", 70100, 5000))
    println(countComission("Mastercard", 1000, 5000))
    println(countComission(sumOfDeals = 74950, sum = 5000))
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
fun countComission(type: String = "VKpay", sumOfDeals: Int = 0, sum: Int): Double {
    return when (type) {
        "Mastercard", "Maestro" -> {
            if (sumOfDeals + sum <= 75000) {
                0.0
            } else {
                sum * 0.6 / 100 + 20
            }
        }

        "Visa", "Мир" -> {
            if (sum * 0.75 / 100 > 35) {
                sum * 0.75 / 100
            } else {
                35.0
            }
        }

        else -> 0.0
    }
}