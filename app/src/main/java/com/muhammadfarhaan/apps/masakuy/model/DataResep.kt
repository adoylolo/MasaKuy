package com.muhammadfarhaan.apps.masakuy.model

class DataResep{

    var nama : String? = null
    var desc : String? = null
    var image : String? = null

    constructor(){}

    constructor(nama: String?, desc: String?, image: String?) {
        this.nama = nama
        this.desc = desc
        this.image = image
    }

}