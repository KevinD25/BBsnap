package be.eaict.blackboardsnapshotapp.Objects

data class Les(
        val eindtijd: String,
        val id: Int,
        val klas: Klas,
        val lokaal : Lokaal,
        val starttijd: String,
        val vak: Vak
)