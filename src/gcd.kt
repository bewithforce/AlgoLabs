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

    var multiplier = 1
    while (b != BigInteger.ZERO){
        if (a == b) {
            return a.multiply(BigInteger(multiplier.toString()))
        }
        if (a == BigInteger.ZERO) {
            return b.multiply(BigInteger(multiplier.toString()))
        }
        if (b == BigInteger.ZERO) {
            return a.multiply(BigInteger(multiplier.toString()))
        }
        if (a == BigInteger.ONE || b == BigInteger.ONE) {
            return BigInteger.ONE
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
    return a.multiply(BigInteger(multiplier.toString()))
}

fun main() {
    val a = BigInteger("16602747662452097049541800472897701834948051198384828062358553091918573717701170201065510185595898605104094736918879278462233015981029522997836311232618760539199036765399799926731433239718860373345088375054249")
    val b = BigInteger("10261062362033262336604926729245222132668558120602124277764622905699407982546711488272859468887457959087733119242564077850743657661180827326798539177758919828135114407499369796465649524266755391104990099120377")

    var start = System.nanoTime()
    var gcd = Euclidean(a, b)
    var end = System.nanoTime()
    var result = end - start
    println("Euclidean GCD of $a and $b equals $gcd")// 1
    println("It takes $result ns to count it by Euclidean algorithm\n")

    start = System.nanoTime()
    gcd = Binary(a, b)
    end = System.nanoTime()
    result = end - start
    println("Binary GCD of $a and $b equals $gcd")// 1
    println("It takes $result ns to count it by Binary algorithm")

}