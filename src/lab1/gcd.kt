package lab1

import java.math.BigInteger

fun Euclidean(first: BigInteger, second: BigInteger):BigInteger {
    var a = first
    var b = second
    a = a.abs()
    b = b.abs()
    if (a == b) {
        return a
    }
    if (a == BigInteger.ZERO) {
        return b
    }
    if (b == BigInteger.ZERO) {
        return a
    }
    if (a == BigInteger.ONE || b == BigInteger.ONE) {
        return BigInteger.ONE
    }

    while (b != BigInteger.ZERO) {
        a %= b
        val c = a
        a = b
        b = c
    }
    return a
}

fun Binary(first: BigInteger, second: BigInteger):BigInteger{
    var a = first
    var b = second
    a = a.abs()
    b = b.abs()
    var multiplier = BigInteger.ONE
    while (b != BigInteger.ZERO){
        if (a == b) {
            return a* multiplier
        }
        if (a == BigInteger.ZERO) {
            return b* multiplier
        }
        if (b == BigInteger.ZERO) {
            return a* multiplier
        }
        if (a == BigInteger.ONE || b == BigInteger.ONE) {
            return BigInteger.ONE * multiplier
        }
        val remA = a.mod(BigInteger(2.toString()))
        val remB = b.mod(BigInteger(2.toString()))
        if (remA == BigInteger.ZERO && remB == BigInteger.ZERO){
            a = a shr 1
            b = b shr 1
            multiplier = multiplier shl 1
            continue
        }
        if (remA == BigInteger.ZERO){
            a = a shr 1
            continue
        }
        if (remB == BigInteger.ZERO){
            b = b shr 1
            continue
        }
        if(a > b)
            a = (a - b) shr 1
        else
            b = (b - a) shr 1
    }
    return a* multiplier
}

fun main() {
    val a = BigInteger("14")
    val b = BigInteger("12")

    var start = System.nanoTime()
    var gcd = Euclidean(a, b)
    var end = System.nanoTime()
    var result = end - start
    println("lab1.Euclidean GCD of $a and $b equals $gcd")// 1
    println("It takes $result ns to count it by lab1.Euclidean algorithm\n")

    start = System.nanoTime()
    gcd = Binary(a, b)
    end = System.nanoTime()
    result = end - start
    println("lab1.Binary GCD of $a and $b equals $gcd")// 1
    println("It takes $result ns to count it by lab1.Binary algorithm")

}