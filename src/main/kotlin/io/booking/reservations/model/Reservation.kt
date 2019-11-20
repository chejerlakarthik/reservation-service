package io.booking.reservations.model

import java.time.LocalDateTime
import java.util.*

data class Reservation(val id: UUID = UUID.randomUUID(),
                       val reservedBy: String = "System",
                       val reservedFor: LocalDateTime = LocalDateTime.now())