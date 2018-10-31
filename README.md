Kotlin Btc Key Generator Demo
=============================

学到的一些知识：

- 一个private key实际上是一个随机的很大的数字（长度为160的巨大数字），其值 1 <= key < `0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141`
- 选定了private key，可以通过secp256k1算法，得到它的public key
- 一个private key只能有一个public key。但是在bitcoin中，这个public key有两种格式（压缩与非压缩），它们对应曲线上的同一个点，只是表达方式不同，但是实际上可以看作是两个public key
- 每个public key又可以生成两个address，一个用于mainnet，一个用于testnet
- 当一个钱包支持生成多个address，有两种可能：
  - 每次都生成一个新的private key，与已有的private key之间没有关系
  - 使用一个主private key（或者一个主密码之类），可以通过它生成其它需要的private key，所以用户只需要记住一个密码就可以

Run `Hello.kt` in your IDE.

参考文章

- 图形版generator: <https://royalforkblog.github.io/2014/08/11/graphical-address-generator/#hello>
- 如何手动创建btc private key: <https://medium.freecodecamp.org/how-to-generate-your-very-own-bitcoin-private-key-7ad0f4936e6c>
- 幽默形象的解释为什么一个巨大的数字就是安全的：<https://www.youtube.com/watch?v=ZloHVKk7DHk>
- 如何一步步从private key -> public key -> address: <https://en.bitcoin.it/wiki/Technical_background_of_version_1_Bitcoin_addresses#How_to_create_Bitcoin_Address>
- 生成public key的python代码： https://bitcoin.stackexchange.com/a/25039
- 说明private key -> public key -> address之间数量关系（以及相应的java代码）：https://bitcoin.stackexchange.com/a/48326/89074
- 一个private key多个address: https://bitcoin.stackexchange.com/questions/35983/can-same-private-key-generate-multiple-addresses

![demo](./images/demo.png)
