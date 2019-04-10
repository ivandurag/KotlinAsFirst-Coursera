@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.PI
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var num = n
    do {
        count++
        num /= 10
    } while (num > 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n < 3) return 1
    var prev = 1
    var prev_prev = 1
    var num = 0
    for (i in 3..n) {
        num = prev + prev_prev
        prev_prev = prev
        prev = num
    }
    return num
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = when {
    m == n -> m
    m % n == 0 -> m
    n % m == 0 -> n
    else -> {
        val max = Math.max(m, n)
        val min = Math.min(m, n)
        /*(2..m * n)
                .asSequence()
                .filter { max * it % min == 0 }
                .first() * max*/
        var res = 0
        for (i in 2..m * n) {
            if ((max * i) % min == 0) {
                res = max * i
                break
            }
        }
        res
    }
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var delitel = 1
    var res = 1
    while (delitel++ <= n / 2) if (n % delitel == 0) {
        res = delitel
        break
    }
    return if (res == 1)
        n
    else
        res
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var delitel = 1
    for (i in n / 2 downTo 2) if (n % i == 0) {
        delitel = i
        break
    }
    return delitel
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = when {
    (m % n == 0 || n % m == 0) -> false
    else -> {
        val max = Math.max(m, n)
        val min = Math.min(m, n)
        var res = true
        for (i in 2..min / 2)
            if (min % i == 0 && max % i == 0) {
                res = false
                break
            }
        res
    }
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in sqrt(m.toDouble()).toInt()..sqrt(n.toDouble()).toInt()) {
        if (i * i in m..n)
            return true
    }
    return false
}


/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var current = x
    while (current > 1) {
        if (current % 2 == 0)
            current /= 2
        else
            current = current * 3 + 1
        count++
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    if (x % PI < eps)
        return 0.0
    var plus = false
    var sinus = x
    var current: Double
    var i = 3
    do {
        current = Math.pow(x, i.toDouble()) / factorial(i)
        if (plus)
            sinus += current
        else
            sinus -= current
        i += 2
        plus = !plus
    } while (current > eps && -current < -eps)
    return sinus
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    if (x % (2 * PI) < eps)
        return 1.0
    var plus = false
    var cosinus = 1.0
    var current: Double
    var i = 2
    do {
        current = Math.pow(x, i.toDouble()) / factorial(i)
        if (plus)
            cosinus += current
        else
            cosinus -= current
        i += 2
        plus = !plus
    } while (current > eps && -current < -eps)
    return cosinus
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    val count = digitNumber(n)
    var res = 0
    var step = count
    var current = n
    for (i in 0 until count) {
        res += (current % 10) * Math.pow(10.0, (step - 1).toDouble()).toInt()
        step--
        current /= 10
    }
    return res
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val count = digitNumber(n)
    if (count == 1)
        return true
    val middle = count / 2
    var num = n
    var numb = n
    for (i in 1..middle) {
        var little = num % 10
        var big = numb / Math.pow(10.0, (count - i).toDouble()).toInt()
        if (little != big)
            return false
        num /= 10
        numb -= (big * Math.pow(10.0, (count - i).toDouble()).toInt())
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val count = digitNumber(n)
    if (count == 1)
        return false
    var prev = 0
    var current: Int
    var num = n
    var i = 1
    do {
        current = num % 10
        if (i > 1) {
            if (current != prev)
                return true
        }
        num /= 10
        prev = current
        i++
    } while (i <= count)
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int)
        : Int = TODO()
