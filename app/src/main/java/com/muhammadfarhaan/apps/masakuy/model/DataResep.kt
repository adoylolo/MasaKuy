package com.muhammadfarhaan.apps.masakuy.model

class DataResep{

    var nama : String? = null
    var bahan : String? = null
    var langkah : String? = null
    var image : String? = null

    constructor(){}

    constructor(nama: String?, bahan: String?, langkah: String?, image: String?) {
        this.nama = nama
        this.bahan = bahan
        this.langkah = langkah
        this.image = image
    }

}