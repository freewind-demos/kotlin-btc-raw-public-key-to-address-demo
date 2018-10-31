package example

import org.apache.commons.codec.binary.Hex
import org.apache.commons.lang3.RandomStringUtils
import org.bitcoinj.core.ECKey
import java.math.BigInteger
import java.security.SecureRandom
import java.util.*


fun main(args: Array<String>) {
    val input = userInput()
    val privateKey = generatePrivateKey(input)

    println("--------- private key -------------")
    println(privateKey.size)
    println(Hex.encodeHex(privateKey))

    val publicKey = generatePublicKey(privateKey)
    println("--------- public key --------------")
    println(publicKey.size)
    println(Hex.encodeHex(publicKey))

//    randomStrings()
}

fun generatePublicKey(privateKey: ByteArray): ByteArray {
    val ecKey = ECKey.fromPrivate(privateKey)
    return ecKey.pubKey
}

fun randomStrings() {
    (0..100).forEach {
        val input = RandomStringUtils.randomAlphanumeric(20)
        val key = generatePrivateKey(input)
        println(key.size)
        println(Hex.encodeHex(key))
    }
}

fun userInput(): String {
    while (true) {
        println("Input a long random string(length >= 20), then press enter:")
        val randomInput = System.`in`.bufferedReader().readLine()
        if (randomInput.length < 20) {
            println("too short, try again")
            continue
        } else {
            return randomInput
        }
    }
}

val MAX_LIMIT = positiveBigInteger(Hex.decodeHex("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141"))

fun generatePrivateKey(seed: String): ByteArray {
    val rawPrivateKey = generateRawPrivateKey(seed)
    val validPrivateKey = rawPrivateKey % (MAX_LIMIT - BigInteger.ONE) + BigInteger.ONE
    val bytes = validPrivateKey.toByteArray()
    return getTail(bytes, 32)
}

fun getTail(bytes: ByteArray, count: Int): ByteArray {
    val diff = bytes.size - count
    return if (diff > 0) {
        Arrays.copyOfRange(bytes, diff, bytes.size)
    } else {
        bytes
    }
}

private fun generateRawPrivateKey(seed: String): BigInteger {
    val random = SecureRandom.getInstanceStrong()
    random.setSeed(seed.toByteArray())
    val result = ByteArray(32)
    random.nextBytes(result)
    return positiveBigInteger(result)
}

private fun positiveBigInteger(bytes: ByteArray): BigInteger {
    val shouldBePositive = 1
    return BigInteger(shouldBePositive, bytes)
}