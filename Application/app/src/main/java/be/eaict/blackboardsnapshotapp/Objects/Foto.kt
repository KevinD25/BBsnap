package be.eaict.blackboardsnapshotapp.Objects

import java.io.Serializable

data class Foto(
        val camera: Camera,
        val id: Int,
        val les: Les,
        val naam: String
):Serializable