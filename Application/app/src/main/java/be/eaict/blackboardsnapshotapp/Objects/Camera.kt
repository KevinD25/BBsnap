package be.eaict.blackboardsnapshotapp.Objects

import java.io.Serializable

data class Camera(
        val id: Int,
        val ip: String,
        val lokaal: Lokaal
)