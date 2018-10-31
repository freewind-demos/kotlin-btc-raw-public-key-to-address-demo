package example

import org.apache.commons.codec.binary.Hex
import org.bitcoinj.core.Base58
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.Sha256Hash
import org.spongycastle.crypto.digests.RIPEMD160Digest
import java.util.*

// Follow the steps of
// https://en.bitcoin.it/wiki/Technical_background_of_version_1_Bitcoin_addresses#How_to_create_Bitcoin_Address
fun main(args: Array<String>) {
    val privateKey = "18e14a7b6a307f426a94f8114701e7c8e774e7f9a47e2c2035db29a206321725"
    println("private key: ${privateKey}")

    val ecKey = ECKey.fromPrivate(Hex.decodeHex(privateKey))
    val publicKey = ecKey.publicKeyAsHex
    println("public key: ${publicKey}")

    val sha256 = Sha256Hash.hash(ecKey.pubKey)
    printArrayAsHex("public key(sha256)", sha256)

    val hash160 = run {
        val digest = RIPEMD160Digest()
        digest.update(sha256, 0, sha256.size)
        val out = ByteArray(20)
        digest.doFinal(out, 0)
        out
    }
    printArrayAsHex("public key(hash160)", hash160)

    val extended = "00${Hex.encodeHexString(hash160)}"
    val checksum = checksum(extended)
    println("checksum: ${checksum}")

    val address = Base58.encode(Hex.decodeHex(extended + checksum))
    println("address: $address")
}

private fun checksum(extended: String) {
    val checksum = Sha256Hash.hash(Sha256Hash.hash(Hex.decodeHex(extended)))
    val shortPrefix = Arrays.copyOfRange(checksum, 0, 4)
    Hex.encodeHexString(shortPrefix)!!
}

fun printArrayAsHex(prefix: String, bytes: ByteArray) {
    println("${prefix}> length: ${bytes.size}, hex: ${Hex.encodeHexString(bytes)}")
}