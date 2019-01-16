package example

import org.apache.commons.codec.binary.Hex
import org.bitcoinj.core.Address
import org.bitcoinj.core.Sha256Hash
import org.bitcoinj.params.MainNetParams
import org.spongycastle.crypto.digests.RIPEMD160Digest

fun main(args: Array<String>) {
    val rawPublicKey = "04678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f"
    val pubKeyBytes = Hex.decodeHex(rawPublicKey)
    val address = Address(MainNetParams.get(), hash160(pubKeyBytes)).toBase58()
    println(address) // 1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa
}


fun hash160(data: ByteArray): ByteArray {
    val sha256 = Sha256Hash.hash(data)
    return ripe160(sha256)
}

private fun ripe160(data: ByteArray): ByteArray {
    val digest = RIPEMD160Digest()
    digest.update(data, 0, data.size)
    val out = ByteArray(20)
    digest.doFinal(out, 0)
    return out
}

